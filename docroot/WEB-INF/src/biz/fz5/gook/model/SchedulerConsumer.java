package biz.fz5.gook.model;

import biz.fz5.gook.service.BookLocalServiceUtil;
import biz.fz5.gook.service.OAuthConfigurationLocalServiceUtil;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SchedulerConsumer implements MessageListener {

	private static Logger _log = Logger.getLogger(SchedulerConsumer.class);

	@Override
	public void receive(Message arg0) throws MessageListenerException {

		try {

			OAuthConfiguration oAuthConfiguration = null;
			String apiKey = null;
			if ((oAuthConfiguration = OAuthConfigurationLocalServiceUtil
					.getOAuthConfiguration("apiKey")) != null)
				apiKey = oAuthConfiguration.getOauthValue();
			String userId = null;
			if ((oAuthConfiguration = OAuthConfigurationLocalServiceUtil
					.getOAuthConfiguration("userId")) != null)
				userId = oAuthConfiguration.getOauthValue();

			if (apiKey == null)
				throw new Exception(
						"You need to provide portlet configuration for apiKey");

			String content = null;
			content = getContent(userId, apiKey);

			Map<String, Object> books = null;
			Map<String, String> book = null;
			books = getBooks(content);

			Iterator<String> iter = books.keySet().iterator();
			String bookId = null;

			while (iter.hasNext()) {
				bookId = iter.next();

				book = (Map<String, String>) books.get(bookId);

				if (!BookLocalServiceUtil.bookExists(bookId)) {
					BookLocalServiceUtil.addBook(bookId, book.get("data"),
							book.get("thumbnail"),
							book.get("canonicalVolumeLink"));
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private String getContent(String userId, String apiKey) {

		String content = null;

		try {

			CloseableHttpClient httpclient = HttpClients.createDefault();

			try {

				URI uri = new URIBuilder()
						.setScheme("https")
						.setHost("www.googleapis.com")
						.setPath(
								"/books/v1/users/" + userId
										+ "/bookshelves/0/volumes")
						.setParameter("key", apiKey).build();

				HttpGet httpget = new HttpGet(uri);

				ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

					@Override
					public String handleResponse(final HttpResponse response)
							throws ClientProtocolException, IOException {
						int status = response.getStatusLine().getStatusCode();
						if (status >= 200 && status < 300) {
							HttpEntity entity = response.getEntity();
							return entity != null ? EntityUtils
									.toString(entity) : null;
						} else {
							throw new ClientProtocolException(
									"Unexpected response status: " + status);
						}
					}

				};

				content = httpclient.execute(httpget, responseHandler);

			} finally {
				httpclient.close();
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return content;
	}

	private Map<String, Object> getBooks(String content) {

		Map<String, Object> books_ = new HashMap<String, Object>();

		Map<String, String> book_ = null;

		try {

			JSONParser parser = new JSONParser();

			try {

				Object obj = parser.parse(content);

				JSONObject jsonObject = (JSONObject) obj;

				JSONArray items = (JSONArray) jsonObject.get("items");
				Iterator<JSONObject> iterator = items.iterator();

				JSONObject obj_ = null;

				while (iterator.hasNext()) {

					obj_ = iterator.next();

					JSONObject obj__ = (JSONObject) obj_.get("volumeInfo");
					JSONObject obj___ = (JSONObject) obj__.get("imageLinks");

					book_ = new HashMap<String, String>();
					book_.put("data", obj_.toJSONString());
					book_.put("thumbnail", (String) obj___.get("thumbnail"));
					book_.put("canonicalVolumeLink",
							(String) obj__.get("canonicalVolumeLink"));

					books_.put((String) obj_.get("id"), book_);

				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return books_;
	}

}

package biz.fz5.gook.action;

import biz.fz5.gook.service.OAuthConfigurationLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class OAuthConfigurationActionImpl extends DefaultConfigurationAction {

	private static final Log _log = LogFactoryUtil
			.getLog(OAuthConfigurationActionImpl.class);

	public void processAction(PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {

		String apiKey = HtmlUtil.escape(ParamUtil.getString(actionRequest,
				"apiKey", StringPool.BLANK));
		String userId = HtmlUtil.escape(ParamUtil.getString(actionRequest,
				"userId", StringPool.BLANK));

		OAuthConfigurationLocalServiceUtil
				.updateConfiguration("apiKey", apiKey);
		OAuthConfigurationLocalServiceUtil
				.updateConfiguration("userId", userId);

		SessionMessages.add(actionRequest, portletConfig.getPortletName()
				+ ".doConfigure");
	}

	public String render(PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
			throws Exception {
		return "/configuration.jsp";
	}
}

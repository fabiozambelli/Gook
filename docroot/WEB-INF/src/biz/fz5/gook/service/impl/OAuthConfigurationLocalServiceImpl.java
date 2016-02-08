/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package biz.fz5.gook.service.impl;

import biz.fz5.gook.model.OAuthConfiguration;
import biz.fz5.gook.service.base.OAuthConfigurationLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * The implementation of the o auth configuration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link biz.fz5.gook.service.OAuthConfigurationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author fabiozambelli
 * @see biz.fz5.gook.service.base.OAuthConfigurationLocalServiceBaseImpl
 * @see biz.fz5.gook.service.OAuthConfigurationLocalServiceUtil
 */
public class OAuthConfigurationLocalServiceImpl extends
		OAuthConfigurationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * biz.fz5.gook.service.OAuthConfigurationLocalServiceUtil} to access the o
	 * auth configuration local service.
	 */
	private static final Log _log = LogFactoryUtil
			.getLog(OAuthConfigurationLocalServiceImpl.class);

	public OAuthConfiguration updateConfiguration(String oauthKey,
			String oauthValue) throws SystemException {

		_log.debug("oauthKey:" + oauthKey);
		_log.debug("oauthValue:" + oauthValue);

		OAuthConfiguration conf = null;
		try {
			conf = oAuthConfigurationPersistence.fetchByPrimaryKey(oauthKey);
			conf.setOauthValue(oauthValue);
		} catch (Exception e) {
			conf = oAuthConfigurationPersistence.create(oauthKey);
			conf.setOauthValue(oauthValue);
		}

		return oAuthConfigurationPersistence.update(conf);
	}
}
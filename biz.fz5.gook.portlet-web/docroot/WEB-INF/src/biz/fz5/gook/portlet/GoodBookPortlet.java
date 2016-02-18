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

package biz.fz5.gook.portlet;

import com.liferay.osgi.util.service.ReflectionServiceTracker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class GoodBookPortlet extends FreeMarkerPortlet {
	
	private static final Log _log = LogFactoryUtil
			.getLog(GoodBookPortlet.class);

	
	/**
     * An OSGi service to track and inject other services with the portlet. We
     * can not use a component framework like DS here as the portlet instance is
     * created by the portal.
     */
    private ReflectionServiceTracker reflectionServiceTracker;
    
    
    	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
    	
    	_log.debug("doView");
    	
		renderRequest.setAttribute("message", "Hello World");
		super.doView(renderRequest, renderResponse);
	}
	
	
	/**
     * Initializes the {@link ReflectionServiceTracker} responsible to inject
     * services
     */
    @Override
    public void init()
            throws PortletException {

            super.init();        
            
            reflectionServiceTracker = new ReflectionServiceTracker(this);
            
            _log.debug("init");
    }
    
    
    /**
     * Clear the {@link ReflectionServiceTracker} instance created during
     * {@link #init()}
     */
    @Override
    public void destroy() {

            super.destroy();
            if (reflectionServiceTracker != null) {
                    reflectionServiceTracker.close();
            }
            
            _log.debug("destroy");
    }
	

}
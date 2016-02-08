<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@ page import="biz.fz5.gook.service.OAuthConfigurationLocalServiceUtil" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<%
String apiKey = "";
String userId = "";
try {
	
	if (OAuthConfigurationLocalServiceUtil.getOAuthConfiguration("apiKey")!=null)
		apiKey = OAuthConfigurationLocalServiceUtil.getOAuthConfiguration("apiKey").getOauthValue();

	if (OAuthConfigurationLocalServiceUtil.getOAuthConfiguration("userId")!=null)
		userId = OAuthConfigurationLocalServiceUtil.getOAuthConfiguration("userId").getOauthValue();
} catch (Exception e) {}

%>
<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:fieldset label="ApiKey">	
		<aui:input cssClass="lfr-input-text-container" name="apiKey" value='<%=apiKey%>' label=""/>			
	</aui:fieldset>
	<aui:fieldset label="UserId">
		<aui:input cssClass="lfr-input-text-container" name="userId" value='<%=userId%>' label="" />			
	</aui:fieldset>
	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>
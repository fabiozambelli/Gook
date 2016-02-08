<%
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="biz.fz5.gook.service.BookLocalServiceUtil" %>
<%@ page import="biz.fz5.gook.model.Book" %>
<%@ page import="java.util.List" %>

<portlet:defineObjects />


<%
int count = BookLocalServiceUtil.getBooksCount();
List<Book> books = BookLocalServiceUtil.getBooks(0, count);
java.util.Random rand = new java.util.Random();
Integer r = rand.nextInt(count);
Book book = books.get(r);
%>
<table class="table table-hover">
<thead>
<tr>
<th style="width:30px;"><h1 style="font-size: 24.5px;color: #999999;font-weight: normal;"><i class="fa fa-google"></i></h1></th>
<th><h1 style="font-size: 24.5px;color: #999999;font-weight: normal;">Good Books</h1></th>                  
</tr>
</thead>
</table>
<div id="gook" class="text-center">
<a href="<%=book.getCanonicalVolumeLink()%>" target="_blank"><img src="<%=book.getThumbnail()%>"/></a>
</div>
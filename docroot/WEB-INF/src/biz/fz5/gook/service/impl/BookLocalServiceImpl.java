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

import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

import biz.fz5.gook.NoSuchBookException;
import biz.fz5.gook.model.Book;
import biz.fz5.gook.service.base.BookLocalServiceBaseImpl;

/**
 * The implementation of the book local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link biz.fz5.gook.service.BookLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author fabiozambelli
 * @see biz.fz5.gook.service.base.BookLocalServiceBaseImpl
 * @see biz.fz5.gook.service.BookLocalServiceUtil
 */
public class BookLocalServiceImpl extends BookLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * biz.fz5.gook.service.BookLocalServiceUtil} to access the book local
	 * service.
	 */

	public boolean bookExists(String bookId) throws SystemException {

		Book book = null;

		try {
			book = bookPersistence.findByPrimaryKey(bookId);

		} catch (NoSuchBookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (book != null)
			return true;

		return false;

	}

	public Book addBook(String bookId, String data, String thumbnail,
			String canonicalVolumeLink) throws SystemException {

		Book book = bookPersistence.create(bookId);

		book.setData(data);
		book.setCreateDate(new Date());
		book.setThumbnail(thumbnail);
		book.setCanonicalVolumeLink(canonicalVolumeLink);

		return bookPersistence.update(book);
	}
}
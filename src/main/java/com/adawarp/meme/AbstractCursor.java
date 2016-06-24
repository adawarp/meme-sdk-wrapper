/*
 * Copyright (C) 2016 Shotaro Uchida <fantom@xmaker.mx>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.adawarp.meme;

import android.database.Cursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class AbstractCursor implements Cursor {

	private final Logger logger = LoggerFactory.getLogger(AbstractCursor.class);

	public int i;
	public long l;

	@Override
	public boolean moveToLast() {
		logger.debug("moveToLast");
		return true;
	}

	@Override
	public int getInt(int columnIndex) {
		logger.debug("getInt");
		logger.debug("columnIndex=" + columnIndex);
		return i;
	}

	@Override
	public long getLong(int columnIndex) {
		logger.debug("getLong");
		logger.debug("columnIndex=" + columnIndex);
		return l;
	}

	@Override
	public boolean moveToNext() {
		logger.debug("moveToNext");
		return true;
	}

	@Override
	public int getColumnIndex(String columnName) {
		logger.debug("getColumnIndex");
		logger.debug("columnName=" + columnName);
		return 0;
	}

	@Override
	public void close() {
		logger.debug("close");
	}
}
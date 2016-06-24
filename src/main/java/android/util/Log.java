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
package android.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class Log {

	private static final Logger logger = LoggerFactory.getLogger(Log.class);

	public static int i(String tag, String msg) {
		logger.info(tag + ": " + msg);
		return 0;
	}

	public static int d(String tag, String msg) {
		logger.debug(tag + ": " + msg);
		return 0;
	}
}

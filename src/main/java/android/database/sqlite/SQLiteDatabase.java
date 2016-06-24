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
package android.database.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import com.adawarp.meme.AbstractCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class SQLiteDatabase {

	private final Logger logger = LoggerFactory.getLogger(SQLiteDatabase.class);

	public interface CursorFactory {}

	public void execSQL(String sql) {
		logger.debug("execSQL");
		logger.debug("sql=" + sql);
	}

	public Cursor rawQuery(String sql, String[] selectionArgs) {
		logger.debug("rawQuery");
		logger.debug("sql=" + sql);
		if (selectionArgs != null) {
			for (String arg : selectionArgs) {
				logger.debug("arg=" + arg);
			}
		}
		if (sql.equals("select count(id) from authentication_status where client_id = ?;")) {
			return new AbstractCursor() {
				@Override
				public long getLong(int columnIndex) {
					return 1;
				}
			};
		}
		if (sql.equals("select id, client_id, status from authentication_status where client_id = ? limit 1")) {
			return new AbstractCursor() {
				@Override
				public int getColumnIndex(String columnName) {
					switch (columnName) {
					case "id":
						return 0;
					case "status":
						return 2;
					}
					return -1;
				}

				@Override
				public int getInt(int columnIndex) {
					switch (columnIndex) {
					case 0:		// id
						return 0;
					case 2:		// status
						return 1;
					}
					return 0;
				}
			};
		}
		logger.debug("rawQuery Failed!!!");
		return null;
	}

	public long insert(String table, String nullColumnHack, ContentValues values) {
		logger.debug("insert");
		logger.debug("table=" + table);
		logger.debug("nullColumnHack=" + nullColumnHack);
		return 0;
	}

	public int delete(String table, String whereClause, String[] whereArgs) {
		logger.debug("delete");
		logger.debug("table=" + table);
		logger.debug("whereClause=" + whereClause);
		if (whereArgs != null) {
			for (String whereArg : whereArgs) {
				logger.debug("whereArgs=" + whereArg);
			}
		}
		return 0;
	}
}

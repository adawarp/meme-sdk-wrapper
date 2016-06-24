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

import android.content.Context;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public abstract class SQLiteOpenHelper {

	private final SQLiteDatabase db = new SQLiteDatabase();

	private final Context context;
	private final String name;
	private final SQLiteDatabase.CursorFactory factory;
	private final int version;

	public SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		this.context = context;
		this.name = name;
		this.factory = factory;
		this.version = version;
		this.onCreate(db);
	}

	public SQLiteDatabase getReadableDatabase() {
		return db;
	}

	public SQLiteDatabase getWritableDatabase() {
		return db;
	}

	public abstract void onCreate(SQLiteDatabase db);
}

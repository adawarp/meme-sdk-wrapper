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
package android.app;

import android.util.ArrayMap;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class ActivityThread {

	private static final ActivityThread current = new ActivityThread();

	private final ArrayMap mActivities = new ArrayMap();

	public ActivityThread() {
		mActivities.put(this, new DummyObj());
	}

	public static ActivityThread currentActivityThread() {
		return current;
	}

	private class DummyObj {
		Activity activity = new Activity();
		boolean paused = false;
	}
}

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
package android.content;

import android.bluetooth.BluetoothManager;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import com.adawarp.meme.MemeSdk;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class Context {

	private final MemeSdk daemon;
	private final PackageManager pkgMgr = new PackageManager();
	private final Resources rsrcs = new Resources();
	private final BluetoothManager btMgr;
	private final ConnectivityManager connMgr = new ConnectivityManager();

	public Context(MemeSdk daemon) {
		this.daemon = daemon;
		btMgr = new BluetoothManager(daemon);
	}

	public Context getApplicationContext() {
		return this;
	}

	public PackageManager getPackageManager() {
		return pkgMgr;
	}

	public Resources getResources() {
		return rsrcs;
	}

	public Object getSystemService(String name) {
		if (name.equals("bluetooth")) {
			return btMgr;
		}
		if (name.equals("connectivity")) {
			return connMgr;
		}
		return null;
	}
}

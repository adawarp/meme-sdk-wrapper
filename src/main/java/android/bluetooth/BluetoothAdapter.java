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
package android.bluetooth;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class BluetoothAdapter {

	private final BluetoothManager btMgr;

	public BluetoothAdapter(BluetoothManager btMgr) {
		this.btMgr = btMgr;
	}

	protected BluetoothAdapter.LeScanCallback callback;

	public static interface LeScanCallback {

	public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord);
	}

	public void stopLeScan(BluetoothAdapter.LeScanCallback callback) {
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean startLeScan(BluetoothAdapter.LeScanCallback callback) {
		btMgr.logger.debug("startLeScan: " + callback.getClass().getName());
		this.callback = callback;
		btMgr.daemon.eventReceived("scan");
		return true;
	}
}

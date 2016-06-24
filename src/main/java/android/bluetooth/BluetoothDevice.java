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

import android.content.Context;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class BluetoothDevice {

	private final BluetoothManager btMgr;
	private String name;
	private String address;

	protected final BluetoothGatt gatt;

	public BluetoothDevice(BluetoothManager btMgr, String name, String address) {
		this.btMgr = btMgr;
		this.name = name;
		this.address = address;
		this.gatt = new BluetoothGatt(btMgr, this);
	}

	public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback) {
		btMgr.logger.debug("connectGatt: autoConnect=" + autoConnect
				+ ", callback=" + callback.getClass().getName());
		gatt.callback = callback;
		btMgr._markAsConnectedDevice(address);
		return gatt;
	}

	public void _markAsDiscoveredDevice(byte[] data) {
		btMgr._markAsDiscoveredDevice(this, 255, data);
	}

	public BluetoothGatt _getBluetoothGatt() {
		return gatt;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
}

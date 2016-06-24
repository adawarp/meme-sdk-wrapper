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

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import java.util.UUID;

/**
 * MemeLib Event Daemon
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class MemeGattContext {

	/* MEME Constants */
	public static final byte[] MEME_ADV_DATA = {
		(byte) 0x02,		// Length
			/* Flags */
			(byte) 0x01,	// Type
			(byte) 0x06,
		(byte) 0x11,		// Length
			/* Incomplete UUID128 List */
			(byte) 0x06,	// Type
			(byte) 0xef, (byte) 0xc7, (byte) 0x04, (byte) 0x2e,
			(byte) 0xa6, (byte) 0x7a, (byte) 0xd8, (byte) 0x96,
			(byte) 0x60, (byte) 0x43, (byte) 0x54, (byte) 0x5b,
			(byte) 0xd1, (byte) 0x5b, (byte) 0xf2, (byte) 0xd6,
		(byte) 0x09,		// Length
			/* Complete Local Name */
			(byte) 0x09,	// Type
			(byte) 0x30, (byte) 0x34, (byte) 0x31, (byte) 0x2d, (byte) 0x31, (byte) 0x30, (byte) 0x45, (byte) 0x38,
	};
	public static final UUID MEME_SERVICE_UUID = UUID.fromString("D6F25BD1-5B54-4360-96D8-7AA62E04C7EF");
	public static final UUID WRITE_ENDPOINT_UUID = UUID.fromString("D6F25BD2-5B54-4360-96D8-7AA62E04C7EF");
	public static final UUID READ_ENDPOINT_UUID = UUID.fromString("D6F25BD4-5B54-4360-96D8-7AA62E04C7EF");
	public static final UUID CLIENT_CHARACTERISTIC_CONFIGURATION_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

	/* Dummy BT Instances */
	private final BluetoothDevice peripheral;
	private final BluetoothGattService memeService;
	private final BluetoothGattCharacteristic txChar;
	private final BluetoothGattCharacteristic rxChar;

	public MemeGattContext(BluetoothManager btMgr, String name, String address) {
		peripheral = new BluetoothDevice(btMgr, name, address);
		memeService = new BluetoothGattService(MEME_SERVICE_UUID);
		txChar = new BluetoothGattCharacteristic(WRITE_ENDPOINT_UUID);
		rxChar = new BluetoothGattCharacteristic(READ_ENDPOINT_UUID);
		rxChar.addDescriptor(new BluetoothGattDescriptor(CLIENT_CHARACTERISTIC_CONFIGURATION_UUID));
		memeService.addCharacteristic(txChar);
		memeService.addCharacteristic(rxChar);
		peripheral._getBluetoothGatt()._addService(memeService);
	}

	public BluetoothDevice getDevice() {
		return peripheral;
	}

	public void notificationReceived(byte[] value) {
		rxChar.setValue(value);
		peripheral._getBluetoothGatt()._notifyCharacteristicChange(rxChar);
	}

	public void discover() {
		peripheral._markAsDiscoveredDevice(MEME_ADV_DATA);
	}
}

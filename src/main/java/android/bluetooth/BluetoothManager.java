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

import com.adawarp.meme.MemeSdk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class BluetoothManager {

	protected final Logger logger = LoggerFactory.getLogger(BluetoothManager.class);

	protected final MemeSdk daemon;

	private final BluetoothAdapter adapter = new BluetoothAdapter(this);
	private final HashMap<String, BluetoothDevice> unconnectedDevices = new HashMap<>();
	private final ArrayList<BluetoothDevice> connectedDevices = new ArrayList<>();

	public BluetoothManager(MemeSdk daemon) {
		this.daemon = daemon;
	}

	protected void _markAsDiscoveredDevice(BluetoothDevice peripheral, int rssi, byte[] data) {
		unconnectedDevices.put(peripheral.getAddress(), peripheral);
		adapter.callback.onLeScan(peripheral, rssi, data);
	}

	protected void _markAsConnectedDevice(String address) {
		BluetoothDevice device = unconnectedDevices.remove(address);
		if (device != null) {
			logger.debug("Device found");
			connectedDevices.add(device);
			device.gatt.callback.onConnectionStateChange(device.gatt, 0, 2);
		}
	}

	public BluetoothAdapter getAdapter() {
		return adapter;
	}

	public List<BluetoothDevice> getConnectedDevices(int profile) {
		logger.debug("getConnectedDevices: profile=" + profile);
		return connectedDevices;
	}
}

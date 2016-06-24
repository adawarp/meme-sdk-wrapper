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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class BluetoothGatt {

	private final BluetoothManager btMgr;
	private final BluetoothDevice device;

	private final ArrayList<BluetoothGattService> services = new ArrayList<>();

	protected BluetoothGattCallback callback;

	public BluetoothGatt(BluetoothManager btMgr, BluetoothDevice device) {
		this.btMgr = btMgr;
		this.device = device;
	}

	public BluetoothDevice getDevice() {
		return device;
	}

	public void _addService(BluetoothGattService service) {
		services.add(service);
	}

	public void _notifyCharacteristicChange(BluetoothGattCharacteristic characteristic) {
		callback.onCharacteristicChanged(this, characteristic);
	}

	public boolean discoverServices() {
		callback.onServicesDiscovered(this, 0);
		return true;
	}

	public List<BluetoothGattService> getServices() {
		return services;
	}

	public boolean setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enable) {
		btMgr.logger.debug("setCharacteristicNotification");
		return true;
	}

	public boolean writeCharacteristic(BluetoothGattCharacteristic characteristic) {
		btMgr.daemon.eventReceived("write", characteristic);
		callback.onCharacteristicWrite(this, characteristic, 0);
		return true;
	}

	public boolean writeDescriptor(BluetoothGattDescriptor descriptor) {
		String uuid = descriptor.getUuid().toString();
		byte[] value = descriptor.getValue();
		btMgr.logger.debug("writeDescriptor: uuid=" + uuid);
		callback.onDescriptorWrite(this, descriptor, 0);
		return true;
	}
}

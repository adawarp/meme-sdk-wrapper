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
import java.util.UUID;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class BluetoothGattCharacteristic {

	protected List<BluetoothGattDescriptor> mDescriptors;

	private final UUID uuid;

	private byte[] value;

	public BluetoothGattCharacteristic(UUID uuid) {
		this.uuid = uuid;
		mDescriptors = new ArrayList<>();
	}

	public UUID getUuid() {
		return uuid;
	}

	public boolean addDescriptor(BluetoothGattDescriptor descriptor) {
		mDescriptors.add(descriptor);
		return true;
	}

	public BluetoothGattDescriptor getDescriptor(UUID uuid) {
		for (BluetoothGattDescriptor descriptor : mDescriptors) {
			if (descriptor.getUuid().equals(uuid)) {
				return descriptor;
			}
		}
		return null;
	}

	public byte[] getValue() {
		return value;
	}

	public boolean setValue(byte[] value) {
		this.value = value;
		return true;
	}
}

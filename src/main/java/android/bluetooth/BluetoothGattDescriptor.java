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

import java.util.UUID;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class BluetoothGattDescriptor {

	public static final byte[] ENABLE_NOTIFICATION_VALUE = new byte[] {0x01, 0x00};
	public static final byte[] ENABLE_INDICATION_VALUE = new byte[] {0x02, 0x00};

	private UUID uuid;
	private byte[] value;

	public BluetoothGattDescriptor(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
	}

	public byte[] getValue() {
		return value;
	}

	public boolean setValue(byte[] value) {
		this.value = value;
		return true;
	}
}
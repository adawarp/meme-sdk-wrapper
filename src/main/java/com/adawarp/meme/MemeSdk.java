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

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.jins_jp.meme.MemeConnectListener;
import com.jins_jp.meme.MemeLib;
import com.jins_jp.meme.MemeRealtimeListener;
import com.jins_jp.meme.MemeScanListener;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MemeLib SDK Wrapper
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class MemeSdk implements MemeConnectListener, MemeScanListener {

	private final Logger logger = LoggerFactory.getLogger(MemeSdk.class);

	/* MEME Constants */
	public static final String MEME_APP_ID = "000000000000000";
	public static final String MEME_APP_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	public static final String MEME_NAME = "000-0000";
	public static final String MEME_ADDRESS = "00:00:00:00:00:00";

	/* Meme SDK */
	private final MemeLib meme;

	/* Dummy Android Instances */
	private final Context ctx = new Context(this);
	private final BluetoothManager btMgr = (BluetoothManager) ctx.getSystemService("bluetooth");
	private final MemeGattContext memeGattCtx;

	private final Listener listener;

	public MemeSdk(Listener listener) {
		this.listener = listener;
		MemeLib.setAppClientID(ctx, MEME_APP_ID, MEME_APP_SECRET);
		meme = MemeLib.getInstance();
		meme.setMemeConnectListener(this);
		logger.info("MEME SDK Ver: " + meme.getSDKVersion());
		memeGattCtx = new MemeGattContext(btMgr, MEME_NAME, MEME_ADDRESS);
	}

	public void initialize() {
		// TODO: Init BT Mgr
		meme.startScan(this);
	}

	public void startDataReport(MemeRealtimeListener listener) {
		meme.startDataReport(listener);
	}

	public void responseCommand(byte[] gattValue) {
		memeGattCtx.notificationReceived(gattValue);
	}

	public void eventReceived(String event, Object... args) {
		switch (event) {
		case "scan":
			memeGattCtx.discover();
			break;
		case "write": {
			BluetoothGattCharacteristic characteristic = (BluetoothGattCharacteristic) args[0];
			UUID uuid = characteristic.getUuid();
			if (uuid.equals(MemeGattContext.WRITE_ENDPOINT_UUID)) {
				byte[] value = characteristic.getValue();
			//	logger.debug(ByteUtil.toString(value, 0, value.length, false));
				listener.writeCommand(value);
			}
		} break;
		}
	}

	@Override
	public void memeConnectCallback(boolean status) {
		logger.debug("connected: " + status);
		if (status) {
			listener.deviceReady();
		}
	}

	@Override
	public void memeDisconnectCallback() {
		logger.debug("disconnected");
	}

	@Override
	public void memeFoundCallback(String address) {
		meme.connect(memeGattCtx.getDevice().getAddress());
	}

	public static interface Listener {
		public void deviceReady();
		public void writeCommand(byte[] gattValue);
	}

	public static void main(String[] args) {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
		MemeSdk meme = new MemeSdk(new Listener() {
			@Override
			public void deviceReady() {
				System.out.println("Meme Device Ready");
				// Meme device is successfully setup, now you can call startDataReport.
			}

			@Override
			public void writeCommand(byte[] gattValue) {
				System.out.println("Write");
				// TODO: Send gattValue to the actual meme device
			}
		});
		meme.initialize();
	}
}
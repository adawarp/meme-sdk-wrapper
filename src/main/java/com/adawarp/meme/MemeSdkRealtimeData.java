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

import com.jins_jp.meme.MemeRealtimeData;

/**
 *
 * @author Shotaro Uchida <fantom@xmaker.mx>
 */
public class MemeSdkRealtimeData {

	public float accX;
	public float accY;
	public float accZ;
	public int blinkSpeed;
	public int blinkStrength;
	public int eyeMoveDown;
	public int eyeMoveLeft;
	public int eyeMoveRight;
	public int eyeMoveUp;
	public int fitError;
	public float pitch;
	public float roll;
	public float yaw;
	public int powerLeft;

	public MemeSdkRealtimeData() {
		
	}

	public MemeSdkRealtimeData(MemeRealtimeData data) {
		accX = data.getAccX();
		accY = data.getAccY();
		accZ = data.getAccZ();
		blinkSpeed = data.getBlinkSpeed();
		blinkStrength = data.getBlinkStrength();
		eyeMoveDown = data.getEyeMoveDown();
		eyeMoveLeft = data.getEyeMoveLeft();
		eyeMoveRight = data.getEyeMoveRight();
		eyeMoveUp = data.getEyeMoveUp();
		fitError = data.getFitError().getId();
		pitch = data.getPitch();
		roll = data.getRoll();
		yaw = data.getYaw();
		powerLeft = data.getPowerLeft();
	}
}

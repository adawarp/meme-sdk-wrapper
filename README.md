# JINS MEME SDK Wrapper for Java
This wrapper imitates Android-like environment to run Meme SDK in JDK7.

***Requirements***

* JDK 7 (with option -XX:-UseSplitVerifier)
* Meme SDK for Android (Tested with 1.1.0 2016.02.19)

## Usage
```
MemeSdk meme = new MemeSdk(new Listener() {
	@Override
	public void deviceReady() {
		System.out.println("Meme Device Ready");
		// Meme device is successfully setup, now you can call startDataReport.
		meme.startDataReport(memeListener);
	}

	@Override
	public void writeCommand(byte[] gattValue) {
		// You must send gattValue to actual TX characteristic.
	}
});
meme.initialize();
// You need to call this method when GATT notification is received from RX characteristic.
meme.responseCommand(gattValue);
```
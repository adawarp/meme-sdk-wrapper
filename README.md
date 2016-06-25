# JINS MEME SDK Wrapper for Java
This wrapper imitates Android-like environment to run Meme SDK in JDK.

***Requirements***

* JDK 7 (with option -XX:-UseSplitVerifier) or JDK 8 (with option -noverify)
* [Meme SDK for Android](https://developers.jins.com/ja/login/?goto=/sdks/android/download/1.1.0/)  (Tested with 1.1.0 2016.02.19)
* Maven

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

## How to BUild (JDK 7)

Open with [NetBeans](https://netbeans.org/downloads/)

## How to Build (JDK 8)
```sh
 $ git clone https://github.com/FantomJAC/meme-sdk-wrapper
```
Download [JINS Meme SDK (need to sign up)](https://developers.jins.com/ja/login/?goto=/sdks/android/download/1.1.0/) and unzip.
```sh
 $ mv /path/to/memelib/MemeLib.jar meme-sdk-wrapper/
 $ cd meme-sdk-wrapper
 $ ./install-meme-lib.sh
 $ mvn install
 $ mvn compile
```

## Quick Start
```sh
 $ ./run.sh
```

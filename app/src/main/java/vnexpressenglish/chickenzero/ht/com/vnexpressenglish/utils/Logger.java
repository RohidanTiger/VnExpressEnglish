package vnexpressenglish.chickenzero.ht.com.vnexpressenglish.utils;

import android.util.Log;

public class Logger {
	private static final boolean DEBUG_MODE = true ;
	private static final boolean DEBUG_DB = true;

	public static void e(String TAG, String msg) {
		if (DEBUG_MODE) {
			Log.e(TAG, msg);
		}
	}

	public static void d(String TAG, String msg) {
		if (DEBUG_MODE) {
			Log.d(TAG, msg);
		}
	}

	public static void i(String TAG, String msg) {
		if (DEBUG_MODE) {
			Log.i(TAG, msg);
		}
	}

	public static void w(String TAG, String msg) {
		if (DEBUG_MODE) {
			Log.w(TAG, msg);
		}
	}

	public static void e(String TAG, String msg, boolean hasLOG) {
		if (DEBUG_MODE) {
			Log.e(TAG, msg);
		}
	}

	public static void d(String TAG, String msg, boolean hasLOG) {
		if (DEBUG_MODE) {
			Log.d(TAG, msg);
		}
	}

	public static void i(String TAG, String msg, boolean hasLOG) {
		if (DEBUG_MODE) {
			Log.i(TAG, msg);
		}
	}

	public static void w(String TAG, String msg, boolean hasLOG) {
		if (DEBUG_MODE) {
			Log.w(TAG, msg);
		}
	}

	public static void logDB(String TAG, String msg) {
		if (DEBUG_DB) {
			Log.e(TAG, msg);
		}
	}

}
package com.esic.preference;

import java.util.prefs.Preferences;

public class PreferenceManager {

	private static Preferences prefs;

	static void init() {
		prefs = Preferences.userRoot().node(PreferenceManager.class.getName());

	}

	public static Preferences getPreferences() {

		if (prefs == null) {
			init();
		}
		
		
		return prefs;
	}

}

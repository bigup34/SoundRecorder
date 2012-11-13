package com.soundrecorder.libraries;

import com.soundrecorder.ressources.FileFormats;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
		
	private Context context = null;
	
	public Settings(final Context context) {
        this.context = context;
    }
	
	public String getFilePrefix() {
	   SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context); 
	   return sharedPref.getString("editPrefix", "SoundRecorder_");
   }
	
	public boolean getAutoRecordCall() {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context); 
		return sharedPref.getBoolean("checkboxAutoRecordCall", false);
	}
	
	public boolean getSDStatus() {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context); 
		return sharedPref.getBoolean("checkboxSDCARD", false);
	}

	public boolean getStereo() {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context); 
		return sharedPref.getBoolean("checkboxStereo", true);
	}
	
	public int getBitRates() {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context); 
		String tmp = sharedPref.getString("listBitrates", "64000");
		return Integer.parseInt(tmp);
	}
	
	public FileFormats getFormat() {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context); 
		String tmp = sharedPref.getString("listFormats", "3GP");
		if (tmp == "3GP")
			return FileFormats.GPP;
		else if (tmp == "MP4")
			return FileFormats.MP4;
		else if (tmp == "AMR")
			return FileFormats.AMR;
		else
			return FileFormats.GPP;
	}
}


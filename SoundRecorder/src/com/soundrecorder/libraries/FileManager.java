package com.soundrecorder.libraries;

import java.io.File;

import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;


public class FileManager {
	
	private String rootFolder;
	private Context c;
	private boolean storageMethod;
	
	public FileManager(Context context)
	{
		c = context;
		storageMethod = false;
		Environment.getExternalStorageDirectory().getPath();
		File file = new File(Environment.getExternalStorageDirectory().getPath() + "/SoundRecorder");
		if (file.exists() == false)
		{
			if (file.mkdir())
				Log.e("FileManager", "Ajout du dossier : " + file.getAbsolutePath());
			else
				Log.e("FileManager", "Echec d'ajout dossier : " + file.getAbsolutePath());
		}
		rootFolder = file.getAbsolutePath();
	}
	
	public boolean isStorageMethod()
	{
		return storageMethod;
	}


	public void setStorageMethod(boolean storageMethod)
	{
		this.storageMethod = storageMethod;
		if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState()) && this.isStorageMethod() == true)
		{
			File f = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/SoundRecord");
			if (f.exists() == false)
			{
				File dir = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/SoundRecord");
				dir.mkdir();
			}
			this.setRootFolder(f.getAbsolutePath());
		}
		else
		{
			this.storageMethod = false;
			File f = c.getDir("SoundRecord", Context.MODE_PRIVATE);
			this.setRootFolder(f.getAbsolutePath());
		}
	}
	
	public String getRootFolder()
	{
		return rootFolder;
	}

	public void setRootFolder(String rootFolder)
	{
		
		this.rootFolder = rootFolder;
	}
	
	public boolean moveFile(String filename, String newPath)
	{
		File file = new File (rootFolder + File.separator + filename);
		
		file.renameTo(new File(rootFolder + File.separator + newPath + File.separator + filename));
		return (true);
	}

	public boolean renameFile(String filename, String newFilename)
	{
		File file = new File (rootFolder + File.separator + filename);
		
		return (file.renameTo(new File(rootFolder + File.separator + newFilename)));
	}
	
	public boolean removeFile(String filename)
	{
		File file = new File (rootFolder + File.separator + filename);
		
		return (file.delete());
	}
	
	public String[] getContentDir()
	{
		File dir = new File(rootFolder);
		
		if (dir.isDirectory())
			return dir.list();
	    return null;
	}
	
	// TYPES : RingtoneManager.TYPE_NOTIFICATION, RingtoneManager.TYPE_ALARM, RingtoneManager.TYPE_RINGTONE
	public void setAs(int type, String filename) {
		File tmp = new File(rootFolder + File.separator + filename);
		Uri uri = Uri.fromFile(tmp);
		RingtoneManager.setActualDefaultRingtoneUri(c, type, uri);
	}
}

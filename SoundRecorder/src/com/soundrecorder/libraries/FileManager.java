package com.soundrecorder.libraries;

import java.io.File;

import android.content.Context;
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
		
		file.renameTo(new File(rootFolder + File.separator + newFilename));
		return (true);
	}
	
	public boolean removeFile(String filename)
	{
		File file = new File (rootFolder + File.separator + filename);
		
		file.delete();
		return (true);
	}
	
	public String[] getContentDir()
	{
		File dir = new File(rootFolder);
		
		if (dir.isDirectory())
			return dir.list();
	    return null;
	}
}

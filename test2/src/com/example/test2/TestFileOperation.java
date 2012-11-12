package com.example.test2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.util.Log;

public class TestFileOperation
{
	private String rootFolder;
	private Context c;
	private boolean storageMethod;
	
	TestFileOperation(Context context)
	{
		c = context;
		storageMethod = false;
		File f = c.getDir("SoundRecord", Context.MODE_PRIVATE);
		rootFolder = f.getAbsolutePath();
		Log.e("test Constructor", rootFolder);
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
	
	public Collection<String> getContentDir()
	{
		Collection<String> files = new ArrayList<String>();
		File dir = new File(this.getRootFolder());
		
		if (dir.isDirectory())
		{
			File[] listFiles = dir.listFiles();

			for (File file : listFiles)
		    {
				if(file.isFile())
		        {
					files.add(file.getName());
					Log.e("list folder", file.getName());
		        }
		    }
		}
	    return files;
	}
}

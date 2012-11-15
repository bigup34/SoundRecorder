package com.soundrecorder.libraries;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.os.Environment;

public class FileManagerTest
{
	protected FileManager fm;

	@Before
	public void setUp() throws Exception
	{
		this.fm = new FileManager(null);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testRenameFile() throws Exception
	{
		assertEquals(true, this.fm.renameFile("Windows.3gpp", "toto.txt"));
	}

}

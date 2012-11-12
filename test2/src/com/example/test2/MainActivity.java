package com.example.test2;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	TestFileOperation t = new TestFileOperation(this);
    	//t.setStorageMethod(true);
    	Log.e("New PATH",t.getRootFolder());
    	t.getContentDir();
    	t.renameFile("test1.txt", "TEST1111111111111.txt");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}

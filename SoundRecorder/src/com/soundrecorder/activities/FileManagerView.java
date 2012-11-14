package com.soundrecorder.activities;

import com.soundrecorder.libraries.FileManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FileManagerView extends Activity {
	
	private ImageButton back;
	private LinearLayout svContent;
	private FileManager	fileManager;
	
	private OnClickListener clickListenerBack = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent().setClass(getApplicationContext(), RecorderMenu.class);
			startActivity(intent);
		}
	};
	
	private void loadFileList()
	{
		String[] fileList;
		int i;
		
		fileList = fileManager.getContentDir();
		for (i = 0; i < fileList.length; i++)
		{
			TextView text = new TextView(this);
			text.setTextColor(Color.WHITE);
			if (i % 2 == 1)
				text.setBackgroundColor(Color.rgb(90, 90, 90));
			text.setMinimumHeight(80);
			text.setText(fileList[i]);
			svContent.addView(text);
		}
		if (i == 0)
		{
			TextView text = new TextView(this);
			text.setTextColor(Color.WHITE);
			text.setText("No sound recorded");
			svContent.addView(text);
		}
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager_view);
        
        back = (ImageButton) findViewById(R.id.back_btn);
        svContent = (LinearLayout) findViewById(R.id.sv_content);
        
        back.setOnClickListener(clickListenerBack);
        fileManager = new FileManager(this.getApplicationContext());
        svContent.addTouchables(null);
        loadFileList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_file_manager_view, menu);
        return true;
    }
}

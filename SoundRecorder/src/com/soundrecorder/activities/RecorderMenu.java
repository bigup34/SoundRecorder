package com.soundrecorder.activities;

import com.soundrecorder.activities.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RecorderMenu extends Activity {

	private Button recordButton;
	private Button playerButton;
	private Button managerButton;

	private OnClickListener clickListenerRecord = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent().setClass(getApplicationContext(), RecordView.class);
			startActivity(intent);
		}
	};
	
	private OnClickListener clickListenerPlayer = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent().setClass(getApplicationContext(), SoundPlayerView.class);
			startActivity(intent);
		}
	};
	
	private OnClickListener clickListenerManage = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent().setClass(getApplicationContext(), FileManagerView.class);
			startActivity(intent);
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder_menu);
        recordButton = (Button) findViewById(R.id.record_button);
        playerButton = (Button) findViewById(R.id.sound_player);
        managerButton = (Button) findViewById(R.id.file_manager);
        
        recordButton.setOnClickListener(clickListenerRecord);
        playerButton.setOnClickListener(clickListenerPlayer);
        managerButton.setOnClickListener(clickListenerManage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_recorder_menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.menu_settings:
            Intent intent = new Intent(this, SettingsView.class);
            this.startActivity(intent);
            break;
        default:
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}

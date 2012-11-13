package com.soundrecorder.activities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class SoundPlayerView extends Activity {
	private ImageButton back;
	
	private OnClickListener clickListenerBack = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent().setClass(getApplicationContext(), RecorderMenu.class);
			startActivity(intent);
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_player_view);
        
        back = (ImageButton) findViewById(R.id.back_btn);
        back.setOnClickListener(clickListenerBack);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sound_player_view, menu);
        return true;
    }
}

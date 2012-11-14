package com.soundrecorder.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

public class PopUp extends Activity {

	private boolean wantrecord = false;
	
	private OnClickListener clickListenerYesRecord = new OnClickListener() {
		public void onClick(View v) {
			wantrecord = true;
		}
	};

	private OnClickListener clickListenerNoRecord = new OnClickListener() {
		public void onClick(View v) {
			wantrecord = false;
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        Button bYes = (Button)findViewById(R.id.recordYES);
        bYes.setOnClickListener(clickListenerYesRecord);
        Button bNo = (Button)findViewById(R.id.recordNO);
        bNo.setOnClickListener(clickListenerNoRecord);
    }

	public boolean getWantrecord() {
		return wantrecord;
	}

	public void setWantrecord(boolean wantrecord) {
		this.wantrecord = wantrecord;
	}
}

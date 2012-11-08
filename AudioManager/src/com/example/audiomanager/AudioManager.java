package com.example.audiomanager;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AudioManager extends Activity {

	private Button startRecord = null;
	private Button stopRecord = null;
	private Button callRecord = null;
	private Button startPlaying = null;
	private Button stopPlaying = null;
	private Button pausePlaying = null;
	
	private static final String LOG_TAG = "AudioManager";
	private MediaRecorder recorder = null;
	private boolean isRecording = false;
	private boolean isSongLoaded = false;
	private boolean isPlaying = false;
	private boolean isPaused = false;
	private float recordVolume = 100;
	private File audiofile = null;
	private MediaPlayer Player = null;
	private String tmpCurrentSong = null;
	
	public boolean isSongLoad() {
		return isSongLoaded;
	}

	public boolean isPlaying() {
		return isPlaying;
	}
	
	public boolean isRecording() {
		return isRecording;
	}

	public void setRecording(boolean isRecording) {
		this.isRecording = isRecording;
	}
	
	public void loadSong(String Path) {
		Player = new MediaPlayer();
		try {
			Player.setDataSource(Path);
		}
		catch (IOException e) {
            Log.e(LOG_TAG, "setDataSource() failed");
            return;
        }
		isSongLoaded = true;
	}
	
	public void playSong() {
		if (isSongLoad()) {
			try {
				Player.prepare();
				Player.start();	
			}
			catch (IOException e) {
				Log.e(LOG_TAG, tmpCurrentSong);
				Log.e(LOG_TAG, "prepare() failed");
				return;
			}
			isPlaying = true;
		}
	}
	
	public void pauseSong() {
		if (isSongLoad()) {
			if (isPaused) {
				Player.start();
				isPaused = false;
			}
			else {
				Player.pause();
				isPaused = true;
			}
		}
	}
	
	public int getCurrentTime() {
		return Player.getCurrentPosition();
	}
	
	public void setCurrentTime(int msec) {
		Player.seekTo(msec);
	}
	
	public int getDuration() {
		return Player.getDuration();
	}
	
	public void setRecordVolume(float volume) {
		Player.setVolume(volume, volume);
		recordVolume = volume;
	}
	
	public float getRecordVolume() {
		return recordVolume;
	}
	
	public void stopSong() {
		if (isPlaying()) {
			Player.release();
			Player = null;
		}
	}
	
	public void recordCall(String OutputFilename, int bitrates, FileFormats format) {
		File dir = Environment.getExternalStorageDirectory();
		String formatsuf = null;
		recorder = new MediaRecorder();
		recorder.setAudioEncodingBitRate(bitrates);
		recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        switch (format) {
        	case GPP:
        		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        		formatsuf = "3gpp";
        		break;
        	case AMR:
        		recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        		formatsuf = "amr";
        		break;
        	case MP4:
        		recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        		formatsuf = "mp4";
        		break;
        	default:
        		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        		formatsuf = "3gpp";
        		break;
        	}
		try {
			audiofile = File.createTempFile(OutputFilename, "." + formatsuf, dir);
		} catch (IOException e) {
			Log.e(LOG_TAG, "sdcard access error");
			return;
		}
        recorder.setOutputFile(audiofile.getAbsolutePath());
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.v(LOG_TAG, "prepare() failed");
            return;
        }
        recorder.start();
        setRecording(true);
        tmpCurrentSong = audiofile.getAbsolutePath();
        Toast.makeText(getApplicationContext(), "RecordCall Launch !", Toast.LENGTH_LONG).show();
	}
	
	public void recordMic(String OutputFilename, int bitrates, FileFormats format) {
		File dir = Environment.getExternalStorageDirectory();
		String formatsuf = null;
		recorder = new MediaRecorder();
		recorder.setAudioEncodingBitRate(bitrates);
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        switch (format) {
        	case GPP:
        		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        		formatsuf = "3gpp";
        		break;
        	case AMR:
        		recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        		formatsuf = "amr";
        		break;
        	case MP4:
        		recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        		formatsuf = "mp4";
        		break;
        	default:
        		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        		formatsuf = "3gpp";
        		break;
        	}
		try {
			tmpCurrentSong = dir.getAbsolutePath() + "/" + OutputFilename + "." + formatsuf;
			audiofile = File.createTempFile(OutputFilename, "." + formatsuf, dir);
		} catch (IOException e) {
			Log.e(LOG_TAG, "sdcard access error");
			return;
		}
        recorder.setOutputFile(audiofile.getAbsolutePath());
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.v(LOG_TAG, "prepare() failed");
            return;
        }
        recorder.start();
        setRecording(true);
        tmpCurrentSong = audiofile.getAbsolutePath();
        Toast.makeText(getApplicationContext(), "Record Launch !", Toast.LENGTH_LONG).show();
	}
	
	public void stopRecording() {
		recorder.stop();
		recorder.release();
		recorder = null;
		setRecording(false);
		Toast.makeText(getApplicationContext(), "Record Stop !", Toast.LENGTH_LONG).show();
	}

	private OnClickListener clickListenerRecordMic = new OnClickListener() {
		  public void onClick(View v) {
			  recordMic("testrecord", 64000, FileFormats.GPP);
		}
	};
	
	private OnClickListener clickListenerStopRecording = new OnClickListener() {
		public void onClick(View v) {
			stopRecording();
		}
	};

	private OnClickListener clickListenerCallRecord = new OnClickListener() {
		public void onClick(View v) {
			recordCall("testrecordcall", 128000, FileFormats.MP4);
		}
	};

	private OnClickListener clickListenerStartPlaying = new OnClickListener() {
		public void onClick(View v) {
			loadSong(tmpCurrentSong);
			playSong();
		}
	};

	private OnClickListener clickListenerStopPlaying = new OnClickListener() {
		public void onClick(View v) {
			stopSong();
		}
	};

	private OnClickListener clickListenerPausePlaying = new OnClickListener() {
		public void onClick(View v) {
			pauseSong();
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startRecord = (Button)findViewById(R.id.startrecord);
        stopRecord = (Button)findViewById(R.id.stoptrecord);
        callRecord = (Button)findViewById(R.id.callrecord);
        startPlaying = (Button)findViewById(R.id.startplaying);
        stopPlaying = (Button)findViewById(R.id.stopplaying);
        pausePlaying = (Button)findViewById(R.id.pauseplaying);
        startRecord.setOnClickListener(clickListenerRecordMic);
        stopRecord.setOnClickListener(clickListenerStopRecording);
        callRecord.setOnClickListener(clickListenerCallRecord);
        startPlaying.setOnClickListener(clickListenerStartPlaying);
        stopPlaying.setOnClickListener(clickListenerStopPlaying);
        pausePlaying.setOnClickListener(clickListenerPausePlaying);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}

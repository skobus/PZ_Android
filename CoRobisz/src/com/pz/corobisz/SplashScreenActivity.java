package com.pz.corobisz;

import com.example.corobisz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

public class SplashScreenActivity extends Activity {

	/**
	 * false when we touch the screen to skip the splash screen
	 */
	protected boolean active = true;

	/**
	 * true when we pressed back button (onBackPressed) or home button (onPause)
	 */
	protected boolean canceled = false;

	/**
	 * duration in ms
	 */
	protected int splashTime = 2500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // fullscreen, without
														// titlebar
		setContentView(R.layout.spash_screen);

		Thread logoTimer = new Thread() {

			@Override
			public void run() {
				try {
					int logoTimer = 0;
					while (active && (logoTimer < splashTime)) {
						if (active) {
							logoTimer = logoTimer + 100;
						}
						sleep(100);
					}

					if (!canceled) {
						startActivity(new Intent(SplashScreenActivity.this,
								MainActivity.class));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();
				}
			}
		};

		logoTimer.start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			active = false;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		this.canceled = true;
		super.onBackPressed();
	}

	@Override
	protected void onPause() {
		this.canceled = true;
		super.onPause();
	}

	@Override
	protected void onResume() {
		this.canceled = false;
		super.onResume();
	}

}

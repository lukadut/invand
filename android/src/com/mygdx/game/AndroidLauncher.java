package com.mygdx.game;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
//import com.mygdx.game.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
//		config.useGL30 = true;
		config.useWakelock = true;
		config.useCompass = false;
		config.r = config.g = config.b = config.a = 8;





//		initialize(new MyGdxGame(), config);
//		initialize(new MainMenuScreen(), config);
		initialize(new SpaceInvaders(), config);
//		initialize(new TouchpadTest(), config);

	}
}

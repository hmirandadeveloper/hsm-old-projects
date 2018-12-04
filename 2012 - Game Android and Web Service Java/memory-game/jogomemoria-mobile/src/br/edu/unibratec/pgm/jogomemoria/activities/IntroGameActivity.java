package br.edu.unibratec.pgm.jogomemoria.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import br.edu.unibratec.pgm.jogomemoria.constants.EScreens;
import br.edu.unibratec.pgm.jogomemoria.persistencia.RepositorioPlayer;
import br.edu.unibratec.pgm.jogomemoria.screens.IntroGameView;


public class IntroGameActivity extends Activity {
	
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setWindowAnimations(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		RepositorioPlayer playerDB = new RepositorioPlayer(this);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new IntroGameView(this, EScreens.INTRO_B_SCREEN));
	}
}

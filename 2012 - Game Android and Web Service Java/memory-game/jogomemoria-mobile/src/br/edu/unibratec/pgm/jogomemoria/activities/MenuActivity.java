package br.edu.unibratec.pgm.jogomemoria.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.edu.unibratec.pgm.jogomemoria.constants.EScreens;
import br.edu.unibratec.pgm.jogomemoria.managers.ScreenManager;
import br.edu.unibratec.pgm.projeto.jogomemoria.R;

public class MenuActivity extends Activity implements OnClickListener{
	
	private Button novoJogo;
	private Button ranking;
	private Button voltar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_menu);
		
		this.novoJogo = (Button)findViewById(R.id.bt_newgame);
		this.ranking = (Button)findViewById(R.id.bt_ranking);
		this.voltar = (Button)findViewById(R.id.bt_back);
		
		this.novoJogo.setOnClickListener(this);
		this.ranking.setOnClickListener(this);
		this.voltar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_newgame:
			ScreenManager.setarString(getIntent().getStringExtra("nome"));
			ScreenManager.screenControl(this, EScreens.MAIN_GAME_SCREEN);
			break;
			
		case R.id.bt_ranking:
			ScreenManager.screenControl(this, EScreens.RANKING_SCREEN);
			break;
			
		case R.id.bt_back:
			ScreenManager.screenControl(this, EScreens.LOGIN_SCREEN);
			break;

		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		ScreenManager.screenControl(this, EScreens.LOGIN_SCREEN);
		super.onBackPressed();
	}	
}

package br.edu.unibratec.pgm.jogomemoria.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import br.edu.unibratec.pgm.jogomemoria.basicas.Partida;
import br.edu.unibratec.pgm.jogomemoria.constants.EScreens;
import br.edu.unibratec.pgm.jogomemoria.managers.ScreenManager;
import br.edu.unibratec.pgm.jogomemoria.persistencia.RepositorioPlayer;
import br.edu.unibratec.pgm.projeto.jogomemoria.R;
import br.edu.unibratec.pgm.projeto.jogomemoria.RankingAdapter;

public class RankingActivity extends Activity{
	
	private ListView lista;
	private RepositorioPlayer playerDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_ranking);
		
		this.lista = (ListView)findViewById(R.id.listView1);
		
		this.playerDB = new RepositorioPlayer(this);
		
		List<Partida> partidas = new ArrayList<Partida>();
		partidas = playerDB.gerarRanking();
		
		lista.setAdapter(new RankingAdapter(this, partidas));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ranking, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		ScreenManager.screenControl(this, EScreens.MENU_SCREEN);
		super.onBackPressed();
	}	

}

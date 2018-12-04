package br.edu.unibratec.pgm.jogomemoria.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.unibratec.pgm.jogomemoria.constants.EScreens;
import br.edu.unibratec.pgm.jogomemoria.managers.ScreenManager;
import br.edu.unibratec.pgm.projeto.jogomemoria.R;

public class LoginActivity extends Activity implements OnClickListener{
	
	private Button avancar;
	private Button voltar;
	private EditText nome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login);
		
		this.avancar = (Button)findViewById(R.id.bt_login);
		this.voltar  = (Button)findViewById(R.id.bt_back_login);
		this.nome = (EditText)findViewById(R.id.txt_nome);
		
		this.avancar.setOnClickListener(this);
		this.voltar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login:
			if(!this.nome.getText().toString().equals(""))
			{
				ScreenManager.setarString(this.nome.getText().toString());
				ScreenManager.screenControl(this, EScreens.MENU_SCREEN);
			}
			else
			{
				Toast.makeText(this, getString(R.string.name_empty), Toast.LENGTH_SHORT).show();
			}
			break;
			
		case R.id.bt_back_login:
			ScreenManager.screenControl(this, EScreens.TITLE_SCREEN);
			break;

		default:
			break;
		}
		
	}
	@Override
	public void onBackPressed() {
		ScreenManager.screenControl(this, EScreens.TITLE_SCREEN);
		super.onBackPressed();
	}	

}

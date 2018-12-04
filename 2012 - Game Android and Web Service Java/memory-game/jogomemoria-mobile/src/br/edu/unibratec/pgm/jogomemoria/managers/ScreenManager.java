package br.edu.unibratec.pgm.jogomemoria.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import br.edu.unibratec.pgm.jogomemoria.activities.GameOverActivity;
import br.edu.unibratec.pgm.jogomemoria.activities.IntroBGameActivity;
import br.edu.unibratec.pgm.jogomemoria.activities.IntroGameActivity;
import br.edu.unibratec.pgm.jogomemoria.activities.LoginActivity;
import br.edu.unibratec.pgm.jogomemoria.activities.MainGameActivity;
import br.edu.unibratec.pgm.jogomemoria.activities.MenuActivity;
import br.edu.unibratec.pgm.jogomemoria.activities.RankingActivity;
import br.edu.unibratec.pgm.jogomemoria.activities.TitleGameActivity;
import br.edu.unibratec.pgm.jogomemoria.constants.EScreens;

public class ScreenManager {
	
	private static String parametro = null;
	
	public static void screenControl(Context context, EScreens screen)
	{		
		Intent intent;
		switch (screen) {
		case INTRO_SCREEN:
			((Activity) context).finish();
			intent = new Intent(context, IntroGameActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			if(parametro != null)
			{
				intent.putExtra("nome", parametro);
			}
			context.startActivity(intent);
			break;
			
		case INTRO_B_SCREEN:
			((Activity) context).finish();
			intent = new Intent(context, IntroBGameActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			if(parametro != null)
			{
				intent.putExtra("nome", parametro);
			}
			context.startActivity(intent);
			break;	
			
		case TITLE_SCREEN:
			((Activity)context).finish();
			intent = new Intent(context, TitleGameActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			if(parametro != null)
			{
				intent.putExtra("nome", parametro);
			}
			context.startActivity(intent);
			break;			
			
		case LOGIN_SCREEN:
			((Activity)context).finish();
			intent = new Intent(context, LoginActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			if(parametro != null)
			{
				intent.putExtra("nome", parametro);
			}
			context.startActivity(intent);
			break;
			
		case MENU_SCREEN:
			((Activity)context).finish();
			intent = new Intent(context, MenuActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			if(parametro != null)
			{
				intent.putExtra("nome", parametro);
			}
			context.startActivity(intent);
			break;
			
		case RANKING_SCREEN:
			((Activity)context).finish();
			intent = new Intent(context, RankingActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			if(parametro != null)
			{
				intent.putExtra("nome", parametro);
			}
			context.startActivity(intent);
			break;
			
		case MAIN_GAME_SCREEN:
			((Activity)context).finish();
			intent = new Intent(context, MainGameActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			if(parametro != null)
			{
				intent.putExtra("nome", parametro);
			}
			context.startActivity(intent);
			break;
			
		case GAME_OVER_SCREEN:
			((Activity)context).finish();
			intent = new Intent(context, GameOverActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			if(parametro != null)
			{
				intent.putExtra("nome", parametro);
			}
			context.startActivity(intent);
			break;
		}
	}
	
	public static void setarString(String par)
	{
		parametro = par;
	}
}

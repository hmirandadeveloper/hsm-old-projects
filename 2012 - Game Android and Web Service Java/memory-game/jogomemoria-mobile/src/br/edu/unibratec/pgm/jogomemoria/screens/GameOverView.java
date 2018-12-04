package br.edu.unibratec.pgm.jogomemoria.screens;

import br.edu.unibratec.pgm.jogomemoria.constants.EScreens;
import br.edu.unibratec.pgm.jogomemoria.gamecomponents.BackgroundComponent;
import br.edu.unibratec.pgm.projeto.jogomemoria.R;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameOverView extends GameView {
	
	private String fimDeJogo;
	private BackgroundComponent imagemMonstros;
	
	public GameOverView(Context context, EScreens nextScreen) {
		super(context, nextScreen);
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		
		this.fimDeJogo = getContext().getString(R.string.gameover);
		
		this.imagemMonstros = new BackgroundComponent(BitmapFactory.decodeResource(
				getResources(), R.drawable.fimdejogo), 0, 0, "fim_de_jogo_monstros");
		
		getGraphicContent().add(this.imagemMonstros);
	}
	
	@Override
	public void update() {
		super.update();
		
		this.imagemMonstros.setX(getWidth()/2 - (this.imagemMonstros.getWidth()/2));
		this.imagemMonstros.setY(getHeight() - (this.imagemMonstros.getHeight() + 20));
		
		if(getGameTime() == 50)
		{
			setChangeScreen(true);
		}
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		
		getPaint().setTextSize(30);

		getPaint().setColor(Color.BLACK);

		getPaint().setTextAlign(Paint.Align.CENTER);
		getPaint().setShadowLayer(1, 0, 0, Color.BLACK);
		getPaint().setAntiAlias(true);
		
		super.onDraw(canvas);
		
		canvas.drawText(this.fimDeJogo, getWidth()/2, getHeight()/2, getPaint());
	}

	public String getFimDeJogo() {
		return fimDeJogo;
	}

	public void setFimDeJogo(String fimDeJogo) {
		this.fimDeJogo = fimDeJogo;
	}

	public BackgroundComponent getImagemMonstros() {
		return imagemMonstros;
	}

	public void setImagemMonstros(BackgroundComponent imagemMonstros) {
		this.imagemMonstros = imagemMonstros;
	}
}

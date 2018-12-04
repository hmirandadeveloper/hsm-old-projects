package br.edu.unibratec.pgm.jogomemoria.screens;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import br.edu.unibratec.pgm.jogomemoria.constants.EScreens;
import br.edu.unibratec.pgm.jogomemoria.gamecomponents.Background;
import br.edu.unibratec.pgm.projeto.jogomemoria.R.drawable;


public class IntroBGameView extends GameView {
	Bitmap backgroundBmp;
	public IntroBGameView(Context context, EScreens nextScreen) {
		super(context, nextScreen);
	}
	@Override
	protected void onLoad() {
		this.backgroundBmp = BitmapFactory.decodeResource(getResources(), drawable.jimkellytribute);
		super.onLoad();
	}
	
	@Override
	public void update() {
		getGraphicContent().add(new Background(this.backgroundBmp, 
				getWidth() / 2 - this.backgroundBmp.getWidth() / 2, 
				getHeight() / 2 - backgroundBmp.getHeight() / 2, 
				"spr_jim_kelly_tributte"));
		if(getGameTime() == 30)
		{
			setChangeScreen(true);
		}
		super.update();
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		super.onDraw(canvas);
	}

}

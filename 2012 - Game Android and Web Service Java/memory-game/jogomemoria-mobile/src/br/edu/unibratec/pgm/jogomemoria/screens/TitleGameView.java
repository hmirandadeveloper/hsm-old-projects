package br.edu.unibratec.pgm.jogomemoria.screens;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import br.edu.unibratec.pgm.jogomemoria.constants.EScreens;
import br.edu.unibratec.pgm.jogomemoria.gamecomponents.Background;
import br.edu.unibratec.pgm.jogomemoria.gamecomponents.Sprite;
import br.edu.unibratec.pgm.projeto.jogomemoria.R;

public class TitleGameView extends GameView {
	
	private Sprite title_background;
	private String txt_start;
	
	
	public TitleGameView(Context context, EScreens nextScreen) {
		super(context, nextScreen);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		this.title_background = new Background(BitmapFactory.decodeResource
				(getResources(), R.drawable.menu),0,0, "spr_titulo");
		getGraphicContent().add(title_background);
		
		this.txt_start =  getContext().getString(R.string.game_title);
	}
	
	@Override
	public void update() {
		super.update();
		
		this.title_background.setX(getWidth()/2 - this.title_background.getWidth()/2);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		if(getGameTime() <= 1)
		{
			getPaint().setTextSize(18);
		}
		else if(getGameTime() == 2)
		{
			getPaint().setTextSize(19);
		}
		else if(getGameTime() == 3)
		{
			getPaint().setTextSize(20);
		}
		else if(getGameTime() == 4)
		{
			getPaint().setTextSize(21);
		}
		else if(getGameTime() > 4 && getGameTime() <= 6)
		{
			getPaint().setTextSize(22);
		}
		else if(getGameTime() == 7)
		{
			getPaint().setTextSize(21);
		}
		else if(getGameTime() == 8)
		{
			getPaint().setTextSize(20);
		}
		else
		{
			getPaint().setTextSize(19);
			setGameTime(0);
		}

		getPaint().setColor(Color.BLACK);

		getPaint().setTextAlign(Paint.Align.CENTER);
		getPaint().setShadowLayer(1, 0, 0, Color.BLACK);
		getPaint().setAntiAlias(true);
		
		super.onDraw(canvas);
		
		canvas.drawText(txt_start, getWidth()/2 , getHeight() - 30, getPaint()); 
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		
		setChangeScreen(true);
		
		return super.onTouchEvent(event);
	}

	public Sprite getTitle_background() {
		return title_background;
	}

	public void setTitle_background(Sprite title_background) {
		this.title_background = title_background;
	}

	public String getTxt_start() {
		return txt_start;
	}

	public void setTxt_start(String txt_start) {
		this.txt_start = txt_start;
	}
}

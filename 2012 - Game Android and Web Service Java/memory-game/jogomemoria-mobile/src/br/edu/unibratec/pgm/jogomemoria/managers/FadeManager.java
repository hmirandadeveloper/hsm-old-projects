package br.edu.unibratec.pgm.jogomemoria.managers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import br.edu.unibratec.pgm.jogomemoria.constants.EFade;


public class FadeManager {

	private Paint paint;
	private int fadeColor;
	private EFade fadeState;
	private final int fadeTime;

	public FadeManager(int fadeColor)
	{
		this.paint = new Paint();
		this.fadeColor = Color.BLACK;
		this.paint.setColor(this.fadeColor);
		this.fadeState = EFade.IN;
		this.fadeTime = 15;
	}

	public void fadeControl(Canvas canvas, EFade fadeType, int timeFade)
	{
		this.fadeState = fadeType;
		switch (fadeType) {
		case NONE:
			break;

		case IN:
			fadeIn(canvas, timeFade);
			break;

		case OUT:
			fadeOut(canvas, timeFade);
			break;
			
		case FINISH:
			break;
		}
		return;
	}

	private void fadeIn(Canvas canvas, int timeFade)
	{
		if(timeFade < this.fadeTime)
		{
			this.paint.setAlpha(255 - (timeFade * 17));
		}
		else
		{
			this.paint.setAlpha(0);
			this.fadeState = EFade.NONE;
		}

		onDraw(canvas);
	}

	private void fadeOut(Canvas canvas, int timeFade)
	{
		if(timeFade < this.fadeTime)
		{
			this.paint.setAlpha(timeFade * 17);
		}
		else
		{
			this.paint.setAlpha(255);
			this.fadeState = EFade.FINISH;
		}

		onDraw(canvas);
	}
	
	private void onDraw(Canvas canvas)
	{
		canvas.drawColor(Color.TRANSPARENT);
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), this.paint);
	}
	
	public EFade getFadeState() {
		return fadeState;
	}

	public int getFadeTime() {
		return fadeTime;
	}

	public int getFadeColor() {
		return fadeColor;
	}

	public void setFadeColor(int fadeColor) {
		this.fadeColor = fadeColor;
	}
}

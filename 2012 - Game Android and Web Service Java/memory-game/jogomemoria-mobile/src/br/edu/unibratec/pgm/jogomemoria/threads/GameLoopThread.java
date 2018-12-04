package br.edu.unibratec.pgm.jogomemoria.threads;

import android.graphics.Canvas;
import br.edu.unibratec.pgm.jogomemoria.screens.GameView;


public class GameLoopThread extends Thread {
	private GameView view;
	private final int FPS;
	private boolean running;
	
	public GameLoopThread(GameView view, int FPS)
	{
		this.FPS = FPS;
		this.view = view;
		this.running = false;
	}
	
	@Override
	public void run() {
		super.run();
		
		long tickPS = 1000 / this.FPS;
		long startTime;
		long sleepTime;
		
		while(this.running)
		{
			Canvas canvas = null;
			startTime = System.currentTimeMillis();
			
			try
			{
				canvas = this.view.getHolder().lockCanvas();
				synchronized(this.view.getHolder())
				{
					this.view.update();
					this.view.onDraw(canvas);
				}
			}
			finally
			{
				if(canvas != null)
				{
					this.view.getHolder().unlockCanvasAndPost(canvas);
				}
			}
			
			sleepTime = tickPS - (System.currentTimeMillis() - startTime);
			
			try
			{
				if(sleepTime > 0)
				{
					sleep(sleepTime);
				}
				else 
				{
					sleep(10);
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}

	public GameView getView() {
		return view;
	}

	public int getFPS() {
		return FPS;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	
}

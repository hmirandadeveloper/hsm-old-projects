package br.edu.unibratec.pgm.jogomemoria.screens;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import br.edu.unibratec.pgm.jogomemoria.constants.EFade;
import br.edu.unibratec.pgm.jogomemoria.constants.EScreens;
import br.edu.unibratec.pgm.jogomemoria.gamecomponents.Sprite;
import br.edu.unibratec.pgm.jogomemoria.managers.FadeManager;
import br.edu.unibratec.pgm.jogomemoria.managers.ScreenManager;
import br.edu.unibratec.pgm.jogomemoria.threads.GameLoopThread;


public abstract class GameView extends SurfaceView {
	
	private SurfaceHolder holder;
	private Paint paint;
	private FadeManager fadeManager;
	private int gameTimeFade;
	private boolean changeScreen;
	private List<Sprite> graphicContent;
	private EScreens nextScreen;
	private int FPS;
	private GameLoopThread gameLoopThread;
	private long gameTime;
		
	public GameView(Context context, EScreens nextScreen) {
		super(context);
		onLoad();
		this.gameLoopThread = new GameLoopThread(this, this.FPS);
		this.nextScreen = nextScreen;
		this.holder = getHolder();
		this.holder.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean repeat = true;
				gameLoopThread.setRunning(false);
				while(repeat)
				{
					try
					{
						gameLoopThread.join();
						repeat = false;
					}
					catch(InterruptedException iEx)
					{
						
					}
				}
				
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
				
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				
				
			}
		});
	}
	
	protected void onLoad()
	{
		this.setFPS(10);
		this.gameTime = 0;
		this.gameTimeFade = 0;
		this.changeScreen = false;
		this.fadeManager = new FadeManager(Color.BLACK);
		this.setPaint(new Paint());
		this.graphicContent = new ArrayList<Sprite>();
	}
	
	public void update()
	{
		if(this.fadeManager.getFadeState() != EFade.IN && 
				this.fadeManager.getFadeState() != EFade.OUT)
		{
			this.gameTimeFade = 0;
			this.gameTime++;
		}
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.TRANSPARENT);
		
		if(getFadeManager().getFadeState() != EFade.FINISH)
		{
			for (Sprite sprite : this.graphicContent) {
				sprite.onDraw(canvas);
			}

			if(this.changeScreen)
			{
				this.fadeManager.fadeControl(canvas, EFade.OUT, gameTimeFade++);

				if(this.fadeManager.getFadeState() == EFade.FINISH)
				{
					this.changeScreen = false;
					ScreenManager.screenControl(getContext(), nextScreen);
				}
			}
			else if(this.fadeManager.getFadeState() == EFade.IN && this.fadeManager.getFadeState() != EFade.FINISH)
			{
				this.fadeManager.fadeControl(canvas, EFade.IN, gameTimeFade++);
			}

		}
		else 
		{
			canvas.drawColor(Color.BLACK);
		}
	}

	public int getFPS() {
		return FPS;
	}

	public void setFPS(int fPS) {
		FPS = fPS;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public GameLoopThread getGameLoopThread() {
		return gameLoopThread;
	}

	public boolean isChangeScreen() {
		return changeScreen;
	}

	public void setChangeScreen(boolean changeScreen) {
		this.changeScreen = changeScreen;
	}

	public EScreens getNextScreen() {
		return nextScreen;
	}

	public void setNextScreen(EScreens nextScreen) {
		this.nextScreen = nextScreen;
	}

	public long getGameTime() {
		return gameTime;
	}

	public void setGameTime(long gameTime) {
		this.gameTime = gameTime;
	}
	
	public FadeManager getFadeManager() {
		return fadeManager;
	}

	public int getGameTimeFade() {
		return gameTimeFade;
	}

	public List<Sprite> getGraphicContent() {
		return graphicContent;
	}

	public void setGraphicContent(List<Sprite> graphicContent) {
		this.graphicContent = graphicContent;
	}
	
}

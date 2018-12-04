package br.edu.unibratec.pgm.jogomemoria.gamecomponents;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class Sprite {
	private Bitmap bmp;
	private int x;
	private int y;
	private int width;
	private int height;
	private Paint paint;
	private String id;
	private boolean visible;
	
	public Sprite(Bitmap bmp, int posX, int posY, String id)
	{
		this.id = id;
		this.bmp = bmp;
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
		this.x = posX;
		this.y = posY;
		this.visible = true;
		this.paint = new Paint();
	}
	
	public Sprite(Bitmap bmp, String id)
	{
		this.id = id;
		this.bmp = bmp;
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
		this.x = 0;
		this.y = 0;
		this.visible = true;
		this.paint = new Paint();
	}
	
	public Rect getBounds()
	{
		return new Rect(x, y, width + x, height + y);
	}

	public void onDraw(Canvas canvas)
	{
		if(this.visible)
		{
			canvas.drawBitmap(this.bmp, this.x, this.y, this.paint);
		}
	}
	
	public void paintBitmap(int colorRGB)
	{
		ColorFilter colorFilter = new LightingColorFilter(colorRGB, Color.rgb(0, 0, 0));
		this.paint.setColorFilter(colorFilter);
	}
	
	public void paintBitmap(int colorRGB1, int colorRGB2)
	{
		ColorFilter colorFilter = new LightingColorFilter(colorRGB1, colorRGB2);
		this.paint.setColorFilter(colorFilter);
	}
	
	public void resizing(double percAumento)
	{
		double scale = (double)this.height / (double)this.width;
		int nWidth = (int)(this.width * ((percAumento / 100) + 1));
		int nHeigth = (int)(this.height + ((nWidth - this.width) * scale));
		this.bmp = Bitmap.createScaledBitmap(bmp, nWidth, nHeigth, true);
	}
	
	public boolean isCollision(float x2, float y2)
	{
		return (x2 > getX() && x2 < getX() + getWidth()) && (y2 > getY() && y2 < getY() + getHeight()); 
	}
	
	public Bitmap getBmp() {
		return bmp;
	}
	
	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}

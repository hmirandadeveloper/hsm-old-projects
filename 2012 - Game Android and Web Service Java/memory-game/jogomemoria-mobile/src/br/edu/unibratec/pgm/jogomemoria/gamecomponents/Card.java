package br.edu.unibratec.pgm.jogomemoria.gamecomponents;

import android.graphics.Bitmap;

public class Card extends Sprite {
	
	private String cardName;
	private boolean displaying;
	private boolean inativa;
	
	public Card(Bitmap bmp, int posX, int posY, String id, String cardName, 
			boolean displaying) {
		super(bmp, posX, posY, id);
		this.inativa = false;
		this.cardName = cardName;
		this.displaying = displaying;
	}
	
	public Card(Bitmap bmp, int posX, int posY, String id) {
		super(bmp, posX, posY, id);
		this.inativa = false;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public boolean isDisplaying() {
		return displaying;
	}

	public void setDisplaying(boolean displaying) {
		this.displaying = displaying;
	}

	public boolean isInativa() {
		return inativa;
	}

	public void setInativa(boolean inativa) {
		this.inativa = inativa;
	}

}

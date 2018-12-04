package br.edu.unibratec.pgm.jogomemoria.managers;

import br.edu.unibratec.pgm.jogomemoria.gamecomponents.Card;

public class Jogada {
	private Card cardA;
	private Card cardB;
	
	
	public Jogada()
	{
		cardA = null;
		cardB = null;
	}
	
	public boolean cartasIguais()
	{
		if(cardA != null && cardB != null)
		{
			if(cardA.getCardName().equals(cardB.getCardName()))
			{
				return true;
			}
		}
		return false;
	}
	
	public Card getCardA() {
		return cardA;
	}
	public void setCardA(Card cardA) {
		this.cardA = cardA;
	}
	public Card getCardB() {
		return cardB;
	}
	public void setCardB(Card cardB) {
		this.cardB = cardB;
	}
}

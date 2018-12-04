package br.edu.unibratec.pgm.jogomemoria.managers;

public class TimeManager {
	private final long inicialTime;
	
	public TimeManager(long inicialTime)
	{
		this.inicialTime = inicialTime;
	}

	public long getInicialTime() {
		return inicialTime;
	}
}

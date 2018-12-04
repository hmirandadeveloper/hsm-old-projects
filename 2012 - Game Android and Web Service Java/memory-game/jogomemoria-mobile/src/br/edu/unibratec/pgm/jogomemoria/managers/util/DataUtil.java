package br.edu.unibratec.pgm.jogomemoria.managers.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
	
	
	public static String getDataAtual()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date data = new Date();
		return  dateFormat.format(data.getTime());
		
	}
}

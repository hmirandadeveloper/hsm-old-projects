package negocio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Data {
	
	public static Date gerardataPorString(String data)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dataRetorno = null;
		try {
			dataRetorno = (Date)format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataRetorno; 
	}
}

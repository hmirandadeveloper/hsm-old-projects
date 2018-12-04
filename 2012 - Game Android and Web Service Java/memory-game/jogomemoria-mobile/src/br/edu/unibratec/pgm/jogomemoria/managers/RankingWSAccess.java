package br.edu.unibratec.pgm.jogomemoria.managers;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;

public class RankingWSAccess extends AsyncTask<String[], Void, Void>{
	private static final String NAMESPACE = "acao";
	private static final String URL = 
			"http://192.168.202.220:8080/jogomemoria.web/services/RankingWS?wsdl";
	private static final String SOAP_ACTION = "RankingWS";

	
	public String webServiceIncluirRanking(String nome, String pontuacao, String data)
	{
		SoapObject request = new SoapObject(NAMESPACE, "regitrarRanking");
		SoapSerializationEnvelope envelope = 
				new SoapSerializationEnvelope(SoapEnvelope.VER11);
		String retorno = null;
		
		request.addProperty("nome", nome);
		request.addProperty("pontuacao", pontuacao);
		request.addProperty("data", data);
		
		envelope.setOutputSoapObject(request);
		
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		
		try
		{
			androidHttpTransport.call(SOAP_ACTION, envelope);
			SoapObject resultRequestSOAP = 
					(SoapObject) envelope.bodyIn;
			retorno = resultRequestSOAP.toString();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return retorno;
	}

	@Override
	protected Void doInBackground(String[]... params) {
		webServiceIncluirRanking(params[0][0], params[0][1], params[0][2]);
		return null;
	}
}

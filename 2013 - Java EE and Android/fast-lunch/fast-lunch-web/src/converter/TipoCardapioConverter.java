package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import dto.TipoCardapioDTO;

public class TipoCardapioConverter implements Converter{

	@SuppressWarnings("null")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, 
			String value) {
		TipoCardapioDTO tipoCardapioDTO = null; 
		System.out.println("Validação de Tipo de Cardápio: " + value);
		
		if(value != null || !value.equals(""))
		{
			System.out.println("Valor: " + value);
			tipoCardapioDTO = (TipoCardapioDTO)component.getAttributes().get(value);
		}

		return tipoCardapioDTO;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, 
			Object object) {
		if(object != null && object instanceof TipoCardapioDTO)
		{
			TipoCardapioDTO tipoCardapioDTO = (TipoCardapioDTO)object;
			component.getAttributes().put(tipoCardapioDTO.getNome(), tipoCardapioDTO);
			
			return tipoCardapioDTO.getNome();
		}
		
		return "";
	}

}

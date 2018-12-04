package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import dto.CardapioDTO;

public class CardapioConverter implements Converter{

	@SuppressWarnings("null")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, 
			String value) {
		CardapioDTO cardapioDTO = null; 
		System.out.println("Validação Cardápio: " + value);
		
		if(value != null || !value.equals(""))
		{
			System.out.println("Valor: ");
			cardapioDTO = (CardapioDTO)component.getAttributes().get(value);
		}

		return cardapioDTO;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, 
			Object object) {
		if(object != null && object instanceof CardapioDTO)
		{
			CardapioDTO cardapioDTO = (CardapioDTO)object;
			component.getAttributes().put(cardapioDTO.getNome(), cardapioDTO);
			
			return cardapioDTO.getNome();
		}
		
		return "";
	}

}

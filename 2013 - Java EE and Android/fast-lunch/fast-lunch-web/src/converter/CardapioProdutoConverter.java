package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import dto.CardapioProdutoDTO;

public class CardapioProdutoConverter implements Converter{

	@SuppressWarnings("null")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, 
			String value) {
		CardapioProdutoDTO cardapioProdutoDTO = null; 
		System.out.println("Validação Cardápio Produto: " + value);
		
		if(value != null || !value.equals(""))
		{
			System.out.println("Valor: ");
			cardapioProdutoDTO = (CardapioProdutoDTO)component.getAttributes().get(value);
		}

		return cardapioProdutoDTO;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, 
			Object object) {
		if(object != null && object instanceof CardapioProdutoDTO)
		{
			CardapioProdutoDTO cardapioProdutoDTO = (CardapioProdutoDTO)object;
			component.getAttributes().put(cardapioProdutoDTO.getProduto().getNome(), cardapioProdutoDTO);
			
			return cardapioProdutoDTO.getProduto().getNome();
		}
		
		return "";
	}

}

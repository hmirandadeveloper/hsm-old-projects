package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import dto.TipoPagamentoDTO;

public class TipoPagamentoConverter implements Converter{

	@SuppressWarnings("null")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, 
			String value) {
		TipoPagamentoDTO tipoPagamentoDTO = null; 
		System.out.println("Validação de Tipo de Pagamento: " + value);
		
		if(value != null || !value.equals(""))
		{
			System.out.println("Valor: " + value);
			tipoPagamentoDTO = (TipoPagamentoDTO)component.getAttributes().get(value);
		}

		return tipoPagamentoDTO;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, 
			Object object) {
		if(object != null && object instanceof TipoPagamentoDTO)
		{
			TipoPagamentoDTO tipoPagamentoDTO = (TipoPagamentoDTO)object;
			component.getAttributes().put(tipoPagamentoDTO.getDescricao(), tipoPagamentoDTO);
			
			return tipoPagamentoDTO.getDescricao();
		}
		
		return "";
	}

}

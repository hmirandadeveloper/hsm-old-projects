package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import dto.CargoDTO;

public class CargoConverter implements Converter{

	@SuppressWarnings("null")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, 
			String value) {
		System.out.println("Conversão iniciada!!!!");
		CargoDTO cargoDTO = null; 
		
		if(value != null || !value.equals(""))
		{
			System.out.println("Entrou na conversão... " + value);
			cargoDTO = (CargoDTO)component.getAttributes().get(value);
		}
		
		System.out.println("Valor retornado: " + cargoDTO.getIdCargo());
		System.out.println("Valor retornado: " + cargoDTO.getDescricao());
		System.out.println("Valor retornado: " + cargoDTO.getEstabelecimento().getCnpj());
		
		return cargoDTO;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, 
			Object object) {
		
		if(object != null && object instanceof CargoDTO)
		{
			CargoDTO cargoDTO = (CargoDTO)object;
			System.out.println("Valor retornado como String Desc.: " + cargoDTO.getDescricao());
			System.out.println("Valor retornado como String ID: " + cargoDTO.getIdCargo());
			
			component.getAttributes().put(cargoDTO.getDescricao(), cargoDTO);
			
			return cargoDTO.getDescricao();
		}
		
		return "";
	}

}

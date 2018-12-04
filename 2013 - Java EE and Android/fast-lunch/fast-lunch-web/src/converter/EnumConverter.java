package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EnumType;

import constantes.ETipoUsuario;

public class EnumConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, 
			String value) {
		if(value != null)
		{
			return ETipoUsuario.valueOf(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, 
			Object object) {
		
		if(object != null && object instanceof EnumType)
		{
			return ((ETipoUsuario) object).name();
		}
		
		return "";
	}

}

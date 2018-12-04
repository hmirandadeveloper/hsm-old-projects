package managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import constantes.ETipoUsuario;

@ManagedBean
@SessionScoped
public class UsuarioMB {
	
	public ETipoUsuario[] getTipoUsuarioFuncionario()
	{
		ETipoUsuario[] funcionarios = new ETipoUsuario[2];
		funcionarios[0] = ETipoUsuario.valueOf(ETipoUsuario.FUNC.name());
		funcionarios[1] = ETipoUsuario.valueOf(ETipoUsuario.GER.name());
		
		return funcionarios;
	}
	
	public ETipoUsuario[] getTipoUsuarioFuncionarioEspecial()
	{
		ETipoUsuario[] funcionarios = new ETipoUsuario[2];
		funcionarios[0] = ETipoUsuario.valueOf(ETipoUsuario.TEMP.name());
		
		return funcionarios;
	}
}

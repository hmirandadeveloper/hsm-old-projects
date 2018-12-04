package negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.CpfInvalidoException;
import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.util.conversor.dto.ClienteConversorDTO;
import negocio.util.validador.ValidadorAtributosEspeciais;
import negocio.util.validador.entidade.ClienteAtributoValidador;
import persistencia.ClienteDAO;
import persistencia.UsuarioDAO;
import constantes.ETipoUsuario;
import dto.ClienteDTO;
import entidade.Cliente;
import entidade.Usuario;
import fachada.IClienteFachada;

@Stateless
@Remote(IClienteFachada.class)
public class ClienteSB implements IClienteFachada{
	
	@EJB
	ClienteDAO clienteDAO;
	@EJB
	UsuarioDAO usuarioDAO;
	
	@Override
	public void salvar(ClienteDTO clienteDTO) throws 
	EntidadeAtributoIncompletoException, 
	EntidadeJaCadastradaException, 
	CpfInvalidoException {
		if(ClienteAtributoValidador.
				validarAtributosPreenchidosEntidade(clienteDTO))
		{
			System.out.println("Atributos Validados!!!");
			if(buscaPeloCpf(clienteDTO.getCpf()) == null)/* &&
					buscaPeloEmail(clienteDTO.getEmail()) == null)*/
			{
				
				System.out.println("Validar Login!!!");
				if(!validarExistenciaLoginUsuario(clienteDTO))
				{
					clienteDTO.setDataCadastro(new Date());
					clienteDTO.setAtivo(true);

					this.clienteDAO.salvar(
							ClienteConversorDTO.converterDTOEmEntidade(clienteDTO));
					System.out.println("Incluido com sucesso!");
				}
				else
				{
					throw new EntidadeJaCadastradaException(Usuario.class);
				}
			}
			else
			{
				throw new EntidadeJaCadastradaException(Cliente.class);
			}
			
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Cliente.class);
		}
	}

	@Override
	public void remover(long clientId, ETipoUsuario tipoUsuario) 
			throws EntidadeInexistenteException {
		if(buscar(clientId) != null)
		{
			if(tipoUsuario == ETipoUsuario.ADMIN)
			{
				this.clienteDAO.remover(gerarEntidadePorId(clientId));
				System.out.println("Removido com sucesso!");
			}
			else
			{
				Cliente cliente = clienteDAO.buscar(clientId);
				cliente.setAtivo(false);
				clienteDAO.atualizar(cliente);
				System.out.println("Inativado com sucesso!");
			}
		}
		else
		{
			throw new EntidadeInexistenteException(Cliente.class);
		}
	}

	@Override
	public ClienteDTO atualizar(ClienteDTO clienteDTO) throws 
	EntidadeInexistenteException,
	CpfInvalidoException, EntidadeAtributoIncompletoException{
		if(ClienteAtributoValidador.
				validarAtributosPreenchidosEntidade(clienteDTO))
		{
			if(buscar(clienteDTO.getIdCliente()) != null)
			{
				return ClienteConversorDTO.converterEntidadeEmDTO(
						this.clienteDAO.atualizar(
								ClienteConversorDTO.converterDTOEmEntidadeComId(clienteDTO))
						);
			}
			else
			{
				throw new EntidadeInexistenteException(Cliente.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Cliente.class);
		}
	}

	@Override
	public ClienteDTO buscar(long clienteId){
		return ClienteConversorDTO.
				converterEntidadeEmDTOComId(
						this.clienteDAO.buscar(clienteId));
	}

	@Override
	public List<ClienteDTO> buscarTodos() {
		return ClienteConversorDTO.converterEntidadesListEmDTOsList(
					this.clienteDAO.buscarTodos()
				);
	}	

	@Override
	public ClienteDTO buscaPeloCpf(String cpf) throws CpfInvalidoException{
		if(ValidadorAtributosEspeciais.validarCpf(cpf))
		{
			Cliente cliente = this.clienteDAO.buscarClientePeloCpf(cpf);
			
			return cliente != null ? ClienteConversorDTO.converterEntidadeEmDTOComId(
					cliente) : null;
		}
		else
		{
			throw new CpfInvalidoException();
		}
	}

	@Override
	public ClienteDTO buscaPeloEmail(String email){
		
		return ClienteConversorDTO.converterEntidadeEmDTO(
				this.clienteDAO.buscarClientePeloEmail(email));
	}
	

	@Override
	public ClienteDTO buscaPeloUsuario(Long id_usuario)
			throws EntidadeInexistenteException {
		System.out.println("ID para busca: " + id_usuario);
		ClienteDTO clienteDTO = ClienteConversorDTO.
				converterEntidadeEmDTOComId(
						this.clienteDAO.buscarClientePeloUsuario(id_usuario));
		
		return clienteDTO;
	}
	
	
	private Cliente gerarEntidadePorId(long clienteId)
	{
		Cliente cliente = new Cliente();
		cliente.setIdCliente(clienteId);
		return cliente;
	}
	
	private boolean validarExistenciaLoginUsuario(ClienteDTO clienteDTO)
	{
		if(usuarioDAO.buscarUsuarioPeloLogin(clienteDTO.getUsuario().getLogin()) != null)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public void controleAtivacaoCliente(ClienteDTO clienteDTO, boolean situacao) {
		// TODO Auto-generated method stub
		
	}

}

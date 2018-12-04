package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.util.conversor.dto.EnderecoConversorDTO;
import negocio.util.validador.entidade.EnderecoAtributoValidador;
import persistencia.EnderecoDAO;
import dto.EnderecoDTO;
import entidade.Endereco;
import fachada.IEnderecoFachada;

@Stateless
@Remote(IEnderecoFachada.class)
public class EnderecoSB implements IEnderecoFachada{
	
	@EJB
	EnderecoDAO enderecoDAO;
	
	@Override
	public void salvar(EnderecoDTO enderecoDTO) throws EntidadeAtributoIncompletoException {
		if(EnderecoAtributoValidador.validarAtributosPreenchidosEntidade(enderecoDTO))
		{
			this.enderecoDAO.salvar(EnderecoConversorDTO.converterDTOEmEntidade(enderecoDTO));
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Endereco.class);
		}
	}

	@Override
	public void remover(long enderecoId) throws EntidadeInexistenteException {
		if(buscar(enderecoId) != null)
		{
		this.enderecoDAO.remover(gerarEntidadePorId(enderecoId));
		}
		else
		{
			throw new EntidadeInexistenteException(Endereco.class);
		}
	}

	@Override
	public EnderecoDTO atualizar(EnderecoDTO enderecoDTO) throws EntidadeInexistenteException, EntidadeAtributoIncompletoException {
		
		if(EnderecoAtributoValidador.validarAtributosPreenchidosEntidade(enderecoDTO))
		{
			if(buscar(enderecoDTO.getIdEndereco()) != null)
			{
				return EnderecoConversorDTO.converterEntidadeEmDTO(
						this.enderecoDAO.atualizar(
								EnderecoConversorDTO.converterDTOEmEntidadeComId(enderecoDTO))
						);
			}
			else
			{
				throw new EntidadeInexistenteException(Endereco.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Endereco.class);
		}
	}

	@Override
	public EnderecoDTO buscar(long enderecoId) {
		return EnderecoConversorDTO.converterEntidadeEmDTOComId(
				this.enderecoDAO.buscar(enderecoId));
	}

	@Override
	public List<EnderecoDTO> buscarTodos() {
		return EnderecoConversorDTO.converterEntidadesListEmDTOsList(
					this.enderecoDAO.buscarTodos()
				);
	}
	
	private Endereco gerarEntidadePorId(long enderecoId)
	{
		Endereco endereco = new Endereco();
		endereco.setIdEndereco(enderecoId);
		return endereco;
	}

}

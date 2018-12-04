package negocio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import negocio.exception.ValorInvalidoException;
import negocio.util.conversor.dto.ProdutoConversorDTO;
import negocio.util.validador.entidade.ProdutoAtributoValidador;

import persistencia.ProdutoDAO;

import dto.ProdutoDTO;
import entidade.Produto;
import fachada.IProdutoFachada;

@Stateless
@Remote(IProdutoFachada.class)
public class ProdutoSB implements IProdutoFachada{
	
	@EJB
	ProdutoDAO produtoDAO;
	
	@Override
	public void salvar(ProdutoDTO produtoDTO) throws EntidadeAtributoIncompletoException, EntidadeJaCadastradaException, ValorInvalidoException {
		
		if(ProdutoAtributoValidador.validarAtributosPreenchidosEntidade(produtoDTO))
		{
			if(buscarProdutoPorNome(produtoDTO.getNome()) == null)
			{
				if(produtoDTO.getPreco() >= 0 && produtoDTO.getPrecoPromocional() >= 0 &&
						produtoDTO.getPontuacaoFidelidade() >= 0)
				{
					produtoDTO.setAtivo(true);
					this.produtoDAO.salvar(ProdutoConversorDTO.converterDTOEmEntidade(produtoDTO));
				}
				else
				{
					throw new ValorInvalidoException();
				}
			}
			else
			{
				throw new EntidadeJaCadastradaException(Produto.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Produto.class);
		}
	}

	@Override
	public void remover(long produtoId) throws EntidadeInexistenteException {
		
		if(buscar(produtoId) != null)
		{
			this.produtoDAO.remover(gerarEntidadePorId(produtoId));
		}
		else
		{
			throw new EntidadeInexistenteException(Produto.class);
		}
	}

	@Override
	public ProdutoDTO atualizar(ProdutoDTO produtoDTO) throws EntidadeAtributoIncompletoException, EntidadeInexistenteException, ValorInvalidoException {
		
		if(ProdutoAtributoValidador.validarAtributosPreenchidosEntidade(produtoDTO))
		{
			if(buscar(produtoDTO.getIdProduto()) != null)
			{
				if(produtoDTO.getPreco() >= 0 && produtoDTO.getPrecoPromocional() >= 0 &&
						produtoDTO.getPontuacaoFidelidade() >= 0)
				{
					return ProdutoConversorDTO.converterEntidadeEmDTO(
							this.produtoDAO.atualizar(
									ProdutoConversorDTO.converterDTOEmEntidadeComId(produtoDTO))
							);
				}
				else
				{
					throw new ValorInvalidoException();
				}
			}
			else
			{
				throw new EntidadeInexistenteException(Produto.class);
			}
		}
		else
		{
			throw new EntidadeAtributoIncompletoException(Produto.class);
		}
	}

	@Override
	public ProdutoDTO buscar(long produtoId) {
		return ProdutoConversorDTO.converterEntidadeEmDTOComId(
				this.produtoDAO.buscar(produtoId));
	}

	@Override
	public List<ProdutoDTO> buscarTodos() {
		return ProdutoConversorDTO.converterEntidadesListEmDTOsList(
					this.produtoDAO.buscarTodos()
				);
	}
	
	private Produto gerarEntidadePorId(long produtoId)
	{
		Produto produto = new Produto();
		produto.setIdProduto(produtoId);
		return produto;
	}

	@Override
	public ProdutoDTO buscarProdutoPorNome(String nome) {
		return ProdutoConversorDTO.converterEntidadeEmDTO(
				this.produtoDAO.buscarProdutoPeloNome(nome, null));
	}

	@Override
	public void controleAtivacaoProduto(ProdutoDTO produtoDTO, boolean situacao) {
		// TODO Auto-generated method stub
		
	}

}

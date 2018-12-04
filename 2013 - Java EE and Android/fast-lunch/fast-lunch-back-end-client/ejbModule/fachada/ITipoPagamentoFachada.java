package fachada;

import java.util.List;

import constantes.ETipoUsuario;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;
import dto.TipoPagamentoDTO;

public interface ITipoPagamentoFachada {
	public abstract void salvar(TipoPagamentoDTO tipoPagamentoDTO) throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException;
	public abstract void remover(long tipoPagamentoId, ETipoUsuario tipoUsuario) throws 
	EntidadeInexistenteException;
	public abstract TipoPagamentoDTO atualizar(TipoPagamentoDTO tipoPagamentoDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException;
	public abstract TipoPagamentoDTO buscar(long tipoPagamentoId);
	public abstract TipoPagamentoDTO buscarTipoPagamentoPelaDescricao(String descricao, Long idEstabelecimento);
	public abstract List<TipoPagamentoDTO> buscarTodos(Long idEstabelecimento);
	public abstract void controleAtivacaoTipoPagamento(TipoPagamentoDTO tipoPagamentoDTO, boolean situacao);
}

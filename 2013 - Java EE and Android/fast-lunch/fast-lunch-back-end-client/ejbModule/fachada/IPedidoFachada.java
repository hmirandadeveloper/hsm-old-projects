package fachada;

import java.util.List;

import constantes.ETipoUsuario;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.IntervaloHorarioInvalidoException;
import dto.PedidoDTO;

public interface IPedidoFachada {
	public abstract void salvar(PedidoDTO pedidoDTO) throws 
	EntidadeAtributoIncompletoException, IntervaloHorarioInvalidoException;
	public abstract void remover(long pedidoId, ETipoUsuario tipoUsuario) throws 
	EntidadeInexistenteException;
	public abstract PedidoDTO atualizar(PedidoDTO pedidoDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException;
	public abstract PedidoDTO buscar(long pedidoId);
	public abstract List<PedidoDTO> buscarTodos(Long id_estabelecimento);	
	public abstract List<PedidoDTO> buscarPedidosPeloCnpjEstabelecimento(String cnpj);	
	public abstract List<PedidoDTO> buscarPedidosPeloIdFuncionario(Long id_funcionario);
	public abstract List<PedidoDTO> buscarPedidosPeloCpfCliente(String cpf, Long id_estabelecimento);
	public abstract List<PedidoDTO> buscarPedidosPelaOrigem(String origem, Long id_estabelecimento);

}

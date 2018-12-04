package fachada;

import java.util.List;

import constantes.ETipoUsuario;

import negocio.exception.EntidadeAtributoIncompletoException;
import negocio.exception.EntidadeInexistenteException;
import negocio.exception.EntidadeJaCadastradaException;

import dto.CargoDTO;

public interface ICargoFachada {
	public abstract void salvar(CargoDTO cargoDTO)throws 
	EntidadeAtributoIncompletoException, EntidadeJaCadastradaException;
	public abstract void remover(long cargoId, ETipoUsuario eTipoUsuario) throws 
	EntidadeInexistenteException;
	public abstract CargoDTO atualizar(CargoDTO cargoDTO) throws 
	EntidadeInexistenteException, EntidadeAtributoIncompletoException;
	public abstract CargoDTO buscar(long cargoId);
	public abstract CargoDTO buscarCargoPelaDescricao(String descricao, Long id_estabelecimento);
	public abstract List<CargoDTO> buscarTodos(Long id_estabelecimento);
}

package negocio.util.conversor.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dto.FuncionarioDTO;
import entidade.Funcionario;

public abstract class FuncionarioConversorDTO {

	public static Funcionario converterDTOEmEntidade(FuncionarioDTO funcionarioDTO)
	{	
		Funcionario funcionario = new Funcionario();
		if(funcionarioDTO != null)
		{
			funcionario.setUsuario(
					UsuarioConversorDTO.converterDTOEmEntidadeComId(
							funcionarioDTO.getUsuario()));
			funcionario.setCargo(funcionarioDTO.getCargo().getIdCargo() != null ?
					CargoConversorDTO.converterDTOEmEntidadeComId(
							funcionarioDTO.getCargo()) : 
								CargoConversorDTO.converterDTOEmEntidade(
										funcionarioDTO.getCargo()));
			funcionario.setNome(funcionarioDTO.getNome());
			funcionario.setSobrenome(funcionarioDTO.getSobrenome());
			funcionario.setMatricula(funcionarioDTO.getMatricula());
			funcionario.setAtivo(funcionarioDTO.getAtivo());
		}
		return funcionario;
	}

	public static Funcionario converterDTOEmEntidadeComId(FuncionarioDTO funcionarioDTO)
	{	

		Funcionario funcionario = new Funcionario();
		if(funcionarioDTO != null)
		{
			funcionario.setUsuario(
					UsuarioConversorDTO.converterDTOEmEntidadeComId(
							funcionarioDTO.getUsuario()));
			funcionario.setCargo(
					CargoConversorDTO.converterDTOEmEntidadeComId(
							funcionarioDTO.getCargo()));
			funcionario.setNome(funcionarioDTO.getNome());
			funcionario.setSobrenome(funcionarioDTO.getSobrenome());
			funcionario.setMatricula(funcionarioDTO.getMatricula());
			funcionario.setAtivo(funcionarioDTO.getAtivo());
			funcionario.setIdFuncionario(funcionarioDTO.getIdFuncionario());
		}
		return funcionario;
	}

	public static FuncionarioDTO converterEntidadeEmDTO(Funcionario funcionario)
	{	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		if(funcionario != null)
		{
			funcionarioDTO.setUsuario(
					UsuarioConversorDTO.converterEntidadeEmDTO(
							funcionario.getUsuario()));
			funcionarioDTO.setCargo(
					CargoConversorDTO.converterEntidadeEmDTO(
							funcionario.getCargo()));
			funcionarioDTO.setNome(funcionario.getNome());
			funcionarioDTO.setSobrenome(funcionario.getSobrenome());
			funcionarioDTO.setMatricula(funcionario.getMatricula());
			funcionarioDTO.setAtivo(funcionario.getAtivo());
		}
		return funcionarioDTO;
	}

	public static FuncionarioDTO converterEntidadeEmDTOComId(Funcionario funcionario)
	{	
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		if(funcionario != null)
		{
			funcionarioDTO.setUsuario(
					UsuarioConversorDTO.converterEntidadeEmDTOComId(
							funcionario.getUsuario()));
			funcionarioDTO.setCargo(
					CargoConversorDTO.converterEntidadeEmDTOComId(
							funcionario.getCargo()));
			funcionarioDTO.setNome(funcionario.getNome());
			funcionarioDTO.setSobrenome(funcionario.getSobrenome());
			funcionarioDTO.setMatricula(funcionario.getMatricula());
			funcionarioDTO.setAtivo(funcionario.getAtivo());
			funcionarioDTO.setIdFuncionario(funcionario.getIdFuncionario());
		}
		return funcionarioDTO;
	}

	public static List<FuncionarioDTO> converterEntidadesSetEmDTOsList(Set<Funcionario> funcionariosSet)
	{
		List<FuncionarioDTO> funcionariosDTOList = new ArrayList<FuncionarioDTO>();
		if(funcionariosSet != null)
		{
			for(Funcionario funcionario : funcionariosSet)
			{
				funcionariosDTOList.add(converterEntidadeEmDTO(funcionario));
			}
		}
		return funcionariosDTOList;
	}

	public static Set<Funcionario> converterDTOsListEmEntidadesSet(List<FuncionarioDTO> funcionariosDTOList)
	{
		Set<Funcionario> funcionariosSet = new HashSet<Funcionario>(0);
		if(funcionariosDTOList != null)
		{
			for(FuncionarioDTO funcionarioDTO : funcionariosDTOList)
			{
				funcionariosSet.add(converterDTOEmEntidadeComId(funcionarioDTO));
			}
		}
		return funcionariosSet;
	}

	public static List<FuncionarioDTO> converterEntidadesListEmDTOsList(List<Funcionario> funcionariosList)
	{
		List<FuncionarioDTO> funcionariosDTOList = new ArrayList<FuncionarioDTO>();
		if(funcionariosList != null)
		{
			for(Funcionario funcionario : funcionariosList)
			{
				funcionariosDTOList.add(converterEntidadeEmDTOComId(funcionario));
			}
		}
		return funcionariosDTOList;
	}
}

package dto;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import constantes.EDiaSemana;


public class HorarioFuncionamentoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idHorarioFuncionamento;
	private Date horarioAbertura;
	private Date horarioFechamento;
	private EDiaSemana diaSemana;
	private Boolean ativo;
	private List<HorarioFuncionamentoDeliveryDTO> horarioFuncionamentoDeliveryDTOs = 
			new ArrayList<HorarioFuncionamentoDeliveryDTO>();
	private List<HorarioFuncionamentoEstabelecimentoDTO> horarioFuncionamentoEstabelecimentoDTOs = 
			new ArrayList<HorarioFuncionamentoEstabelecimentoDTO>();

	public HorarioFuncionamentoDTO() {
	}

	public HorarioFuncionamentoDTO(Date horarioAbertura, Date horarioFechamento,
			EDiaSemana diaSemana) {
		this.horarioAbertura = horarioAbertura;
		this.horarioFechamento = horarioFechamento;
		this.diaSemana = diaSemana;
	}

	public HorarioFuncionamentoDTO(
			Date horarioAbertura,
			Date horarioFechamento,
			EDiaSemana diaSemana,
			Boolean ativo,
			List<HorarioFuncionamentoDeliveryDTO> horarioFuncionamentoDeliveryDTOs,
			List<HorarioFuncionamentoEstabelecimentoDTO> horarioFuncionamentoEstabelecimentoDTOs) {
		this.horarioAbertura = horarioAbertura;
		this.horarioFechamento = horarioFechamento;
		this.diaSemana = diaSemana;
		this.ativo = ativo;
		this.horarioFuncionamentoDeliveryDTOs = horarioFuncionamentoDeliveryDTOs;
		this.horarioFuncionamentoEstabelecimentoDTOs = horarioFuncionamentoEstabelecimentoDTOs;
	}

	public Long getIdHorarioFuncionamento() {
		return this.idHorarioFuncionamento;
	}

	public void setIdHorarioFuncionamento(Long idHorarioFuncionamento) {
		this.idHorarioFuncionamento = idHorarioFuncionamento;
	}

	public Date getHorarioAbertura() {
		return this.horarioAbertura;
	}

	public void setHorarioAbertura(Date horarioAbertura) {
		this.horarioAbertura = horarioAbertura;
	}

	public Date getHorarioFechamento() {
		return this.horarioFechamento;
	}

	public void setHorarioFechamento(Date horarioFechamento) {
		this.horarioFechamento = horarioFechamento;
	}

	public EDiaSemana getDiaSemana() {
		return this.diaSemana;
	}

	public void setDiaSemana(EDiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<HorarioFuncionamentoDeliveryDTO> getHorarioFuncionamentoDeliveries() {
		return this.horarioFuncionamentoDeliveryDTOs;
	}

	public void setHorarioFuncionamentoDeliveries(
			List<HorarioFuncionamentoDeliveryDTO> horarioFuncionamentoDeliveryDTOs) {
		this.horarioFuncionamentoDeliveryDTOs = horarioFuncionamentoDeliveryDTOs;
	}

	public List<HorarioFuncionamentoEstabelecimentoDTO> getHorarioFuncionamentoEstabelecimentos() {
		return this.horarioFuncionamentoEstabelecimentoDTOs;
	}

	public void setHorarioFuncionamentoEstabelecimentos(
			List<HorarioFuncionamentoEstabelecimentoDTO> horarioFuncionamentoEstabelecimentoDTOs) {
		this.horarioFuncionamentoEstabelecimentoDTOs = horarioFuncionamentoEstabelecimentoDTOs;
	}

}

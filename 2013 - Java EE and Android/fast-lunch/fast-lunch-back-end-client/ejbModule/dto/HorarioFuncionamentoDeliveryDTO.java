package dto;


public class HorarioFuncionamentoDeliveryDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idHorarioFuncionamentoDelivery;
	private HorarioFuncionamentoDTO horarioFuncionamentoDTO;
	private Boolean disponivel;
	private EstabelecimentoDTO estabelecimentoDTO;

	public HorarioFuncionamentoDeliveryDTO() {
	}

	public HorarioFuncionamentoDeliveryDTO(
			HorarioFuncionamentoDTO horarioFuncionamentoDTO) {
		this.horarioFuncionamentoDTO = horarioFuncionamentoDTO;
	}

	public HorarioFuncionamentoDeliveryDTO(
			HorarioFuncionamentoDTO horarioFuncionamentoDTO, Boolean disponivel,
			EstabelecimentoDTO estabelecimentoDTO) {
		this.horarioFuncionamentoDTO = horarioFuncionamentoDTO;
		this.disponivel = disponivel;
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public Long getIdHorarioFuncionamentoDelivery() {
		return this.idHorarioFuncionamentoDelivery;
	}

	public void setIdHorarioFuncionamentoDelivery(
			Long idHorarioFuncionamentoDelivery) {
		this.idHorarioFuncionamentoDelivery = idHorarioFuncionamentoDelivery;
	}

	public HorarioFuncionamentoDTO getHorarioFuncionamento() {
		return this.horarioFuncionamentoDTO;
	}

	public void setHorarioFuncionamento(
			HorarioFuncionamentoDTO horarioFuncionamentoDTO) {
		this.horarioFuncionamentoDTO = horarioFuncionamentoDTO;
	}

	public Boolean getDisponivel() {
		return this.disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public EstabelecimentoDTO getEstabelecimentoDTO() {
		return estabelecimentoDTO;
	}

	public void setEstabelecimentoDTO(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

}

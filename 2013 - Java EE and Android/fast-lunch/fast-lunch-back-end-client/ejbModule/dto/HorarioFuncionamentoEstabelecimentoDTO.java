package dto;


public class HorarioFuncionamentoEstabelecimentoDTO implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idHorarioFuncionamentoEstabelecimento;
	private EstabelecimentoDTO estabelecimentoDTO;
	private HorarioFuncionamentoDTO horarioFuncionamentoDTO;
	private boolean disponivel;

	public HorarioFuncionamentoEstabelecimentoDTO() {
	}

	public HorarioFuncionamentoEstabelecimentoDTO(
			EstabelecimentoDTO estabelecimentoDTO,
			HorarioFuncionamentoDTO horarioFuncionamentoDTO, boolean disponivel) {
		this.estabelecimentoDTO = estabelecimentoDTO;
		this.horarioFuncionamentoDTO = horarioFuncionamentoDTO;
		this.disponivel = disponivel;
	}

	public Long getIdHorarioFuncionamentoEstabelecimento() {
		return this.idHorarioFuncionamentoEstabelecimento;
	}

	public void setIdHorarioFuncionamentoEstabelecimento(
			Long idHorarioFuncionamentoEstabelecimento) {
		this.idHorarioFuncionamentoEstabelecimento = idHorarioFuncionamentoEstabelecimento;
	}

	public EstabelecimentoDTO getEstabelecimento() {
		return this.estabelecimentoDTO;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public HorarioFuncionamentoDTO getHorarioFuncionamento() {
		return this.horarioFuncionamentoDTO;
	}

	public void setHorarioFuncionamento(
			HorarioFuncionamentoDTO horarioFuncionamentoDTO) {
		this.horarioFuncionamentoDTO = horarioFuncionamentoDTO;
	}

	public boolean isDisponivel() {
		return this.disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

}

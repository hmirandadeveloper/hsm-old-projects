package dto;

import java.util.ArrayList;
import java.util.List;

public class EstabelecimentoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idEstabelecimento;
	private EstabelecimentoDTO estabelecimentoDTO;
	private String razaoSocial;
	private String nomeFantasia;
	private String cnpj;
	private String telefone;
	private String email;
	private char tipoEstabelecimento;
	private Boolean ativo;
	private List<CargoDTO> cargoDTOs = new ArrayList<CargoDTO>();
	private List<CardapioDTO> tbCardapios = new ArrayList<CardapioDTO>();
	private List<EstabelecimentoDTO> estabelecimentoDTOs = 
			new ArrayList<EstabelecimentoDTO>();
	private List<HorarioFuncionamentoEstabelecimentoDTO> horarioFuncionamentoEstabelecimentoDTOs = 
			new ArrayList<HorarioFuncionamentoEstabelecimentoDTO>();
	private List<PedidoDTO> pedidoDTOs = new ArrayList<PedidoDTO>();
	private List<FreteDTO> freteDTOs = new ArrayList<FreteDTO>();
	private List<TipoPagamentoDTO> tipoPagamentoDTOs = 
			new ArrayList<TipoPagamentoDTO>();
	private List<HorarioFuncionamentoDeliveryDTO> horarioFuncionamentoDeliveryDTOs = 
			new ArrayList<HorarioFuncionamentoDeliveryDTO>();
	private List<TipoCardapioDTO> tipoCardapioDTOs = new ArrayList<TipoCardapioDTO>();
	private EnderecoDTO enderecoDTO;

	public EstabelecimentoDTO() {
	}

	public EstabelecimentoDTO(String razaoSocial, String cnpj, String telefone,
			String email, char tipoEstabelecimento) {
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
		this.tipoEstabelecimento = tipoEstabelecimento;
	}

	public EstabelecimentoDTO(
			EstabelecimentoDTO estabelecimentoDTO,
			String razaoSocial,
			String nomeFantasia,
			String cnpj,
			String telefone,
			String email,
			char tipoEstabelecimento,
			Boolean ativo,
			List<CargoDTO> cargoDTOs,
			List<CardapioDTO> tbCardapios,
			List<EstabelecimentoDTO> estabelecimentoDTOs,
			List<HorarioFuncionamentoEstabelecimentoDTO> horarioFuncionamentoEstabelecimentoDTOs,
			List<PedidoDTO> pedidoDTOs,
			List<FreteDTO> freteDTOs,
			List<TipoPagamentoDTO> tipoPagamentoDTOs,
			List<HorarioFuncionamentoDeliveryDTO> horarioFuncionamentoDeliveryDTOs,
			List<TipoCardapioDTO> tipoCardapioDTOs,
			EnderecoDTO enderecoDTO
			) {
		this.estabelecimentoDTO = estabelecimentoDTO;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
		this.tipoEstabelecimento = tipoEstabelecimento;
		this.ativo = ativo;
		this.cargoDTOs = cargoDTOs;
		this.tbCardapios = tbCardapios;
		this.estabelecimentoDTOs = estabelecimentoDTOs;
		this.horarioFuncionamentoEstabelecimentoDTOs = horarioFuncionamentoEstabelecimentoDTOs;
		this.pedidoDTOs = pedidoDTOs;
		this.freteDTOs = freteDTOs;
		this.tipoPagamentoDTOs = tipoPagamentoDTOs;
		this.horarioFuncionamentoDeliveryDTOs = horarioFuncionamentoDeliveryDTOs;
		this.tipoCardapioDTOs = tipoCardapioDTOs;
		this.enderecoDTO = enderecoDTO;
	}

	public Long getIdEstabelecimento() {
		return this.idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public EstabelecimentoDTO getEstabelecimento() {
		return this.estabelecimentoDTO;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimentoDTO) {
		this.estabelecimentoDTO = estabelecimentoDTO;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return this.nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getTipoEstabelecimento() {
		return this.tipoEstabelecimento;
	}

	public void setTipoEstabelecimento(char tipoEstabelecimento) {
		this.tipoEstabelecimento = tipoEstabelecimento;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<CargoDTO> getCargos() {
		return this.cargoDTOs;
	}

	public void setCargos(List<CargoDTO> cargoDTOs) {
		this.cargoDTOs = cargoDTOs;
	}

	public List<CardapioDTO> getCardapios() {
		return this.tbCardapios;
	}

	public void setCardapios(List<CardapioDTO> tbCardapios) {
		this.tbCardapios = tbCardapios;
	}

	public List<EstabelecimentoDTO> getEstabelecimentos() {
		return this.estabelecimentoDTOs;
	}

	public void setEstabelecimentos(List<EstabelecimentoDTO> estabelecimentoDTOs) {
		this.estabelecimentoDTOs = estabelecimentoDTOs;
	}

	public List<HorarioFuncionamentoEstabelecimentoDTO> getHorarioFuncionamentoEstabelecimentos() {
		return this.horarioFuncionamentoEstabelecimentoDTOs;
	}

	public void setHorarioFuncionamentoEstabelecimentos(
			List<HorarioFuncionamentoEstabelecimentoDTO> horarioFuncionamentoEstabelecimentoDTOs) {
		this.horarioFuncionamentoEstabelecimentoDTOs = horarioFuncionamentoEstabelecimentoDTOs;
	}

	public List<PedidoDTO> getPedidos() {
		return this.pedidoDTOs;
	}

	public void setPedidos(List<PedidoDTO> pedidoDTOs) {
		this.pedidoDTOs = pedidoDTOs;
	}

	public List<FreteDTO> getFreteDTOs() {
		return freteDTOs;
	}

	public void setFreteDTOs(List<FreteDTO> freteDTOs) {
		this.freteDTOs = freteDTOs;
	}

	public List<TipoPagamentoDTO> getTipoPagamentoDTOs() {
		return tipoPagamentoDTOs;
	}

	public void setTipoPagamentoDTOs(List<TipoPagamentoDTO> tipoPagamentoDTOs) {
		this.tipoPagamentoDTOs = tipoPagamentoDTOs;
	}

	public List<HorarioFuncionamentoDeliveryDTO> getHorarioFuncionamentoDeliveryDTOs() {
		return horarioFuncionamentoDeliveryDTOs;
	}

	public void setHorarioFuncionamentoDeliveryDTOs(
			List<HorarioFuncionamentoDeliveryDTO> horarioFuncionamentoDeliveryDTOs) {
		this.horarioFuncionamentoDeliveryDTOs = horarioFuncionamentoDeliveryDTOs;
	}

	public List<TipoCardapioDTO> getTipoCardapioDTOs() {
		return tipoCardapioDTOs;
	}

	public void setTipoCardapioDTOs(List<TipoCardapioDTO> tipoCardapioDTOs) {
		this.tipoCardapioDTOs = tipoCardapioDTOs;
	}

	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}

	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

}

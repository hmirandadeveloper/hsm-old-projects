package dto;

import java.util.Date;

public class FidelidadeTrocaDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idFidelidadeTroca;
	private ProdutoDTO produtoDTO;
	private ClienteDTO clienteDTO;
	private Date dataHora;
	private int pontos;

	public FidelidadeTrocaDTO() {
	}

	public FidelidadeTrocaDTO(ProdutoDTO produtoDTO, ClienteDTO clienteDTO) {
		this.produtoDTO = produtoDTO;
		this.clienteDTO = clienteDTO;
	}

	public FidelidadeTrocaDTO(ProdutoDTO produtoDTO, ClienteDTO clienteDTO,
			Date dataHora, int pontos) {
		this.produtoDTO = produtoDTO;
		this.clienteDTO = clienteDTO;
		this.dataHora = dataHora;
		this.pontos = pontos;
	}

	public Long getIdFidelidadeTroca() {
		return this.idFidelidadeTroca;
	}

	public void setIdFidelidadeTroca(Long idFidelidadeTroca) {
		this.idFidelidadeTroca = idFidelidadeTroca;
	}

	public ProdutoDTO getProduto() {
		return this.produtoDTO;
	}

	public void setProduto(ProdutoDTO produtoDTO) {
		this.produtoDTO = produtoDTO;
	}

	public ClienteDTO getCliente() {
		return this.clienteDTO;
	}

	public void setCliente(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public Date getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

}

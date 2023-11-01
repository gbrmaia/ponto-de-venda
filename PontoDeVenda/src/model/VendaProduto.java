package model;

import java.sql.Date;

public class VendaProduto extends Produto {
	
	private Date ultimaVenda;
	private String unidade;
		

	public VendaProduto(String id, String codigo, String nome, String preco, String quantidadeEstoque, Date ultimaVenda, String unidade) {
		super(id, codigo, nome, preco, quantidadeEstoque);
		this.ultimaVenda = ultimaVenda;
		this.unidade = unidade;
		
	}


	public Date getUltimaVenda() {
		return ultimaVenda;
	}


	public void setUltimaVenda(Date ultimaVenda) {
		this.ultimaVenda = ultimaVenda;
	}


	public String getUnidade() {
		return unidade;
	}


	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	
	

}

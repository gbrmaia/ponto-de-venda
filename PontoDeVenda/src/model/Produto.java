package model;

import java.util.Date;

public class Produto {
	private String id;
	private String codigo;
	private String nome;
	private String preco;
	private String quantidadeEstoque;

	public Produto(String id, String codigo, String nome, String preco, String quantidadeEstoque) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getPreco() {
		return preco;
	}

	public String getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public void setQuantidadeEmEstoque(String quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

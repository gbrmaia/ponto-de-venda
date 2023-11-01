package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{

	
	public static final String[ ] colunas = {
			"ID", "Codigo", "Nome", "Preco", "Estoque"
			};
	
	
	public ModeloTabela(ArrayList<Produto> produtos) {
		super();
		this.produtos = produtos;
	}

	private ArrayList<Produto> produtos;
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return produtos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Produto produto = produtos.get(rowIndex);
		if(columnIndex == 0) {
			return produto.getId();	
		} else if(columnIndex == 1) {
			return produto.getCodigo();			
		} else if(columnIndex == 2) {
			return produto.getNome();			
		} else if(columnIndex == 3) {
			return produto.getPreco();			
		} else if(columnIndex == 4) {
			return produto.getQuantidadeEstoque();			
		} 
		return null;
	}
	
	public String getColumnName(int column) {
		return colunas[column];
	}

}

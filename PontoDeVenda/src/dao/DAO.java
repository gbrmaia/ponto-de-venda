package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Produto;

public class DAO {

	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private static String CADASTRAR_PRODUTO = " INSERT INTO PRODUTO "
			+ " ( ID , CODIGO, NOME, PRECO, QUANTIDADEESTOQUE ) " + " VALUES (NULL, ?, ?, ?, ?) ";
	
	private static String CONSULTAR_PRODUTO = " SELECT * FROM PRODUTO" + " WHERE ID = ? ";
	
	private static String ALTERAR_PRODUTO = " UPDATE PRODUTO SET " + 
			" NOME = ?, PRECO = ?, QUANTIDADEESTOQUE = ? " + " WHERE ID = ? ";
	
	private static String EXCLUIR_PRODUTO = " DELETE FROM PRODUTO " + " WHERE ID = ? ";
	
	private static String LISTAR_PRODUTO = " SELECT * FROM PRODUTO" + " WHERE 1=1 ";
	
	public DAO() {
		
	}
	
	
	public void cadastrarProduto(Produto produto) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		String query = CADASTRAR_PRODUTO;
		
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			preparedStatement.setString(i++, produto.getCodigo());
			preparedStatement.setString(i++, produto.getNome());
			preparedStatement.setString(i++, produto.getPreco());
			preparedStatement.setString(i++, produto.getQuantidadeEstoque());
			
			preparedStatement.execute();
			connection.commit();

			JOptionPane.showMessageDialog(null, "Produto cadastrado.");
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		
		
	}
	
	public Produto consultarProduto(String id) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		Produto produto = null;
		String query = CONSULTAR_PRODUTO;

		try {
			preparedStatement = connection.prepareStatement(query);
			int i = 1;
			preparedStatement.setString(i++, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				produto = new Produto(resultSet.getString("id"), resultSet.getString("codigo"),
						resultSet.getString("nome"), resultSet.getString("preco"), resultSet.getString("quantidadeEstoque"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if (produto == null) {
			JOptionPane.showMessageDialog(null, "Não foi possivel localizar.", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não foi possivel localizar.");
		}
		return produto;

	}
	
	public void alterarProduto(String id, Produto produto) {
		Connection connection = Conexao.getInstancia().abrirConexao();

		String query = ALTERAR_PRODUTO;

		try {
			preparedStatement = connection.prepareStatement(query);
			int i = 1;
			preparedStatement.setString(i++, produto.getCodigo());
			preparedStatement.setString(i++, produto.getNome());
			preparedStatement.setString(i++, produto.getPreco());
			preparedStatement.setString(i++, produto.getQuantidadeEstoque());
			preparedStatement.setString(i++, id);

			preparedStatement.execute();
			connection.commit();

			JOptionPane.showMessageDialog(null, "Produto alterado.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

	}
	
	
	public void excluirProduto(String id) {
		Connection connection = Conexao.getInstancia().abrirConexao();

		String query = EXCLUIR_PRODUTO;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id); 
			preparedStatement.executeUpdate(); 
			
			connection.commit();

			JOptionPane.showMessageDialog(null, "Produto removido.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

	}
	

	public ArrayList<Produto> listarProduto() throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		ArrayList<Produto> produtos = new ArrayList<>();
		String query = LISTAR_PRODUTO;

		try {
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				produtos.add(new Produto(resultSet.getString("id"), resultSet.getString("codigo"),
						resultSet.getString("nome"), resultSet.getString("preco"), resultSet.getString("quantidadeEstoque")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fecharConexao();
		} if(produtos.size() == 0) {
			JOptionPane.showMessageDialog(null, "Não há produtos cadastrados.", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não há produtos produtos.");
		}
		return produtos;

	}


	private void fecharConexao() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			Conexao.getInstancia().fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	
	
	
}

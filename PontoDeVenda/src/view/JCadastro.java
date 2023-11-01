package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAO;
import model.Produto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCodigo;
	private JTextField textFieldValor;
	private JTextField textFieldEstoque;
	private JPrincipal jPrincipal;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null, null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JCadastro(Produto produtoSelecionado, JPrincipal jPrincipal) throws SQLException{
		this.jPrincipal = jPrincipal;
		DAO dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 254, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do Produto: ");
		lblNewLabel.setBounds(10, 11, 124, 14);
		contentPane.add(lblNewLabel);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 25, 218, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblCdigoDeProduto = new JLabel("Código de Produto: ");
		lblCdigoDeProduto.setBounds(10, 56, 124, 14);
		contentPane.add(lblCdigoDeProduto);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(10, 69, 218, 20);
		textFieldCodigo.setColumns(10);
		contentPane.add(textFieldCodigo);
		
		JLabel lblValorDoProduto = new JLabel("Valor do Produto: ");
		lblValorDoProduto.setBounds(10, 105, 124, 14);
		contentPane.add(lblValorDoProduto);
		
		textFieldValor = new JTextField();
		textFieldValor.setBounds(10, 119, 218, 20);
		textFieldValor.setColumns(10);
		contentPane.add(textFieldValor);
		
		JLabel lblQuantidadeEmEstoque = new JLabel("Quantidade em Estoque:");
		lblQuantidadeEmEstoque.setBounds(10, 154, 156, 14);
		contentPane.add(lblQuantidadeEmEstoque);
		
		textFieldEstoque = new JTextField();
		textFieldEstoque.setBounds(10, 168, 218, 20);
		textFieldEstoque.setColumns(10);
		contentPane.add(textFieldEstoque);
		
		JButton btnNewButton = new JButton(produtoSelecionado == null ? "Cadastrar" : "Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto produto = new Produto( null,  textFieldCodigo.getText(), textFieldNome.getText(), 
						textFieldValor.getText(), textFieldEstoque.getText());
				if (produtoSelecionado == null) {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCodigo.getText())){
						dao.cadastrarProduto(produto);
						abrirTelaPrincipal(jPrincipal);
					} else {
						JOptionPane.showMessageDialog(null, "Confira os dados Nome/Codigo.");
					}
				
				} else {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCodigo.getText())){
						dao.alterarProduto(produtoSelecionado.getId(), produto);
						abrirTelaPrincipal(jPrincipal);
					} else {
						JOptionPane.showMessageDialog(null, "Confira os dados Nome/Codigo.");
					}
					dao.alterarProduto(produtoSelecionado.getId(), produto);
					
				}
				
				
			}
		});
		btnNewButton.setBounds(139, 199, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setForeground(new Color(255, 255, 255));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dao.excluirProduto(produtoSelecionado.getId());
				abrirTelaPrincipal(jPrincipal);
				
				
			}
		});
		btnExcluir.setBackground(new Color(255, 0, 0));
		btnExcluir.setBounds(10, 199, 89, 23);
		btnExcluir.setVisible(false);
		contentPane.add(btnExcluir);
		
		

		if (produtoSelecionado != null) {
			preencherCampos(produtoSelecionado);
			btnExcluir.setVisible(true);

		}}
	
	
		
		
	private void preencherCampos(Produto produtoSelecionado) {
			textFieldCodigo.setText(produtoSelecionado.getCodigo());
			textFieldNome.setText(produtoSelecionado.getNome());
			textFieldValor.setText(produtoSelecionado.getPreco()); 
			textFieldEstoque.setText(produtoSelecionado.getQuantidadeEstoque());
	
		}	
	
	
	private void abrirTelaPrincipal(JPrincipal jPrincipal) {
		 jPrincipal.dispose();
		 dispose();
		 try {
			jPrincipal = new JPrincipal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 jPrincipal.setLocationRelativeTo(jPrincipal);
		 jPrincipal.setVisible(true);
	 }
	}
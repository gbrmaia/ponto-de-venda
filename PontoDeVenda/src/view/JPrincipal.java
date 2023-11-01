package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import dao.DAO;
import model.ModeloTabela;
import model.Produto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;

public class JPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPrincipal jPrincipal;
	private JTable table;
	private TableRowSorter<ModeloTabela> rowSorter;
	private ArrayList<Produto> produtos;
	private JTextField textFieldBusca;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
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
	public JPrincipal() throws SQLException {
		this.jPrincipal = this;
		DAO dao = new DAO();
		try {

			produtos = dao.listarProduto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar Produto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCadastro jCadastro;
				try {
					jCadastro = new JCadastro(null, jPrincipal);
					jCadastro.setLocationRelativeTo(jCadastro);
					jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					jCadastro.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(22, 22, 156, 37);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 103, 844, 388);
		contentPane.add(scrollPane);
		
		ModeloTabela modeloTabela = new ModeloTabela(produtos);
		
		table = new JTable(modeloTabela);
		table.setModel(modeloTabela);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) {
					try {
						Produto produtoSelecionado = dao.consultarProduto(modeloTabela.getValueAt(table.getSelectedRow(), 0).toString());
						JCadastro jCadastro = new JCadastro(produtoSelecionado, jPrincipal);
						jCadastro.setLocationRelativeTo(jCadastro);
						jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						jCadastro.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		rowSorter = new TableRowSorter<>(modeloTabela);
		table.setRowSorter(rowSorter);
		scrollPane.setViewportView(table);
		
		textFieldBusca = new JTextField();
		textFieldBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrar();
			}

		});
		textFieldBusca.setBounds(371, 26, 495, 29);
		contentPane.add(textFieldBusca);
		textFieldBusca.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Lista Produtos");
		lblNewLabel.setBounds(22, 78, 91, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar Produto");
		lblNewLabel_1.setBounds(277, 33, 114, 14);
		contentPane.add(lblNewLabel_1);
		
			
		
	}
	
	private void filtrar() {
		String busca = textFieldBusca.getText().trim();
		if (busca.length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + busca));
		}
	}
}

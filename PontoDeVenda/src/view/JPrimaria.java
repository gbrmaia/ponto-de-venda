package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JPrimaria extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrimaria frame = new JPrimaria();
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
	public JPrimaria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			JPrincipal jPrincipal;

			public void actionPerformed(ActionEvent e) {
				try {
					jPrincipal = new JPrincipal();
					jPrincipal.setLocationRelativeTo(jPrincipal);
					jPrincipal.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					jPrincipal.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
							}
		});
		btnNewButton.setBounds(112, 51, 181, 41);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Consultar / Cadastrar Produtos");
		lblNewLabel.setBounds(116, 26, 181, 14);
		contentPane.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(337, 51, 181, 41);
		contentPane.add(btnNewButton_1);

		JLabel lblConsultarCadastrar = new JLabel("Consultar / Cadastrar Vendas");
		lblConsultarCadastrar.setBounds(341, 26, 181, 14);
		contentPane.add(lblConsultarCadastrar);
	}
}

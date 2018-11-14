package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author Madson Paulo Alexandre da Silva
 *
 *         JFrame que permite a alteração do nome de uma disciplina
 */
public class AlterarNomeDisciplina extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> disciplinas;
	private JTextField nome;
	Preferences prefs = Preferences.userRoot().node(Main.CONFIGS);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarNomeDisciplina frame = new AlterarNomeDisciplina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Atualiza o nome das disciplinas na respectiva JComboBox
	 */
	private void atualizarDisciplinas() {
		disciplinas.setModel(new DefaultComboBoxModel<>(Main.getNomeDisciplinas()));
	}

	public AlterarNomeDisciplina() {
		setTitle("Alterar Nome de Disciplina");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 76);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setMinimumSize(new Dimension(10, 30));
		panel.setPreferredSize(new Dimension(10, 30));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nome.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Novo nome da precisa ser preenchido.", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {
					prefs.put(String.format("NOME%d", disciplinas.getSelectedIndex() + 1), nome.getText());
					JOptionPane.showMessageDialog(null, "Nome alterado com sucesso.", "Atenção",
							JOptionPane.INFORMATION_MESSAGE);
					atualizarDisciplinas();
				}
			}
		});
		btnAlterar.setFocusable(false);
		btnAlterar.setBounds(679, 5, 90, 20);
		panel.add(btnAlterar);

		disciplinas = new JComboBox<String>();
		disciplinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nome.setText(disciplinas.getSelectedItem().toString());
			}
		});
		disciplinas.setBounds(3, 5, 230, 20);
		disciplinas.setModel(new DefaultComboBoxModel<>(Main.getNomeDisciplinas()));
		panel.add(disciplinas);

		nome = new JTextField();
		nome.setText(disciplinas.getSelectedItem().toString());
		nome.setHorizontalAlignment(SwingConstants.LEFT);
		nome.setFont(new Font("Times New Roman", Font.BOLD, 14));
		nome.setColumns(10);
		nome.setBounds(236, 5, 440, 20);
		panel.add(nome);

	}
}

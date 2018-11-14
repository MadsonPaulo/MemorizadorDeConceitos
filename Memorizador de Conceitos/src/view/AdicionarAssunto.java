package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author Madson Paulo Alexandre da Silva
 * 
 *         JFrame que permite que novos assuntos sejam adicionados para disciplinas específicas
 *
 */
public class AdicionarAssunto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField assunto;
	private JTextArea conteudo;
	private JComboBox<String> disciplinas;
	Preferences prefs = Preferences.userRoot().node(Main.CONFIGS);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarAssunto frame = new AdicionarAssunto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdicionarAssunto() {
		setTitle("Adicionar Assunto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 470);
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

		assunto = new JTextField();
		assunto.setToolTipText("");
		assunto.setHorizontalAlignment(SwingConstants.LEFT);
		assunto.setFont(new Font("Times New Roman", Font.BOLD, 14));
		assunto.setBounds(236, 5, 440, 20);
		panel.add(assunto);
		assunto.setColumns(10);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (conteudo.getText().length() == 0 || assunto.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Assunto e conteúdo precisam ser preenchidos.", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else if (assunto.getText().length() > 80) {
					JOptionPane.showMessageDialog(null, String.format(
							"Assunto está muito longo. O tamanho máximo são 80 caracteres e atualmente possui %d.",
							assunto.getText().length()), "Atenção", JOptionPane.WARNING_MESSAGE);
				} else if (conteudo.getText().length() > 8192) {
					JOptionPane.showMessageDialog(null, String.format(
							"Conteúdo está muito longo. O tamanho máximo são 8192 caracteres e atualmente possui %d.",
							conteudo.getText().length()), "Atenção", JOptionPane.WARNING_MESSAGE);
				} else {
					prefs = Preferences.userRoot().node(Main.ARRAY_DISCIPLINAS[disciplinas.getSelectedIndex()]);

					prefs.put(assunto.getText(), conteudo.getText());
					JOptionPane.showMessageDialog(null, "Assunto adicionado com sucesso.", "Atenção",
							JOptionPane.INFORMATION_MESSAGE);
					assunto.setText("");
					conteudo.setText("");

					prefs = Preferences.userRoot().node(Main.CONFIGS);
					Main.atualizarListaTopicos();
					Main.atualizarAssuntoConteudo();
					Main.atualizarMarcadorPosicao();
				}

			}
		});
		btnAdicionar.setFocusable(false);
		btnAdicionar.setBounds(679, 5, 90, 20);
		panel.add(btnAdicionar);

		disciplinas = new JComboBox<String>();
		disciplinas.setBounds(3, 5, 230, 20);
		disciplinas.setModel(new DefaultComboBoxModel<>(Main.getNomeDisciplinas()));
		panel.add(disciplinas);

		conteudo = new JTextArea();
		conteudo.setTabSize(2);
		conteudo.setToolTipText("");
		conteudo.setBackground(SystemColor.text);
		conteudo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		conteudo.setWrapStyleWord(true);
		conteudo.setLineWrap(true);
		conteudo.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(5, 15, 5, 15)));

		JScrollPane scrollPane = new JScrollPane(conteudo);
		scrollPane.setFocusable(false);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}
}
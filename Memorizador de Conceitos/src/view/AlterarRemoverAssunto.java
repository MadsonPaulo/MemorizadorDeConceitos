package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author Madson Paulo Alexandre da Silva
 *
 *         JFrame que permite alterar ou remover assuntos das disciplinas
 */
public class AlterarRemoverAssunto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea conteudo;
	private JComboBox<String> assuntos;
	Preferences prefs = Preferences.userRoot().node(Main.CONFIGS);
	private JComboBox<String> disciplinas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlterarRemoverAssunto frame = new AlterarRemoverAssunto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Atualiza a lista de assuntoss da disciplina selecionada, além de preencher o conteúdo de acordo com o assunto escolhido
	 */
	private void preencherCampos() {
		try {
			prefs = Preferences.userRoot().node(Main.ARRAY_DISCIPLINAS[disciplinas.getSelectedIndex()]);

			String[] keys = prefs.keys();
			Arrays.sort(keys);
			if (keys.length > 0) {
				assuntos.setModel(new DefaultComboBoxModel<>(keys));
				conteudo.setText(prefs.get(assuntos.getSelectedItem().toString(), ""));
			} else {
				assuntos.setModel(new DefaultComboBoxModel<>());
				conteudo.setText("");
			}

			prefs = Preferences.userRoot().node(Main.CONFIGS);
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	public AlterarRemoverAssunto() {
		setTitle("Alterar/Remover Assunto");
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

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs = Preferences.userRoot().node(Main.ARRAY_DISCIPLINAS[disciplinas.getSelectedIndex()]);

				if (conteudo.getText().length() > 0) {
					prefs.put(assuntos.getSelectedItem().toString(), conteudo.getText());
					JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso.", "Atenção",
							JOptionPane.INFORMATION_MESSAGE);

					Main.atualizarListaTopicos();
					Main.atualizarAssuntoConteudo();
					Main.atualizarMarcadorPosicao();
				} else {
					JOptionPane.showMessageDialog(null, "Conteúdo não pode ser vazio. A alteração não foi realizada.",
							"Atenção", JOptionPane.WARNING_MESSAGE);
				}

				prefs = Preferences.userRoot().node(Main.CONFIGS);
			}
		});
		btnAlterar.setFocusable(false);
		btnAlterar.setBounds(582, 5, 90, 20);
		panel.add(btnAlterar);

		assuntos = new JComboBox<String>();
		assuntos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					prefs = Preferences.userRoot().node(Main.ARRAY_DISCIPLINAS[disciplinas.getSelectedIndex()]);
					if (prefs.keys().length > 0) {
						conteudo.setText(prefs.get(assuntos.getSelectedItem().toString(), ""));
					}

					prefs = Preferences.userRoot().node(Main.CONFIGS);
				} catch (BackingStoreException e) {
					e.printStackTrace();
				}
			}
		});
		assuntos.setBounds(242, 5, 334, 20);
		panel.add(assuntos);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs = Preferences.userRoot().node(Main.ARRAY_DISCIPLINAS[disciplinas.getSelectedIndex()]);

				if (JOptionPane.showConfirmDialog(null,
						String.format("Tem certeza que deseja remover o assunto '%s', da disciplina '%s'?",
								assuntos.getSelectedItem().toString(), disciplinas.getSelectedItem().toString()),
						"Atenção", JOptionPane.YES_NO_OPTION) == 0) {

					prefs.remove(assuntos.getSelectedItem().toString());
					preencherCampos();

					JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso.", "Atenção",
							JOptionPane.INFORMATION_MESSAGE);
					Main.atualizarListaTopicos();
					Main.atualizarAssuntoConteudo();
					Main.atualizarMarcadorPosicao();
				}

				prefs = Preferences.userRoot().node(Main.CONFIGS);
			}
		});
		btnRemover.setFocusable(false);
		btnRemover.setBounds(678, 5, 90, 20);
		panel.add(btnRemover);

		disciplinas = new JComboBox<String>();
		disciplinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preencherCampos();
			}
		});
		disciplinas.setBounds(6, 5, 230, 20);
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

		preencherCampos();
	}
}
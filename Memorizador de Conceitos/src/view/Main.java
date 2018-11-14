package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author Madson Paulo Alexandre da Silva
 * 
 *         JFrame principal, permite abrir os outros, além de apresentar a lista de assuntos de todas as disciplinas selecionadas,
 *         em ordem aleatória. Usuário pode avançar e retornar assuntos, além de poder mostrar ou esconder o conteúdo,
 *         visualizando somente o assunto
 *
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Random random = new Random();
	private static JLabel lblPosicao;
	private static JTextArea txtArea;
	static int pos = 0;
	public static final String CONFIGS = "/ConceitosConcursos";
	public static final String DISCIPLINA1 = CONFIGS + "/Disciplina1";
	public static final String DISCIPLINA2 = CONFIGS + "/Disciplina2";
	public static final String DISCIPLINA3 = CONFIGS + "/Disciplina3";
	public static final String DISCIPLINA4 = CONFIGS + "/Disciplina4";
	public static final String DISCIPLINA5 = CONFIGS + "/Disciplina5";
	public static final String DISCIPLINA6 = CONFIGS + "/Disciplina6";
	public static final String DISCIPLINA7 = CONFIGS + "/Disciplina7";
	public static final String DISCIPLINA8 = CONFIGS + "/Disciplina8";
	public static final String DISCIPLINA9 = CONFIGS + "/Disciplina9";
	public static final String DISCIPLINA10 = CONFIGS + "/Disciplina10";
	public static final String DISCIPLINA11 = CONFIGS + "/Disciplina11";
	public static final String DISCIPLINA12 = CONFIGS + "/Disciplina12";
	public static final String DISCIPLINA13 = CONFIGS + "/Disciplina13";
	public static final String DISCIPLINA14 = CONFIGS + "/Disciplina14";
	public static final String DISCIPLINA15 = CONFIGS + "/Disciplina15";
	public static final String[] ARRAY_DISCIPLINAS = new String[] { DISCIPLINA1, DISCIPLINA2, DISCIPLINA3, DISCIPLINA4,
			DISCIPLINA5, DISCIPLINA6, DISCIPLINA7, DISCIPLINA8, DISCIPLINA9, DISCIPLINA10, DISCIPLINA11, DISCIPLINA12,
			DISCIPLINA13, DISCIPLINA14, DISCIPLINA15 };
	private static String[][] listaTopicos;
	private JPanel panel;
	private JButton btnMostrar;
	private JCheckBox chckbxManter;
	private static JTextField txtAssunto;
	private JMenuBar menuBar;
	private JMenu mnArquivo;
	private JMenu mnAjuda;
	private JMenu mnUtilitrios;
	private JMenuItem mntmCalculadoraDeNota;
	private JMenuItem mntmAdicionarAssunto;
	private JMenuItem mntmAlterarremoverAssunto;
	private JMenuItem mntmImportarAssuntos;
	private JMenuItem mntmExportarAssuntos;
	private JMenuItem mntmSobre;
	private JMenu mnDisciplinas;
	private static JCheckBoxMenuItem d1 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d2 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d3 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d4 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d5 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d6 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d7 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d8 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d9 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d10 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d11 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d12 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d13 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d14 = new JCheckBoxMenuItem();
	private static JCheckBoxMenuItem d15 = new JCheckBoxMenuItem();
	private JMenu mnAssuntos;
	private JMenuItem mntmAlterarNome;
	public static Preferences prefs = Preferences.userRoot().node(CONFIGS);
	private JMenuItem mntmExportarContedoEm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @return Array com o nome de todas as disciplinas
	 */
	public static String[] getNomeDisciplinas() {
		return new String[] { prefs.get("NOME1", "Disciplina 01"), prefs.get("NOME2", "Disciplina 02"),
				prefs.get("NOME3", "Disciplina 03"), prefs.get("NOME4", "Disciplina 04"),
				prefs.get("NOME5", "Disciplina 05"), prefs.get("NOME6", "Disciplina 06"),
				prefs.get("NOME7", "Disciplina 07"), prefs.get("NOME8", "Disciplina 08"),
				prefs.get("NOME9", "Disciplina 09"), prefs.get("NOME10", "Disciplina 10"),
				prefs.get("NOME11", "Disciplina 11"), prefs.get("NOME12", "Disciplina 12"),
				prefs.get("NOME13", "Disciplina 13"), prefs.get("NOME14", "Disciplina 14"),
				prefs.get("NOME15", "Disciplina 15") };
	}

	/**
	 * Atualiza a lista de assuntos que são exibidos na tela principal
	 */
	public static void atualizarListaTopicos() {
		try {
			int topicos = 0;
			for (int i = 0; i < 15; i++) {
				if (prefs.getBoolean(String.format("DISCIPLINA%d", i + 1), false)) {
					prefs = Preferences.userRoot().node(Main.ARRAY_DISCIPLINAS[i]);
					topicos += prefs.keys().length;
					prefs = Preferences.userRoot().node(Main.CONFIGS);
				}
			}

			prefs = Preferences.userRoot().node(Main.CONFIGS);
			listaTopicos = new String[topicos][2];
			int pos = 0;
			for (int i = 0; i < 15; i++) {
				if (prefs.getBoolean(String.format("DISCIPLINA%d", i + 1), false)) {
					prefs = Preferences.userRoot().node(Main.ARRAY_DISCIPLINAS[i]);

					for (String key : prefs.keys()) {
						listaTopicos[pos][0] = key;
						listaTopicos[pos][1] = prefs.get(key, "");
						pos++;
					}

					prefs = Preferences.userRoot().node(Main.CONFIGS);
				}
			}
			if (Main.pos >= topicos) {
				Main.pos = 0;
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Atualiza a lista de topicos, o indicador da posição e quantidade total de assuntos e asssunto/conteúdo a ser exibido
	 */
	private static void atualizarInterface() {
		atualizarListaTopicos();
		atualizarMarcadorPosicao();
		atualizarAssuntoConteudo();
	}

	/**
	 * Atualiza o indicador de posição atual e quantidade total de assuntos
	 */
	public static void atualizarMarcadorPosicao() {
		if (listaTopicos.length == 0) {
			lblPosicao.setText("0 / 0");
		} else {
			lblPosicao.setText((pos + 1) + " / " + listaTopicos.length);
		}
	}

	/**
	 * Embaralha os assuntos
	 */
	private void embaralharTopicos() {
		String topico;
		String conteudo;
		int nPos;
		if (listaTopicos.length > 1) {
			for (int i = 0; i < listaTopicos.length; i++) {
				nPos = random.nextInt(listaTopicos.length);
				topico = listaTopicos[i][0];
				conteudo = listaTopicos[i][1];

				listaTopicos[i][0] = listaTopicos[nPos][0];
				listaTopicos[i][1] = listaTopicos[nPos][1];

				listaTopicos[nPos][0] = topico;
				listaTopicos[nPos][1] = conteudo;
			}
		}
	}

	/**
	 * Se existir algum assunto a ser exibido, exibe-o
	 */
	public static void atualizarAssuntoConteudo() {
		if (listaTopicos.length > 0) {
			txtAssunto.setText(listaTopicos[pos][0]);
			txtArea.setText(listaTopicos[pos][1]);
		} else {
			txtAssunto.setText("");
			txtArea.setText("");
		}
	}

	public Main() {
		setTitle("Revisor de Assuntos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 860, 540);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		mntmImportarAssuntos = new JMenuItem("Importar Disciplinas e Assuntos");
		mntmImportarAssuntos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Funcionalidade ainda não implementada.", "Atenção",
						JOptionPane.WARNING_MESSAGE);
			}
		});
		mnArquivo.add(mntmImportarAssuntos);

		mntmExportarAssuntos = new JMenuItem("Exportar Disciplinas e Assuntos");
		mntmExportarAssuntos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Funcionalidade ainda não implementada.", "Atenção",
						JOptionPane.WARNING_MESSAGE);
			}
		});
		mnArquivo.add(mntmExportarAssuntos);

		mntmExportarContedoEm = new JMenuItem("Exportar Conte\u00FAdo em .pdf");
		mntmExportarContedoEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExportarPDF exportarPDF = new ExportarPDF();
				exportarPDF.setVisible(true);
			}
		});
		mnArquivo.add(mntmExportarContedoEm);

		mnDisciplinas = new JMenu("Disciplinas");
		menuBar.add(mnDisciplinas);

		d1 = new JCheckBoxMenuItem(prefs.get("NOME1", "Disciplina 1"));
		d1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefs.putBoolean("DISCIPLINA1", d1.isSelected());
				atualizarInterface();
			}
		});
		d1.setSelected(prefs.getBoolean("DISCIPLINA1", false));
		mnDisciplinas.add(d1);

		d2 = new JCheckBoxMenuItem(prefs.get("NOME2", "Disciplina 02"));
		d2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA2", d2.isSelected());
				atualizarInterface();
			}
		});
		d2.setSelected(prefs.getBoolean("DISCIPLINA2", false));
		mnDisciplinas.add(d2);

		d3 = new JCheckBoxMenuItem(prefs.get("NOME3", "Disciplina 03"));
		d3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA3", d3.isSelected());
				atualizarInterface();
			}
		});
		d3.setSelected(prefs.getBoolean("DISCIPLINA3", false));
		mnDisciplinas.add(d3);

		d4 = new JCheckBoxMenuItem(prefs.get("NOME4", "Disciplina 04"));
		d4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA4", d4.isSelected());
				atualizarInterface();
			}
		});
		d4.setSelected(prefs.getBoolean("DISCIPLINA4", false));
		mnDisciplinas.add(d4);

		d5 = new JCheckBoxMenuItem(prefs.get("NOME5", "Disciplina 05"));
		d5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefs.putBoolean("DISCIPLINA5", d5.isSelected());
				atualizarInterface();
			}
		});
		d5.setSelected(prefs.getBoolean("DISCIPLINA5", false));
		mnDisciplinas.add(d5);

		d6 = new JCheckBoxMenuItem(prefs.get("NOME6", "Disciplina 06"));
		d6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA6", d6.isSelected());
				atualizarInterface();
			}
		});
		d6.setSelected(prefs.getBoolean("DISCIPLINA6", false));
		mnDisciplinas.add(d6);

		d7 = new JCheckBoxMenuItem(prefs.get("NOME7", "Disciplina 07"));
		d7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA7", d7.isSelected());
				atualizarInterface();
			}
		});
		d7.setSelected(prefs.getBoolean("DISCIPLINA7", false));
		mnDisciplinas.add(d7);

		d8 = new JCheckBoxMenuItem(prefs.get("NOME8", "Disciplina 08"));
		d8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA8", d8.isSelected());
				atualizarInterface();
			}
		});
		d8.setSelected(prefs.getBoolean("DISCIPLINA8", false));
		mnDisciplinas.add(d8);

		d9 = new JCheckBoxMenuItem(prefs.get("NOME9", "Disciplina 09"));
		d9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA9", d9.isSelected());
				atualizarInterface();
			}
		});
		d9.setSelected(prefs.getBoolean("DISCIPLINA9", false));
		mnDisciplinas.add(d9);

		d10 = new JCheckBoxMenuItem(prefs.get("NOME10", "Disciplina 10"));
		d10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA10", d10.isSelected());
				atualizarInterface();
			}
		});
		d10.setSelected(prefs.getBoolean("DISCIPLINA10", false));
		mnDisciplinas.add(d10);

		d11 = new JCheckBoxMenuItem(prefs.get("NOME11", "Disciplina 11"));
		d11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA11", d11.isSelected());
				atualizarInterface();
			}
		});
		d11.setSelected(prefs.getBoolean("DISCIPLINA11", false));
		mnDisciplinas.add(d11);

		d12 = new JCheckBoxMenuItem(prefs.get("NOME12", "Disciplina 12"));
		d12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA12", d12.isSelected());
				atualizarInterface();
			}
		});
		d12.setSelected(prefs.getBoolean("DISCIPLINA12", false));
		mnDisciplinas.add(d12);

		d13 = new JCheckBoxMenuItem(prefs.get("NOME13", "Disciplina 13"));
		d13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA13", d13.isSelected());
				atualizarInterface();
			}
		});
		d13.setSelected(prefs.getBoolean("DISCIPLINA13", false));
		mnDisciplinas.add(d13);

		d14 = new JCheckBoxMenuItem(prefs.get("NOME14", "Disciplina 14"));
		d14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA14", d14.isSelected());
				atualizarInterface();
			}
		});
		d14.setSelected(prefs.getBoolean("DISCIPLINA14", false));
		mnDisciplinas.add(d14);

		d15 = new JCheckBoxMenuItem(prefs.get("NOME15", "Disciplina 15"));
		d15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA15", d15.isSelected());
				atualizarInterface();
			}
		});
		d15.setSelected(prefs.getBoolean("DISCIPLINA15", false));
		mnDisciplinas.add(d15);

		mntmAlterarNome = new JMenuItem("Alterar Nome");
		mntmAlterarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterarNomeDisciplina alterarNome = new AlterarNomeDisciplina();
				alterarNome.setVisible(true);
			}
		});
		mnDisciplinas.add(mntmAlterarNome);

		mnAssuntos = new JMenu("Assuntos");
		menuBar.add(mnAssuntos);

		mntmAdicionarAssunto = new JMenuItem("Adicionar Assunto");
		mnAssuntos.add(mntmAdicionarAssunto);

		mntmAlterarremoverAssunto = new JMenuItem("Alterar/Remover Assunto");
		mnAssuntos.add(mntmAlterarremoverAssunto);
		mntmAlterarremoverAssunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarRemoverAssunto altAssunto = new AlterarRemoverAssunto();
				altAssunto.setVisible(true);
			}
		});
		mntmAdicionarAssunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarAssunto adcAssunto = new AdicionarAssunto();
				adcAssunto.setVisible(true);
			}
		});

		mnUtilitrios = new JMenu("Utilit\u00E1rios");
		menuBar.add(mnUtilitrios);

		mntmCalculadoraDeNota = new JMenuItem("Calculadora de Nota");
		mntmCalculadoraDeNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Nota nota = new Nota();
				nota.setVisible(true);
			}
		});
		mnUtilitrios.add(mntmCalculadoraDeNota);

		mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		mnAjuda.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pos == 0) {
					pos = listaTopicos.length - 1;
				} else {
					pos--;
				}
				atualizarAssuntoConteudo();
				atualizarMarcadorPosicao();

				if (chckbxManter.isSelected()) {
					txtArea.setVisible(true);
					btnMostrar.setText("Esconder");
				} else {
					txtArea.setVisible(false);
					btnMostrar.setText("Mostrar");
				}
			}
		});

		txtAssunto = new JTextField();
		txtAssunto.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtAssunto.setBackground(new Color(192, 192, 192));
		txtAssunto.setEditable(false);
		txtAssunto.setHorizontalAlignment(SwingConstants.CENTER);
		txtAssunto.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtAssunto.setText("Assunto");
		contentPane.add(txtAssunto, BorderLayout.NORTH);
		txtAssunto.setColumns(10);
		btnAnterior.setFocusable(false);
		contentPane.add(btnAnterior, BorderLayout.WEST);

		JButton btnProximo = new JButton("Pr\u00F3ximo");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pos == listaTopicos.length - 1) {
					pos = 0;
				} else {
					pos++;
				}
				atualizarAssuntoConteudo();
				atualizarMarcadorPosicao();

				if (chckbxManter.isSelected()) {
					txtArea.setVisible(true);
					btnMostrar.setText("Esconder");
				} else {
					txtArea.setVisible(false);
					btnMostrar.setText("Mostrar");
				}
			}
		});
		btnProximo.setFocusable(false);
		contentPane.add(btnProximo, BorderLayout.EAST);

		lblPosicao = new JLabel("0 / 0");
		lblPosicao.setFocusable(false);
		lblPosicao.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPosicao, BorderLayout.SOUTH);

		txtArea = new JTextArea();
		txtArea.setTabSize(2);
		txtArea.setBackground(UIManager.getColor("Button.background"));
		txtArea.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
		txtArea.setText("Conteúdo");
		txtArea.setEditable(false);
		txtArea.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(5, 15, 5, 15)));

		JScrollPane scrollPane = new JScrollPane(txtArea);
		scrollPane.setFocusable(false);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setColumnHeaderView(panel);

		chckbxManter = new JCheckBox("Manter Vis\u00EDvel");
		chckbxManter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefs.putBoolean("VISIVEL", chckbxManter.isSelected());
			}
		});
		chckbxManter.setSelected(prefs.getBoolean("VISIVEL", true));
		chckbxManter.setToolTipText("Mant\u00E9m o conte\u00FAdo sempre vis\u00EDvel");
		chckbxManter.setFocusable(false);
		panel.add(chckbxManter);

		btnMostrar = new JButton("Esconder");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtArea.isVisible()) {
					btnMostrar.setText("Mostrar");
					txtArea.setVisible(false);
				} else {
					btnMostrar.setText("Esconder");
					txtArea.setVisible(true);
				}
			}
		});
		btnMostrar.setFocusable(false);
		panel.add(btnMostrar);

		atualizarListaTopicos();

		if (listaTopicos.length > 0) {
			embaralharTopicos();
			txtAssunto.setText(listaTopicos[0][0]);
			txtArea.setText(listaTopicos[0][1]);
			atualizarMarcadorPosicao();
		}
	}
}

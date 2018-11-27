package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileSystemView;

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
	public static final String TOKEN_SEPARADOR_LOCAL = "@#&&#@";
	public static final String TOKEN_SEPARADOR_CHAVE = "@#@@#@";
	public static final String TOKEN_SEPARADOR_CONTEUDO = "@####@";
	public static final String CONFIGS = "/MEMORIZADOR_DE_CONCEITOS";
	public static final String DISCIPLINA1 = CONFIGS + "/DISCIPLINA_01";
	public static final String DISCIPLINA2 = CONFIGS + "/DISCIPLINA_02";
	public static final String DISCIPLINA3 = CONFIGS + "/DISCIPLINA_03";
	public static final String DISCIPLINA4 = CONFIGS + "/DISCIPLINA_04";
	public static final String DISCIPLINA5 = CONFIGS + "/DISCIPLINA_05";
	public static final String DISCIPLINA6 = CONFIGS + "/DISCIPLINA_06";
	public static final String DISCIPLINA7 = CONFIGS + "/DISCIPLINA_07";
	public static final String DISCIPLINA8 = CONFIGS + "/DISCIPLINA_08";
	public static final String DISCIPLINA9 = CONFIGS + "/DISCIPLINA_09";
	public static final String DISCIPLINA10 = CONFIGS + "/DISCIPLINA_10";
	public static final String DISCIPLINA11 = CONFIGS + "/DISCIPLINA_11";
	public static final String DISCIPLINA12 = CONFIGS + "/DISCIPLINA_12";
	public static final String DISCIPLINA13 = CONFIGS + "/DISCIPLINA_13";
	public static final String DISCIPLINA14 = CONFIGS + "/DISCIPLINA_14";
	public static final String DISCIPLINA15 = CONFIGS + "/DISCIPLINA_15";
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
	public static JMenuItem mntmImportarAssuntos;
	public static JMenuItem mntmExportarAssuntos;
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
	private static JCheckBoxMenuItem[] arrayCheckBoxDisciplinas = new JCheckBoxMenuItem[] { d1, d2, d3, d4, d5, d6, d7,
			d8, d9, d10, d11, d12, d13, d14, d15 };
	private JMenu mnAssuntos;
	private JMenuItem mntmAlterarNome;
	public static Preferences prefs = Preferences.userRoot().node(CONFIGS);
	private JMenuItem mntmExportarContedoEm;
	private JMenuItem mntmSobreOsDados;
	public static JMenuItem mntmApagarTodosOs;
	private JMenuItem mntmMarcarTodas;

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
		return new String[] { prefs.get("DISCIPLINA_NOME_01", "Disciplina 01"),
				prefs.get("DISCIPLINA_NOME_02", "Disciplina 02"), prefs.get("DISCIPLINA_NOME_03", "Disciplina 03"),
				prefs.get("DISCIPLINA_NOME_04", "Disciplina 04"), prefs.get("DISCIPLINA_NOME_05", "Disciplina 05"),
				prefs.get("DISCIPLINA_NOME_06", "Disciplina 06"), prefs.get("DISCIPLINA_NOME_07", "Disciplina 07"),
				prefs.get("DISCIPLINA_NOME_08", "Disciplina 08"), prefs.get("DISCIPLINA_NOME_09", "Disciplina 09"),
				prefs.get("DISCIPLINA_NOME_10", "Disciplina 10"), prefs.get("DISCIPLINA_NOME_11", "Disciplina 11"),
				prefs.get("DISCIPLINA_NOME_12", "Disciplina 12"), prefs.get("DISCIPLINA_NOME_13", "Disciplina 13"),
				prefs.get("DISCIPLINA_NOME_14", "Disciplina 14"), prefs.get("DISCIPLINA_NOME_15", "Disciplina 15") };
	}

	/**
	 * Atualiza a lista de assuntos que são exibidos na tela principal
	 */
	public static void atualizarListaTopicos() {
		try {
			int topicos = 0;
			for (int i = 0; i < 15; i++) {
				if (prefs.getBoolean(String.format("DISCIPLINA_ATIVA_%02d", i + 1), false)) {
					prefs = Preferences.userRoot().node(Main.ARRAY_DISCIPLINAS[i]);
					topicos += prefs.keys().length;
					prefs = Preferences.userRoot().node(Main.CONFIGS);
				}
			}

			prefs = Preferences.userRoot().node(Main.CONFIGS);
			listaTopicos = new String[topicos][2];
			int pos = 0;
			for (int i = 0; i < 15; i++) {
				if (prefs.getBoolean(String.format("DISCIPLINA_ATIVA_%02d", i + 1), false)) {
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
	 * Exporta as disciplinas, assuntos e configurações para um arquivo
	 * 
	 * @return true caso dados tenham sido exportados com sucesso, false caso contrário
	 */
	private boolean exportarDados() {
		try {
			JFileChooser fileSaving = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int result = fileSaving.showSaveDialog(null);

			if (result != JFileChooser.APPROVE_OPTION) {
				return false;
			}

			File saveSelectedFile = fileSaving.getSelectedFile();
			FileWriter fW = new FileWriter(saveSelectedFile, false);
			PrintWriter printWriter = new PrintWriter(fW);

			prefs = Preferences.userRoot().node(CONFIGS);
			String data = CONFIGS;

			String[] keys = prefs.keys();
			Arrays.sort(keys);
			for (String key : keys) {
				data += TOKEN_SEPARADOR_CHAVE + key + TOKEN_SEPARADOR_CONTEUDO + prefs.get(key, "");
			}

			for (String disc : ARRAY_DISCIPLINAS) {
				prefs = Preferences.userRoot().node(disc);
				String[] assuntos = prefs.keys();

				if (assuntos.length > 0) {
					Arrays.sort(assuntos);
					data += TOKEN_SEPARADOR_LOCAL + disc;
					for (String assunto : assuntos) {
						data += TOKEN_SEPARADOR_CHAVE + assunto + TOKEN_SEPARADOR_CONTEUDO + prefs.get(assunto, "");
					}
				}
			}

			printWriter.printf(data);

			fW.close();
			prefs = Preferences.userRoot().node(CONFIGS);
			JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso em '" + saveSelectedFile.toString() + "'.",
					"Atenção", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			prefs = Preferences.userRoot().node(CONFIGS);
			JOptionPane.showMessageDialog(null,
					"Não foi possível criar o arquivo em '\" + saveSelectedFile.toString() + \"'.\n"
							+ "Certifique-se de que a localização seja válida ou, caso o arquivo já exista, que ele esteja fechado.",
					"Atenção", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	private boolean importarDados() {
		try {
			JFileChooser fileOpener = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int result = fileOpener.showOpenDialog(null);
			if (result != JFileChooser.APPROVE_OPTION) {
				return false;
			}
			File openSelectedFile = fileOpener.getSelectedFile();
			FileInputStream fis = new FileInputStream(openSelectedFile);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			String conteudo = "";
			ArrayList<String> data = new ArrayList<String>();
			String line = reader.readLine();
			while (line != null) {
				data.add(line);
				conteudo += line + "\n";
				line = reader.readLine();
			}
			reader.close();
			fis.close();

			String[] locais = conteudo.split(TOKEN_SEPARADOR_LOCAL);
			for (String local : locais) {
				String[] chaves = local.split(TOKEN_SEPARADOR_CHAVE);

				for (String chave : chaves) {
					String[] val = chave.split(TOKEN_SEPARADOR_CONTEUDO);
					if (val.length == 2) {
						if (val[1].equals("true")) {// booleano
							prefs.putBoolean(val[0], true);
						} else if (val[1].equals("false")) {// booleano
							prefs.putBoolean(val[0], false);
						} else {
							prefs.put(val[0], val[1]);
						}
					} else {
						prefs = Preferences.userRoot().node(val[0]);
					}
				}
			}
			prefs = Preferences.userRoot().node(CONFIGS);
			JOptionPane.showMessageDialog(null, "Dados importados com sucesso. Por favor, reincie a aplicação\n"
					+ " para que as alterações tenham efeito.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (Exception f) {
			prefs = Preferences.userRoot().node(CONFIGS);
			JOptionPane.showMessageDialog(null,
					"Não foi possível importar os dados do arquivo selecionado. Ele pode ser inválido ou estar corrompido.",
					"Atenção", JOptionPane.WARNING_MESSAGE);
			f.printStackTrace();
			return false;
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
		setTitle("Memorizador de Conceitos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 860, 540);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		mntmImportarAssuntos = new JMenuItem("Importar Disciplinas e Assuntos");
		mntmImportarAssuntos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importarDados();
			}
		});
		mnArquivo.add(mntmImportarAssuntos);

		mntmExportarAssuntos = new JMenuItem("Exportar Disciplinas e Assuntos");
		mntmExportarAssuntos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarDados();
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

		mntmApagarTodosOs = new JMenuItem("Apagar Todos os Dados");
		mntmApagarTodosOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja excluir todas as configurações deste aplicativo, assim como as disciplinas e assuntos salvos?\n\nNote que esta ação é irreversível. Considere exportar os dados antes para manter um backup.",
							"Atenção", JOptionPane.YES_NO_OPTION) == 0) {
						int cont = 0;
						prefs = Preferences.userRoot().node(CONFIGS);

						for (String key : prefs.keys()) {
							prefs.remove(key);
							cont++;
						}

						for (String disc : ARRAY_DISCIPLINAS) {
							prefs = Preferences.userRoot().node(disc);
							for (String assunto : prefs.keys()) {
								prefs.remove(assunto);
								cont++;
							}
						}

						if (cont > 0) {
							JOptionPane.showMessageDialog(null,
									"Todos os " + cont + " dados foram excluídos com sucesso.", "Atenção",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null,
									"Não havia nenhum dado deste aplicativo salvo em seu computador para excluir.",
									"Atenção", JOptionPane.INFORMATION_MESSAGE);
						}
					}

					prefs = Preferences.userRoot().node(CONFIGS);
				} catch (Exception f) {
					prefs = Preferences.userRoot().node(CONFIGS);
					f.printStackTrace();
				}
			}
		});
		mnArquivo.add(mntmApagarTodosOs);

		mnDisciplinas = new JMenu("Disciplinas");
		menuBar.add(mnDisciplinas);

		d1 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_01", "Disciplina 01"));
		d1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefs.putBoolean("DISCIPLINA_ATIVA_01", d1.isSelected());
				atualizarInterface();
			}
		});
		d1.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_01", false));
		mnDisciplinas.add(d1);

		d2 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_02", "Disciplina 02"));
		d2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_02", d2.isSelected());
				atualizarInterface();
			}
		});
		d2.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_02", false));
		mnDisciplinas.add(d2);

		d3 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_03", "Disciplina 03"));
		d3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_03", d3.isSelected());
				atualizarInterface();
			}
		});
		d3.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_03", false));
		mnDisciplinas.add(d3);

		d4 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_04", "Disciplina 04"));
		d4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_04", d4.isSelected());
				atualizarInterface();
			}
		});
		d4.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_04", false));
		mnDisciplinas.add(d4);

		d5 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_05", "Disciplina 05"));
		d5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefs.putBoolean("DISCIPLINA_ATIVA_05", d5.isSelected());
				atualizarInterface();
			}
		});
		d5.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_05", false));
		mnDisciplinas.add(d5);

		d6 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_06", "Disciplina 06"));
		d6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_06", d6.isSelected());
				atualizarInterface();
			}
		});
		d6.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_06", false));
		mnDisciplinas.add(d6);

		d7 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_07", "Disciplina 07"));
		d7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_07", d7.isSelected());
				atualizarInterface();
			}
		});
		d7.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_07", false));
		mnDisciplinas.add(d7);

		d8 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_08", "Disciplina 08"));
		d8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_08", d8.isSelected());
				atualizarInterface();
			}
		});
		d8.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_08", false));
		mnDisciplinas.add(d8);

		d9 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_09", "Disciplina 09"));
		d9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_09", d9.isSelected());
				atualizarInterface();
			}
		});
		d9.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_09", false));
		mnDisciplinas.add(d9);

		d10 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_10", "Disciplina 10"));
		d10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_10", d10.isSelected());
				atualizarInterface();
			}
		});
		d10.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_10", false));
		mnDisciplinas.add(d10);

		d11 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_11", "Disciplina 11"));
		d11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_11", d11.isSelected());
				atualizarInterface();
			}
		});
		d11.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_11", false));
		mnDisciplinas.add(d11);

		d12 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_12", "Disciplina 12"));
		d12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_12", d12.isSelected());
				atualizarInterface();
			}
		});
		d12.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_12", false));
		mnDisciplinas.add(d12);

		d13 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_13", "Disciplina 13"));
		d13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_13", d13.isSelected());
				atualizarInterface();
			}
		});
		d13.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_13", false));
		mnDisciplinas.add(d13);

		d14 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_14", "Disciplina 14"));
		d14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_14", d14.isSelected());
				atualizarInterface();
			}
		});
		d14.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_14", false));
		mnDisciplinas.add(d14);

		d15 = new JCheckBoxMenuItem(prefs.get("DISCIPLINA_NOME_15", "Disciplina 15"));
		d15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("DISCIPLINA_ATIVA_15", d15.isSelected());
				atualizarInterface();
			}
		});
		d15.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_15", false));
		mnDisciplinas.add(d15);

		mntmAlterarNome = new JMenuItem("Alterar Nome");
		mntmAlterarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlterarNomeDisciplina alterarNome = new AlterarNomeDisciplina();
				alterarNome.setVisible(true);
			}
		});
		mnDisciplinas.add(mntmAlterarNome);

		mntmMarcarTodas = new JMenuItem("Marcar Todas");
		mntmMarcarTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arrayCheckBoxDisciplinas = new JCheckBoxMenuItem[] { d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12,
						d13, d14, d15 };
				boolean todosSelecionados = true;
				for (JCheckBoxMenuItem check : arrayCheckBoxDisciplinas) {
					if (check.isSelected() == false) {
						check.doClick();
						todosSelecionados = false;
					}
				}
				if (todosSelecionados) {
					for (JCheckBoxMenuItem check : arrayCheckBoxDisciplinas) {
						check.doClick();
					}
				}
			}
		});
		mnDisciplinas.add(mntmMarcarTodas);

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

		mntmSobre = new JMenuItem("Sobre o Aplicativo");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});

		mntmSobreOsDados = new JMenuItem("Sobre os Dados");
		mntmSobreOsDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SobreOsDados dados = new SobreOsDados();
				dados.setVisible(true);
			}
		});
		mnAjuda.add(mntmSobreOsDados);
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
				prefs.putBoolean("EXIBIR_CONTEUDO", chckbxManter.isSelected());
			}
		});
		chckbxManter.setSelected(prefs.getBoolean("EXIBIR_CONTEUDO", true));
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

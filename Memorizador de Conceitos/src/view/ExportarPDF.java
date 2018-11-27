package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.ListNumberingType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

/**
 * @author Madson Paulo Alexandre da Silva
 * 
 *         JFrame que permite exportar em formato .pdf todos os assuntos das disciplinas selecionadas. É possível determinar um
 *         título, um subtítulo e realizar algumas configurações para o .pdf a ser gerado
 */
@SuppressWarnings("deprecation")
public class ExportarPDF extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField titulo;
	private JCheckBox d1;
	private JCheckBox d2;
	private JCheckBox d3;
	private JCheckBox d4;
	private JCheckBox d5;
	private JCheckBox d6;
	private JCheckBox d7;
	private JCheckBox d8;
	private JCheckBox d9;
	private JCheckBox d10;
	private JCheckBox d11;
	private JCheckBox d12;
	private JCheckBox d13;
	private JCheckBox d14;
	private JCheckBox d15;
	private JLabel lblSubttulo;
	private JTextField subtitulo;
	private JLabel lblSalvarEm;
	private JTextField diretorio;
	private JLabel lblFonte;
	private JComboBox<String> fonteTipo;
	private JButton btnExportar;
	private JLabel lblMensagens;
	private final String NOME_PADRAO = "memorizador.pdf";
	private JComboBox<String> fonteFamilia;
	private JComboBox<String> fonteTamanho;
	private JCheckBox chckbxDisciplinaMaiusculo;
	private JCheckBox chckbxDisciplinaEmNegrito;
	private JCheckBox chckbxAssuntoEmMaisculo;
	private JCheckBox chckbxAssuntoEmNegrito;
	public static Preferences prefs = Preferences.userRoot().node(Main.CONFIGS);
	private JCheckBox chckbxNumeroDaPagina;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExportarPDF frame = new ExportarPDF();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Permite alterar o diretório onde o .pdf será salvo e alterar o nome do arquivo
	 */
	private void alterarDiretorio() {
		JFileChooser novoDiretorio = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int resultado = novoDiretorio.showSaveDialog(null);
		// se o usuário não aprovar o acesso aos arquivos, retorna inalterado
		if (resultado != JFileChooser.APPROVE_OPTION) {
			return;
		}

		String local = novoDiretorio.getSelectedFile().toString();

		if (local.endsWith(".pdf") == false) {
			local = local + ".pdf";
		}

		// altera a localização selecionada
		diretorio.setText(local);
	}

	/**
	 * @return String referente às configurações da fonte
	 */
	private String getFonte() {
		String fonte = FontConstants.TIMES_ROMAN;

		if (fonteFamilia.getSelectedIndex() == 0) {
			if (fonteTipo.getSelectedIndex() == 0) {
				fonte = FontConstants.TIMES_ROMAN;
			} else if (fonteTipo.getSelectedIndex() == 1) {
				fonte = FontConstants.TIMES_BOLD;
			} else if (fonteTipo.getSelectedIndex() == 2) {
				fonte = FontConstants.TIMES_ITALIC;
			} else {
				fonte = FontConstants.TIMES_BOLDITALIC;
			}
		} else if (fonteFamilia.getSelectedIndex() == 1) {
			if (fonteTipo.getSelectedIndex() == 0) {
				fonte = FontConstants.COURIER;
			} else if (fonteTipo.getSelectedIndex() == 1) {
				fonte = FontConstants.COURIER_BOLD;
			} else if (fonteTipo.getSelectedIndex() == 2) {
				fonte = FontConstants.COURIER_OBLIQUE;
			} else {
				fonte = FontConstants.COURIER_BOLDOBLIQUE;
			}
		} else {
			if (fonteTipo.getSelectedIndex() == 0) {
				fonte = FontConstants.HELVETICA;
			} else if (fonteTipo.getSelectedIndex() == 1) {
				fonte = FontConstants.HELVETICA_BOLD;
			} else if (fonteTipo.getSelectedIndex() == 2) {
				fonte = FontConstants.HELVETICA_OBLIQUE;
			} else {
				fonte = FontConstants.HELVETICA_BOLDOBLIQUE;
			}
		}

		return fonte;
	}

	/**
	 * Gera o .pdf de acordo com as configurações especificadas
	 */
	private void gerarArquivo() {
		try {
			String txtTitulo = titulo.getText();
			String txtSubtitulo = subtitulo.getText() + "\n\n\n";
			String fonte = getFonte();
			float tamanho = Float.valueOf(fonteTamanho.getSelectedItem().toString());
			JCheckBox[] listaCheckBox = new JCheckBox[] { d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14,
					d15 };
			String[] nomeDisciplinas = Main.getNomeDisciplinas();

			PdfWriter writer = new PdfWriter(diretorio.getText());
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf);
			PdfFont font = PdfFontFactory.createFont(fonte);

			// Titulo e subtitulo
			document.add(
					new Paragraph(txtTitulo).setFont(font).setFontSize(tamanho).setTextAlignment(TextAlignment.CENTER));
			document.add(new Paragraph(txtSubtitulo).setFont(font).setFontSize(tamanho - 1)
					.setTextAlignment(TextAlignment.CENTER));

			List listaDisciplinas = new List(ListNumberingType.DECIMAL).setFont(font).setFontSize(tamanho);
			List listaAssuntos = new List(ListNumberingType.ROMAN_UPPER).setFont(font).setFontSize(tamanho);

			for (int i = 0; i < 15; i++) {
				if (listaCheckBox[i].isSelected()) {
					ListItem disciplina = new ListItem();
					// adiciona nome da disciplina
					if (chckbxDisciplinaMaiusculo.isSelected()) {
						if (chckbxDisciplinaEmNegrito.isSelected()) {
							disciplina.add(new Paragraph().add(nomeDisciplinas[i].toUpperCase()).setBold());
						} else {
							disciplina.add(new Paragraph().add(nomeDisciplinas[i].toUpperCase()));
						}
					} else {
						if (chckbxDisciplinaEmNegrito.isSelected()) {
							disciplina.add(new Paragraph().add(nomeDisciplinas[i]).setBold());
						} else {
							disciplina.add(new Paragraph().add(nomeDisciplinas[i]));
						}
					}

					prefs = Preferences.userRoot().node(Main.ARRAY_DISCIPLINAS[i]);
					String[] keys = prefs.keys();
					Arrays.sort(keys);

					if (keys.length > 0) {
						ListItem assunto = new ListItem();
						for (int j = 0; j < keys.length; j++) {
							assunto = new ListItem();
							// adiciona assunto
							if (chckbxAssuntoEmMaisculo.isSelected()) {
								if (chckbxAssuntoEmNegrito.isSelected()) {
									assunto.add(new Paragraph().add(keys[j].toUpperCase()).setBold());
								} else {
									assunto.add(new Paragraph().add(keys[j].toUpperCase()));
								}
							} else {
								if (chckbxAssuntoEmNegrito.isSelected()) {
									assunto.add(new Paragraph().add(keys[j]).setBold());
								} else {
									assunto.add(new Paragraph().add(keys[j]));
								}
							}

							// adiciona conteúdo
							assunto.add(new Paragraph().add(prefs.get(keys[j], "")));

							listaAssuntos.add(assunto);
						}
					}
					disciplina.add(listaAssuntos);
					prefs = Preferences.userRoot().node(Main.CONFIGS);
					listaDisciplinas.add(disciplina);
				}
				listaAssuntos = new List(ListNumberingType.ROMAN_UPPER).setFont(font).setFontSize(tamanho);
			}

			document.add(listaDisciplinas);
			document.close();
			pdf.close();
			if (chckbxNumeroDaPagina.isSelected()) {
				adicionarPagina();
			}

			JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso.", "Atenção",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException | BackingStoreException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível gerar o arquivo. Talvez o nome ou local seja inválido.\nSe o arquivo estiver aberto, feche-o.",
					"Atenção", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

		prefs.put("EXPORTAR_TITULO", titulo.getText());
		prefs.put("EXPORTAR_SUBTITULO", subtitulo.getText());
	}

	private void adicionarPagina() {
		try {
			PdfDocument pdf = new PdfDocument(new PdfReader(diretorio.getText()),
					new PdfWriter(FileSystemView.getFileSystemView().getHomeDirectory() + "\\" + "teste.pdf"));
			Document document = new Document(pdf);

			String fonte = getFonte();
			float tamanho = Float.valueOf(fonteTamanho.getSelectedItem().toString());
			PdfFont font = PdfFontFactory.createFont(fonte);

			for (int i = 1; i <= pdf.getNumberOfPages(); i++) {
				float x = pdf.getPage(i).getPageSize().getWidth() / 2;
				float y = pdf.getPage(i).getPageSize().getBottom() + 20;
				Paragraph header = new Paragraph("Página " + i).setFont(font).setFontSize(tamanho - 2);
				document.showTextAligned(header, x, y, i, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);
			}
			document.close();

			File antigo = new File(diretorio.getText());
			antigo.delete();
			File novo = new File(FileSystemView.getFileSystemView().getHomeDirectory() + "\\" + "teste.pdf");
			File novoNome = new File(diretorio.getText());
			novo.renameTo(novoNome);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Preenche campos e marca checkboxes, mantendo os dados preenchidos na geração de .pdfs anteriores
	 */
	private void ajusteInicial() {
		d1.setText(prefs.get("DISCIPLINA_NOME_01", "Disciplina 01"));
		d1.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_01", false));
		d2.setText(prefs.get("DISCIPLINA_NOME_02", "Disciplina 02"));
		d2.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_02", false));
		d3.setText(prefs.get("DISCIPLINA_NOME_03", "Disciplina 03"));
		d3.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_03", false));
		d4.setText(prefs.get("DISCIPLINA_NOME_04", "Disciplina 04"));
		d4.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_04", false));
		d5.setText(prefs.get("DISCIPLINA_NOME_05", "Disciplina 05"));
		d5.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_05", false));
		d6.setText(prefs.get("DISCIPLINA_NOME_06", "Disciplina 06"));
		d6.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_06", false));
		d7.setText(prefs.get("DISCIPLINA_NOME_07", "Disciplina 07"));
		d7.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_07", false));
		d8.setText(prefs.get("DISCIPLINA_NOME_08", "Disciplina 08"));
		d8.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_08", false));
		d9.setText(prefs.get("DISCIPLINA_NOME_09", "Disciplina 09"));
		d9.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_09", false));
		d10.setText(prefs.get("DISCIPLINA_NOME_10", "Disciplina 10"));
		d10.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_10", false));
		d11.setText(prefs.get("DISCIPLINA_NOME_11", "Disciplina 11"));
		d11.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_11", false));
		d12.setText(prefs.get("DISCIPLINA_NOME_12", "Disciplina 12"));
		d12.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_12", false));
		d13.setText(prefs.get("DISCIPLINA_NOME_13", "Disciplina 13"));
		d13.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_13", false));
		d14.setText(prefs.get("DISCIPLINA_NOME_14", "Disciplina 14"));
		d14.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_14", false));
		d15.setText(prefs.get("DISCIPLINA_NOME_15", "Disciplina 15"));
		d15.setSelected(prefs.getBoolean("DISCIPLINA_ATIVA_15", false));

		d1.setToolTipText(d1.getText());
		d2.setToolTipText(d2.getText());
		d3.setToolTipText(d3.getText());
		d4.setToolTipText(d4.getText());
		d5.setToolTipText(d5.getText());
		d6.setToolTipText(d6.getText());
		d7.setToolTipText(d7.getText());
		d8.setToolTipText(d8.getText());
		d9.setToolTipText(d9.getText());
		d10.setToolTipText(d10.getText());
		d11.setToolTipText(d11.getText());
		d12.setToolTipText(d12.getText());
		d13.setToolTipText(d13.getText());
		d14.setToolTipText(d14.getText());
		d15.setToolTipText(d15.getText());

		diretorio.setText(FileSystemView.getFileSystemView().getHomeDirectory() + "\\" + NOME_PADRAO);
		titulo.setText(prefs.get("EXPORTAR_TITULO", "Título"));
		subtitulo.setText(prefs.get("EXPORTAR_SUBTITULO", "Subtítulo"));
	}

	public ExportarPDF() {
		setResizable(false);
		setTitle("Exportar PDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 860, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 6, 178, 419);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblIncluir = new JLabel("Disciplinas a Incluir");
		lblIncluir.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblIncluir.setBounds(6, 3, 166, 23);
		panel.add(lblIncluir);

		d1 = new JCheckBox("Disciplina 01");
		d1.setFocusable(false);
		d1.setBounds(6, 29, 166, 23);
		panel.add(d1);

		d2 = new JCheckBox("Disciplina 02");
		d2.setFocusable(false);
		d2.setBounds(6, 55, 166, 23);
		panel.add(d2);

		d3 = new JCheckBox("Disciplina 03");
		d3.setFocusable(false);
		d3.setBounds(6, 81, 166, 23);
		panel.add(d3);

		d4 = new JCheckBox("Disciplina 04");
		d4.setFocusable(false);
		d4.setBounds(6, 107, 166, 23);
		panel.add(d4);

		d5 = new JCheckBox("Disciplina 05");
		d5.setFocusable(false);
		d5.setBounds(6, 133, 166, 23);
		panel.add(d5);

		d6 = new JCheckBox("Disciplina 06");
		d6.setFocusable(false);
		d6.setBounds(6, 159, 166, 23);
		panel.add(d6);

		d7 = new JCheckBox("Disciplina 07");
		d7.setFocusable(false);
		d7.setBounds(6, 185, 166, 23);
		panel.add(d7);

		d8 = new JCheckBox("Disciplina 08");
		d8.setFocusable(false);
		d8.setBounds(6, 211, 166, 23);
		panel.add(d8);

		d9 = new JCheckBox("Disciplina 09");
		d9.setFocusable(false);
		d9.setBounds(6, 237, 166, 23);
		panel.add(d9);

		d10 = new JCheckBox("Disciplina 10");
		d10.setFocusable(false);
		d10.setBounds(6, 263, 166, 23);
		panel.add(d10);

		d11 = new JCheckBox("Disciplina 11");
		d11.setFocusable(false);
		d11.setBounds(6, 289, 166, 23);
		panel.add(d11);

		d12 = new JCheckBox("Disciplina 12");
		d12.setFocusable(false);
		d12.setBounds(6, 315, 166, 23);
		panel.add(d12);

		d13 = new JCheckBox("Disciplina 13");
		d13.setFocusable(false);
		d13.setBounds(6, 341, 166, 23);
		panel.add(d13);

		d14 = new JCheckBox("Disciplina 14");
		d14.setFocusable(false);
		d14.setBounds(6, 367, 166, 23);
		panel.add(d14);

		d15 = new JCheckBox("Disciplina 15");
		d15.setFocusable(false);
		d15.setBounds(6, 393, 166, 23);
		panel.add(d15);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(198, 6, 646, 414);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblDisciplinasAIncluir = new JLabel("T\u00EDtulo");
		lblDisciplinasAIncluir.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDisciplinasAIncluir.setBounds(6, 3, 207, 23);
		panel_1.add(lblDisciplinasAIncluir);

		titulo = new JTextField();
		titulo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		titulo.setBounds(217, 3, 419, 23);
		panel_1.add(titulo);
		titulo.setColumns(10);

		lblSubttulo = new JLabel("Subt\u00EDtulo");
		lblSubttulo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSubttulo.setBounds(6, 37, 207, 23);
		panel_1.add(lblSubttulo);

		subtitulo = new JTextField();
		subtitulo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		subtitulo.setColumns(10);
		subtitulo.setBounds(217, 37, 419, 23);
		panel_1.add(subtitulo);

		lblSalvarEm = new JLabel("Diret\u00F3rio");
		lblSalvarEm.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSalvarEm.setBounds(6, 71, 207, 23);
		panel_1.add(lblSalvarEm);

		diretorio = new JTextField();
		diretorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		diretorio.setEditable(false);
		diretorio.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		diretorio.setColumns(10);
		diretorio.setBounds(217, 71, 332, 23);
		panel_1.add(diretorio);

		lblFonte = new JLabel("Fonte");
		lblFonte.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFonte.setBounds(6, 105, 207, 23);
		panel_1.add(lblFonte);

		fonteTamanho = new JComboBox<String>();
		fonteTamanho.setModel(
				new DefaultComboBoxModel<String>(new String[] { "6", "8", "10", "12", "14", "16", "18", "20" }));
		fonteTamanho.setSelectedIndex(3);
		fonteTamanho.setBounds(217, 105, 60, 23);
		panel_1.add(fonteTamanho);

		fonteFamilia = new JComboBox<String>();
		fonteFamilia.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Times New Roman", "Courier New", "Helvetica" }));
		fonteFamilia.setBounds(416, 105, 220, 23);
		panel_1.add(fonteFamilia);

		fonteTipo = new JComboBox<String>();
		fonteTipo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Normal", "Negrito", "It\u00E1lico", "Negrito e It\u00E1lico" }));
		fonteTipo.setBounds(287, 105, 119, 23);
		panel_1.add(fonteTipo);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterarDiretorio();
			}
		});
		btnAlterar.setFocusable(false);
		btnAlterar.setBounds(556, 71, 80, 23);
		panel_1.add(btnAlterar);

		btnExportar = new JButton("Gerar Arquivo");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerarArquivo();
			}
		});
		btnExportar.setFocusable(false);
		btnExportar.setBounds(517, 380, 119, 23);
		panel_1.add(btnExportar);

		lblMensagens = new JLabel("");
		lblMensagens.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblMensagens.setBounds(6, 380, 501, 23);
		panel_1.add(lblMensagens);

		chckbxDisciplinaMaiusculo = new JCheckBox("Disciplina em Mai\u00FAsculo");
		chckbxDisciplinaMaiusculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefs.putBoolean("EXPORTAR_DISCIPLINA_MAIUSCULO", chckbxDisciplinaMaiusculo.isSelected());
			}
		});
		chckbxDisciplinaMaiusculo.setFocusable(false);
		chckbxDisciplinaMaiusculo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		chckbxDisciplinaMaiusculo.setBounds(217, 135, 208, 23);
		chckbxDisciplinaMaiusculo.setSelected(prefs.getBoolean("EXPORTAR_DISCIPLINA_MAIUSCULO", false));
		panel_1.add(chckbxDisciplinaMaiusculo);

		chckbxDisciplinaEmNegrito = new JCheckBox("Disciplina em Negrito");
		chckbxDisciplinaEmNegrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("EXPORTAR_DISCIPLINA_NEGRITO", chckbxDisciplinaEmNegrito.isSelected());
			}
		});
		chckbxDisciplinaEmNegrito.setFont(new Font("Times New Roman", Font.BOLD, 14));
		chckbxDisciplinaEmNegrito.setFocusable(false);
		chckbxDisciplinaEmNegrito.setBounds(428, 135, 208, 23);
		chckbxDisciplinaEmNegrito.setSelected(prefs.getBoolean("EXPORTAR_DISCIPLINA_NEGRITO", false));
		panel_1.add(chckbxDisciplinaEmNegrito);

		chckbxAssuntoEmMaisculo = new JCheckBox("Assunto em Mai\u00FAsculo");
		chckbxAssuntoEmMaisculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.putBoolean("EXPORTAR_ASSUNTO_MAIUSCULO", chckbxAssuntoEmMaisculo.isSelected());
			}
		});
		chckbxAssuntoEmMaisculo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		chckbxAssuntoEmMaisculo.setFocusable(false);
		chckbxAssuntoEmMaisculo.setBounds(217, 166, 208, 23);
		chckbxAssuntoEmMaisculo.setSelected(prefs.getBoolean("EXPORTAR_ASSUNTO_MAIUSCULO", false));
		panel_1.add(chckbxAssuntoEmMaisculo);

		chckbxAssuntoEmNegrito = new JCheckBox("Assunto em Negrito");
		chckbxAssuntoEmNegrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefs.putBoolean("EXPORTAR_ASSUNTO_NEGRITO", chckbxAssuntoEmNegrito.isSelected());
			}
		});
		chckbxAssuntoEmNegrito.setFont(new Font("Times New Roman", Font.BOLD, 14));
		chckbxAssuntoEmNegrito.setFocusable(false);
		chckbxAssuntoEmNegrito.setBounds(428, 166, 208, 23);
		chckbxAssuntoEmNegrito.setSelected(prefs.getBoolean("EXPORTAR_ASSUNTO_NEGRITO", false));
		panel_1.add(chckbxAssuntoEmNegrito);

		chckbxNumeroDaPagina = new JCheckBox("Incluir N\u00FAmero da P\u00E1gina");
		chckbxNumeroDaPagina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefs.putBoolean("EXPORTAR_INCLUIR_NUMERO_DA_PAGINA", chckbxNumeroDaPagina.isSelected());
			}
		});
		chckbxNumeroDaPagina.setFont(new Font("Times New Roman", Font.BOLD, 14));
		chckbxNumeroDaPagina.setFocusable(false);
		chckbxNumeroDaPagina.setSelected(prefs.getBoolean("EXPORTAR_INCLUIR_NUMERO_DA_PAGINA", true));
		chckbxNumeroDaPagina.setBounds(428, 350, 208, 23);
		panel_1.add(chckbxNumeroDaPagina);

		// altera o nome das disciplinas
		ajusteInicial();
	}
}

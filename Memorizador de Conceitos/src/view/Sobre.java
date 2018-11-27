package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Madson Paulo Alexandre da Silva
 * 
 *         JFrame que exibe informações sobre o programa
 */
public class Sobre extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	String dataVersao = "26/11/2018";
	String versao = "2.0";
	String objetivo = "Este programa foi desenvolvido por Madson Paulo Alexandre da Silva, em 04/11/2018, e tem como objetivo auxiliar na memorização de conteúdos.";
	String licensa = "Pode ser usado e distribuído gratuitamente.";
	String contato = "Caso tenha sugestões ou agradecimentos, envie um e-mail para <a href=madson-paulo@hotmail.com>madson-paulo@hotmail.com</a>.";
	String downloadLink = "<a href=\\\"https://www.4shared.com/file/m6Vsxjm7da/Memorizador_de_Conceitos.html\\\">https://www.4shared.com/file/m6Vsxjm7da/Memorizador_de_Conceitos.html</a>";

	String texto = String.format("%s<br><br>%s<br><br><b>Link para download:</b> %s<br><b>Versão: </b>%s (%s)<br><br>%s",
			objetivo, licensa, downloadLink, versao, dataVersao, contato);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre frame = new Sobre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Sobre() {
		setTitle("Sobre o Aplicativo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setText(texto);
		editorPane.setOpaque(false);
		editorPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		editorPane.setEditable(false);
		contentPane.add(editorPane, BorderLayout.CENTER);
	}
}
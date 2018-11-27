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
public class SobreOsDados extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	String info1 = "Os dados deste aplicativo são salvos na memória do seu computador. Caso venha a formatar o computador, todos os dados serão perdidos.";
	String info2 = "Para evitar a perda de dados em caso de formatação, para fazer backup ou para compartilhar suas disciplinas e assuntos, utilize a opção 'Exportar Disciplinas e Assuntos', no menu Arquivo, para gerar um arquivo contendo todas as disciplinas, assuntos e configurações do aplicativo.";
	String info3 = "Para apagar todos os dados deste aplicativo, utilize a opção 'Apagar Todos os Dados', no menu Arquivo. Esta ação irá apagar tanto as suas configurações quanto as disciplinas e assuntos salvos. Considere exportar os dados antes de executar esta ação irreversível.";

	String texto = String.format("%s<br><br>%s<br><br>%s", info1, info2, info3);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SobreOsDados frame = new SobreOsDados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SobreOsDados() {
		setTitle("Sobre os Dados");
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
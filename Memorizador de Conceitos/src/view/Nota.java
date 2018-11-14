package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Madson Paulo Alexandre da Silva
 *
 *         JFrame que permite simular a nota da banca CESPE de acordo com a quantidade de acertos, nulas e erros
 */
public class Nota extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel total;
	private JLabel nota;
	private JSpinner erros;
	private JSpinner acertos;
	private JSpinner nulas;
	private JLabel porcentagem;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nota frame = new Nota();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Atualiza a quantidade total de questões, nota final e porcentagem de acertos
	 */
	private void updateValues() {
		double questoes = 1.0 * ((int) acertos.getValue() + (int) erros.getValue() + (int) nulas.getValue());
		double valorPorcentagem = 100.0 * ((int) acertos.getValue() / questoes);

		total.setText(String.format("%.0f", questoes));
		nota.setText("" + ((int) acertos.getValue() - (int) erros.getValue()));
		porcentagem.setText(String.format(Locale.US, "%.2f", valorPorcentagem));

	}

	public Nota() {
		setResizable(false);
		setTitle("Nota CESPE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 110);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTotal = new JLabel("  Total");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTotal.setFocusable(false);
		lblTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTotal.setBounds(4, 45, 55, 23);
		contentPane.add(lblTotal);

		JLabel lblAcertos = new JLabel("  Acertos");
		lblAcertos.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblAcertos.setFocusable(false);
		lblAcertos.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblAcertos.setBounds(4, 11, 55, 23);
		contentPane.add(lblAcertos);

		JLabel lblErros = new JLabel("  Erros");
		lblErros.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblErros.setFocusable(false);
		lblErros.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblErros.setBounds(240, 11, 55, 23);
		contentPane.add(lblErros);

		acertos = new JSpinner();
		acertos.setFont(new Font("Times New Roman", Font.BOLD, 13));
		acertos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateValues();
			}
		});
		acertos.setBorder(new LineBorder(new Color(0, 0, 0)));
		acertos.setBackground(Color.WHITE);
		acertos.setBounds(63, 11, 55, 23);
		contentPane.add(acertos);

		erros = new JSpinner();
		erros.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateValues();
			}
		});
		erros.setFont(new Font("Times New Roman", Font.BOLD, 13));
		erros.setBorder(new LineBorder(new Color(0, 0, 0)));
		erros.setBackground(Color.WHITE);
		erros.setBounds(299, 11, 55, 23);
		contentPane.add(erros);

		nota = new JLabel("0");
		nota.setFont(new Font("Times New Roman", Font.BOLD, 14));
		nota.setHorizontalAlignment(SwingConstants.CENTER);
		nota.setOpaque(true);
		nota.setBackground(Color.WHITE);
		nota.setFocusable(false);
		nota.setBorder(new LineBorder(new Color(0, 0, 0)));
		nota.setBounds(181, 45, 55, 23);
		contentPane.add(nota);

		JLabel lblNota = new JLabel("  Nota");
		lblNota.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNota.setFocusable(false);
		lblNota.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNota.setBounds(122, 45, 55, 23);
		contentPane.add(lblNota);

		total = new JLabel("0");
		total.setFont(new Font("Times New Roman", Font.BOLD, 14));
		total.setHorizontalAlignment(SwingConstants.CENTER);
		total.setOpaque(true);
		total.setFocusable(false);
		total.setBorder(new LineBorder(new Color(0, 0, 0)));
		total.setBackground(Color.WHITE);
		total.setBounds(63, 45, 55, 23);
		contentPane.add(total);

		JLabel lblNulas = new JLabel("  Nulas");
		lblNulas.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNulas.setFocusable(false);
		lblNulas.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNulas.setBounds(122, 11, 55, 23);
		contentPane.add(lblNulas);

		nulas = new JSpinner();
		nulas.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateValues();
			}
		});
		nulas.setFont(new Font("Times New Roman", Font.BOLD, 13));
		nulas.setBorder(new LineBorder(new Color(0, 0, 0)));
		nulas.setBackground(Color.WHITE);
		nulas.setBounds(181, 11, 55, 23);
		contentPane.add(nulas);

		JLabel lblPorcentagem = new JLabel("% Certo");
		lblPorcentagem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPorcentagem.setFocusable(false);
		lblPorcentagem.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPorcentagem.setBounds(240, 45, 55, 23);
		contentPane.add(lblPorcentagem);

		porcentagem = new JLabel("0.00");
		porcentagem.setOpaque(true);
		porcentagem.setHorizontalAlignment(SwingConstants.CENTER);
		porcentagem.setFont(new Font("Times New Roman", Font.BOLD, 14));
		porcentagem.setFocusable(false);
		porcentagem.setBorder(new LineBorder(new Color(0, 0, 0)));
		porcentagem.setBackground(Color.WHITE);
		porcentagem.setBounds(299, 45, 55, 23);
		contentPane.add(porcentagem);
	}
}
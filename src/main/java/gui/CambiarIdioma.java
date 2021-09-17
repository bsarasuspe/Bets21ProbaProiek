package gui;


import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CambiarIdioma {

	private JFrame frame;
	private final JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CambiarIdioma"));//najshiushad
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnEuskera;
	private JRadioButton rdbtnCastellano;
	private JRadioButton rdbtnInglis;
	private InicioGUI retorno;

	/**
	 * Create the application.
	 */
	public CambiarIdioma(InicioGUI retorno) {
		this.retorno=retorno;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		retorno.setVisible(false);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnEuskera.isSelected()) {
					Locale.setDefault(new Locale("eus"));
				}
				if (rdbtnInglis.isSelected()) {
					Locale.setDefault(new Locale("en"));
				}
				if (rdbtnCastellano.isSelected()) {
					Locale.setDefault(new Locale("es"));
				}
				retorno.idioma();
				retorno.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(68, 179, 312, 58);
		frame.getContentPane().add(btnNewButton);
		
		rdbtnEuskera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Eus"));
		buttonGroup.add(rdbtnEuskera);
		rdbtnEuskera.setBounds(133, 8, 149, 23);
		frame.getContentPane().add(rdbtnEuskera);
		
		rdbtnCastellano = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Cas"));
		buttonGroup.add(rdbtnCastellano);
		rdbtnCastellano.setBounds(133, 64, 149, 23);
		frame.getContentPane().add(rdbtnCastellano);
		
		rdbtnInglis = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Ing"));
		buttonGroup.add(rdbtnInglis);
		rdbtnInglis.setBounds(133, 118, 149, 23);
		frame.getContentPane().add(rdbtnInglis);
		if (Locale.getDefault().toString().equals("es_ES")) {
			rdbtnCastellano.setSelected(true);
		}
		if (Locale.getDefault().toString().equals("eus")) {
			rdbtnEuskera.setSelected(true);
		}
		if (Locale.getDefault().toString().equals("en")) {
			rdbtnInglis.setSelected(true);
		}
	}
}

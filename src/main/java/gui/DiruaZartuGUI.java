package gui;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;

public class DiruaZartuGUI {

	private JFrame frame;
	private JTextField textDirukantitatea;
	private double asierakoDirua;
	InicioGUI buelta;


	/**
	 * Create the application.
	 */
	public DiruaZartuGUI(double asierakoDirua, InicioGUI buelta) {
		this.asierakoDirua=asierakoDirua;
		this.buelta=buelta;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textDirukantitatea = new JTextField();
		textDirukantitatea.setHorizontalAlignment(SwingConstants.CENTER);
		textDirukantitatea.setBounds(10, 46, 264, 20);
		frame.getContentPane().add(textDirukantitatea);
		textDirukantitatea.setColumns(10);
		
		JLabel lblErrorea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblErrorea1"));
		lblErrorea.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorea.setForeground(Color.RED);
		lblErrorea.setBounds(10, 25, 264, 20);
		lblErrorea.setVisible(false);
		frame.getContentPane().add(lblErrorea);
	
		
		JButton btnDiruaZartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnDiruaZartu"));
		btnDiruaZartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				
				double tmp=Double.parseDouble(textDirukantitatea.getText());
				//frame.setVisible(false);
				if (tmp<0) {
					lblErrorea.setText(ResourceBundle.getBundle("Etiquetas").getString("lblErrorea2"));
					lblErrorea.setVisible(true);
				}else {
					buelta.diruagehitu(tmp);
					frame.dispose();
				}
				}catch (java.lang.NullPointerException e) {
					buelta.diruagehitu(0);
					frame.dispose();
				}catch (java.lang.NumberFormatException e) {
					lblErrorea.setText(ResourceBundle.getBundle("Etiquetas").getString("lblErrorea3"));
					lblErrorea.setVisible(true);
				}
				
			}
		});
		btnDiruaZartu.setBounds(71, 77, 148, 23);
		frame.getContentPane().add(btnDiruaZartu);
		
		JLabel lblDiruKantitatea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblDiruKantitatea")+Double.toString(asierakoDirua));
		lblDiruKantitatea.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiruKantitatea.setBounds(10, 11, 264, 14);
		frame.getContentPane().add(lblDiruKantitatea);
		frame.setVisible(true);
	}
}

package gui;


import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.User;
import exceptions.MismachingUseraAndPasword;
import exceptions.UserDoesNotExist;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Login {

	private JFrame frame;
	private JPasswordField pwdPasaitza;
	private JTextField txtUsuarioa;
	private InicioGUI origen;	

	/**
	 * Create the application.
	 */
	public Login(InicioGUI nondikdeitu) {
		origen=nondikdeitu;
		origen.setVisible(false);
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		pwdPasaitza = new JPasswordField();
		pwdPasaitza.setBounds(446, 69, 117, 19);
		frame.getContentPane().add(pwdPasaitza);
		
		JLabel lblPasahitza = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblPasahitza"));
		lblPasahitza.setBounds(358, 71, 70, 15);
		frame.getContentPane().add(lblPasahitza);
		
		txtUsuarioa = new JTextField();
		txtUsuarioa.setBounds(147, 69, 114, 19);
		frame.getContentPane().add(txtUsuarioa);
		txtUsuarioa.setColumns(10);
		
		JLabel lblUsuarioa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblUsuarioa"));
		lblUsuarioa.setBounds(59, 71, 70, 15);
		frame.getContentPane().add(lblUsuarioa);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 101, 564, 14);
		lblNewLabel.setVisible(false);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogeatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnLogeatu"));
		btnLogeatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade facade = MainGUI.getBusinessLogic();
				String teporal= String.valueOf(pwdPasaitza.getPassword());
				if (txtUsuarioa.getText().equals("")) {
					lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("lblNewLabel1"));
					lblNewLabel.setVisible(true);
					
				}else if (teporal.equals("")) {
					lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("lblNewLabel2"));
					lblNewLabel.setVisible(true);
				}
				else {
					try {
					User momento=facade.login(txtUsuarioa.getText(), teporal);
					frame.setVisible(false);
					origen.loged(momento);
					}catch (UserDoesNotExist e) {
						lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("lblNewLabel3"));
						lblNewLabel.setVisible(true);
					}
					catch (MismachingUseraAndPasword e) {
						lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("lblNewLabel4"));
						lblNewLabel.setVisible(true);
					}
				}
			}
		});
		btnLogeatu.setBounds(240, 126, 117, 25);
		frame.getContentPane().add(btnLogeatu);
		
		JButton btnErregistratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnErregistratu"));
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				new Register(origen);
			}
		});
		btnErregistratu.setBounds(403, 12, 159, 25);
		frame.getContentPane().add(btnErregistratu);
		

	}
}


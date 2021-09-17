package gui;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import exceptions.UserAlreadyExist;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Register {

	private JFrame frame;
	private JTextField IzenaSartu;
	private JTextField IdSartu;
	private JTextField EmailSartu;
	private JTextField PasaitzaSartu;
	private JTextField Urtea;
	private JTextField BankuZenbakiaSartu;
	private InicioGUI origen;
	private JButton btnLogeatu;
	private JButton btnErregistratu;
	private JLabel Errores;
	private JTextField hilabetea;
	private JTextField eguna;
	private JLabel lblNewLabel_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the application.
	 */
	public Register(InicioGUI nondikdeitu) {
		origen=nondikdeitu;
		origen.setVisible(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		IzenaSartu = new JTextField();
		IzenaSartu.setBounds(246, 96, 114, 19);
		frame.getContentPane().add(IzenaSartu);
		IzenaSartu.setColumns(10);
		
		JLabel lblIzena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblIzena"));
		lblIzena.setBounds(96, 98, 70, 15);
		frame.getContentPane().add(lblIzena);
		
		JLabel lblNan = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblNan"));
		lblNan.setBounds(96, 171, 70, 15);
		frame.getContentPane().add(lblNan);
		
		JLabel lblPasahitza = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblPasahitza"));
		lblPasahitza.setBounds(96, 305, 92, 15);
		frame.getContentPane().add(lblPasahitza);
		
		JLabel lblEmail = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblEmail"));
		lblEmail.setBounds(96, 236, 70, 15);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblJaiotzaEguna = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblJaiotzaEguna"));
		lblJaiotzaEguna.setBounds(10, 370, 189, 15);
		frame.getContentPane().add(lblJaiotzaEguna);
		
		JLabel lblBankuZenbakia = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblBankuZenbakia"));
		lblBankuZenbakia.setBounds(96, 420, 122, 15);
		frame.getContentPane().add(lblBankuZenbakia);
		
		IdSartu = new JTextField();
		IdSartu.setBounds(246, 169, 114, 19);
		frame.getContentPane().add(IdSartu);
		IdSartu.setColumns(10);
		
		EmailSartu = new JTextField();
		EmailSartu.setBounds(246, 234, 114, 19);
		frame.getContentPane().add(EmailSartu);
		EmailSartu.setColumns(10);
		
		PasaitzaSartu = new JTextField();
		PasaitzaSartu.setBounds(246, 303, 114, 19);
		frame.getContentPane().add(PasaitzaSartu);
		PasaitzaSartu.setColumns(10);
		
		Urtea = new JTextField();
		Urtea.setBounds(192, 367, 57, 19);
		frame.getContentPane().add(Urtea);
		Urtea.setColumns(10);
		
		BankuZenbakiaSartu = new JTextField();
		BankuZenbakiaSartu.setBounds(246, 418, 114, 19);
		frame.getContentPane().add(BankuZenbakiaSartu);
		BankuZenbakiaSartu.setColumns(10);
		
		Errores = new JLabel("New label");
		Errores.setHorizontalAlignment(SwingConstants.CENTER);
		Errores.setForeground(Color.RED);
		Errores.setBounds(29, 468, 395, 14);
		Errores.setVisible(false);
		frame.getContentPane().add(Errores);
		
		hilabetea = new JTextField();
		hilabetea.setBounds(259, 367, 38, 20);
		frame.getContentPane().add(hilabetea);
		hilabetea.setColumns(10);
		
		eguna = new JTextField();
		eguna.setBounds(310, 367, 40, 20);
		frame.getContentPane().add(eguna);
		eguna.setColumns(10);
		
		JRadioButton UserSelect = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("UserSelect"));
		buttonGroup.add(UserSelect);
		UserSelect.setBounds(82, 525, 109, 23);
		frame.getContentPane().add(UserSelect);
		
		JRadioButton AdminSelect = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("AdminSelect"));
		buttonGroup.add(AdminSelect);
		AdminSelect.setBounds(193, 525, 92, 23);
		frame.getContentPane().add(AdminSelect);
		
		JRadioButton WorkerSelect = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("WorkerSelect"));
		buttonGroup.add(WorkerSelect);
		WorkerSelect.setBounds(296, 525, 109, 23);
		frame.getContentPane().add(WorkerSelect);
		
		
		btnErregistratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnErregistratu"));
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((IzenaSartu.getText().equals("")||IdSartu.getText().equals("")||EmailSartu.getText().equals("")||PasaitzaSartu.getText().equals("")||Urtea.getText().equals("")||BankuZenbakiaSartu.getText().equals("")||hilabetea.getText().equals("")||eguna.getText().equals(""))) {
					Errores.setText(ResourceBundle.getBundle("Etiquetas").getString("Errores1"));
					Errores.setVisible(true);
				}else if (!(UserSelect.isSelected()||AdminSelect.isSelected()||WorkerSelect.isSelected())){
					Errores.setText(ResourceBundle.getBundle("Etiquetas").getString("Errores2"));
					Errores.setVisible(true);
				}else {
					int ano,mes,dia;
					long numbank;
					try {
						ano=Integer.parseInt(Urtea.getText());
						try {
							mes=Integer.parseInt(hilabetea.getText());
							try {
								dia=Integer.parseInt(eguna.getText());
								try {
									numbank=Long.parseLong(BankuZenbakiaSartu.getText());
									BLFacade facade = MainGUI.getBusinessLogic();
									try {
										if (ano>1900&&mes>=0&&dia>=0&&dia<=31&&mes<=12&&ano<Calendar.getInstance().get(Calendar.YEAR)-18) {
											if (BankuZenbakiaSartu.getText().matches("^[0-9]{10,12}$")) {
												if (UserSelect.isSelected()) {
													facade.register(IzenaSartu.getText(),IdSartu.getText(), EmailSartu.getText(), PasaitzaSartu.getText(), ano, mes, dia , numbank,2);
												}
												if (AdminSelect.isSelected()) {
													facade.register(IzenaSartu.getText(),IdSartu.getText(), EmailSartu.getText(), PasaitzaSartu.getText(), ano, mes, dia , numbank,0);
												}
												if (WorkerSelect.isSelected()) {
													facade.register(IzenaSartu.getText(),IdSartu.getText(), EmailSartu.getText(), PasaitzaSartu.getText(), ano, mes, dia , numbank,1);
												}
												origen.setVisible(true);
												frame.setVisible(false);
											}else {
													Errores.setText(ResourceBundle.getBundle("Etiquetas").getString("Errores3"));
													Errores.setVisible(true);
												}
										}else {
											Errores.setText(ResourceBundle.getBundle("Etiquetas").getString("Errores4"));
											Errores.setVisible(true);
										}
										
									}catch (UserAlreadyExist e) {
										Errores.setText(ResourceBundle.getBundle("Etiquetas").getString("Errores5"));
										Errores.setVisible(true);
									}
								} catch (java.lang.NumberFormatException e) {
									Errores.setText(ResourceBundle.getBundle("Etiquetas").getString("Errores6"));
									Errores.setVisible(true);
								}
							} catch (java.lang.NumberFormatException e) {
								Errores.setText(ResourceBundle.getBundle("Etiquetas").getString("Errores7"));
								Errores.setVisible(true);
							}
						} catch (java.lang.NumberFormatException e) {
							Errores.setText(ResourceBundle.getBundle("Etiquetas").getString("Errores8"));
							Errores.setVisible(true);
						}
					} catch ( java.lang.NumberFormatException e) {
						Errores.setText(ResourceBundle.getBundle("Etiquetas").getString("Errores9"));
						Errores.setVisible(true);
					}
				}
				
			}
		});
		btnErregistratu.setBounds(180, 493, 117, 25);
		frame.getContentPane().add(btnErregistratu);
		
		btnLogeatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnLogeatu"));
		btnLogeatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				new Login(origen);
			}
		});
		btnLogeatu.setBounds(321, 12, 117, 25);
		frame.getContentPane().add(btnLogeatu);
		
		
		
		JLabel lblNewLabel = new JLabel("/");
		lblNewLabel.setBounds(250, 370, 11, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("/");
		lblNewLabel_1.setBounds(300, 370, 11, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
	}
}

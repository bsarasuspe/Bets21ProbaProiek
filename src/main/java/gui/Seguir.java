package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import domain.RegisteredUser;
import exceptions.JarraitzenZenuenException;
import exceptions.MutualFollowingException;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class Seguir {

	private JFrame frame;
	private JTextField nombrepersonasegur;
	private JTextField Portzentagea;
	private JLabel porcentaje;
	private JButton btnAktualizatu;
	private JButton btnItxi;
	private JButton eliminar;
	private JComboBox<String> comboBox;
	private JCheckBox chckbxNewCheckBox;
	private JButton btnMultiusos;
	private JLabel lblMenaje;
	private RegisteredUser usuario;
	private InicioGUI entrada;
	private JTextField txtKopiatzeko;
	private JLabel label;
	private businessLogic.BLFacade BLFacade;
	private boolean enprocesao=false;
	private Timer timer;
	
	
	/**
	 * Create the application.
	 */
	public Seguir(RegisteredUser usuario,InicioGUI entrada,businessLogic.BLFacade BLFacade) {
		this.usuario=usuario;
		this.entrada=entrada;
		this.BLFacade=BLFacade;
		initialize();
		entrada.setVisible(false);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		timer = new Timer (10000, new ActionListener ()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        enprocesao=false;
		        btnMultiusos.setText("Gehitu");
		        lblMenaje.setText("");
		     }
		});
		
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 290, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		nombrepersonasegur = new JTextField();
		nombrepersonasegur.setText("Pertzonaren Izena");
		nombrepersonasegur.setBounds(12, 123, 154, 19);
		frame.getContentPane().add(nombrepersonasegur);
		nombrepersonasegur.setColumns(10);
		
		lblMenaje = new JLabel("");
		lblMenaje.setBounds(12, 106, 278, 15);
		frame.getContentPane().add(lblMenaje);
		
		btnMultiusos = new JButton("Gehitu");
		btnMultiusos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (enprocesao) {
					timer.stop();
					timer.restart();
					try {
						//BLFacade.jarraitu(usuario, nombrepersonasegur.getText(), Integer.parseInt(txtKopiatzeko.getText()));
						usuario= BLFacade.jarraitu(usuario, nombrepersonasegur.getText(), Integer.parseInt(txtKopiatzeko.getText()));
						lblMenaje.setText("eginda");
					} catch (NumberFormatException e1) {
						lblMenaje.setText("Senbaki formato desegokia");
					} catch (MutualFollowingException e1) {
						lblMenaje.setText("Pertzona onek suri jada jarraitzen dizu");
					} catch (JarraitzenZenuenException e1) {
						lblMenaje.setText("Pertzona hau jada jarritzen zenuen");
					}
					enprocesao=false;
				}else{
					try {
						if (nombrepersonasegur.getText().equals(usuario.getUsername())) {
							lblMenaje.setText("Ezin zara zu zeu jaraitu");
						}else {
							RegisteredUser tmp =BLFacade.conseguirusuario(nombrepersonasegur.getText());
							if (tmp.isJarraitzeunutzi()) {
								lblMenaje.setText("Pertzona onek %"+tmp.getComision()+"eskatzen du");
								btnMultiusos.setText("Confirmatu");
								timer.restart();
								timer.start();
								enprocesao=true;
							}else {
								lblMenaje.setText("Pertzona onek ez ditu pertzonak jaraitzen uzten");
							}
						}
					}catch (NullPointerException ex) {
						lblMenaje.setText("Sartu duzun usuarioa ez da existitzen");
					}
				}
			}
		});
		btnMultiusos.setBounds(12, 154, 246, 25);
		frame.getContentPane().add(btnMultiusos);
		
		chckbxNewCheckBox = new JCheckBox("Jarraitzen Utzi");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAktualizatu.setEnabled(true);
			}
		});
		if (usuario.isJarraitzeunutzi()) {
			chckbxNewCheckBox.setSelected(true);
		}else {
			chckbxNewCheckBox.setSelected(false);
		}
		chckbxNewCheckBox.setBounds(12, 8, 129, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		Portzentagea = new JTextField();
		Portzentagea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAktualizatu.setEnabled(true);
			}
		});
		Portzentagea.setText(Integer.toString(usuario.getComision()));
		Portzentagea.setBounds(12, 39, 114, 19);
		frame.getContentPane().add(Portzentagea);
		Portzentagea.setColumns(10);
		
		porcentaje = new JLabel("% comisioa");
		porcentaje.setBounds(132, 41, 78, 15);
		frame.getContentPane().add(porcentaje);
		
		btnAktualizatu = new JButton("Aktualizatu");
		btnAktualizatu.setEnabled(false);
		btnAktualizatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuario =BLFacade.apgarseguir(usuario);
				if (chckbxNewCheckBox.isSelected()) {
					try {
						usuario =BLFacade.encenderseguir(usuario, Integer.parseInt(Portzentagea.getText()));
					}catch (NumberFormatException e) {
						lblMenaje.setText("Portzentaia egoki bat ipini");
					}
				}
			}
		});
		btnAktualizatu.setBounds(12, 67, 117, 25);
		frame.getContentPane().add(btnAktualizatu);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(12, 194, 246, 24);
		frame.getContentPane().add(comboBox);
		for (String i: BLFacade.conseguirusuario(usuario.getUsername()).infoseguidos()) {
			comboBox.addItem(i);
		}
		
		eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (-1!=comboBox.getSelectedIndex()) {
					usuario = BLFacade.unfollow(usuario, BLFacade.conseguirusuario(usuario.getUsername()).getJarraitzenditut().get(comboBox.getSelectedIndex()));
					comboBox.removeAllItems();
					for (String i: usuario.infoseguidos()) {
						comboBox.addItem(i);
					}
				}
			}
		});
		eliminar.setBounds(12, 226, 117, 25);
		frame.getContentPane().add(eliminar);
		
		btnItxi = new JButton("Itxi");
		btnItxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entrada.setVisible(true);
				frame.dispose();
			}
		});
		btnItxi.setBounds(141, 226, 117, 25);
		frame.getContentPane().add(btnItxi);
		
		JLabel rallaDeDivision = new JLabel("___________________________________________________");
		rallaDeDivision.setBounds(0, 85, 311, 15);
		frame.getContentPane().add(rallaDeDivision);
		
		txtKopiatzeko = new JTextField();
		txtKopiatzeko.setText("Kopiat %");
		txtKopiatzeko.setBounds(178, 123, 78, 19);
		frame.getContentPane().add(txtKopiatzeko);
		txtKopiatzeko.setColumns(10);
		
		label = new JLabel("%");
		label.setBounds(266, 125, 24, 15);
		frame.getContentPane().add(label);
	}
}

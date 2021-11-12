package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import exceptions.IncorrectBetException;
import patroiak.UserAdapter;

public class apustuDatuakGUI extends JFrame{

	private JFrame frame;
	private InicioGUI origen;
	private JButton itzuli;
	private RegisteredUser usuario;
	private DefaultTableModel modelDatuak; 
	private JTable tableDatuak; 
	private businessLogic.BLFacade BLFacade;
	private JScrollPane scrollPane;
	
	/**
	 * Create the application.
	 */
	public apustuDatuakGUI(InicioGUI origen,RegisteredUser usuario,businessLogic.BLFacade BLFacade) {
		this.BLFacade=BLFacade;
		this.origen=origen;
		this.usuario=usuario;
		UserAdapter adaptadorea = new UserAdapter(usuario);
		this.tableDatuak = adaptadorea.getTableDatuak();
		this.modelDatuak = adaptadorea.getModelDatuak();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		scrollPane.setViewportView(tableDatuak);
		
		tableDatuak.setBounds(144, 191, 1, 1);
		tableDatuak.setModel(modelDatuak);
		
		itzuli = new JButton("Itzuli");
		itzuli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				jButtonClose_actionPerformed(arg0);
			}
		});
		itzuli.setBounds(12, 178, 516, 25);
		frame.getContentPane().add(itzuli);
		
		
		
		frame.setVisible(true);
	}
	
	
	public void apagar() {
		frame.dispose();
	}
	
	
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		origen.setVisible(true);
	}
}

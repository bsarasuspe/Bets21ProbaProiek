package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import exceptions.IncorrectBetException;
import patroiak.UserAdapter;

public class apustuDatuakGUI extends JFrame {

	private JFrame frame;
	private InicioGUI origen;
	private JButton itzuli;
	private RegisteredUser usuario;
	private DefaultTableModel modelDatuak;
	private JTable tableDatuak;
	private businessLogic.BLFacade BLFacade;
	private apustuDatuakGUI nireFrame;

	/**
	 * Create the application.
	 */
	public apustuDatuakGUI(InicioGUI origen, RegisteredUser usuario, businessLogic.BLFacade BLFacade) {
		this.BLFacade = BLFacade;
		this.origen = origen;
		this.usuario = usuario;
		this.setTitle("Ikusi apostuak");
		UserAdapter adaptadorea = new UserAdapter(usuario);
		this.tableDatuak = adaptadorea.getTableDatuak();
		this.modelDatuak = adaptadorea.getModelDatuak();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		nireFrame = this;
		this.setSize(new Dimension(450, 340));
		tableDatuak.setBounds(144, 191, 1, 1);
		tableDatuak.setModel(modelDatuak);
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane(tableDatuak);
		scrollPane.setBounds(0, 0, 436, 263);
		getContentPane().add(scrollPane);
		
		JButton itzuliButton = new JButton("Itzuli");
		itzuliButton.setBounds(173, 273, 85, 21);
		getContentPane().add(itzuliButton);
		
		itzuliButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				origen.setVisible(true);
				nireFrame.setVisible(false);
			}
		});
	}

	public void apagar() {
		frame.dispose();
	}

}

package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacadeImplementation;
import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import exceptions.IncorrectBetException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MultibetingGUI {

	private JFrame frame;
	private InicioGUI origen;
	private JButton btnEliminar;
	private businessLogic.BLFacade BLFacade;
	private JLabel labelDineroTotalGanado;
	private JLabel labelMultiplicador;
	//
	private double DiruKantitaea;
	private RegisteredUser usuario;
	private Vector<Pronostico>  pronostico;
	private double multiplicador;
	//
	
	//
	private DefaultTableModel tableModelPronotiko;//tableModelEvents
	private JScrollPane scrollPane;
	private JTable tableQueries;
	private String[] columnNamesPronotiko = new String[] {//columnNamesEvents
			ResourceBundle.getBundle("Etiquetas").getString("EventoDeLaApuesta"),
			ResourceBundle.getBundle("Etiquetas").getString("PreguntaDeLaApuesta"), 
			ResourceBundle.getBundle("Etiquetas").getString("PronoticoRespuesta"), 
			ResourceBundle.getBundle("Etiquetas").getString("PronoticoCuota"), 
	};
	private JButton btnNewButton;
	//
	
	/**
	 * Create the application.
	 */
	public MultibetingGUI(InicioGUI origen,RegisteredUser usuario,businessLogic.BLFacade BLFacade) {
		this.multiplicador=1;
		this.BLFacade=BLFacade;
		this.origen=origen;
		this.DiruKantitaea=0;
		this.usuario=usuario;
		pronostico=new Vector<Pronostico>();
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 826, 169);
		frame.getContentPane().add(scrollPane);
		
		btnEliminar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnEliminar"));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tableQueries.getSelectedRow()>=0&tableQueries.getSelectedRow()<pronostico.size()) {
					multiplicador/=pronostico.get(tableQueries.getSelectedRow()).getCuota();
					actualizar();
					pronostico.remove(tableQueries.getSelectedRow());
					tableModelPronotiko.removeRow(tableQueries.getSelectedRow());
				}
			}
		});
		btnEliminar.setBounds(12, 220, 516, 25);
		frame.getContentPane().add(btnEliminar);
		
		
		//
		tableQueries=new JTable();
		tableModelPronotiko=new DefaultTableModel(null, columnNamesPronotiko);
		//tableModelPronotiko.setDataVector(null, columnNamesPronotiko);
		tableModelPronotiko.setColumnCount(4);
		scrollPane.setViewportView(tableQueries);
		tableQueries.setModel(tableModelPronotiko);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnNewButton"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					origen.loged(BLFacade.ApostuaEgin(DiruKantitaea, usuario, pronostico));
					multiplicador=0;
					for(int i=0;i<pronostico.size();i++) {
						tableModelPronotiko.removeRow(0);
					}
					pronostico.removeAllElements();
					actualizar();
				} catch (IncorrectBetException e) {
					labelDineroTotalGanado.setForeground(new Color(255,0,0));
					labelDineroTotalGanado.setText(ResourceBundle.getBundle("Etiquetas").getString("labelDineroTotalGanado1"));
				}
			}
		});
		btnNewButton.setBounds(12, 178, 516, 25);
		frame.getContentPane().add(btnNewButton);
		
		labelMultiplicador = new JLabel("");
		labelMultiplicador.setBounds(537, 183, 301, 15);
		frame.getContentPane().add(labelMultiplicador);
		
		labelDineroTotalGanado = new JLabel("");
		labelDineroTotalGanado.setBounds(537, 220, 301, 15);
		frame.getContentPane().add(labelDineroTotalGanado);
		
		frame.setVisible(true);
	}
	
	public void apostuaGeitu(double DiruKantitaea,Pronostico pronostico) {
		if (this.pronostico.contains(pronostico)) {
			labelDineroTotalGanado.setText(ResourceBundle.getBundle("Etiquetas").getString("labelDineroTotalGanado2"));
			labelMultiplicador.setText("");
		}else {
		this.DiruKantitaea=DiruKantitaea;
		this.pronostico.add(pronostico);
		Vector<String> row = new Vector<String>();
		Question galdera = BLFacade.GalderaLortu(pronostico);
		row.add(BLFacade.EbentuaLortu(galdera).getDescription());
		row.add(galdera.getQuestion());
		row.add(pronostico.getErantzuna());
		row.add(Double.toString(pronostico.getCuota()));
		tableModelPronotiko.addRow(row);
		this.multiplicador*=pronostico.getCuota();
		actualizar();
		}
	}
	public void apagar() {
		frame.dispose();
	}
	
	private void actualizar() {
		labelDineroTotalGanado.setForeground(new Color(51,51,51));
		labelDineroTotalGanado.setText((ResourceBundle.getBundle("Etiquetas").getString("labelDineroTotalGanado3"))  +"= "+this.DiruKantitaea*this.multiplicador);
		labelMultiplicador.setText((ResourceBundle.getBundle("Etiquetas").getString("labelMultiplicador"))+ "= "+this.multiplicador);
	}
}

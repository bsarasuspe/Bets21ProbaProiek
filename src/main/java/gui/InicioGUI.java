package gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JCalendar;
import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Admin;
import domain.Pronostico;
import domain.Question;
import domain.RegisteredUser;
import domain.User;
import domain.Worker;
import exceptions.IncorrectBetException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;

public class InicioGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	BLFacade facade;
	private final JButton BotonRegister = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BotonRegister")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton BotonLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BotonLogin")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton Administrar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Administrar")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton LogOut = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LogOut")); //$NON-NLS-1$ //$NON-NLS-2$
	private JButton btnDiruaSartu;
	private RegisteredUser usuario;
	private JLabel lblBalanzea ;
	private JTextField textFieldDirua;
	private JButton btnApostua;
	private JComboBox <Pronostico> comboBoxPronostico; 
	private JLabel lblErrores;
	private JLabel lblPronostikoa;
	private JButton btnMultibeting;
	private boolean multibeting=false;
	private MultibetingGUI multibetingGUI;
	private JButton btnC;
	private JButton btnJarraitu;
	
	
	public InicioGUI()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit() throws Exception
	{
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(900, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries")); //$NON-NLS-1$ //$NON-NLS-2$

		jLabelEventDate.setBounds(new Rectangle(42, 57, 140, 25));
		jLabelQueries.setBounds(42, 266, 406, 14);
		jLabelEvents.setBounds(297, 61, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(274, 419, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(42, 92, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();///////////////////////////////////////////////////////////////////////////////
		this.facade=facade;
		InicioGUI tempora=this;
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
		Administrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
				tempora.setVisible(false);
				new Administer(tempora).setVisible(true);
			}
		});

		
		
		Administrar.setBounds(42, 11, 117, 23);
		Administrar.setVisible(false);
		getContentPane().add(Administrar);
		
		btnApostua = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnApostua"));
		btnApostua.setVisible(false);
		btnApostua.setEnabled(false);
		btnApostua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double dirukantitatea;
				reset();
				try {
					dirukantitatea=Double.parseDouble(textFieldDirua.getText());
				}
			catch (java.lang.NumberFormatException e) {
				lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("lblErrores1"));
				return;
			}catch (java.lang.NullPointerException e) {
				dirukantitatea=0;
			}
				
				if (usuario.getBalance()<dirukantitatea) {
					lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("lblErrores2"));
					return;
				}
				
				if (multibeting) {
					multibetingGUI.apostuaGeitu(dirukantitatea, (Pronostico)comboBoxPronostico.getSelectedItem());
				}else {
				
					try {
						loged(facade.ApostuaEgin(dirukantitatea, usuario, (Pronostico)comboBoxPronostico.getSelectedItem()));
					}
					catch (IncorrectBetException e) {
						lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("lblErrores3"));
					}
				}
			}
		});
		btnApostua.setBounds(551, 419, 130, 30);
		getContentPane().add(btnApostua);
		
		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(294, 92, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(42, 292, 406, 116));

		
		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnApostua.setEnabled(false);
				
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());
				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();
					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBoxPronostico.removeAllItems();
				List<Pronostico> tmp=facade.PronostikoakLortu((int)tableQueries.getValueAt(tableQueries.getSelectedRow(),0));
				for (Pronostico i : tmp) {
					comboBoxPronostico.addItem(i);
				}
				/**if (tmp.size()>0) {
					btnApostua.setEnabled(true);
				}**/
			}
		});


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		//InicioGUI temporal=this;
		BotonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
				new Register(tempora);
			}
		});
		BotonRegister.setBounds(551, 11, 89, 23);
		
		getContentPane().add(BotonRegister);
		BotonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
				new Login(tempora);
			}
		});
		BotonLogin.setBounds(452, 11, 89, 23);
		
		getContentPane().add(BotonLogin);
		LogOut.setVisible(false);
		LogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
		});
		LogOut.setBounds(178, 11, 121, 23);
		
		getContentPane().add(LogOut);
		
		btnDiruaSartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnDiruaSartu")); //$NON-NLS-1$ //$NON-NLS-2$
		btnDiruaSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
				tempora.setVisible(false);
				new DiruaZartuGUI(usuario.getBalance(), tempora);
			}
		});
		btnDiruaSartu.setBounds(452, 39, 117, 25);
		btnDiruaSartu.setVisible(false);
		getContentPane().add(btnDiruaSartu);
		
		lblBalanzea = new JLabel("");
		lblBalanzea.setBounds(318, 19, 70, 15);
		lblBalanzea.setVisible(false);
		getContentPane().add(lblBalanzea);
		
		comboBoxPronostico = new JComboBox <Pronostico> ();
		comboBoxPronostico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxPronostico.getItemCount()<1) {
					btnApostua.setEnabled(false);
				}else {
					if(((Pronostico)comboBoxPronostico.getSelectedItem()).getEstado()) {
						btnApostua.setEnabled(false);
					}else {
						btnApostua.setEnabled(true);
					}
				}
			}
		});
		
		comboBoxPronostico.setBounds(511, 295, 346, 22);
		getContentPane().add(comboBoxPronostico);
		
		textFieldDirua = new JTextField("");
		textFieldDirua.setVisible(false);
		textFieldDirua.setBounds(511, 343, 346, 20);
		getContentPane().add(textFieldDirua);
		textFieldDirua.setColumns(10);
		
		
		lblPronostikoa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblPronostikoa"));
		lblPronostikoa.setBounds(511, 266, 346, 14);
		getContentPane().add(lblPronostikoa);
		
		lblErrores = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		lblErrores.setForeground(Color.RED);
		lblErrores.setBounds(511, 374, 346, 14);
		getContentPane().add(lblErrores);
		
		btnC = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CambiarIdioma")); //$NON-NLS-1$ //$NON-NLS-2$
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CambiarIdioma(tempora);
			}
		});
		btnC.setBounds(671, 10, 173, 25);
		getContentPane().add(btnC);
		
		btnMultibeting = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Multibrting"));
		btnMultibeting.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if (multibeting) {
					multibeting=false;
					multibetingGUI.apagar();
					multibetingGUI=null;
					btnApostua.setText(ResourceBundle.getBundle("Etiquetas").getString("btnApostua"));
					btnMultibeting.setText(ResourceBundle.getBundle("Etiquetas").getString("Multibrting"));
					btnDiruaSartu.setEnabled(true);
					LogOut.setEnabled(true);
					btnC.setEnabled(true);
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}else {
					multibeting=true;
					multibetingGUI= new MultibetingGUI(tempora,usuario,facade);
					btnMultibeting.setText(ResourceBundle.getBundle("Etiquetas").getString("Monobrting"));
					btnApostua.setText(ResourceBundle.getBundle("Etiquetas").getString("btnApostua2"));
					btnDiruaSartu.setEnabled(false);
					LogOut.setEnabled(false);
					btnC.setEnabled(false);
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});
		btnMultibeting.setBounds(725, 422, 130, 30);
		btnMultibeting.setVisible(false);
		getContentPane().add(btnMultibeting);
		
		btnJarraitu = new JButton("jaraitu"); //$NON-NLS-1$ //$NON-NLS-2$
		btnJarraitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Seguir(usuario, tempora, facade);
			}
		});
		btnJarraitu.setBounds(584, 39, 117, 25);
		btnJarraitu.setVisible(false); 
		getContentPane().add(btnJarraitu);
		
		
		
		

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		if (multibeting) {
			multibetingGUI.apagar();
		}
		this.dispose();
	}
	
	public void loged (User user) {
		reset();
		LogOut.setVisible(true);
		BotonLogin.setVisible(false);
		BotonRegister.setVisible(false);
		this.setVisible(true);
		if (user instanceof Worker||user instanceof Admin) {
			Administrar.setVisible(true);
		}
		if (user instanceof RegisteredUser) {
			this.usuario=(RegisteredUser)user;
			btnDiruaSartu.setVisible(true);
			lblBalanzea.setText(Double.toString(usuario.getBalance()));
			lblBalanzea.setVisible(true);
			textFieldDirua.setVisible(true);
			btnApostua.setVisible(true);
			btnMultibeting.setVisible(true);
			btnJarraitu.setVisible(true);
		}
	}
	
	public void logout () {
		reset();
		LogOut.setVisible(false);
		BotonLogin.setVisible(true);
		BotonRegister.setVisible(true);
		Administrar.setVisible(false);
		this.usuario=null;
		lblBalanzea.setText("");
		lblBalanzea.setVisible(false);
		btnDiruaSartu.setVisible(false);
		textFieldDirua.setVisible(false);
		btnApostua.setVisible(false);
		btnMultibeting.setVisible(false);
		btnJarraitu.setVisible(false);
	}
	
	public void diruagehitu(double sebat) {
		reset();
		this.setVisible(true);
		loged(facade.diruaGeitu(usuario, sebat));
	}
	
	public void reset() {
		lblErrores.setText("");
	}
	
	public void idioma() {
		jLabelEventDate.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
		jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
		jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		BotonRegister.setText(ResourceBundle.getBundle("Etiquetas").getString("BotonRegister"));
		BotonLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("BotonLogin"));
		Administrar.setText(ResourceBundle.getBundle("Etiquetas").getString("Administrar"));
		LogOut.setText(ResourceBundle.getBundle("Etiquetas").getString("LogOut"));
		btnDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("btnDiruaSartu"));
		btnApostua.setText(ResourceBundle.getBundle("Etiquetas").getString("btnApostua"));
		lblPronostikoa.setText(ResourceBundle.getBundle("Etiquetas").getString("lblPronostikoa"));
		
		
		columnNamesQueries = new String[] {
				ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
				ResourceBundle.getBundle("Etiquetas").getString("Query")

		};
		columnNamesEvents = new String[] {
				ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
				ResourceBundle.getBundle("Etiquetas").getString("Event"), 

		};
		tableModelQueries.setDataVector(null, columnNamesQueries);//Cmabia la tabla
		tableModelEvents.setDataVector(null, columnNamesEvents);
	}
}

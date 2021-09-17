package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import exceptions.EvenAlreadyExists;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import javax.swing.JTextPane;

public class Administer extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelQuery = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Query"));
	private JLabel jLabelMinBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));

	private JTextField jTextFieldQuery = new JTextField();
	private JTextField jTextFieldPrice = new JTextField();
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JLabel EventDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDescription")); //$NON-NLS-1$ //$NON-NLS-2$
	private JTextPane textPane;
	private final JButton CreateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent")); //$NON-NLS-1$ //$NON-NLS-2$
	private JComboBox<Question> QuestionLIst = new JComboBox<Question>();
	
	private InicioGUI anterior;
	private final JTextField textPronostiko = new JTextField();
	private final JLabel lblPronostiko = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblPronostico"));
	private final JTextField textMultiplikadorea = new JTextField();
	private final JLabel lblMultiplikadorea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblMultiplikadorea"));
	private final JButton btnPronostiko = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnPronostiko")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblListofPronostico = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblListofPronostico"));
	private JComboBox<Pronostico> comboPronostico;
	private JButton buttonPronosticoZuzena;

	public Administer (InicioGUI temp) {
		textMultiplikadorea.setBounds(116, 332, 50, 20);
		textMultiplikadorea.setColumns(10);
		textPronostiko.setBounds(116, 301, 425, 20);
		textPronostiko.setColumns(10);
		anterior =temp;
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(850, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		jComboBoxEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QuestionLIst.removeAllItems();
				if (jComboBoxEvents.getItemCount()>0) {
					for (Question i:((Event) jComboBoxEvents.getSelectedItem()).getQuestions()) {
						QuestionLIst.addItem(i);
					}
				}
			}
		});
		
		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(275, 47, 250, 20));
		jLabelListOfEvents.setBounds(new Rectangle(290, 18, 277, 20));
		jLabelQuery.setBounds(new Rectangle(25, 211, 91, 20));
		jTextFieldQuery.setBounds(new Rectangle(116, 213, 425, 20));
		jLabelMinBet.setBounds(new Rectangle(25, 243, 91, 20));
		jTextFieldPrice.setBounds(new Rectangle(116, 245, 60, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setBounds(new Rectangle(191, 244, 130, 30));
		jButtonCreate.setEnabled(false);

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(694, 420, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(390, 252, 434, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldQuery, null);
		this.getContentPane().add(jLabelQuery, null);
		this.getContentPane().add(jTextFieldPrice, null);

		this.getContentPane().add(jLabelMinBet, null);
		this.getContentPane().add(jLabelListOfEvents, null);
		this.getContentPane().add(jComboBoxEvents, null);

		this.getContentPane().add(jCalendar, null);
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
		paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);
		
		

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);
		EventDescription.setBounds(599, 50, 99, 14);
		
		getContentPane().add(EventDescription);
		
		textPane = new JTextPane();
		textPane.setBounds(599, 73, 225, 49);
		getContentPane().add(textPane);
		
		
		CreateEvent.setBounds(new Rectangle(275, 275, 130, 30));
		CreateEvent.setBounds(694, 133, 130, 30);
		
		getContentPane().add(CreateEvent);
		QuestionLIst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboPronostico.removeAllItems();
				
				btnPronostiko.setEnabled(QuestionLIst.getItemCount()>0);
				
				if (QuestionLIst.getItemCount()>0) {
					//facade.PronostikoakLortu(((Question)QuestionLIst.getSelectedItem()).getQuestionNumber());
					//ArrayList<Pronostico> tmp=((Question)QuestionLIst.getSelectedItem()).getPronostikoak();
					List<Pronostico> tmp=facade.PronostikoakLortu(((Question)QuestionLIst.getSelectedItem()).getQuestionNumber());
					if (tmp!=null) {
						for(Pronostico i:tmp) {
							comboPronostico.addItem(i);
						}
					}
				}
			}
		});
		
		comboPronostico = new JComboBox<Pronostico>();
		comboPronostico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonPronosticoZuzena.setEnabled(false);
				if ((comboPronostico.getSelectedItem()!=null)&&(!((Pronostico)comboPronostico.getSelectedItem()).getEstado())) {
					buttonPronosticoZuzena.setEnabled(true);
				}
			}
		});
		comboPronostico.setBounds(275, 158, 250, 22);
		getContentPane().add(comboPronostico);

		
		QuestionLIst.setBounds(275, 100, 250, 22);
		getContentPane().add(QuestionLIst);
		
		JLabel lblQuestion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblQuestion")); //$NON-NLS-1$ //$NON-NLS-2$
		lblQuestion.setBounds(290, 78, 84, 14);
		getContentPane().add(lblQuestion);
		
		getContentPane().add(textPronostiko);
		lblPronostiko.setBounds(25, 302, 91, 14);
		
		getContentPane().add(lblPronostiko);
		
		getContentPane().add(textMultiplikadorea);
		lblMultiplikadorea.setBounds(25, 333, 91, 14);
		
		getContentPane().add(lblMultiplikadorea);
		btnPronostiko.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jLabelError.setVisible(false);
				try {
				facade.createForecast((Question)QuestionLIst.getSelectedItem(),textPronostiko.getText(),Double.parseDouble(textMultiplikadorea.getText()));
				}catch (java.lang.NumberFormatException e) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("jLabelError1"));
					jLabelError.setVisible(true);
				}catch (java.lang.NullPointerException e) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("jLabelError2"));
					jLabelError.setVisible(true);
				}catch (exceptions.ForecastAlreadyExist e) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("jLabelError3"));
					jLabelError.setVisible(true);
				}
			}
		});
		btnPronostiko.setBounds(191, 332, 130, 30);
		btnPronostiko.setEnabled(false);
		getContentPane().add(btnPronostiko);
		lblListofPronostico.setBounds(290, 133, 84, 14);
		
		getContentPane().add(lblListofPronostico);
		
		buttonPronosticoZuzena = new JButton(ResourceBundle.getBundle("Etiquetas").getString("buttonPronosticoZuzena"));
		buttonPronosticoZuzena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				buttonPronosticoZuzena.setEnabled(false);
				facade.irabazi((Pronostico)comboPronostico.getSelectedItem());
				
				
				
				comboPronostico.removeAllItems();
				
				btnPronostiko.setEnabled(QuestionLIst.getItemCount()>0);
				
				if (QuestionLIst.getItemCount()>0) {
					//facade.PronostikoakLortu(((Question)QuestionLIst.getSelectedItem()).getQuestionNumber());
					//ArrayList<Pronostico> tmp=((Question)QuestionLIst.getSelectedItem()).getPronostikoak();
					List<Pronostico> tmp=facade.PronostikoakLortu(((Question)QuestionLIst.getSelectedItem()).getQuestionNumber());
					if (tmp!=null) {
						for(Pronostico i:tmp) {
							comboPronostico.addItem(i);
						}
					}
				}
				
				
				
				
			}
		});
		buttonPronosticoZuzena.setBounds(587, 195, 216, 25);
		buttonPronosticoZuzena.setEnabled(false);
		getContentPane().add(buttonPronosticoZuzena);
		
	
		CreateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade facade = MainGUI.getBusinessLogic();

					try {
						facade.createEvent(textPane.getText(), UtilDate.trim(calendarAct.getTime()));
						}catch (EvenAlreadyExists e) {
							jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("jLabelMsg"));
							jLabelMsg.setVisible(true);
						}

				
			}
		});
		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: "+calendarAnt.getTime());
					System.out.println("calendarAct: "+calendarAct.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) { 
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente, devolverÃ­a 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este cÃ³digo se dejarÃ¡ como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}
						
						jCalendar.setCalendar(calendarAct);
						
						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
					}



					paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);

					//	Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
					Date firstDay = UtilDate.trim(calendarAct.getTime());

					try {
						BLFacade facade = MainGUI.getBusinessLogic();

						Vector<domain.Event> events = facade.getEvents(firstDay);

						if (events.isEmpty())
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarAct.getTime()));
						else
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarAct.getTime()));
						jComboBoxEvents.removeAllItems();
						System.out.println("Events " + events);

						for (domain.Event ev : events)
							modelEvents.addElement(ev);
						jComboBoxEvents.repaint();

						if (events.size() == 0)
							jButtonCreate.setEnabled(false);
						else
							jButtonCreate.setEnabled(true);

					} catch (Exception e1) {

						jLabelError.setText(e1.getMessage());
					}

				}
			}
		});
	}

	
public static void paintDaysWithEvents(JCalendar jCalendar,Vector<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day is changed.

		
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		int year=calendar.get(Calendar.YEAR);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
		
	 	for (Date d:datesWithEventsCurrentMonth){

	 		calendar.setTime(d);
	 		System.out.println(d);
	 		

			
			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
	 	}
	 	
 			calendar.set(Calendar.DAY_OF_MONTH, today);
	 		calendar.set(Calendar.MONTH, month);
	 		calendar.set(Calendar.YEAR, year);

	 	
	}
	
	 
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		domain.Event event = ((domain.Event) jComboBoxEvents.getSelectedItem());

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty
			String inputQuery = jTextFieldQuery.getText();

			if (inputQuery.length() > 0) {

				// It could be to trigger an exception if the introduced string is not a number
				float inputPrice = Float.parseFloat(jTextFieldPrice.getText());

				if (inputPrice <= 0)
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				else {

					// Obtain the business logic from a StartWindow class (local or remote)
					BLFacade facade = MainGUI.getBusinessLogic();

					facade.createQuestion(event, inputQuery, inputPrice);

					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryCreated"));
				}
			} else
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuery"));
		} catch (EventFinished e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished") + ": "
					+ event.getDescription());
		} catch (QuestionAlreadyExist e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		} catch (java.lang.NumberFormatException e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		anterior.setVisible(true);
	}
}

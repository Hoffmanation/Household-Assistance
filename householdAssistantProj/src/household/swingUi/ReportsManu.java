package household.swingUi;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.prompt.PromptSupport;

import household.enumeration.Category;
import household.enumeration.Payment;
import household.enumeration.Persons;
import household.swingUi.MainManu.JPanelWithBackground;
import logic.Bean.Input;
import logic.Bean.ReportLogic;

public class ReportsManu extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3642570993883902963L;
	/**
	 * 
	 */
	private Persons p = new Persons();
	private List<Input> newreports = new ArrayList<>();
	static java.util.List<Input> deserializedInputs;
	static java.util.List<Input> reports = new ArrayList<>();
	private List<Input> pendingDeletion = new ArrayList<>();
	private JTable table;
	private DefaultTableModel model;
	private ReportLogic rl = new ReportLogic();
	private static JFrame frame;
	private static JPanel top;
	// buttons
	private JButton magBtn;
	private JButton submitBtn;
	private JButton backBtn;
	private JButton printBtn;
	private JButton deleteBtn;
	// adding columns to the table
	private Object columnNames[] = { "Person", "Date", "Payment Type", "Category", "Amount", "Description", "Delete" };
	// time text box
	private static JTextArea time = new JTextArea();
	// filter label
	private JLabel filters = new JLabel("Filters");
	// magnifying glass image
	private ImageIcon mag_topic = new ImageIcon("pics\\mag.png");
	private JLabel magImg = new JLabel(mag_topic);
	// printer image
	private ImageIcon img_print = new ImageIcon("pics\\printer.png");
	private JLabel printerImg = new JLabel(img_print);
	// delete image
	private ImageIcon img_delete = new ImageIcon("pics\\delete.png");
	private JLabel deleteImg = new JLabel(img_delete);
	// search text box
	private JTextField searchBox = new JTextField();
	// person label
	private JLabel person = new JLabel("Person: ");
	// date label
	private JLabel date = new JLabel("Date: ");
	// from Date label
	private JLabel fromD = new JLabel("From: ");
	// to Date label
	private JLabel toD = new JLabel("To: ");
	// payment Type label
	private JLabel paymentType = new JLabel("Payment Type: ");
	// Amount label
	private JLabel Amount = new JLabel("Amount: ");
	// person Option box
	private DefaultComboBoxModel nameInput = new DefaultComboBoxModel();
	private JComboBox nameOptionBox = new JComboBox(nameInput);
	// PaymentType Option box
	private DefaultComboBoxModel paymentTypeInput = new DefaultComboBoxModel();
	private JComboBox paymentTypeOptionBox = new JComboBox(paymentTypeInput);
	// from Date calendar component
	private JXDatePicker fromDate = new JXDatePicker();
	// until Date calendar component
	private JXDatePicker untilDate = new JXDatePicker();
	// search image
	private ImageIcon img_submit = new ImageIcon("pics\\searchBtn.png");
	private JLabel submitImg = new JLabel(img_submit);
	// back image
	private ImageIcon img_back = new ImageIcon("pics\\back.png");
	private JLabel backImg = new JLabel(img_back);
	// from amount text box
	private JTextField paymenStartVal = new JTextField();
	// from amount label
	private JLabel startAmount = new JLabel("From: ");
	// to amount text box
	private JTextField paymenEndVal = new JTextField();
	// from amount label
	private JLabel endAmount = new JLabel("To: ");
	// category label
	private JLabel category = new JLabel("Category: ");
	// category Option box
	private DefaultComboBoxModel categoryInput = new DefaultComboBoxModel();
	private JComboBox categoryOptionBox = new JComboBox(categoryInput);
	// comment text box
	private JTextArea comment = new JTextArea();

	public ReportsManu() {
		prepareGUI();
	}

	// an init method will create all components
	private void prepareGUI() {
		List<Persons> names = p.printNames();

		// main frame
		frame = new JFrame("Household Assistant");
		frame.setBounds(600, 150, 905, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		top = new JPanel();
		top.setLayout(new FlowLayout());
		top.setSize(0, 0);

		// filters label
		filters.setBounds(30, 95, 120, 15);
		filters.setFont(new Font("Tahoma", Font.BOLD, 20));
		filters.setForeground(new Color(51, 51, 51));

		// comment label (inside a scroll pane)
		comment.setFont(new Font("Tahoma", Font.BOLD, 15));
		comment.setOpaque(false);
		comment.setBackground(new Color(0, 0, 0, 0));
		JScrollPane scrollPane666 = new JScrollPane(comment);
		scrollPane666.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scrollPane666.setBounds(250, 505, 600, 25);
		scrollPane666.getViewport().setOpaque(false);
		scrollPane666.setOpaque(false);

		// name label
		person.setBounds(30, 160, 100, 50);
		person.setFont(new Font("Tahoma", Font.BOLD, 15));
		person.setForeground(new Color(51, 51, 51));

		// date label
		date.setBounds(30, 230, 100, 50);
		date.setFont(new Font("Tahoma", Font.BOLD, 15));
		date.setForeground(new Color(51, 51, 51));

		// search text Box
		searchBox.setBounds(30, 130, 130, 30);
		searchBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchBox.setForeground(new Color(51, 51, 51));
		PromptSupport.setPrompt("Search by keyword", searchBox);
		searchBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Border lowered_bevelborder = BorderFactory.createLoweredBevelBorder();
		searchBox.setBorder(lowered_bevelborder);

		// name Option Box
		nameOptionBox.setBounds(30, 200, 160, 25);
		nameOptionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameOptionBox.setForeground(new Color(51, 51, 51));
		nameOptionBox.setBackground(Color.white);
		nameInput.addElement("All");
		for (int i = 0; i < names.size(); i++) {
			nameInput.addElement(names.get(i).getName());
		}
		nameOptionBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// magnifying glass button
		magBtn = new JButton();
		magBtn.setBounds(170, 130, 50, 30);
		magBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		magBtn.setForeground(new Color(51, 51, 51));
		magBtn.setBackground(Color.gray);
		magBtn.setBorder(null);
		magBtn.setBorder(null);
		magBtn.setOpaque(true);
		magBtn.setContentAreaFilled(false);
		magBtn.setBorderPainted(false);
		magBtn.setBorder(null);
		magBtn.setFocusPainted(false);
		magBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// from Date label
		fromD.setBounds(30, 255, 100, 50);
		fromD.setFont(new Font("Tahoma", Font.BOLD, 15));
		fromD.setForeground(new Color(51, 51, 51));

		// from Date calendar component
		fromDate.setDate(Calendar.getInstance().getTime());
		fromDate.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
		fromDate.setBounds(30, 295, 70, 20);
		fromDate.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// to date label
		toD.setBounds(120, 255, 100, 50);
		toD.setFont(new Font("Tahoma", Font.BOLD, 15));
		toD.setForeground(new Color(51, 51, 51));

		// until Date calendar component
		untilDate.setDate(Calendar.getInstance().getTime());
		untilDate.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
		untilDate.setBounds(120, 295, 70, 21);
		untilDate.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// paymentType label
		paymentType.setBounds(30, 325, 200, 50);
		paymentType.setFont(new Font("Tahoma", Font.BOLD, 15));
		paymentType.setForeground(new Color(51, 51, 51));

		// paymentType Option Box
		paymentTypeOptionBox.setBounds(30, 365, 160, 25);
		paymentTypeOptionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paymentTypeOptionBox.setForeground(new Color(51, 51, 51));
		paymentTypeOptionBox.setBackground(Color.white);
		paymentTypeInput.addElement(Payment.All);
		paymentTypeInput.addElement(Payment.Check);
		paymentTypeInput.addElement(Payment.Bank_transfer);
		paymentTypeInput.addElement(Payment.Cash);
		paymentTypeInput.addElement(Payment.Credit_Card);
		paymentTypeInput.addElement(Payment.Other);
		paymentTypeOptionBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Amount label
		Amount.setBounds(30, 410, 160, 25);
		Amount.setFont(new Font("Tahoma", Font.BOLD, 15));
		Amount.setForeground(new Color(51, 51, 51));

		// start Amount label
		startAmount.setBounds(30, 420, 100, 50);
		startAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		startAmount.setForeground(new Color(51, 51, 51));

		// From amount text box
		paymenStartVal.setBounds(30, 460, 70, 20);
		paymenStartVal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymenStartVal.setForeground(Color.black);
		PromptSupport.setPrompt("Insert amount", paymenStartVal);
		paymenStartVal.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Border lowered_bevelborder1 = BorderFactory.createLoweredBevelBorder();
		paymenStartVal.setBorder(lowered_bevelborder1);

		// to amount label
		endAmount.setBounds(120, 420, 100, 50);
		endAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		endAmount.setForeground(new Color(51, 51, 51));

		// to amount text box
		paymenEndVal.setBounds(120, 460, 70, 21);
		paymenEndVal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymenEndVal.setForeground(new Color(51, 51, 51));
		PromptSupport.setPrompt("Insert amount", paymenEndVal);
		paymenEndVal.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Border lowered_bevelborderr = BorderFactory.createLoweredBevelBorder();
		paymenEndVal.setBorder(lowered_bevelborderr);

		// category label
		category.setBounds(30, 500, 160, 25);
		category.setFont(new Font("Tahoma", Font.BOLD, 15));
		category.setForeground(new Color(51, 51, 51));

		// category Option Box
		categoryOptionBox.setBounds(30, 530, 160, 25);
		categoryOptionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		categoryOptionBox.setForeground(new Color(51, 51, 51));
		categoryOptionBox.setBackground(Color.white);
		categoryInput.addElement(Category.All);
		categoryInput.addElement(Category.Animals);
		categoryInput.addElement(Category.Electronics);
		categoryInput.addElement(Category.Groceries);
		categoryInput.addElement(Category.Household);
		categoryInput.addElement(Category.Pharmaceutical);
		categoryInput.addElement(Category.Pub);
		categoryInput.addElement(Category.Restaurant);
		categoryInput.addElement(Category.Other);
		categoryOptionBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// search button
		submitBtn = new JButton();
		submitBtn.setBounds(340, 560, 100, 40);
		submitBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		submitBtn.setForeground(Color.gray);
		submitBtn.setBackground(Color.gray);
		submitBtn.setBorder(null);
		submitBtn.setBorder(null);
		submitBtn.setOpaque(true);
		submitBtn.setContentAreaFilled(false);
		submitBtn.setBorderPainted(false);
		submitBtn.setBorder(null);
		submitBtn.setFocusPainted(false);
		submitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// back button
		backBtn = new JButton();
		backBtn.setBounds(480, 560, 100, 40);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setForeground(Color.gray);
		backBtn.setBackground(Color.gray);
		backBtn.setBorder(null);
		backBtn.setOpaque(true);
		backBtn.setContentAreaFilled(false);
		backBtn.setBorderPainted(false);
		backBtn.setBorder(null);
		backBtn.setFocusPainted(false);
		backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// printer button
		printBtn = new JButton();
		printBtn.setBounds(845, 500, 40, 40);
		printBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		printBtn.setForeground(Color.gray);
		printBtn.setBackground(Color.gray);
		printBtn.setBorder(null);
		printBtn.setOpaque(true);
		printBtn.setContentAreaFilled(false);
		printBtn.setBorderPainted(false);
		printBtn.setBorder(null);
		printBtn.setFocusPainted(false);
		printBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// delete selected button
		deleteBtn = new JButton();
		deleteBtn.setBounds(650, 506, 170, 30);
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deleteBtn.setForeground(Color.gray);
		deleteBtn.setBackground(Color.gray);
		deleteBtn.setBorder(null);
		deleteBtn.setOpaque(true);
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setBorderPainted(false);
		deleteBtn.setBorder(null);
		deleteBtn.setFocusPainted(false);
		deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Adding components to the MainFraim
		deleteBtn.add(deleteImg);
		magBtn.add(magImg);
		printBtn.add(printerImg);
		frame.add(time);
		frame.add(filters);
		frame.add(searchBox);
		frame.add(magBtn);
		frame.add(person);
		frame.add(nameOptionBox);
		frame.add(date);
		frame.add(untilDate);
		frame.add(backBtn);
		frame.add(submitBtn);
		frame.add(fromDate);
		frame.add(fromD);
		frame.add(toD);
		frame.add(paymentType);
		frame.add(paymentTypeOptionBox);
		submitBtn.add(submitImg);
		backBtn.add(backImg);
		frame.add(Amount);
		frame.add(paymenEndVal);
		frame.add(paymenStartVal);
		frame.add(startAmount);
		frame.add(endAmount);
		frame.add(category);
		frame.add(categoryOptionBox);
		frame.add(printBtn);
		frame.add(deleteBtn);
		frame.add(scrollPane666);

		// DataBase table
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnIndex == 6 ? Boolean.class : super.getColumnClass(columnIndex);
			}
		};

		JTable table = new JTable(model);

		// delete button action - deleting unwanted reports
		deleteBtn.addActionListener(new ActionListener() {
			Input in = null;
			boolean checked = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				comment.setText(null);
				pendingDeletion.clear();
				// making a for loop on all report results that came out of the
				// user search
				for (int i = 0; i < table.getModel().getRowCount(); i++) {
					String pesron1 = (String) table.getValueAt(i, 0);
					Date date = (Date) table.getValueAt(i, 1);
					Payment paymentType = (Payment) table.getValueAt(i, 2);
					Category category = (Category) table.getValueAt(i, 3);
					double amount = (double) table.getValueAt(i, 4);
					String description = (String) table.getValueAt(i, 5);
					in = new Input(pesron1, date, amount, paymentType, category, description);

					// checked boolean variable : return true if the "delete"
					// checkbox is checked and false if it isn't
					checked = (boolean) table.getModel().getValueAt(i, 6);

					// if checked return true , add the report to a new
					// arreyList of "pending deletion" reports
					if (checked) {
						pendingDeletion.add(in);
					}
				}

				// check if the "pending deletion" array is empty
				try {
					if (pendingDeletion.isEmpty()) {
						comment.append("Please select a report to delete ! ");
						return;
					}
					// 1).imports the original data file and store all he's
					// 		data in the "reports" arrayList
					// 2).making a for loop checking if any of the reports in
					// 		the data arrayList contain any reports in "pending
					// 		deletion" array , if not , add this report to a "new
					// 		data" arrayList , if they are , do not add it the "new
					// 		data" arrayList , resulting the "new data" arrayList to
					// 		contain only wanted reports without the deleted ones
					// 3).finally the arrayList "new data" will be copy back to
					// 		the
					// 		database file
					ObjectInputStream ois = new ObjectInputStream(
							new FileInputStream("data\\reports.obg"));
					deserializedInputs = (java.util.List<Input>) ois.readObject();
					reports = deserializedInputs;
					ois.close();
					System.out.println("reports:" + reports.size());
					System.out.println("ppeding delishen: " + pendingDeletion.size());
					for (int i = 0; i < reports.size(); i++) {
						if (!pendingDeletion.contains(reports.get(i))) {
							newreports.add(reports.get(i));
						}
					}

				} catch (IOException | ClassNotFoundException e1) {
					// do nothing
				}
				System.out.println("new data:" + newreports.size());

				try (ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream("data\\reports.obg"));) {
					out.writeObject(newreports);
					out.close();
					JOptionPane.showMessageDialog(null, "The report/s has been deleted");
					frame.setVisible(false);
					frame.dispose();
					MainManu m = new MainManu();
					m.prepareGUI();
				} catch (IOException r) {
					comment.setText("An error had occurred !");
				}
			}
		});

		// adding the database table to a scrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBounds(250, 100, 620, 400);
		frame.add(scrollPane, BorderLayout.CENTER);

		try {
			// Inserting background Image to the frame
			frame.getContentPane()
					.add(new JPanelWithBackground("pics\\reportsbg.png"));
		} catch (IOException e) {
			// do nothing
		}
		frame.setVisible(true);

		// back button action
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainManu m = new MainManu();
				try {
					m.prepareGUI();
				} catch (IOException e1) {
					// do nothing
				}
				frame.setVisible(false);
				frame.dispose();
			}
		});

		// submit button - searching reports
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				comment.setText(null);
				// Getting all values from the customer's search
				double pay1 = 0;
				double pay2 = 0;
				String a = paymenStartVal.getText();
				String b = paymenEndVal.getText();
				String person = (String) nameOptionBox.getSelectedItem();
				Category categor = (Category) categoryOptionBox.getSelectedItem();
				Payment payType = (Payment) paymentTypeOptionBox.getSelectedItem();
				java.util.Date dateA = fromDate.getDate();
				java.util.Date dateB = untilDate.getDate();

				java.sql.Date date1 = new java.sql.Date(dateA.getTime());
				java.sql.Date date2 = new java.sql.Date(dateB.getTime());

				// methods for filtering user report search like : date, amount.
				// description and so that will be executed if the value
				// "Amount" text box
				// are null and if the customer diden't insert any input in the
				// (from - to)
				// Amount boxes ,
				if (a.equals("") && b.equals("")) {
					model.setRowCount(0);
					comment.setText(null);
					if (person.equals("All") && payType.equals(Payment.All) && categor.equals(Category.All)) {
						try {
							double rv = 0;
							List<Input> reports = rl.getReportByDate(date1, date2);
							if (reports.isEmpty()) {
								comment.append("Sorry, no results found !");
								return;
							} else {
								comment.setText(null);

							}
							
							for (Input input : reports) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(input.getDate());
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								int year = cal.get(Calendar.YEAR);
								Object newrepots1[] = { input.getPerson(), day+"/"+month+"/"+year, input.getPaymentType(),
										input.getCategory(), input.getAmount(), input.getDescription(), false };

								model.addRow(newrepots1);
								rv += input.getAmount();
								comment.setText("Total amount: " + rv);
							}
							return;
						} catch (ParseException e1) {
							comment.append("Plaese insert numbers only !");
						}
					}

					if (payType.equals(Payment.All) && categor.equals(Category.All)) {
						double rv = 0;

						try {
							List<Input> reports = rl.getReportByPersonAndDate(person, date1, date2);

							if (reports.isEmpty()) {
								comment.append("Sorry, no results found !");
								return;
							} else {
								comment.setText(null);

							}
							for (Input input : reports) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(input.getDate());
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								int year = cal.get(Calendar.YEAR);
								Object newrepots1[] = { input.getPerson(),day+"/"+month+"/"+year, input.getPaymentType(),
										input.getCategory(), input.getAmount(), input.getDescription(), false };
								model.addRow(newrepots1);
								rv += input.getAmount();
								comment.setText("Total amount: " + rv);
							}
							return;
						} catch (Exception e1) {
							comment.append("Plaese insert numbers only !");
						}
					} else if (person.equals("All") && payType.equals(Payment.All)) {
						try {
							double rv = 0;
							List<Input> reports = rl.getReportByCategoryAndDate(date1, date2, categor);

							if (reports.isEmpty()) {
								comment.append("Sorry, no results found !");
								return;
							} else {
								comment.setText(null);

							}
							for (Input input : reports) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(input.getDate());
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								int year = cal.get(Calendar.YEAR);
								Object newrepots1[] = { input.getPerson(), day+"/"+month+"/"+year, input.getPaymentType(),
										input.getCategory(), input.getAmount(), input.getDescription(), false };
								model.addRow(newrepots1);
								rv += input.getAmount();
								comment.setText("Total amount: " + rv);
							}
							return;
						} catch (Exception e1) {
							comment.append("Plaese insert numbers only !");
						}

					} else if (person.equals("All") && categor.equals(Category.All)) {
						try {
							double rv = 0;
							List<Input> reports = rl.getReportByPaymentAndDate(payType, date1, date2);
							System.out.println("3");
							if (reports.isEmpty()) {
								comment.append("Sorry, no results found !");
								return;
							} else {
								comment.setText(null);

							}
							for (Input input : reports) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(input.getDate());
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								int year = cal.get(Calendar.YEAR);
								Object newrepots1[] = { input.getPerson(), day+"/"+month+"/"+year, input.getPaymentType(),
										input.getCategory(), input.getAmount(), input.getDescription(), false };
								model.addRow(newrepots1);
								rv += input.getAmount();
								comment.setText("Total amount: " + rv);
							}
							return;
						} catch (Exception e1) {
							comment.append("Plaese insert numbers only !");
						}
					} else if (person.equals("All")) {
						try {
							double rv = 0;
							List<Input> reports = rl.getReportByCategoryPaymentTypeAndDate(date1, date2, categor,
									payType);

							if (reports.isEmpty()) {
								comment.append("Sorry, no results found !");
								return;
							} else {
								comment.setText(null);

							}
							for (Input input : reports) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(input.getDate());
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								int year = cal.get(Calendar.YEAR);
								Object newrepots1[] = { input.getPerson(), day+"/"+month+"/"+year, input.getPaymentType(),
										input.getCategory(), input.getAmount(), input.getDescription(), false };
								model.addRow(newrepots1);
								rv += input.getAmount();
								comment.setText("Total amount: " + rv);
							}
							return;
						} catch (Exception e1) {
							comment.append("Plaese insert numbers only !");
						}
					} else if (categor.equals(Category.All)) {
						try {
							double rv = 0;
							List<Input> reports = rl.getReportByPersonPaymentAndDate(date1, date2, payType, person);

							if (reports.isEmpty()) {
								comment.append("Sorry, no results found !");
								return;
							} else {
								comment.setText(null);

							}
							for (Input input : reports) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(input.getDate());
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								int year = cal.get(Calendar.YEAR);
								Object newrepots1[] = { input.getPerson(), day+"/"+month+"/"+year, input.getPaymentType(),
										input.getCategory(), input.getAmount(), input.getDescription(), false };
								model.addRow(newrepots1);
								rv += input.getAmount();
								comment.setText("Total amount: " + rv);
							}
							return;
						} catch (Exception e1) {
							comment.append("Plaese insert numbers only !");
						}
					} else if (payType.equals(Payment.All)) {
						try {
							double rv = 0;
							List<Input> reports = rl.getReportByPersonCategoryAndDate(date1, date2, categor, person);

							if (reports.isEmpty()) {
								comment.append("Sorry, no results found !");
								return;
							} else {
								comment.setText(null);

							}
							for (Input input : reports) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(input.getDate());
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								int year = cal.get(Calendar.YEAR);
								Object newrepots1[] = { input.getPerson(),day+"/"+month+"/"+year, input.getPaymentType(),
										input.getCategory(), input.getAmount(), input.getDescription(), false };
								model.addRow(newrepots1);
								rv += input.getAmount();
								comment.setText("Total amount: " + rv);
							}
							return;
						} catch (Exception e1) {
							comment.append("Plaese insert numbers only !");
						}
					}
					try {
						double rv = 0;
						List<Input> reports = rl.getReportWithountPayment(person, date1, date2, categor, payType);

						if (reports.isEmpty()) {
							comment.append("Sorry, no results found !");
							return;
						} else {
							comment.setText(null);

						}
						for (Input input : reports) {
							Calendar cal = Calendar.getInstance();
							cal.setTime(input.getDate());
							int month = cal.get(Calendar.MONTH);
							int day = cal.get(Calendar.DAY_OF_MONTH);
							int year = cal.get(Calendar.YEAR);
							Object newrepots1[] = { input.getPerson(), day+"/"+month+"/"+year, input.getPaymentType(),
									input.getCategory(), input.getAmount(), input.getDescription(), false };
							model.addRow(newrepots1);
							rv += input.getAmount();
							comment.setText("Total amount: " + rv);
						}
						return;
					} catch (Exception e1) {
						comment.append("Plaese insert numbers only !");
					}

				}

				// methods for filtering user report search like : date, amount.
				// description and so, will be executed if the "Amount" text box
				// have
				// value in them commit will happend if the customer inserted
				// integer value in the (from - to) Amount boxes
				else {
					model.setRowCount(0);
					comment.setText(null);

					try {
						pay1 = Double.parseDouble(a);
						pay2 = Double.parseDouble(b);
					} catch (Exception e2) {
						comment.append("Please insent a valid number !");
						return;
					}
					if (person.equals("All") && payType.equals(Payment.All) && categor.equals(Category.All)) {
						try {
							double rv = 0;
							List<Input> reports = rl.getReportByAmountAndDate(date1, date2, pay1, pay2);
							if (reports.isEmpty()) {
								comment.append("Sorry, no results found !");
								return;
							} else {
								comment.setText(null);

							}
							for (Input input : reports) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(input.getDate());
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								int year = cal.get(Calendar.YEAR);
								Object newrepots1[] = { input.getPerson(), day+"/"+month+"/"+year, input.getPaymentType(),
										input.getCategory(), input.getAmount(), input.getDescription(), false };

								model.addRow(newrepots1);
								rv += input.getAmount();
								comment.setText("Total amount: " + rv);
							}
							return;
						} catch (ParseException e1) {
							comment.append("Plaese insert numbers only !");
						}
					} else {
						if (payType.equals(Payment.All) && categor.equals(Category.All)) {
							comment.setText(null);
							try {
								double rv = 0;
								List<Input> reports = rl.getReportByPersonAmountAndDate(person, date1, date2, pay1,
										pay2);

								if (reports.isEmpty()) {
									comment.append("Sorry, no results found !");
									return;
								} else {
									comment.setText(null);
								}
								for (Input input : reports) {
									Calendar cal = Calendar.getInstance();
									cal.setTime(input.getDate());
									int month = cal.get(Calendar.MONTH);
									int day = cal.get(Calendar.DAY_OF_MONTH);
									int year = cal.get(Calendar.YEAR);
									Object newrepots1[] = { input.getPerson(),day+"/"+month+"/"+year, input.getPaymentType(),
											input.getCategory(), input.getAmount(), input.getDescription(), false };
									model.addRow(newrepots1);
									rv += input.getAmount();
									comment.setText("Total amount: " + rv);
								}
								return;
							} catch (Exception e1) {
								comment.append("Plaese insert numbers only !");
							}
						} else if (person.equals("All") && payType.equals(Payment.All)) {
							try {
								double rv = 0;
								List<Input> reports = rl.getReportByCategoryAmountAndDate(categor, date1, date2, pay1,
										pay2);

								if (reports.isEmpty()) {
									comment.append("Sorry, no results found !");
									return;
								} else {
									comment.setText(null);

								}
								for (Input input : reports) {
									Calendar cal = Calendar.getInstance();
									cal.setTime(input.getDate());
									int month = cal.get(Calendar.MONTH);
									int day = cal.get(Calendar.DAY_OF_MONTH);
									int year = cal.get(Calendar.YEAR);
									Object newrepots1[] = { input.getPerson(),day+"/"+month+"/"+year, input.getPaymentType(),
											input.getCategory(), input.getAmount(), input.getDescription(), false };
									model.addRow(newrepots1);
									rv += input.getAmount();
									comment.setText("Total amount: " + rv);
								}
								return;
							} catch (Exception e1) {
								comment.append("Plaese insert numbers only !");
							}

						} else if (person.equals("All") && categor.equals(Category.All)) {
							try {
								double rv = 0;
								List<Input> reports = rl.getReportByPaymentAmountAndDate(payType, date1, date2, pay1,
										pay2);

								if (reports.isEmpty()) {
									comment.append("Sorry, no results found !");
									return;
								} else {
									comment.setText(null);

								}
								for (Input input : reports) {
									Calendar cal = Calendar.getInstance();
									cal.setTime(input.getDate());
									int month = cal.get(Calendar.MONTH);
									int day = cal.get(Calendar.DAY_OF_MONTH);
									int year = cal.get(Calendar.YEAR);
									Object newrepots1[] = { input.getPerson(), day+"/"+month+"/"+year, input.getPaymentType(),
											input.getCategory(), input.getAmount(), input.getDescription(), false };
									model.addRow(newrepots1);
									rv += input.getAmount();
									comment.setText("Total amount: " + rv);
								}
								return;
							} catch (Exception e1) {
								comment.append("Plaese insert numbers only !");
							}
						} else if (person.equals("All")) {
							try {
								double rv = 0;
								List<Input> reports = rl.getReportByCategoryPaymentAmountAndDate(categor, payType,
										date1, date2, pay1, pay2);
								System.out.println("11");
								if (reports.isEmpty()) {
									comment.append("Sorry, no results found !");
									return;
								} else {
									comment.setText(null);

								}
								for (Input input : reports) {
									Calendar cal = Calendar.getInstance();
									cal.setTime(input.getDate());
									int month = cal.get(Calendar.MONTH);
									int day = cal.get(Calendar.DAY_OF_MONTH);
									int year = cal.get(Calendar.YEAR);
									Object newrepots1[] = { input.getPerson(),day+"/"+month+"/"+year, input.getPaymentType(),
											input.getCategory(), input.getAmount(), input.getDescription(), false };
									model.addRow(newrepots1);
									rv += input.getAmount();
									comment.setText("Total amount: " + rv);
								}
								return;
							} catch (Exception e1) {
								comment.append("Plaese insert numbers only !");
								return;
							}
						} else if (categor.equals(Category.All)) {
							try {
								double rv = 0;
								List<Input> reports = rl.getReportByPersonPaymentAmountAndDate(person, payType, date1,
										date2, pay1, pay2);

								if (reports.isEmpty()) {
									comment.append("Sorry, no results found !");
									return;
								} else {
									comment.setText(null);

								}
								for (Input input : reports) {
									Calendar cal = Calendar.getInstance();
									cal.setTime(input.getDate());
									int month = cal.get(Calendar.MONTH);
									int day = cal.get(Calendar.DAY_OF_MONTH);
									int year = cal.get(Calendar.YEAR);
									Object newrepots1[] = { input.getPerson(),day+"/"+month+"/"+year, input.getPaymentType(),
											input.getCategory(), input.getAmount(), input.getDescription(), false };
									model.addRow(newrepots1);
									rv += input.getAmount();
									comment.setText("Total amount: " + rv);
								}
								return;
							} catch (Exception e1) {
								comment.append("Plaese insert numbers only !");
							}
						} else if (payType.equals(Payment.All)) {
							try {
								double rv = 0;
								List<Input> reports = rl.getReportByPersonCategoryAmountAndDate(person, categor, date1,
										date2, pay1, pay2);

								if (reports.isEmpty()) {
									comment.append("Sorry, no results found !");
									return;
								} else {
									comment.setText(null);

								}
								for (Input input : reports) {
									Calendar cal = Calendar.getInstance();
									cal.setTime(input.getDate());
									int month = cal.get(Calendar.MONTH);
									int day = cal.get(Calendar.DAY_OF_MONTH);
									int year = cal.get(Calendar.YEAR);
									Object newrepots1[] = { input.getPerson(),day+"/"+month+"/"+year, input.getPaymentType(),
											input.getCategory(), input.getAmount(), input.getDescription(), false };
									model.addRow(newrepots1);
									rv += input.getAmount();
									comment.setText("Total amount: " + rv);
								}
								return;
							} catch (Exception e1) {
								comment.append("Plaese insert numbers only !");
								return;
							}
						}
						try {
							double rv = 0;
							List<Input> reports = rl.getReport(person, date1, date2, pay1, pay2, categor, payType);

							if (reports.isEmpty()) {
								comment.append("Sorry, no results found !");
								return;
							} else {
								comment.setText(null);

							}
							for (Input input : reports) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(input.getDate());
								int month = cal.get(Calendar.MONTH);
								int day = cal.get(Calendar.DAY_OF_MONTH);
								int year = cal.get(Calendar.YEAR);
								Object newrepots1[] = { input.getPerson(),day+"/"+month+"/"+year, input.getPaymentType(),
										input.getCategory(), input.getAmount(), input.getDescription(), false };
								model.addRow(newrepots1);
								rv += input.getAmount();
								comment.setText("Total amount: " + rv);
							}
							return;
						} catch (Exception e1) {
							comment.append("Plaese insert numbers only !");
						}

					}
				}

			}
		});

		// mag button action - Search by a keyword in the description text box
		// value
		magBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				comment.setText(null);
				double rv = 0;
				String des = searchBox.getText();
				if (des.equals("")) {
					comment.append("Sorry, no results found !");
					return;
				} else {
					List<Input> reports = rl.getReportByDescription(des);

					if (reports.isEmpty()) {
						comment.append("Sorry, no results found !");
						return;
					} else {

						for (Input input : reports) {
							Calendar cal = Calendar.getInstance();
							cal.setTime(input.getDate());
							int month = cal.get(Calendar.MONTH);
							int day = cal.get(Calendar.DAY_OF_MONTH);
							int year = cal.get(Calendar.YEAR);
							Object newrepots1[] = { input.getPerson(), day+"/"+month+"/"+year, input.getPaymentType(),
									input.getCategory(), input.getAmount(), input.getDescription(), false };

							model.addRow(newrepots1);
							rv += input.getAmount();
							comment.setText("Total amount: " + rv);
						}

					}

				}
			}
		});

		// print button action - Prints the report by the the printer interface
		printBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						table.print();
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						// do nothing
					}
				} catch (PrinterException e1) {
					// do nothing
				}
			}
		});

	}

	// drawing the background image to the frame
	public class JPanelWithBackground extends JPanel {

		private Image backgroundImage;

		// Here, we use the constructor to load the image. This
		// can vary depending on the use case of the panel and the layout type
		// of the frame
		public JPanelWithBackground(String fileName) throws IOException {
			backgroundImage = ImageIO.read(new File(fileName));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Draw the background image.
			g.drawImage(backgroundImage, 0, 0, this);
		}
	}

	// method for categorize every column in the table assigning a java class to
	// them
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class clazz = String.class;
		switch (columnIndex) {
		case 0:
			clazz = String.class;
			break;
		case 1:
			clazz = Date.class;
			break;
		case 2:
			clazz = Enumeration.class;
			break;
		case 3:
			clazz = Enumeration.class;
			break;
		case 4:
			clazz = Double.class;
			break;
		case 5:
			clazz = String.class;
			break;
		case 6:
			clazz = Boolean.class;
			break;
		}
		return clazz;
	}

	// method will return the value of column no.6 of every row
	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 6;
	}

	// method will change value at a box of a row in a column when called
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (aValue instanceof Boolean && column == 6) {
			System.out.println(aValue);
			Vector rowData = (Vector) getDataVector().get(row);
			rowData.set(2, (boolean) aValue);
			fireTableCellUpdated(row, column);
		}
	}

}

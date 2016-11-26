package household.swingUi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.prompt.PromptSupport;

import household.enumeration.Category;
import household.enumeration.Payment;
import household.enumeration.Persons;
import household.swingUi.MainManu.JPanelWithBackground;
import logic.Bean.Input;

public class expencesManu extends JFrame {
	/**
	 * 
	 */
	public static BufferedImage img;
	private float scale = 1;
	private JLabel selectedImg = new JLabel();
	private String path = null;
	private ImageIcon icon;
	private Settings s = new Settings();
	private Persons p = new Persons();
	private static JFrame frame;
	private static JPanel top;
	// labels
	private JLabel scan = new JLabel("Scan receipt");
	private JLabel searchRecit = new JLabel("Search for receipt");
	private JLabel name = new JLabel("Name:");
	private JLabel date = new JLabel("Date:");
	private JLabel payment = new JLabel("Amount:");
	private JLabel category = new JLabel("Category:");
	private JLabel description = new JLabel("Description:");
	private JLabel paymentType = new JLabel("Payment type:");
	// buttons
	private JButton submitBtn;
	private JButton backBtn;
	private JButton scanBtn;
	private JButton searchBtn;
	private JButton deleteBtn;
	private JButton imagecontainer;
	// text boxes
	private JTextField paymentValue = new JTextField();
	private JTextArea descriptionValue = new JTextArea();
	private JTextArea comment = new JTextArea();

	// name Option box
	private DefaultComboBoxModel nameInput = new DefaultComboBoxModel();
	private JComboBox nameOptionBox = new JComboBox(nameInput);
	// calendar component
	private JXDatePicker CalendarDisplay = new JXDatePicker();
	// category Option box
	private DefaultComboBoxModel categoryInput = new DefaultComboBoxModel();
	private JComboBox categoryOptionBox = new JComboBox(categoryInput);
	// PaymentType Option box
	private DefaultComboBoxModel paymentTypeInput = new DefaultComboBoxModel();
	private JComboBox paymentTypeOptionBox = new JComboBox(paymentTypeInput);
	// submit image
	private ImageIcon img_submit = new ImageIcon("pics\\submitBtn.png");
	private JLabel submitImg = new JLabel(img_submit);
	// back image
	private ImageIcon img_back = new ImageIcon("pics\\back.png");
	private JLabel backImg = new JLabel(img_back);
	// scanner image
	private ImageIcon img_scan = new ImageIcon("pics\\scanner.png");
	private JLabel scanImg = new JLabel(img_scan);
	// magnifying glass image
	private ImageIcon img_search = new ImageIcon("pics\\mag.png");
	private JLabel searchImg = new JLabel(img_search);
	// input/reports database
	private java.util.List<Input> deserializedInputs;
	private java.util.List<Input> reports = new ArrayList<>();

	public expencesManu() {
		prepareGUI();

	}

	// an init method will create all components
	private void prepareGUI() {

		java.util.List<Persons> names = p.printNames();

		// main frame
		frame = new JFrame("New expense");
		frame.setBounds(600, 150, 905, 650);
		frame.getContentPane().setBackground(Color.gray);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// main panel
		top = new JPanel();
		top.setLayout(new GridLayout(10, 3));
		top.setBackground(Color.gray);
		top.setSize(0, 0);

		// name label
		name.setBounds(80, 140, 200, 20);
		name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		name.setForeground(Color.black);

		// category label
		category.setBounds(80, 340, 200, 20);
		category.setFont(new Font("Tahoma", Font.PLAIN, 15));
		category.setForeground(Color.black);

		// name Option Box
		nameOptionBox.setBounds(220, 140, 130, 20);
		nameOptionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameOptionBox.setForeground(Color.black);
		for (int i = 0; i < names.size(); i++) {
			nameInput.addElement(names.get(i).getName());
		}

		nameOptionBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// category Option Box
		categoryOptionBox.setBounds(220, 340, 130, 20);
		categoryOptionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		categoryOptionBox.setForeground(Color.black);
		categoryInput.addElement(Category.Animals);
		categoryInput.addElement(Category.Electronics);
		categoryInput.addElement(Category.Groceries);
		categoryInput.addElement(Category.Household);
		categoryInput.addElement(Category.Pharmaceutical);
		categoryInput.addElement(Category.Pub);
		categoryInput.addElement(Category.Restaurant);
		categoryInput.addElement(Category.Other);
		categoryOptionBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// paymentType Optio nBox
		paymentTypeOptionBox.setBounds(220, 290, 130, 20);
		paymentTypeOptionBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		paymentTypeOptionBox.setForeground(Color.black);
		paymentTypeInput.addElement(Payment.Bank_transfer);
		paymentTypeInput.addElement(Payment.Cash);
		paymentTypeInput.addElement(Payment.Credit_Card);
		paymentTypeInput.addElement(Payment.Other);
		paymentTypeOptionBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Calendar component

		CalendarDisplay.setDate(Calendar.getInstance().getTime());
		CalendarDisplay.setFormats(new SimpleDateFormat("dd-MM-yyyy"));
		CalendarDisplay.setBounds(220, 190, 130, 20);
		CalendarDisplay.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// date label
		date.setBounds(80, 190, 200, 20);
		date.setFont(new Font("Tahoma", Font.PLAIN, 15));
		date.setForeground(Color.black);

		// scan receipt label
		scan.setBounds(592, 170, 100, 30);
		scan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scan.setForeground(Color.black);

		// search for receipt label
		searchRecit.setBounds(575, 270, 150, 30);
		searchRecit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchRecit.setForeground(Color.black);

		// comment label (inside a scroll pane)
		// comment.setBounds(250, 505, 200, 20);
		comment.setFont(new Font("Tahoma", Font.BOLD, 15));
		comment.setOpaque(false);
		comment.setBackground(new Color(0, 0, 0, 0));
		JScrollPane scrollPane666 = new JScrollPane(comment);
		scrollPane666.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scrollPane666.setBounds(210, 540, 600, 55);
		scrollPane666.getViewport().setOpaque(false);
		scrollPane666.setOpaque(false);

		// amount label
		payment.setBounds(80, 240, 200, 20);
		payment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		payment.setForeground(Color.black);

		// paymentType label
		paymentType.setBounds(80, 290, 200, 20);
		paymentType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymentType.setForeground(Color.black);

		// description label
		description.setBounds(80, 400, 200, 20);
		description.setFont(new Font("Tahoma", Font.PLAIN, 15));
		description.setForeground(Color.black);

		// payment Value box
		paymentValue.setBounds(220, 240, 130, 20);
		paymentValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymentValue.setForeground(Color.black);
		PromptSupport.setPrompt("Insert amount", paymentValue);
		paymentValue.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Border lowered_bevelborderr = BorderFactory.createLoweredBevelBorder();
		paymentValue.setBorder(lowered_bevelborderr);

		// description Value box
		descriptionValue.setBounds(220, 405, 130, 100);
		descriptionValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		descriptionValue.setForeground(Color.black);
		descriptionValue.setLineWrap(true);
		descriptionValue.setWrapStyleWord(true);
		PromptSupport.setPrompt("Write a description", descriptionValue);
		descriptionValue.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Border lowered_bevelborder = BorderFactory.createLoweredBevelBorder();
		descriptionValue.setBorder(lowered_bevelborder);

		// image container button --- will display the receipt image
		imagecontainer = new JButton();
		imagecontainer.setBounds(585, 330, 105, 155);
		imagecontainer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		imagecontainer.setForeground(Color.gray);
		imagecontainer.setBackground(Color.gray);
		imagecontainer.setBorder(null);
		imagecontainer.setBorder(null);
		imagecontainer.setOpaque(true);
		imagecontainer.setContentAreaFilled(false);
		imagecontainer.setBorderPainted(false);
		imagecontainer.setBorder(null);
		imagecontainer.setFocusPainted(false);
		imagecontainer.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// submit button
		submitBtn = new JButton();
		submitBtn.setBounds(610, 530, 100, 40);
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
		backBtn.setBounds(725, 530, 100, 40);
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

		// scan receipt button
		scanBtn = new JButton();
		scanBtn.setBounds(616, 140, 100, 30);
		scanBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scanBtn.setForeground(Color.gray);
		scanBtn.setBackground(Color.gray);
		scanBtn.setBorder(null);
		scanBtn.setOpaque(true);
		scanBtn.setContentAreaFilled(false);
		scanBtn.setBorderPainted(false);
		scanBtn.setBorder(null);
		scanBtn.setFocusPainted(false);
		scanBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// search receipt button
		searchBtn = new JButton();
		searchBtn.setBounds(618, 240, 100, 30);
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchBtn.setForeground(Color.gray);
		searchBtn.setBackground(Color.gray);
		searchBtn.setBorder(null);
		searchBtn.setOpaque(true);
		searchBtn.setContentAreaFilled(false);
		searchBtn.setBorderPainted(false);
		searchBtn.setBorder(null);
		searchBtn.setFocusPainted(false);
		searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// adding components to the frame
		searchBtn.add(searchImg);
		submitBtn.add(submitImg);
		backBtn.add(backImg);
		scanBtn.add(scanImg);
		frame.add(nameOptionBox);
		frame.add(name);
		frame.add(paymentType);
		frame.add(CalendarDisplay);
		frame.add(categoryOptionBox);
		frame.add(date);
		frame.add(category);
		frame.add(payment);
		frame.add(paymentTypeOptionBox);
		frame.add(paymentValue);
		frame.add(description);
		frame.add(backBtn);
		frame.add(submitBtn);
		frame.add(scanBtn);
		frame.add(descriptionValue);
		frame.add(scan);
		frame.add(searchRecit);
		frame.add(searchBtn);
		frame.add(imagecontainer);
		frame.add(scrollPane666);

		// setting BGimage to the frame
		try {
			frame.getContentPane()
					.add(new JPanelWithBackground("pics\\expensebg.png"));
		} catch (IOException e1) {
			// do nothing
		}
		frame.setVisible(true);

		// submit button action
		submitBtn.addActionListener(new ActionListener() {
			Input input;

			@Override
			public void actionPerformed(ActionEvent e) {
				comment.setText(null);
				// creating a new folder to store scanned receipt (if dosen't
				// exist already)
				File file = new File("recits");
				if (!file.exists()) {
					if (file.mkdir()) {
						System.out.println("Directory is created!");
					} else {
						System.out.println("Failed to create directory!");
					}
				}

				try {
					// storing all the "Expense form" values in variables
					DateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
					String des = descriptionValue.getText();
					double pay = Double.parseDouble(paymentValue.getText());
					String person = (String) nameOptionBox.getSelectedItem();
					Category branch = (Category) categoryOptionBox.getSelectedItem();
					Payment payType = (Payment) paymentTypeOptionBox.getSelectedItem();
					Date dateofPurchase = CalendarDisplay.getDate();
					SimpleDateFormat simpDate = new SimpleDateFormat("MM-dd-yyyy");
					String s = simpDate.format(dateofPurchase);
					java.sql.Date sqlDate = new java.sql.Date(dateofPurchase.getTime());

					// assigning all variables and creating new input(report)
					// object
					if (person != null) {
						input = new Input(person, sqlDate, pay, payType, branch, des);
						System.out.println("from if:  " + person);
					} else {
						comment.setText("Name box is empty. \nPlease run settings from main menu !");
						System.out.println("from else:  " + person);
						return;
					}

					// because ObjectOutputStream doesn't support "append" of
					// a new object, we first need to read all object from the
					// file, add the new one and write it again to the file

					try {

						File reports = new File("data\\reports.obg");

					} catch (Exception v) {
						//do nothing
					}
					ObjectInputStream ois = new ObjectInputStream(
							new FileInputStream("data\\reports.obg"));
					deserializedInputs = (java.util.List<Input>) ois.readObject();
					reports = deserializedInputs;
					reports.add(input);
					ois.close();
					comment.setText("New expense added");

				} catch (Exception v) {
					comment.setText("Amount field is typed incorrectly !");
				}

				try (ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream("data\\reports.obg"));) {
					out.writeObject(reports);
					out.close();
				} catch (IOException r) {
					comment.setText("An error had occurred !");
				}

			}
		});

		// back button action
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainManu m = new MainManu();
				try {
					m.prepareGUI();
				} catch (IOException e1) {
					//do nothing
				}
				frame.setVisible(true);
				frame.dispose();

			}
		});

		// scanner button action
		scanBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// tying to open the scanner interface (if the path the user
					// provided works)
					File file = new File(s.getSacannerPath());
					File file2 = new File("C:\\Windows\\System32\\devmgmt.msc");

					// first check if Desktop is supported by Platform or not

					Desktop desktop = Desktop.getDesktop();
					if (file.exists()) {
						try {
							desktop.open(file);
						} catch (IOException e1) {
							comment.setText("This file type is not supported ! ");
						}
					} else {
						try {
							// if scanner interface failed to load,
							// open the O.S driver manager
							desktop.open(file2);
						} catch (IOException e1) {
							//do nothing
						}
					}
				} catch (Exception v) {
					comment.setText("No scanner interface has found. \nPlease run settings from main menu");
				}
			}
		});

		// search button action
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// open the file chooser to import an image
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(getParent());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						// store the file path in a variable "path"
						path = chooser.getSelectedFile().getPath();
					}
					img = ImageIO.read(new File(path));
					int width = img.getWidth();
					int height = img.getHeight();
					icon = new ImageIcon(img);
					Image image = icon.getImage();
					// change size of the chosen image to fit the jlabel
					// container
					Image newimg = image.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH);
					icon = new ImageIcon(newimg);
					selectedImg.setIcon(icon);
					// add the container(with the image inside it) to a button
					imagecontainer.add(selectedImg);
					frame.setVisible(true);

				} catch (Exception e1) {
					comment.setText("Unable to upload the file. \nThis file type is not supported !");
				}

			}
		});

		// image container button -- will open a new frame with the chosen image
		// to dispaly for the user
		imagecontainer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new DragAndZoomImage();

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

	public BufferedImage getImg() {
		return img;
	}
}

package household.swingUi;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import org.jdesktop.swingx.JXSearchField.LayoutStyle;

import household.enumeration.Category;
import household.enumeration.Payment;
import logic.Bean.Input;

public class MainManu extends JFrame {
	/**
	 * 
	 */

	private File f;
	private static final long serialVersionUID = 5061958872089751733L;
	public static JFrame frame;
	private JLabel string;
	private static JPanel top;
	private JButton expenseBtn;
	private JButton reportsBtn;
	private JButton statBtn;
	private JButton authorBtn;

	// menu bar
	final JMenuBar menuBar = new JMenuBar();

	// menu category's
	final JMenu fileMenu = new JMenu("File");

	final JMenu helpMenu = new JMenu("Help");
	final JMenu windowMenu = new JMenu("Window");

	// file img
	private ImageIcon img_file = new ImageIcon("pics\\file.png");
	private JLabel filetImg = new JLabel(img_file);

	// expenses img
	private ImageIcon img_expe = new ImageIcon("pics\\expenses.png");
	private JLabel expentImg = new JLabel(img_expe);

	// reports img
	private ImageIcon img_repo = new ImageIcon("pics\\reports.png");
	private JLabel reportImg = new JLabel(img_repo);

	// statistics img
	private ImageIcon img_stat = new ImageIcon("pics\\statistics.png");
	private JLabel statImg = new JLabel(img_stat);

	public MainManu() {

	}

	public static void main(String[] args) {

		// loading progress bar frame will open in the beginning of the program
		JLabel number = new JLabel("number");
		JFrame frameProg = new JFrame();
		frameProg.setLocationRelativeTo(null);
		frameProg.setResizable(false);
		// progress bar
		JProgressBar dpb = new JProgressBar(0, 100);
		frameProg.setSize(160, 30);
		frameProg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameProg.setUndecorated(true);
		frameProg.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		number.setBounds(45, 3, 100, 20);
		dpb.setForeground(new Color(39, 127, 201));
		frameProg.add(dpb);
		dpb.add(number);
		frameProg.setVisible(true);

		// displaying a string that count till 100 inside the progress bar
		for (int i = 0; i <= 100; i++) {
			try {
				// will stop the progress bar for 0.5 sec in the "i" places
				if (i == 20 | i == 70 | i == 90) {
					Thread.sleep(500);
				}
			} catch (InterruptedException e1) {
				// do nothing
			}
			number.setText("Loading " + i + "%");
			dpb.setValue(i);
			if (dpb.getValue() == 100) {
				frameProg.setVisible(false);
				frameProg.dispose();
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e2) {
					// do nothing
				}
				try {
					MainManu main = new MainManu();
					main.prepareGUI();
					
				} catch (IOException e) {
					// do nothing
				}
			}
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// do nothing
			}
			try {

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Could not load components");
			}
		}

	}

	// an init method will create all components
	public void prepareGUI() throws IOException {
	

		// all menu items
		JMenuItem clearDBMenuItem = new JMenuItem("Clear Data",
				new ImageIcon("pics\\deletedata.png"));
		JMenuItem aboutMenuItem = new JMenuItem("About",
				new ImageIcon("pics\\pig.png"));
		JMenuItem newMenuItem = new JMenuItem("New expense",
				new ImageIcon("pics\\file.png"));
		JMenuItem SettingsMenuItem = new JMenuItem("Settings",
				new ImageIcon("pics\\settings.png"));
		JMenuItem openMenuItem = new JMenuItem("Open",
				new ImageIcon("pics\\openfile.png"));
		JMenuItem printMenuItem = new JMenuItem("Print",
				new ImageIcon("pics\\printer1.png"));
		JMenuItem exitMenuItem = new JMenuItem("Exit",
				new ImageIcon("pics\\exit.png"));
		JMenuItem webMenuItem = new JMenuItem("Website",
				new ImageIcon("pics\\internet.png"));
		JMenuItem StartMenuItem = new JMenuItem("Get started",
				new ImageIcon("pics\\start.png"));
		JMenuItem systemMenuItem = new JMenuItem("System report",
				new ImageIcon("pics\\report.png"));

		// main frame
		frame = new JFrame("Household Assistant");
		frame.setBounds(600, 150, 905, 650);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// menu bar
		menuBar.setBackground(new Color(39, 127, 201));

		// main panel
		top = new JPanel();
		top.setLayout(new FlowLayout());
		top.setBackground(Color.blue);
		top.setSize(0, 0);

		// menu bar categories
		fileMenu.setForeground(Color.black);
		windowMenu.setForeground(Color.black);
		helpMenu.setForeground(Color.black);

		// expense button
		expenseBtn = new JButton();
		expenseBtn.setBounds(330, 280, 250, 250);
		expenseBtn.setFont(new Font("Serif", Font.BOLD, 15));
		expenseBtn.setOpaque(true);
		expenseBtn.setContentAreaFilled(false);
		expenseBtn.setBorderPainted(false);
		expenseBtn.setBorder(null);
		expenseBtn.setFocusPainted(false);
		expenseBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// reports button
		reportsBtn = new JButton();
		reportsBtn.setBounds(630, 280, 250, 250);
		reportsBtn.setFont(new Font("Serif", Font.BOLD, 15));
		reportsBtn.setOpaque(true);
		reportsBtn.setContentAreaFilled(false);
		reportsBtn.setBorderPainted(false);
		reportsBtn.setBorder(null);
		reportsBtn.setFocusPainted(false);
		reportsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// statistics button
		statBtn = new JButton();
		statBtn.setBounds(30, 280, 250, 250);
		statBtn.setFont(new Font("Serif", Font.BOLD, 15));
		statBtn.setOpaque(true);
		statBtn.setContentAreaFilled(false);
		statBtn.setBorderPainted(false);
		statBtn.setBorder(null);
		statBtn.setFocusPainted(false);
		statBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// adding menu items to menu categories
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(printMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		helpMenu.add(StartMenuItem);
		helpMenu.add(webMenuItem);
		helpMenu.add(systemMenuItem);
		windowMenu.add(clearDBMenuItem);
		windowMenu.add(SettingsMenuItem);
		windowMenu.add(aboutMenuItem);

		// adding categories to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		menuBar.add(helpMenu);

		// adding components to the frame
		statBtn.add(statImg);
		expenseBtn.add(expentImg);
		reportsBtn.add(reportImg);
		frame.add(expenseBtn);
		frame.add(reportsBtn);
		frame.add(statBtn);
		frame.setJMenuBar(menuBar);
		frame.add(top);
		// setting BGimage to the frame
		frame.getContentPane()
				.add(new JPanelWithBackground("pics\\mainbg.png"));
		frame.setVisible(true);
		top.setVisible(true);

		// expenses button action
		expenseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				expencesManu x = new expencesManu();
				frame.setVisible(false);
				frame.dispose();

			}
		});

		// report button action
		reportsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ReportsManu r = new ReportsManu();
				frame.setVisible(false);
				frame.dispose();

			}
		});

		// statistics button action
		statBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StatisticsManu sm = new StatisticsManu();
				frame.setVisible(false);
				frame.dispose();

			}
		});

		SettingsMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Settings s = new Settings();
				frame.setEnabled(false);
				try {
					s.prepareGUI();
				} catch (IOException e1) {
					// do nothing
				}

			}
		});

		// "clear database" menu sub category action
		clearDBMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// creating a frame with 2 options to clear the data base,
				// "yes"/"no"
				JFrame cleardata = new JFrame("Clear Database");
				cleardata.setBounds(600, 150, 310, 150);
				cleardata.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				cleardata.setLocationRelativeTo(null);
				cleardata.setResizable(false);
				cleardata.setForeground(new Color(39, 127, 201));
				cleardata.setBackground(new Color(39, 127, 201));
				cleardata.setLayout(null);
				JLabel text = new JLabel("Are you sure you want to reset all settings ?");
				JLabel text2 = new JLabel("this will delete all data");
				text.setBounds(15, 10, 300, 20);
				text2.setBounds(80, 30, 300, 20);
				JButton yes = new JButton("Yes");
				JButton no = new JButton("No");
				yes.setBounds(70, 80, 60, 30);
				no.setBounds(170, 80, 60, 30);
				text.setFont(new Font("Tahoma", Font.BOLD, 12));
				text2.setFont(new Font("Tahoma", Font.BOLD, 12));

				cleardata.add(yes);
				cleardata.add(no);
				cleardata.add(text);
				cleardata.add(text2);
				cleardata.setVisible(true);

				no.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						cleardata.dispatchEvent(new WindowEvent(cleardata, WindowEvent.WINDOW_CLOSING));

					}
				});

				// attempting to clear database file
				yes.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						cleardata.dispatchEvent(new WindowEvent(cleardata, WindowEvent.WINDOW_CLOSING));

						try {
							File db = new File("data\\reports.obg");
							db.delete();
							JOptionPane.showMessageDialog(null,
									"Please restart the program for the applied changes to take effect");
							frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

						} catch (Exception v) {
//do nothing
						}

					}
				});

			}
		});

		// "system settings" menu category action
		systemMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				SystemReport sys = new SystemReport();

			}
		});

		// "about" menu category action
		aboutMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Opens the about Jframe
				About a = new About();
				try {
					a.prepareGUI();
				} catch (IOException e1) {
					//do nothing
				}

			}
		});

		// "new" menu sub category action
		newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				expencesManu ex = new expencesManu();
				frame.setVisible(false);
				frame.dispose();
			}
		});

		// "exit" menu item action
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});

		// "get started" menu item action
		StartMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// url provider direct user to the "House hold" website
				URI helpWeb;
				try {
					helpWeb = new URI(
							"https://cdnd.icons8.com/wp-content/uploads/2015/06/Website-Under-Construction.jpg");
					openWeb(helpWeb);
				} catch (URISyntaxException e1) {
					//do nothing
				}
			}
		});

		// "website" menu item action
		webMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// url provider direct user to the "House hold" website
				URI website;
				try {
					website = new URI(
							"https://cdnd.icons8.com/wp-content/uploads/2015/06/Website-Under-Construction.jpg");
					openWeb(website);
				} catch (URISyntaxException e1) {
					//do nothing
				}
			}
		});

		// "open" menu item action
		openMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String path = null;
				try {
					// 'will open a file chooser proving a way to the user to
					// manually open data file that will replace he's original
					// one
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Java database files", "obj", "data");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(getParent());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						path = chooser.getSelectedFile().getPath();
					}
					f = new File(path);

					JOptionPane.showMessageDialog(null, "This file type is not supported !");

				} catch (Exception e1) {
					//do nothing
				}
			}
		});

		// "print" menu item action
		printMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextArea table = new JTextArea();
				try {
					table.print();
				} catch (PrinterException e1) {
					//do nothing
				}
			}
		});

		// menu bar categories mouse actions --- providing a mouse hover on the
		// categories (to be pushed inside)
		fileMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// do nothing

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// do nothing

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// do nothing

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				fileMenu.doClick();

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// do nothing

			}
		});

		// edit menu category mouse action
		helpMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// do nothing

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// do nothing

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// do nothing

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				helpMenu.doClick();

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// do nothing

			}
		});

		// window menu category mouse action
		windowMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// do nothing

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// do nothing

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// do nothing

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				windowMenu.doClick();

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// do nothing

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

	// methods will use the "desktop" java interface to open the url
	private void openWeb(URI website) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(website);
			} catch (IOException e) {
				//do nothing
			}
		} else {
		}

	}
}
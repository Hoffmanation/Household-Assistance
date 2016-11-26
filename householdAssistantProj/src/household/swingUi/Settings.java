package household.swingUi;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

import org.jdesktop.swingx.prompt.PromptSupport;

import household.enumeration.Persons;
import household.swingUi.MainManu.JPanelWithBackground;

public class Settings extends JFrame {
	private static JFrame frame;
	private JLabel string;
	private static JPanel top;
	private JButton addnameBtn;
	private JButton addscanfileBtn;
	private JButton adddataBtn;
	private JButton backBtn;
	private JButton saveBtn;
	private JButton imagecontainer;
	private JTextArea nameinput;
	private JTextArea fileinput;
	private JTextArea datainput;
	private JLabel name;
	private JLabel data;
	private JLabel scanner;
	private JLabel saving = new JLabel("Saving settings...");
	private static String sacannerPath;
	private JFileChooser chooser;
	private JTextArea comment = new JTextArea();
	private JTextArea comment2 = new JTextArea();

	// add1 image
	private ImageIcon img_add = new ImageIcon("pics\\addbtn.png");
	private JLabel addImg = new JLabel(img_add);

	// add2 image
	private ImageIcon img_add1 = new ImageIcon("pics\\addbtn.png");
	private JLabel addImg1 = new JLabel(img_add1);

	// add3 image
	private ImageIcon img_add2 = new ImageIcon("pics\\addbtn.png");
	private JLabel addImg2 = new JLabel(img_add2);

	// save setting image
	private ImageIcon img_sava = new ImageIcon("pics\\savesettingsbtn.png");
	private JLabel saveitImg = new JLabel(img_sava);

	// back image
	private ImageIcon img_back = new ImageIcon("pics\\back.png");
	private JLabel backImg = new JLabel(img_back);

	public Settings() {

	}

	public Settings(String sacannerPath) throws HeadlessException {
		super();
		this.sacannerPath = sacannerPath;
	}

	public void prepareGUI() throws IOException {

		// main frame
		frame = new JFrame("Household Assistant");
		frame.setBounds(600, 150, 500, 650);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);

		// comment label (inside a scroll pane)
		comment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comment.setOpaque(false);
		comment.setBackground(new Color(0, 0, 0, 0));
		DefaultCaret caret = (DefaultCaret) comment.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		comment.setEditable(false);
		comment.getCaret().deinstall(comment);
		JScrollPane scrollPane666 = new JScrollPane(comment);
		scrollPane666.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scrollPane666.setBounds(30, 100, 400, 40);
		scrollPane666.getViewport().setOpaque(false);
		scrollPane666.setOpaque(false);

		// second comment label (inside a scroll pane)
		comment2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comment2.setOpaque(false);
		comment2.setBackground(new Color(0, 0, 0, 0));
		DefaultCaret caret1 = (DefaultCaret) comment.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		comment2.setEditable(false);
		comment2.getCaret().deinstall(comment2);
		JScrollPane scrollPane777 = new JScrollPane(comment2);
		scrollPane777.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		scrollPane777.setBounds(30, 500, 250, 40);
		scrollPane777.getViewport().setOpaque(false);
		scrollPane777.setOpaque(false);

		// add data button
		adddataBtn = new JButton();
		adddataBtn.setBounds(366, 380, 65, 40);
		adddataBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		adddataBtn.setForeground(Color.gray);
		adddataBtn.setBackground(Color.gray);
		adddataBtn.setBorder(null);
		adddataBtn.setOpaque(true);
		adddataBtn.setContentAreaFilled(false);
		adddataBtn.setBorderPainted(false);
		adddataBtn.setBorder(null);
		adddataBtn.setFocusPainted(false);
		adddataBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// add name button
		addnameBtn = new JButton();
		addnameBtn.setBounds(366, 180, 65, 40);
		addnameBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addnameBtn.setForeground(Color.gray);
		addnameBtn.setBackground(Color.gray);
		addnameBtn.setBorder(null);
		addnameBtn.setOpaque(true);
		addnameBtn.setContentAreaFilled(false);
		addnameBtn.setBorderPainted(false);
		addnameBtn.setBorder(null);
		addnameBtn.setFocusPainted(false);
		addnameBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// add scanner file button
		addscanfileBtn = new JButton();
		addscanfileBtn.setBounds(366, 280, 65, 40);
		addscanfileBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addscanfileBtn.setForeground(Color.gray);
		addscanfileBtn.setBackground(Color.gray);
		addscanfileBtn.setBorder(null);
		addscanfileBtn.setOpaque(true);
		addscanfileBtn.setContentAreaFilled(false);
		addscanfileBtn.setBorderPainted(false);
		addscanfileBtn.setBorder(null);
		addscanfileBtn.setFocusPainted(false);
		addscanfileBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// back button
		backBtn = new JButton();
		backBtn.setBounds(120, 550, 100, 40);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setForeground(Color.gray);
		backBtn.setBackground(Color.gray);
		backBtn.setOpaque(true);
		backBtn.setContentAreaFilled(false);
		backBtn.setBorderPainted(false);
		backBtn.setBorder(null);
		backBtn.setFocusPainted(false);
		backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// save setting button
		saveBtn = new JButton();
		saveBtn.setBounds(250, 550, 150, 40);
		saveBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		saveBtn.setForeground(Color.gray);
		saveBtn.setBackground(Color.gray);
		saveBtn.setBorder(null);
		saveBtn.setOpaque(true);
		saveBtn.setContentAreaFilled(false);
		saveBtn.setBorderPainted(false);
		saveBtn.setBorder(null);
		saveBtn.setFocusPainted(false);
		saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// main panel
		top = new JPanel();
		top.setLayout(new FlowLayout());
		top.setBackground(Color.blue);
		top.setSize(0, 0);

		// name label
		name = new JLabel("Add user's name: ");
		name.setBounds(30, 150, 160, 25);
		name.setFont(new Font("Tahoma", Font.BOLD, 15));
		name.setForeground(new Color(51, 51, 51));

		// data label
		data = new JLabel("Add an external data file: ");
		data.setBounds(30, 350, 200, 25);
		data.setFont(new Font("Tahoma", Font.BOLD, 15));
		data.setForeground(new Color(51, 51, 51));

		// scanner label
		scanner = new JLabel("Add scanner device: ");
		scanner.setBounds(30, 250, 220, 25);
		scanner.setFont(new Font("Tahoma", Font.BOLD, 15));
		scanner.setForeground(new Color(51, 51, 51));

		// name input text box
		nameinput = new JTextArea();
		nameinput.setBounds(300, 150, 130, 23);
		nameinput.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameinput.setForeground(Color.black);
		nameinput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Border lowered_bevelborderr = BorderFactory.createLoweredBevelBorder();
		nameinput.setBorder(lowered_bevelborderr);

		// data file text box
		datainput = new JTextArea();
		datainput.setBounds(300, 350, 130, 23);
		datainput.setFont(new Font("Tahoma", Font.PLAIN, 15));
		datainput.setForeground(Color.black);
		datainput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Border lowered_bevelborderr4 = BorderFactory.createLoweredBevelBorder();
		datainput.setBorder(lowered_bevelborderr4);

		// file path text box
		fileinput = new JTextArea();
		fileinput.setBounds(300, 250, 130, 23);
		fileinput.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fileinput.setForeground(Color.black);
		fileinput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Border lowered_bevelborderr1 = BorderFactory.createLoweredBevelBorder();
		fileinput.setBorder(lowered_bevelborderr1);

		saveBtn.add(saveitImg);
		backBtn.add(backImg);
		addnameBtn.add(addImg);
		addscanfileBtn.add(addImg1);
		adddataBtn.add(addImg2);
		// setting BGimage to the frame
		frame.setContentPane(
				new JLabel(new ImageIcon("pics\\settingsbg.png")));
		frame.add(data);
		frame.add(name);
		frame.add(scanner);
		frame.add(addnameBtn);
		frame.add(saveBtn);
		frame.add(backBtn);
		frame.add(fileinput);
		frame.add(datainput);
		frame.add(adddataBtn);
		frame.add(addscanfileBtn);
		frame.add(scrollPane666);
		frame.add(scrollPane777);
		frame.add(nameinput);

		frame.setVisible(true);

		// add data btn
		adddataBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				datainput.setText("");
				try {
					// method will open the O.S file chooser to choose an
					// external java data file (an object outputstream file)
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Execution files", "exe");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(getParent());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						datainput.append(chooser.getSelectedFile().getName());
					}

					// File file = new File(p.toString());
					// System.out.println(file);
				} catch (Exception e1) {
					// do nothing
				}

			}
		});

		// add scanner file btn
		addscanfileBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				datainput.setText("");
				try {
					// method will open the O.S file chooser to add the
					// scanner interface of the user
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Execution files", "exe");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(getParent());
					if (returnVal == JFileChooser.APPROVE_OPTION) {

						fileinput.append(chooser.getSelectedFile().getName());
					}

				} catch (Exception e1) {
					// do nothing
				}

			}
		});

		// add name btn
		addnameBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// method will add new value both to the "Person's name" box at
				// "expenses menu" and the input (new expense report)
				// java object
				comment.setText("");
				String name = nameinput.getText();
				Persons p = new Persons();
				if (!name.equals("")) {
					p.addName(name);
					comment.setText("New name added");
				} else {
					comment.setText("*User's name cannot be empty");
				}
			}
		});

		// back button action
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					frame.setVisible(false);
					frame.dispose();
					MainManu.frame.setVisible(true);
					MainManu.frame.enable(true);

				} catch (Exception e1) {
					// do nothing

				}

			}
		});

		// save button action
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// method will save all new inputs that had registered by the
				// user in the settings menu
				comment.setText("");
				try {
					sacannerPath = chooser.getSelectedFile().getPath();
				} catch (Exception e1) {
					comment.setText("*No scanner interface has been selected ! ");
					return;
				}
				// after "saving settings" are done a SwingWorker method will
				// provide a "saving" .gif to display in the frame to the user
				// validating the saving
				SwingWorker<Object, Object> sw = new SwingWorker<Object, Object>() {
					@Override
					protected Object doInBackground() throws Exception {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								ImageIcon icon = new ImageIcon(
										"pics\\save.gif");
								JLabel selectedImg = new JLabel(icon);

								selectedImg.setBounds(185, 420, 100, 100);
								selectedImg.setFont(new Font("Tahoma", Font.BOLD, 15));
								selectedImg.setForeground(new Color(51, 51, 51));

								saving.setBounds(185, 450, 150, 100);
								saving.setFont(new Font("Tahoma", Font.PLAIN, 15));
								saving.setForeground(new Color(51, 51, 51));

								frame.add(selectedImg);
								frame.add(saving);
								frame.repaint();
							}
						});
						Thread.sleep(2300);
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								ImageIcon icon = new ImageIcon(
										"pics\\save.gif");
								JLabel selectedImg = new JLabel(icon);

								selectedImg.setBounds(185, 420, 100, 100);
								selectedImg.setFont(new Font("Tahoma", Font.BOLD, 15));
								selectedImg.setForeground(new Color(51, 51, 51));

								saving.setBounds(185, 450, 150, 100);
								saving.setFont(new Font("Tahoma", Font.PLAIN, 15));
								saving.setForeground(new Color(51, 51, 51));

								frame.add(selectedImg);
								frame.add(saving);
								frame.repaint();
								frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
							}
						});
						return null;
					}
				};

				sw.execute();
			}
		});

	}

	// methods getter and setter for "sacannerPath" will provide the path for
	// the user local scanner interface , making he's scanner available through
	// the program
	public String getSacannerPath() {
		return sacannerPath;
	}

	public void setSacannerPath(String sacannerPath) {
		this.sacannerPath = sacannerPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adddataBtn == null) ? 0 : adddataBtn.hashCode());
		result = prime * result + ((addscanfileBtn == null) ? 0 : addscanfileBtn.hashCode());
		result = prime * result + ((addnameBtn == null) ? 0 : addnameBtn.hashCode());
		result = prime * result + ((chooser == null) ? 0 : chooser.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((datainput == null) ? 0 : datainput.hashCode());
		result = prime * result + ((fileinput == null) ? 0 : fileinput.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nameinput == null) ? 0 : nameinput.hashCode());
		result = prime * result + ((scanner == null) ? 0 : scanner.hashCode());
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Settings other = (Settings) obj;
		if (adddataBtn == null) {
			if (other.adddataBtn != null)
				return false;
		} else if (!adddataBtn.equals(other.adddataBtn))
			return false;
		if (addscanfileBtn == null) {
			if (other.addscanfileBtn != null)
				return false;
		} else if (!addscanfileBtn.equals(other.addscanfileBtn))
			return false;
		if (addnameBtn == null) {
			if (other.addnameBtn != null)
				return false;
		} else if (!addnameBtn.equals(other.addnameBtn))
			return false;
		if (chooser == null) {
			if (other.chooser != null)
				return false;
		} else if (!chooser.equals(other.chooser))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (datainput == null) {
			if (other.datainput != null)
				return false;
		} else if (!datainput.equals(other.datainput))
			return false;
		if (fileinput == null) {
			if (other.fileinput != null)
				return false;
		} else if (!fileinput.equals(other.fileinput))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nameinput == null) {
			if (other.nameinput != null)
				return false;
		} else if (!nameinput.equals(other.nameinput))
			return false;
		if (scanner == null) {
			if (other.scanner != null)
				return false;
		} else if (!scanner.equals(other.scanner))
			return false;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}

}

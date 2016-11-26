package household.swingUi;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.lang.instrument.Instrumentation;
import org.jdesktop.swingx.prompt.PromptSupport;

import logic.Bean.Input;
import logic.Bean.ReportLogic;

public class SystemReport {
	JScrollPane scrollPane = new JScrollPane();
	Instrumentation instrumentation = null;
	Object o = null;
	long a = 0;
	private ReportLogic g = new ReportLogic();
	private JFrame frame;
	private JPanel top;
	private JTextArea textArea;
	private String s = null;

	public SystemReport() {
		preperGui();
	}

	// an init method will create all components
	public void preperGui() {

		// main frame
		frame = new JFrame("System report");
		frame.setBounds(600, 150, 500, 650);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		textArea = new JTextArea();
		textArea.setBounds(40, 80, 420, 500);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setForeground(Color.white);
		textArea.setBackground(new Color(39, 127, 201));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.getCaret().deinstall(textArea);
		textArea.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Border lowered_bevelborder = BorderFactory.createLoweredBevelBorder();
		textArea.setBorder(lowered_bevelborder);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(textArea);
		scrollPane.setBounds(40, 80, 420, 500);

		// calculating and displaying database file report
		List<Input> in = g.getAllReport();
		ByteArrayInputStream baos;
		File f = new File("data\\reports.obg");
		textArea.append("\n");
		textArea.append(" HouseHold database:" + "\n");
		textArea.append("    Data size: " + f.length() / 1024 + "KB" + "\n");
		textArea.append("    Reports amount: " + in.size() + "\n\n");
		textArea.append("----------------------------------------------" + "\n\n");

		
		// calculating and displaying local CPU core report
		textArea.append(" Available processors (cores): " + Runtime.getRuntime().availableProcessors() + "\n");
		// free memory
		double freeMemory = Runtime.getRuntime().freeMemory() / 1073741824;
		if (freeMemory != 0.0) {
			textArea.append("    Free memory: " + freeMemory + " GB" + "\n");
		} else {
			textArea.append("    Free memory: " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " MB" + "\n");
		}

		// max memory
		double maxMemory = Runtime.getRuntime().maxMemory() / 1073741824;
		if (maxMemory != 0.0) {
			textArea.append("    Maximum memory: " + maxMemory + " GB" + "\n");
		} else {
			textArea.append("    Maximum memory: " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + " MB" + "\n\n");
		}
		textArea.append("----------------------------------------------" + "\n\n");

		
		// calculating and displaying local CPU drivers capacity report
		textArea.append(" Available drivers capacity:" + "\n");
		File[] roots = File.listRoots();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		for (File root : roots) {
			double totalSpace = root.getTotalSpace() / 1073741824;
			double freeSpace = root.getFreeSpace() / 1073741824;
			double usableSpace = (root.getTotalSpace() - root.getFreeSpace()) / 1073741824;
			double usableSpace2 = (root.getTotalSpace() - root.getFreeSpace()) / 1024 / 1024;
			textArea.append("    Driver name: " + root + " " + fsv.getSystemTypeDescription(root) + "\n");
			if (totalSpace != 0.0) {
				textArea.append("        Total space: " + totalSpace + " GB" + "\n");

			} else {
				textArea.append("        Total space: " + root.getTotalSpace() / 1024 / 1024 + " MB" + "\n");
			}

			if (freeSpace != 0.0) {
				textArea.append("        Free space: " + freeSpace + " GB" + "\n");
			} else {
				textArea.append("        Free space: " + root.getFreeSpace() / 1024 / 1024 + " MB" + "\n");
			}

			if (usableSpace != 0.0) {
				textArea.append("        Usable space: " + usableSpace + " GB" + "\n\n");

			} else {
				textArea.append("        Usable space: " + usableSpace2 + " MB" + "\n\n");
			}
		}

		// adding components to the main frame
		frame.setContentPane(
				new JLabel(new ImageIcon("pics\\syssettingsbg.png")));
		frame.add(scrollPane);
		frame.setVisible(true);
	}

}

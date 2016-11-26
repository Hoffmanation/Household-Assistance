package household.swingUi;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import household.swingUi.MainManu.JPanelWithBackground;

public class About {
	private JFrame aboutFrame = new JFrame("Household Assistant");;
	private JPanel top;

	// an init method will create all components
	public void prepareGUI() throws IOException {
		info info = new info();
		info.execute();

		// main frame
		aboutFrame.setBounds(600, 150, 600, 458);
		aboutFrame.setDefaultCloseOperation(aboutFrame.DISPOSE_ON_CLOSE);
		aboutFrame.setLocationRelativeTo(null);
		aboutFrame.setResizable(false);

		// main panel
		top = new JPanel();
		top.setLayout(new FlowLayout());
		top.setBackground(Color.blue);
		top.setSize(0, 0);

		aboutFrame.add(top);

	}

	// Swing worker will provide clickable url links on the frame
	public class info extends SwingWorker<Object, Object> {

		@Override
		protected Object doInBackground() throws Exception {
			final URI author = new URI("http://www.flaticon.com/authors/madebyoliver");
			final URI flaticon = new URI("http://www.flaticon.com");

			// method will call the "open####" method in attempt to open url
			class Openflaticon implements ActionListener {

				public void actionPerformed(ActionEvent e) {

					openFlatIcon(flaticon);

				}

			}

			class Openauthor implements ActionListener {

				public void actionPerformed(ActionEvent e) {

					openAuthor(author);

				}

			}

			// creating buttons for links and attaching the url provider to it
			JButton flaticonBtn = new JButton();
			flaticonBtn.setBounds(330, 385, 100, 10);
			flaticonBtn.setOpaque(true);
			flaticonBtn.setContentAreaFilled(false);
			flaticonBtn.setBorderPainted(false);
			flaticonBtn.setBorder(null);
			flaticonBtn.setFocusPainted(false);
			flaticonBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			flaticonBtn.addActionListener(new Openflaticon());

			JButton AuthorBtn = new JButton();
			AuthorBtn.setBounds(220, 385, 80, 10);
			AuthorBtn.setOpaque(true);
			AuthorBtn.setContentAreaFilled(false);
			AuthorBtn.setBorderPainted(false);
			AuthorBtn.setBorder(null);
			AuthorBtn.setFocusPainted(false);
			AuthorBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			AuthorBtn.addActionListener(new Openauthor());

			// setting BGimage to the frame
			aboutFrame.setContentPane(
					new JLabel(new ImageIcon("pics\\aboutbg.png")));
			// adding all components to the frame
			aboutFrame.add(AuthorBtn);
			aboutFrame.add(flaticonBtn);
			aboutFrame.setLayout(null);
			aboutFrame.setAlwaysOnTop(true);
			aboutFrame.setVisible(true);
			top.setVisible(true);

			return null;
		}

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
	private void openFlatIcon(URI flaticon) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(flaticon);
			} catch (IOException e) {
				//do nothing
			}
		} else {
		}

	}

	private void openAuthor(URI author) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(author);
			} catch (IOException e) {
				//do nothing
			}
		} else {
		}

	}

}

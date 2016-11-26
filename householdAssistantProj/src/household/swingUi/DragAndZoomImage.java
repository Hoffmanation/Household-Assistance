package household.swingUi;
/* Adapted from code posted by R.J. Lorimer in an articleentitled "Java2D: Have Fun With Affine
     Transform". The original post and code can be found 
     at http://www.javalobby.org/java/forums/t19387.html.
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;

public class DragAndZoomImage {
	PanAndZoomCanvas canvas;
	// the current pan and zoom transform
	AffineTransform at; 
	// storage for a transformed mouse point
	Point2D XFormedPoint; 

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new DragAndZoomImage();
			}
		});
	}

	public DragAndZoomImage() {
		JFrame frame = new JFrame("Receipt image");
		
		// Add the components to the canvas
		canvas = new PanAndZoomCanvas();
		PanningHandler panner = new PanningHandler();
		canvas.addMouseListener(panner);
		canvas.addMouseMotionListener(panner);
		canvas.setBorder(BorderFactory.createLineBorder(Color.black));

		// Add the components to the frame
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(600, 150,expencesManu.img.getWidth()+25 , expencesManu.img.getHeight()+50);
		frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.setVisible(true);
	}

	class PanAndZoomCanvas extends JComponent {
		double translateX;
		double translateY;
		double scale;

		PanAndZoomCanvas() {
			translateX = 0;
			translateY = 0;
			scale = 1;
	
			addMouseWheelListener(new MouseAdapter() {

				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					double delta = 0.05f * e.getPreciseWheelRotation();
					scale += delta;
					revalidate();
					repaint();
				}

			});

		}

		public void paintComponent(Graphics g) {
			Graphics2D ourGraphics = (Graphics2D) g;
			// save the original transform so that we can restore
			// it later
			AffineTransform saveTransform = ourGraphics.getTransform();

			// blank the screen. If we do not call super.paintComponent, then
			// we need to blank it ourselves
			ourGraphics.setColor(Color.black);
			ourGraphics.fillRect(0, 0, getWidth(), getHeight());

			// We need to add new transforms to the existing
			// transform, rather than creating a new transform from scratch.
			// If we create a transform from scratch, we will
			// will start from the upper left of a JFrame,
			// rather than from the upper left of our component
			at = new AffineTransform(saveTransform);

			// The zooming transformation. Notice that it will be performed
			// after the panning transformation, zooming the panned scene,
			// rather than the original scene
			at.translate(getWidth() / 2, getHeight() / 2);
			at.scale(scale, scale);
			at.translate(-getWidth() / 2, -getHeight() / 2);

			// The panning transformation
			at.translate(translateX, translateY);

			ourGraphics.setTransform(at);

			// draw the objects
			ourGraphics.setColor(Color.BLACK);

			// make sure you restore the original transform or else the drawing
			// of borders and other components might be messed up
			ourGraphics.setTransform(saveTransform);
			ourGraphics.drawImage(expencesManu.img, at, this);
		}

		public Dimension getPreferredSize() {
			return new Dimension(500, 500);
		}
	}

	class PanningHandler implements MouseListener, MouseMotionListener {
		double referenceX;
		double referenceY;
		// saves the initial transform at the beginning of the pan interaction
		AffineTransform initialTransform;

		// capture the starting point
		public void mousePressed(MouseEvent e) {

			// first transform the mouse point to the pan and zoom
			// coordinates
			try {
				XFormedPoint = at.inverseTransform(e.getPoint(), null);
			} catch (NoninvertibleTransformException te) {
				//do nothing
			}

			// save the transformed starting point and the initial
			// transform
			referenceX = XFormedPoint.getX();
			referenceY = XFormedPoint.getY();
			initialTransform = at;
		}

		public void mouseDragged(MouseEvent e) {

			// first transform the mouse point to the pan and zoom
			// coordinates. We must take care to transform by the
			// initial tranform, not the updated transform, so that
			// both the initial reference point and all subsequent
			// reference points are measured against the same origin.
			try {
				XFormedPoint = initialTransform.inverseTransform(e.getPoint(), null);
			} catch (NoninvertibleTransformException te) {
				//do nothing
			}

			// the size of the pan translations
			// are defined by the current mouse location subtracted
			// from the reference location
			double deltaX = XFormedPoint.getX() - referenceX;
			double deltaY = XFormedPoint.getY() - referenceY;

			// make the reference point be the new mouse point.
			referenceX = XFormedPoint.getX();
			referenceY = XFormedPoint.getY();

			canvas.translateX += deltaX;
			canvas.translateY += deltaY;

			// schedule a repaint.
			canvas.repaint();
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
	}

}
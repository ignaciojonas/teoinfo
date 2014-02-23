/*
 * Teoría de la información - 2013
 * TP Especial
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class LoadBmp extends Component {
	
	private static final long serialVersionUID = 1L;	
	private BufferedImage img;

	public LoadBmp() {
		try {
			img = ImageIO.read(new File("actorPrincipal.bmp"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		img = invert(img);
	}

	// ---------------------------------------------------------------------------------------------------------

	public BufferedImage invert(BufferedImage img) {
		Color col;
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				int RGBA = img.getRGB(x, y); // gets RGBA data for the specific pixel

				col = new Color(RGBA, true); // get the color data of the specific pixel

				col = new Color(255 - col.getRed(), 255 - col.getGreen(),
						255 - col.getBlue()); // Swaps values

				img.setRGB(x, y, col.getRGB()); // set the pixel to the altered colors
			}
		}
		return img;
	}

	// ---------------------------------------------------------------------------------------------------------

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	// ---------------------------------------------------------------------------------------------------------

	public Dimension getPreferredSize() {
		if (img == null) {
			return new Dimension(100, 100);
		} else {
			return new Dimension(img.getWidth(), img.getHeight());
		}
	}

	// ---------------------------------------------------------------------------------------------------------

	// ---------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------
	public static void main(String[] args) {

		JFrame frame = new JFrame("Load Image");

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.add(new LoadBmp());
		frame.pack();
		frame.setVisible(true);
	}
}
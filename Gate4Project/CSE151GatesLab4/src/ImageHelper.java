import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ImageHelper {
	/*
	 * Method to resize an image and return it as an imageicon. See detailed comments below.
	 */
	public static ImageIcon resizeIcon(File src){
		int newHeight = 80, newWidth = 80;        // Variables for the new height and width
		int priorHeight = 0, priorWidth = 0;
		BufferedImage image = null;
		ImageIcon sizeImage; 					// Need to return an imageicon for JLabel

		try {
			image = ImageIO.read(src);        // read the image
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Image could not be found!");
		}
		//set icon to image
		sizeImage = new ImageIcon(image);
		//set old dimensions
		if(sizeImage != null)
		{
			priorHeight = sizeImage.getIconHeight(); 
			priorWidth = sizeImage.getIconWidth();
		}

		//Create a new Buffered Image and Graphic2D object
		//Notice I am using ABGR and not RGB, alpha channel must be used for transparency
		BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2 = resizedImg.createGraphics();

		//This was to rotate the image of wire 45 degrees(method takes radians so 45 * pi/180 = .785
		//It works just fine but the layout manager is clipping it and only half is showing. We can fix later or throw it out. Not a big priority issue obviously
		if(src.toString().contains("WIRE")){
			//g2.rotate(.785, 10, 0);
		}
		//Use the Graphic object to draw a new image to the image in the buffer
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, 0, 0, newWidth, newHeight, null);
		//need to dispose after creating Graphics instance
		g2.dispose();

		// Convert the buffered image into an ImageIcon for return
		return (new ImageIcon(resizedImg));
	}

}


import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class NotGate extends Gate{
	protected boolean input = false;
	protected boolean output = false;
	protected ImageIcon img = new ImageIcon();
	protected JLabel lab = new JLabel();
	protected String myIcon = null;
	protected int x,y = 0;

	public NotGate(){
		myIcon = "src/NOTGate.png";
		setIcon();
	}
	
	public boolean isInBounds(int x, int y){
		if(x >= this.x && y >= this.y && x < this.x + 82 && y < this.y + 82){
			return true;
		}
		return false;
	}

	public NotGate(boolean input, boolean output, ImageIcon img, JLabel lab, String myIcon){
		this.input = input;
		this.output = output;
		this.img = img;
		this.lab = lab;
		this.myIcon = myIcon;
	}


	/**
	 * Alternative style for a copy constructor, using a static newInstance
	 * method.
	 */
	public static Gate newInstance(Gate gate) {
		return new Gate(gate.isInputOne(),gate.isInputTwo(),gate.isOutput(),gate.getImg(),gate.getLab(),gate.getMyIcon());
	}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isInput() {
		return input;
	}

	public void setInputOne(boolean input) {
		this.input = input;
	}

	public boolean isOutput() {
		return output;
	}

	public void setOutput(boolean output) {
		this.output = output;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public String getMyIcon() {
		return myIcon;
	}

	public void setMyIcon(String myIcon) {
		this.myIcon = myIcon;
	}

	public ImageIcon getIcon(){
		return this.img;
	}

	public JLabel getLab() {
		return lab;
	}

	public void setLab(JLabel lab) {
		this.lab = lab;
	}

	public void setIcon(){
		if(myIcon != null){
			img = resizeIcon(new File(myIcon));
		}
	}

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

	public NotGate(boolean input, boolean output){
		this.input = input;
		this.output = output;
	}

	public boolean LogicOperation(){
		return !input;
	}

}


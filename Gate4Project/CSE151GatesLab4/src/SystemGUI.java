import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class SystemGUI extends JFrame implements ActionListener{
	private JButton wireButton, scissorsButton, runButton, andGateButton, orGateButton, notGateButton, switchButton, clockButton,
								nandGateButton, norGateButton, xorGateButton, lightButton, terminalButton;
	
	private JLabel andGateLabel  = new JLabel(ImageHelper.resizeIcon(new File("src/ANDGate.png")));
	private JLabel orGateLabel   = new JLabel(ImageHelper.resizeIcon(new File("src/ORGate.png")));
	private JLabel notGateLabel  = new JLabel(ImageHelper.resizeIcon(new File("src/NOTGate.png")));
	private JLabel switchLabel   = new JLabel(ImageHelper.resizeIcon(new File("src/SWITCHOFF.png")));
	private JLabel clockLabel    = new JLabel(ImageHelper.resizeIcon(new File("src/CLOCKSpeed1.png")));
	private JLabel nandGateLabel = new JLabel(ImageHelper.resizeIcon(new File("src/NANDGate.png")));
	private JLabel norGateLabel  = new JLabel(ImageHelper.resizeIcon(new File("src/NORGate.png")));
	private JLabel xorGateLabel  = new JLabel(ImageHelper.resizeIcon(new File("src/XORGate.png")));
	private JLabel lightLabel    = new JLabel(ImageHelper.resizeIcon(new File("src/LIGHTOFF.png")));
	private JLabel terminalLabel = new JLabel(ImageHelper.resizeIcon(new File("src/TERMINAL.png")));
	
	private Insets insets 					 = getInsets();
	private MyMouseListener dragListener 	 = new MyMouseListener();
	private MouseClickListener clickListener = new MouseClickListener();
	ArrayList<Gate> gates 					 = new ArrayList<Gate>();
	private ArrayList<Wire> wires 			 = new ArrayList<Wire>();
	private boolean makeGate 				 = false;
	private int gateChosen 					 = 1;
	private Dimension iconDim 				 = new Dimension(BUTTON_SIZE, BUTTON_SIZE);
	private int currWire 					 = -1;
	private BufferedImage bi;
	private boolean isRunning, drawWire, cutOn = false;
	public static final int BUTTON_SIZE = 82, AND_GATE = 0, OR_GATE = 1, NOT_GATE = 2,  SWITCH = 3, CLOCK = 4, 
							  NAND_GATE = 5,  NOR_GATE = 6,XOR_GATE = 7,    LIGHT = 8,TERMINAL = 9;
	
	public void makeOptions(){
		wireButton     = new JButton(ImageHelper.resizeIcon(new File("src/WIRE.png")));	
		scissorsButton = new JButton(ImageHelper.resizeIcon(new File("src/SCISSORS.png")));
		runButton      = new JButton(ImageHelper.resizeIcon(new File("src/RUN.png")));
		
		wireButton.setPreferredSize(iconDim);
		wireButton.setActionCommand("wire");
		wireButton.addActionListener(this);
		
		scissorsButton.setPreferredSize(iconDim);
		scissorsButton.setActionCommand("scissors");
		scissorsButton.addActionListener(this);
		
		runButton.setPreferredSize(iconDim);
		runButton.setActionCommand("run");
		runButton.addActionListener(this);
	}
	
	public void positionOptions(){
		wireButton.setBounds((BUTTON_SIZE + 5) + insets.left, (500 + insets.bottom) - iconDim.height,iconDim.width,iconDim.height);
		scissorsButton.setBounds((BUTTON_SIZE*2 + 5) + insets.left, (500 + insets.bottom) - iconDim.height,iconDim.width,iconDim.height);
		runButton.setBounds((BUTTON_SIZE*3 + 5) + insets.left, (500 + insets.bottom) - iconDim.height,iconDim.width,iconDim.height);
	}

	public void makeGates(){
		
		andGateButton = new JButton(ImageHelper.resizeIcon(new File("src/AndGate.png")));
		andGateButton.setActionCommand("andgate");
		andGateLabel.setPreferredSize(iconDim);
		andGateButton.addActionListener(this);
		andGateButton.setPreferredSize(iconDim);
		
		orGateButton = new JButton(ImageHelper.resizeIcon(new File("src/ORGate.png")));
		orGateButton.setPreferredSize(iconDim);
		orGateButton.setActionCommand("orgate");
		orGateButton.addActionListener(this);
		orGateLabel.setPreferredSize(iconDim);
		
		notGateButton = new JButton(ImageHelper.resizeIcon(new File("src/NOTGate.png")));
		notGateButton.setPreferredSize(iconDim);
		notGateButton.setActionCommand("notgate");
		notGateButton.addActionListener(this);
		notGateLabel.setPreferredSize(iconDim);
		
		switchButton = new JButton(ImageHelper.resizeIcon(new File("src/SWITCHOFF.png")));
		switchButton.setPreferredSize(iconDim);
		switchButton.setActionCommand("switch");
		switchButton.addActionListener(this);
		switchLabel.setPreferredSize(iconDim);
		
		clockButton = new JButton(ImageHelper.resizeIcon(new File("src/CLOCKSpeed1.png")));
		clockButton.setPreferredSize(iconDim);
		clockButton.setActionCommand("clock");
		clockButton.addActionListener(this);
		clockLabel.setPreferredSize(iconDim);
		
		nandGateButton = new JButton(ImageHelper.resizeIcon(new File("src/NANDGate.png")));
		nandGateButton.setPreferredSize(iconDim);
		nandGateButton.setActionCommand("nandgate");
		nandGateButton.addActionListener(this);
		nandGateLabel.setPreferredSize(iconDim);
		
		norGateButton = new JButton(ImageHelper.resizeIcon(new File("src/NORGate.png")));
		norGateButton.setPreferredSize(iconDim);
		norGateButton.setActionCommand("norgate");
		norGateButton.addActionListener(this);
		norGateLabel.setPreferredSize(iconDim);
		
		xorGateButton = new JButton(ImageHelper.resizeIcon(new File("src/XORGate.png")));
		xorGateButton.setPreferredSize(iconDim);
		xorGateButton.setActionCommand("xorgate");
		xorGateButton.addActionListener(this);
		xorGateLabel.setPreferredSize(iconDim);
		
		lightButton = new JButton(ImageHelper.resizeIcon(new File("src/LIGHTOFF.png")));
		lightButton.setPreferredSize(iconDim);
		lightButton.setActionCommand("light");
		lightButton.addActionListener(this);
		lightLabel.setPreferredSize(iconDim);
		
		terminalButton = new JButton(ImageHelper.resizeIcon(new File("src/TERMINAL.png")));
		terminalButton.setPreferredSize(iconDim);
		terminalButton.setActionCommand("terminal");
		terminalButton.addActionListener(this);
		terminalLabel.setPreferredSize(iconDim);
		
		Dimension size = iconDim;
		andGateLabel.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,size.width,size.height);
		orGateLabel.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,size.width,size.height);
		notGateLabel.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,size.width,size.height);
		switchLabel.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,size.width,size.height);
		clockLabel.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,size.width,size.height);
		nandGateLabel.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,size.width,size.height);
		norGateLabel.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,size.width,size.height);
		xorGateLabel.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,size.width,size.height);
		lightLabel.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,size.width,size.height);
		terminalLabel.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,size.width,size.height);
	}

	public void positionGates(){
		andGateButton.setBounds(5 + insets.left, 5 + insets.top,iconDim.width,iconDim.height);
		orGateButton.setBounds(5 + insets.left, (5 + BUTTON_SIZE) + insets.top,iconDim.width,iconDim.height);
		notGateButton.setBounds(5 + insets.left, (5 + BUTTON_SIZE*2) + insets.top,iconDim.width,iconDim.height);
		switchButton.setBounds(5 + insets.left, (5 + BUTTON_SIZE*3) + insets.top,iconDim.width,iconDim.height);
		clockButton.setBounds(5 + insets.left, (5 + BUTTON_SIZE*4) + insets.top,iconDim.width,iconDim.height);
		nandGateButton.setBounds((5 + insets.left + BUTTON_SIZE), 5  + insets.top,iconDim.width,iconDim.height);
		norGateButton.setBounds(5 + insets.left + BUTTON_SIZE, (5 + BUTTON_SIZE) + insets.top,iconDim.width,iconDim.height);
		xorGateButton.setBounds(5 + insets.left + BUTTON_SIZE, (5 + BUTTON_SIZE*2) + insets.top,iconDim.width,iconDim.height);
		lightButton.setBounds(5 + insets.left + BUTTON_SIZE, (5 + BUTTON_SIZE*3) + insets.top,iconDim.width,iconDim.height);
		terminalButton.setBounds(5 + insets.left + BUTTON_SIZE, (5 + BUTTON_SIZE*4) + insets.top,iconDim.width,iconDim.height);
	}
	
	

	public void paint(Graphics g){
			bi = new BufferedImage( this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_ARGB);
			draw(bi.getGraphics());
			g.drawImage(bi, 0, 0, null);
	}
	
	public void draw(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        for(Gate gate : gates){
        	g.drawImage(gate.getIcon().getImage(), gate.getX(), gate.getY(), this);
        }
        for(Wire w : wires){
			g.drawLine(w.getStartX(), w.getStartY(), w.getEndX(), w.getEndY());
		}
    }

	public SystemGUI(){
		super("CSE151 Gates Lab");
		setLayout(null);
		makeGates();
		makeOptions();
		add(wireButton);
		add(scissorsButton);
		add(runButton);
		add(andGateButton);
		add(orGateButton);
		add(notGateButton);
		add(switchButton);
		add(clockButton);
		add(nandGateButton);
		add(norGateButton);
		add(xorGateButton);
		add(lightButton);
		add(terminalButton);
		positionGates();
		positionOptions();
		addMouseMotionListener(dragListener);
		addMouseListener(clickListener);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,540);
		setVisible(true);
	}

	public static void main(String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SystemGUI();
			}
		});
	}

	private class MouseClickListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(makeGate == true){
				switch (gateChosen){
				case AND_GATE: 
					makeGate = !makeGate;
					andGateLabel.setVisible(false);
					AndGate g = new AndGate();
					g.setLab(new JLabel(ImageHelper.resizeIcon(new File("src/ANDGate.png"))));
					g.getLab().addMouseMotionListener(dragListener);
					g.setX(e.getX());
					g.setY(e.getY());
					gates.add(g);
					break;
				case OR_GATE:
					makeGate = !makeGate;
					orGateLabel.setVisible(false);
					OrGate g1 = new OrGate();
					g1.setX(e.getX());
					g1.setY(e.getY());
					gates.add(g1);
					break;
				case NOT_GATE:
					makeGate = !makeGate;
					notGateLabel.setVisible(false);
					NotGate g2 = new NotGate();
					g2.setX(e.getX());
					g2.setY(e.getY());
					gates.add(g2);
					break;
				case SWITCH:
					makeGate = !makeGate;
					switchLabel.setVisible(false);
					Switch g3 = new Switch();
					g3.setX(e.getX());
					g3.setY(e.getY());
					gates.add(g3);
					break;
				case CLOCK:
					makeGate = !makeGate;
					clockLabel.setVisible(false);
					Clock g4 = new Clock();
					g4.setX(e.getX());
					g4.setY(e.getY());
					gates.add(g4);
					break;
			}
				
				repaint();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(drawWire){
				Wire w = new Wire();
				w.setStartX(e.getXOnScreen());
				w.setStartY(e.getYOnScreen());
			
				wires.add(w);
				currWire++;
				wires.get(currWire).setEndX(e.getXOnScreen());
				wires.get(currWire).setEndY(e.getYOnScreen());
				repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(drawWire){
				wires.get(currWire).setEndX(e.getXOnScreen());
				wires.get(currWire).setEndY(e.getYOnScreen());
				repaint();
			}
		}
	}

	private class MyMouseListener implements MouseMotionListener{
		@Override
		public void mouseDragged(MouseEvent e) {
			if(!drawWire){
				for(Gate g : gates){
					if(g.isInBounds(e.getX(), e.getY())){
						g.setX(e.getXOnScreen() - BUTTON_SIZE/2);
						g.setY(e.getYOnScreen() - BUTTON_SIZE/2 - 15);
						repaint();
					}
				}
			}
			else{
				wires.get(currWire).setEndX(e.getXOnScreen());
				wires.get(currWire).setEndY(e.getYOnScreen());
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(makeGate == true){
				switch (gateChosen){
				case AND_GATE: 
					andGateLabel.setVisible(true);
					andGateLabel.setLocation(e.getXOnScreen() - BUTTON_SIZE/2, e.getYOnScreen() - BUTTON_SIZE/2 - 15);
					repaint();
					break;
				case OR_GATE:
					orGateLabel.setVisible(true);
					orGateLabel.setLocation(e.getXOnScreen() - BUTTON_SIZE/2, e.getYOnScreen() - BUTTON_SIZE/2 - 15);
					repaint();
					break;
				case NOT_GATE:
					notGateLabel.setVisible(true);
					notGateLabel.setLocation(e.getXOnScreen() - BUTTON_SIZE/2, e.getYOnScreen() - BUTTON_SIZE/2 - 15);
					repaint();
					break;
				case SWITCH:
					switchLabel.setVisible(true);
					switchLabel.setLocation(e.getXOnScreen() - BUTTON_SIZE/2, e.getYOnScreen() - BUTTON_SIZE/2 - 15);
					repaint();
					break;
				case CLOCK:
					clockLabel.setVisible(true);
					clockLabel.setLocation(e.getXOnScreen() - BUTTON_SIZE/2, e.getYOnScreen() - BUTTON_SIZE/2 - 15);
					repaint();
					break;
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() != null){
		if(e.getActionCommand().equals("andgate")){
			makeGate = true;
			gateChosen = AND_GATE;
			this.add(andGateLabel);
		}
		if(e.getActionCommand().equals("orgate")){
			makeGate = true;
			gateChosen = OR_GATE;
			this.add(orGateLabel);
		}
		if(e.getActionCommand().equals("notgate")){
			makeGate = true;
			gateChosen = NOT_GATE;
			this.add(notGateLabel);
		}
		if(e.getActionCommand().equals("wire")){
			drawWire = !drawWire;
			if(drawWire){
				wireButton.setIcon(ImageHelper.resizeIcon(new File("src/WIREON_1.png")));
			}
			else{
				wireButton.setIcon(ImageHelper.resizeIcon(new File("src/WIRE.png")));
			}
		}
		if(e.getActionCommand().equals("switch")){
			makeGate = true;
			gateChosen = SWITCH;
			this.add(switchLabel);
		}
		if(e.getActionCommand().equals("clock")){
			makeGate = true;
			gateChosen = CLOCK;
			this.add(clockLabel);
		}
		}
	}

}

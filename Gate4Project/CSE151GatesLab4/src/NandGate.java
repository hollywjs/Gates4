import javax.swing.ImageIcon;

public class NandGate extends Gate {
	
	public NandGate(){
		super();
	}
	
	public boolean LogicOperation(){
		if(inputOne == true && inputTwo == true){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void setIcon(){
		myIcon = "src/NANDGate.png";
		img = new ImageIcon(myIcon);
	}
}

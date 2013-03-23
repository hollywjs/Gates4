import javax.swing.ImageIcon;

public class XorGate extends Gate {
	
	public XorGate(){
		super();
	}
	
	public boolean LogicOperation(){
		if(inputOne == true && inputTwo == true){
			return false;
		}
		else if(inputOne == false && inputTwo == false){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void setIcon(){
		myIcon = "src/XORGate.png";
		img = new ImageIcon(myIcon);
	}
}

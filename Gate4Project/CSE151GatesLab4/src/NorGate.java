import javax.swing.ImageIcon;

public class NorGate extends Gate {
	
	public NorGate(){
		super();
	}
	
	public boolean LogicOperation(){
		if(inputOne == false && inputTwo == false){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setIcon(){
		myIcon = "src/NORGate.png";
		img = new ImageIcon(myIcon);
	}
}

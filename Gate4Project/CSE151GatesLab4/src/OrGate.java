import java.io.File;

public class OrGate extends Gate {
	
	public OrGate(){
		super();
		myIcon = "src/ORGate.png";
		setIcon();
	}
	
	public boolean LogicOperation(){
		if(inputOne == true || inputTwo == true){
			return true;
		}
		return false;
	}
	
	public void setIcon(){
		if(myIcon != null){
			img = resizeIcon(new File(myIcon));
		}
		}

}

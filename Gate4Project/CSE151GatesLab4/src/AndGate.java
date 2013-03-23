import java.io.File;

public class AndGate extends Gate{
	
	public AndGate(){
		super();
		myIcon = "src/ANDGate.png";
		setIcon();
	}
	
	public boolean LogicOperation(){
		if(inputOne == true && inputTwo == true){
			return true;
		}
		return false;
	}
	
	public void setIcon(){

			img = resizeIcon(new File(myIcon));

	}
	
	
}

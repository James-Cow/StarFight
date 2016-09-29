import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class random extends Applet implements ActionListener{
	
	public void init(){
		Button button = new Button("SPAM");
		add(button);
	}
	
    public void paint(Graphics g){
    	g.drawString("Welcome in Java Applet.",40,20);
    }

	public void actionPerformed(ActionEvent arg0) {
		
	}
}
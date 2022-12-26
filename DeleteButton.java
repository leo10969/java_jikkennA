import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import javax.swing.*;

public class DeleteButton extends JButton{
    Mediator mediator;
	MyDrawing d;
    public DeleteButton(Mediator mediator) {
		super("Delete");
		addActionListener(new DeleteListener());
		this.mediator = mediator;
	}
	
	class DeleteListener implements ActionListener {
		MyDrawing e1;

		public void actionPerformed(ActionEvent e) {
			Enumeration<MyDrawing> e1 = mediator.selectedDrawingsElements();
			while(e1.hasMoreElements()){
				MyDrawing d = e1.nextElement();
				mediator.removeDrawing(d);
			}	
			
			mediator.repaint();
		}
	}
}

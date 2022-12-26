import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;


public class CutButton extends JButton{
	Mediator mediator;
	
	public CutButton(Mediator mediator) {
		super("Cut");
		addActionListener(new CutListener());
		this.mediator = mediator;
	}
	
	class CutListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mediator.cut();
			mediator.repaint();
		}
	}
}

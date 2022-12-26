import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class CopyButton extends JButton{
	Mediator mediator;
	
	public CopyButton(Mediator mediator) {
		super("Copy");
		addActionListener(new CopyListener());
		this.mediator = mediator;
	}
	
	class CopyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mediator.copy();
			mediator.repaint();
		}
	}
}

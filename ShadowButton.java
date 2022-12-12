import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShadowButton extends JButton
{
	Mediator med;
	
	public ShadowButton(Mediator med) {
		super("Shadow");
		
		addActionListener(new ShadowListener());
		
		this.med = med;
	}
	
	class ShadowListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			med.setShadow();
			med.repaint();
		}
	}
}
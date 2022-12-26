import javax.swing.*;
import java.awt.event.*;

public class AlphaMenu extends JTextField{
    Mediator mediator;
	AlphaMenu(Mediator mediator) {
		super("255", 5);
		this.mediator = mediator;
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = getText();
				try{
					int alpha = Integer.parseInt(value);
					if (alpha > 255) {
						setText("255");
						mediator.setAlpha(255);
						mediator.repaint();
					} else if (alpha < 0) {
						setText("0");
						mediator.setAlpha(0);
						mediator.repaint();
					} else {
						mediator.setAlpha(alpha);
						mediator.repaint();
					}
				}
				catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "0~255の整数値を入力してください。");
				}
			}
		});
	}
}

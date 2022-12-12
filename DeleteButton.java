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
			Enumeration<MyDrawing> d = mediator.drawingsElements();
			while(d.hasMoreElements()){
				e1 = d.nextElement();
				e1.setRegion();//選択する図形を取得。
				if(e1.isSelected == true){
					mediator.removeDrawing(e1);
				}
			}	
			
			mediator.repaint();
		}
	}
}

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
		MyDrawing e1;
		public void actionPerformed(ActionEvent e) {
			Enumeration<MyDrawing> d = mediator.drawingsElements();
			while(d.hasMoreElements()){
				e1 = d.nextElement();
				e1.setRegion();//選択する図形を取得。
				if(e1.isSelected == true){
					mediator.setSelectedDrawing(e1);
					mediator.copy();
				}
			}	
			
			mediator.repaint();
		}
	}
}

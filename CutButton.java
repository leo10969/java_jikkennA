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
		MyDrawing e1;
		public void actionPerformed(ActionEvent e) {
			Enumeration<MyDrawing> d = mediator.drawingsElements();
			while(d.hasMoreElements()){
				e1 = d.nextElement();
				e1.setRegion();//選択する図形を取得。
				if(e1.isSelected == true){
					mediator.setSelectedDrawing(e1);
					mediator.cut();
				}
			}	
			
			mediator.repaint();
		}
	}
}

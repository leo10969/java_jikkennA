import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class LoadButton extends JButton{
    Mediator mediator;
	
	public LoadButton(Mediator mediator) {
		super("Load");
		addActionListener(new LoadListener());
		this.mediator = mediator;
	}

    class LoadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Vector<MyDrawing> v;
			try {
				File file = null;
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) { 
					file = fc.getSelectedFile();
					FileInputStream fin = new FileInputStream(file);
					ObjectInputStream in = new ObjectInputStream(fin);
					v = (Vector) in.readObject();
					mediator.drawings.clear();
					mediator.drawings.addAll(v);
					Enumeration<MyDrawing> e1 = mediator.drawingsElements();
                	while(e1.hasMoreElements()){
                    	MyDrawing d = e1.nextElement();
						d.isSelected = false;
                	}
					mediator.repaint();
					fin.close();
				}
			} catch (Exception ex) {
			}
		}
	}
}

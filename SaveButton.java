import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SaveButton extends JButton{
    Mediator mediator;
	
	public SaveButton(Mediator mediator) {
		super("Save");
		addActionListener(new SaveListener());
		this.mediator = mediator;
	}

    class SaveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                File file = null;
                JFileChooser fc = new JFileChooser();
			    int returnVal = fc.showSaveDialog(null);
			    if (returnVal == JFileChooser.APPROVE_OPTION) { 
				    file = fc.getSelectedFile();
                    FileOutputStream fout = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fout);

                    out.writeObject(mediator.drawings);
                    out.flush();
                    fout.close();
			    }
                
            } catch (Exception ex){
            }
        }
    }
}
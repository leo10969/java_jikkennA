import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasteButton extends JButton{
	StateManager stateManager;
	
	public PasteButton(StateManager stateManager) {
		super("Paste");
		addActionListener(new PasteListener());
		this.stateManager = stateManager;
	}
	
	class PasteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new PasteState(stateManager));
		}
	}
}

class PasteState implements State{
    StateManager stateManager;
	public PasteState(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public void mouseDown(int x, int y) {
		stateManager.mediator.paste(x,y);
	}
    public void mouseUp(int x, int y) {
    }
    public void mouseDrag(int x, int y) {
    }
}
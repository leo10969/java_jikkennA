import javax.swing.*;
import java.awt.event.*;

public class DashButton extends JButton {
    Mediator mediator;

    public DashButton(Mediator mediator){
        super("Dash");
        addActionListener(new DashListener());
        this.mediator = mediator;
    }

    class DashListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            mediator.setDash();
            mediator.repaint();
        }
    }
}

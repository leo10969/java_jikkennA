import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HendeButton extends JButton {
    StateManager stateManager;

    public HendeButton(StateManager stateManager){
        super("Hende");

        addActionListener(new HendeListener());

        this.stateManager = stateManager;
    }

    class HendeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){//Hendeボタンを押したらHende描画モード
            stateManager.setState(new HendeState(stateManager));
        }
    }
}

class HendeState implements State{
    StateManager stateManager;
    MyHendecagonal h;
    int minsz = 10;

    public HendeState(StateManager stateManager){
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y){
        h = new MyHendecagonal(x, y, 0, 0);
        stateManager.addDrawing(h);
    }
    public void mouseUp(int x, int y){
        int x1 = h.getX();
        int y1 = h.getY();
        if(x-x1 <= minsz && x-x1 >= -minsz && y-y1 <= minsz && y-y1 >= -minsz){
            stateManager.removeDrawing(h);
        }
    }
    public void mouseDrag(int x, int y){
        int x2 = h.getX();
        int y2 = h.getY();
        h.setSize(x - x2, y - y2);
    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OvalButton extends JButton {
    StateManager stateManager;

    public OvalButton(StateManager stateManager){
        super("Oval");

        addActionListener(new OvalListener());

        this.stateManager = stateManager;
    }

    class OvalListener implements ActionListener{
        public void actionPerformed(ActionEvent e){//Ovalボタンを押したらOval描画モード
            stateManager.setState(new OvalState(stateManager));
        }
    }
}

class OvalState implements State{
    StateManager stateManager;
    int minsz = 10;
    MyOval o;
    public OvalState(StateManager stateManager){
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y){
        o = new MyOval(x, y, 0, 0);
        stateManager.addDrawing(o);
    }
    public void mouseUp(int x, int y){
        int x1 = o.getX();
        int y1 = o.getY();
        if(x-x1 <= minsz && x-x1 >= -minsz && y-y1 <= minsz && y-y1 >= -minsz){
            stateManager.removeDrawing(o);
        }
    }
    public void mouseDrag(int x, int y){
        int x2 = o.getX();
        int y2 = o.getY();
        o.setSize(x - x2, y - y2);
    }
}
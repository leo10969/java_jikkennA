import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RectButton extends JButton {
    StateManager stateManager;

    public RectButton(StateManager stateManager){
        super("Rectangle");

        addActionListener(new RectListener());

        this.stateManager = stateManager;
    }
    class RectListener implements ActionListener{//Rectボタンを押したらRectangle描画モード
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new RectState(stateManager));
        }
    }
}

class RectState implements State{
    StateManager stateManager;
    MyRectangle r;
    int minsz = 5;
    public RectState(StateManager stateManager){
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y){
        r = new MyRectangle(x, y, 0, 0);
        stateManager.addDrawing(r);
    }
    public void mouseUp(int x, int y){
        int x1 = r.getX();
        int y1 = r.getY();
        if(x-x1 <= minsz && x-x1 >= -minsz && y-y1 <= minsz && y-y1 >= -minsz){
            stateManager.removeDrawing(r);
        }
    }
    public void mouseDrag(int x, int y){
        int x2 = r.getX();
        int y2 = r.getY();
        r.setSize(x - x2, y - y2);
    }
}
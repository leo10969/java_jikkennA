import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PondeButton extends JButton {
    StateManager stateManager;
    Mediator med;

    public PondeButton(StateManager stateManager){
        super("Ponde");

        addActionListener(new PondeListener());

        this.stateManager = stateManager;
    }

    class PondeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){//Pondeボタンを押したらPonde描画モード
            stateManager.setState(new PondeState(stateManager));
            
        }
    }
}

class PondeState implements State{
    StateManager stateManager;
    Mediator mediator;
    int minsz = 10;
    MyPondering p;
    MyPondering pshadow;
    public PondeState(StateManager stateManager){
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y){
        p = new MyPondering(x, y, 0, 0);

        stateManager.addDrawing(p);
    }
    public void mouseUp(int x, int y){
        int x1 = p.getX();
        int y1 = p.getY();
        if(x-x1 <= minsz && x-x1 >= -minsz && y-y1 <= minsz && y-y1 >= -minsz){
            stateManager.removeDrawing(p);
        }
    }
    public void mouseDrag(int x, int y){
        int x2 = p.getX();
        int y2 = p.getY();
        p.setSize(x - x2, y - y2);
    }
}
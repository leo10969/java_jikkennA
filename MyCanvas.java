import java.util.*;
import java.awt.*;
import javax.swing.*;

public class MyCanvas extends JPanel {
    //各図形を格納するVector
    Mediator mediator;
    int x = 500;
    int y = 500;
    public MyCanvas(){
        this.mediator = new Mediator(this);
        setBackground(Color.white);
    }

    public Mediator getMediator(){
        return mediator;
    }
    public void paint(Graphics g){
        super.paint(g);

        Enumeration<MyDrawing> e = mediator.drawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.draw(g);
        }
    }

    
}


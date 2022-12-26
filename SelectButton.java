import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class SelectButton extends JButton{
    StateManager stateManager;
	public SelectButton(StateManager stateManager) {
		super("Select");
		
		addActionListener(new SelectListener());
		
		this.stateManager = stateManager;
	}
	
	class SelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new SelectState(stateManager.mediator));
		}
	}
}

class SelectState implements State{
    Mediator mediator;
    int x,y = 0;
    boolean normal = true;
    boolean multimode = false;
    boolean movemode = false;
    MyRectangle rect;
    
    public SelectState(Mediator mediator){
        this.mediator = mediator; 
    }

    public void mouseDown(int x, int y){
        if(normal){
            mediator.setSelected(x, y);
        }
        Enumeration<MyDrawing> e = mediator.selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            if(d.contains(x, y)){
                movemode = true;
            }
        }

        if(movemode == false){
            mediator.setSelected(x, y);
            multimode = mediator.selectedDrawings.isEmpty();
            if(multimode == false){
                movemode = true;
            }
        }
        //何も入ってなければ単一選択していないつまり図形の何もない箇所をマウスクリックし複数選択モード
        if(multimode){
            normal = false;
            rect = new MyRectangle(x, y, 0, 0);
            mediator.drawings.add(0, rect);
        }
        this.x = x;
        this.y = y;
    }

    public void mouseUp(int x, int y){
        int dx = x - this.x;
        int dy = y - this.y;
        if(multimode){
            rect.setSize(dx, dy);
            mediator.setMultiSelected(rect);
            mediator.selectedDrawings.remove(rect);
            mediator.removeDrawing(rect);
            multimode = false;
        }

        if(movemode){
            mediator.move(dx, dy);
            movemode = false;
        }
    }

    public void mouseDrag(int x, int y){
        int dx = x - this.x;
        int dy = y - this.y;
        if(movemode){
            mediator.move(dx, dy);
            this.x = x;
            this.y = y;
        }
        if(multimode){
            rect.setSize(dx, dy);
            rect.setRegion();
            mediator.setMultiSelected(rect);
        }
        
        
    }
}
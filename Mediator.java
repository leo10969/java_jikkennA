import java.awt.*;
import java.util.*;

public class Mediator{
    Vector<MyDrawing> drawings;
    MyCanvas canvas;
    Vector<MyDrawing> selectedDrawings = new Vector<MyDrawing>();
    MyDrawing shadow;
    Color fillColor = Color.white;
	Color lineColor = Color.black;
	int lineWidth = 1;
    Vector<MyDrawing> buffer = new Vector<MyDrawing>();

    public Mediator(MyCanvas canvas){
        this.canvas = canvas;
        drawings = new Vector<MyDrawing>();
    }

    public Enumeration<MyDrawing> drawingsElements(){
        return drawings.elements();
    }

    public Enumeration<MyDrawing> selectedDrawingsElements(){
        return selectedDrawings.elements();
    }

    public void addDrawing(MyDrawing d){
        drawings.add(d);
        setSelectedDrawing(d);
    }
    
    public void clearBuffer(){
        buffer = null;
    }
    public void copy(){
        clearBuffer();
        buffer = (Vector<MyDrawing>)selectedDrawings.clone();
    }

    public void cut(){
        clearBuffer();
        buffer = (Vector<MyDrawing>)selectedDrawings.clone();
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            removeDrawing(d);
        }
        
    }
    public void paste(int x, int y){
        MyDrawing clone = (MyDrawing)buffer.clone();
        clone.isSelected = false;
        clone.setLocation(x, y);
        addDrawing(clone);
        
        repaint();
    }
    public void removeDrawing(MyDrawing d){
        drawings.remove(d);
    }

    public void setSelectedDrawing(MyDrawing d){
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d1 = e.nextElement();
            d1.setSelected(false);
        }
        selectedDrawings.clear();
        selectedDrawings.add(d);
        System.out.println("setSelected is called!");
        
    }

    public Vector<MyDrawing> getSelectedDrawing(){
        return selectedDrawings;
    }

    public void move(int dx, int dy){
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.move(dx, dy);
        }
    }

    public void repaint(){
        canvas.repaint();
    }

    public void setSelected(int x, int y) {
        Enumeration<MyDrawing> e1 = selectedDrawingsElements();
        while(e1.hasMoreElements()) {
            MyDrawing d1 = e1.nextElement();
            if(selectedDrawings.isEmpty() == false) {
                d1.setSelected(false);
            }
        }
        selectedDrawings.clear();
        
        Enumeration<MyDrawing> e = drawingsElements();
        while(e.hasMoreElements()) {
            MyDrawing d = e.nextElement();
            d.setRegion();
            if(d.contains(x,y)) {
                if(selectedDrawings.isEmpty() == false) {
                    d.setSelected(false); 
                }
                selectedDrawings.clear();
                d.setSelected(true);
                selectedDrawings.add(d);
                break;
            }
        }
    }
   
    public void setMultiSelected(MyRectangle rect) {
        Enumeration<MyDrawing> e1 = selectedDrawingsElements();
        while(e1.hasMoreElements()) {
            MyDrawing d1 = e1.nextElement();
            if(selectedDrawings.isEmpty() == false) {
                d1.setSelected(false);
            }
        }
        selectedDrawings.clear();
        int x, y, w, h;
        int x1, y1;
        int x2, y2;
        int x3, y3;
        int x4, y4;
        Enumeration<MyDrawing> e = drawingsElements();
        while(e.hasMoreElements()) {
            MyDrawing d = e.nextElement();
            x = d.getX();
            y = d.getY();
            w = d.getW();
            h = d.getH();
            x1 = x;
            y1 = y;
            x2 = x + w;
            y2 = y;
            x3 = x;
            y3 = y + h;
            x4 = x + w;
            y4 = y + h;
            rect.setRegion();
            if(rect.contains(x1, y1) || rect.contains(x2, y2) || rect.contains(x3, y3) || rect.contains(x4, y4)){
                
                selectedDrawings.add(d);
                d.setSelected(true);
            }
        }
    }

    public void setFillColor(Color color) {
        fillColor = color;
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.setFillColor(fillColor);
        }
	}
	
	public void setLineColor(Color color) {
		lineColor = color;
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.setLineColor(lineColor);
        }
	}

    public void setLineWidth(int width) {
		lineWidth = width;
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.setLineWidth(lineWidth);
        }
	}

    public void setShadow(){
        Enumeration<MyDrawing> d = drawingsElements();
			while(d.hasMoreElements()){
				MyDrawing e1 = d.nextElement();
				e1.setRegion();//選択する図形を取得。
				if(e1.isSelected == true){
					if(e1.shadow){
                        e1.shadow = false;
                    }
                    else{
                        e1.shadow = true;
                    }
				}
			}
    }
}

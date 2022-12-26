import java.awt.*;
import java.util.*;

public class Mediator{
    Vector<MyDrawing> drawings;
    MyCanvas canvas;
    Vector<MyDrawing> selectedDrawings = new Vector<MyDrawing>();
    Color fillColor = Color.white;
	Color lineColor = Color.black;
	int lineWidth = 1;
    Vector<MyDrawing> buffer = new Vector<MyDrawing>();
    int alpha = 255;//透明度は0

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
    
    public Enumeration<MyDrawing> bufferElements(){
        return buffer.elements();
    }

    public void clearBuffer(){
        buffer.clear();
    }
    public void copy(){
        if(!selectedDrawings.isEmpty()){
            clearBuffer();
            buffer = (Vector<MyDrawing>)selectedDrawings.clone();
        }
    }

    public void cut(){
        if(!selectedDrawings.isEmpty()){
            clearBuffer();
            buffer = (Vector<MyDrawing>)selectedDrawings.clone();
            Enumeration<MyDrawing> e = selectedDrawingsElements();
            while(e.hasMoreElements()){
                MyDrawing d = e.nextElement();
                removeDrawing(d);
            }
        }
    }
    public void paste(int x, int y){
        int x0, y0, x1, y1;
        Enumeration<MyDrawing> e = bufferElements();
        MyDrawing d = e.nextElement();
        MyDrawing clone = (MyDrawing)d.clone();
        x0 = clone.getX();
        y0 = clone.getY();
        clone.setLocation(x, y);
        clone.isSelected = false;
        drawings.add(clone);
        while(e.hasMoreElements()){
            d = e.nextElement();
            clone = (MyDrawing)d.clone();
            x1 = clone.getX();
            y1 = clone.getY();
            clone.setLocation(x+x1-x0, y+y1-y0);
            clone.isSelected = false;
            drawings.add(clone); 
        }
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
        fillColor = setAlphaColor(this.alpha, color);
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.setFillColor(fillColor);
        }
	}
	
	public void setLineColor(Color color) {
		lineColor = setAlphaColor(this.alpha, color);
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.setLineColor(lineColor);
        }
	}

    public Color setAlphaColor(int alpha, Color color){
        int R = color.getRed();
        int G = color.getGreen();
        int B = color.getBlue();
        color = new Color(R, G, B, alpha);
        return color;
    }

    public void setAlpha(int alpha){
        this.alpha = alpha;
        fillColor = setAlphaColor(alpha, fillColor);
        lineColor = setAlphaColor(alpha, lineColor);
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            Color fill = d.getFillColor();
            Color line = d.getLineColor();
            fill = setAlphaColor(alpha, fill);
            line = setAlphaColor(alpha, line);
            d.setFillColor(fill);
            d.setLineColor(line);
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
        Enumeration<MyDrawing> e = selectedDrawingsElements();
		while(e.hasMoreElements()){
			MyDrawing d = e.nextElement();
			d.setRegion();//選択する図形を取得。
			if(d.shadow){
                d.shadow = false;
            }
            else{
                d.shadow = true;
            }	
		}
    }

    public void setDash(){
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            d.setRegion();
            if(d.isDashed){
                d.isDashed = false;
            }
            else{
                d.isDashed = true;
            }
        }
    }

    public void selectDash(float dashfloat[]){
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            if(d.isDashed){
                d.isSelectDashed = true;
                d.setRegion();
                float linewidth = d.getLineWidth();
                d.setLineType(new BasicStroke(linewidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dashfloat, 0));
            }
        }
    }

    public void ToFront(int count){
        Enumeration<MyDrawing> e = selectedDrawingsElements();
        while(e.hasMoreElements()){
            MyDrawing d = e.nextElement();
            if(d != drawings.lastElement()){
                drawings.insertElementAt(d, drawings.indexOf(d)+count);
                drawings.remove(d);
            }
            else{
                return;
            }
        }
    }
}

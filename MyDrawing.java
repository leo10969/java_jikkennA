import java.awt.*;
import java.awt.BasicStroke;
import java.util.function.Supplier;

public class MyDrawing implements Cloneable{
    private int x, y, w, h;
    private Color lineColor = Color.black, fillColor = Color.white;
    private int lineWidth = 1;
    private BasicStroke lineType;
    boolean isSelected = false;
    Shape region;//包含判定用
    final int SIZE = 7;//選択表示矩形につく四角形の大きさ
    boolean shadow;
    
    

    public MyDrawing(){

        x = y = 0;
        w = h = 40;
        lineColor = Color.black;
        fillColor = Color.white;
        lineWidth = 1;
        
        setRegion();
    }

    public MyDrawing(int xpt, int ypt, int wpt, int hpt) {
		setLocation(xpt, ypt);
		setSize(w, h);
	}

    public MyDrawing(int wpt, int hpt, Color line, Color fill){
        w = wpt;
        h = hpt;
        lineColor = line;
        fillColor = fill;
    }
    public MyDrawing(int xpt, int ypt, int wpt, int hpt, Color line, Color fill){
        setLocation(xpt, ypt);
        setSize(wpt, hpt);
        setLineColor(line);
        setFillColor(fill);
    }

    public void draw(Graphics g){
        if(isSelected){
            g.setColor(Color.black);
            g.fillRect(x+w/2-SIZE/2, y-SIZE/2, SIZE, SIZE);
            g.fillRect(x-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
            g.fillRect(x+w/2-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
            g.fillRect(x+w-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
            g.fillRect(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
            g.fillRect(x+w-SIZE/2, y-SIZE/2, SIZE, SIZE);
            g.fillRect(x-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
            g.fillRect(x+w-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
        }
    }

    public boolean getSelected(){
        return isSelected;
    }

    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }

    //包含判定用のメソッド
    public boolean contains(int x, int y){
        //MyDrawing を継承する子クラス内でそれぞれ定義する。
        //包含判定図形が矩形ならば、例えば、
        return region.contains(x, y);
    }

    public void setRegion(){
        //MyDrawing を継承する子クラス内でそれぞれ定義する。
        //包含判定図形が矩形ならば、例えば、
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();
        if(w < 0){
            x += w;
            w *= -1;
        }
        if(h < 0){
            y += h;
            h *= -1;
        }
        region = new Rectangle(x, y, w, h);
    }

    public void move(int dx, int dy){
        x += dx;
        y += dy;
        setRegion();
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
        setRegion();
    }

    public void setSize(int w, int h){
        this.w = w;
        this.h = h;
        setRegion();
    }

    public int getX(){
        int x = this.x;
        return x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        int y = this.y;
        return y;
    }
    public void setY(int y){
        this.y = y;
    }

    public int getW(){
        int w = this.w;
        return w;
    }
    public void setW(int w){
        this.w = w;
    }

    public int getH(){
        int h = this.h;
        return h;
    }
    public void setH(int h){
        this.h = h;
    }

    public Color getLineColor(){
        Color line = this.lineColor;
        return line;
    }
    public void setLineColor(Color line){
        this.lineColor = line;
    }
    public Color getFillColor(){
        Color fill = this.fillColor;
        return fill;
    }
    public void setFillColor(Color fill){
        this.fillColor = fill;
    }

    public int getLineWidth(){
        int lineWidth = this.lineWidth;
        return lineWidth;
    }
    public void setLineWidth(int lineWidth){
        this.lineWidth = lineWidth;
    }

    public BasicStroke getLineType(float width, int cap, int join, float miterlimit,  float[] dash, float dash_phase){
        BasicStroke Stroke1 = new BasicStroke(width, cap, join, miterlimit, dash, dash_phase);
        return Stroke1;
    }
    public void setLineType(BasicStroke lineType){
        this.lineType = lineType;
    }

    public MyDrawing clone() {
		MyDrawing d = null;
		try {
			d = (MyDrawing)super.clone();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
}
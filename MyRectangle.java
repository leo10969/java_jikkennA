import java.awt.*;

public class MyRectangle extends MyDrawing {
    public MyRectangle(int xpt, int ypt){
        super(30, 30, Color.black, Color.black);
        setLocation(xpt, ypt);
        setSize(0, 0);
    }
    public MyRectangle(int xpt, int ypt, int wpt, int hpt){
        super(xpt, ypt, wpt, hpt);
    }
    public boolean contains(int x, int y){
        return region.contains(x, y);
    }

    public void setRegion(){
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

    public void draw(Graphics g){
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();

        // 高さや横幅が負の時のための処理
        if(w < 0){
            x += w;
            w *= -1;
        }
        if(h < 0){
            y += h;
            h *= -1;
        }

        Graphics2D g2 = (Graphics2D)g;
        if(shadow){
            int x2 = x + 10;
            int y2 = y + 10;
            g2.setColor(Color.black);
            g2.fillRect(x2, y2, w, h);
            g2.drawRect(x2, y2, w, h);
        }
        g2.setStroke(new BasicStroke(getLineWidth()));
        g2.setColor(getFillColor());
        g2.fillRect(x, y, w, h);
        g2.setColor(getLineColor());
        g2.drawRect(x, y, w, h);
        super.draw(g2);
    }
}

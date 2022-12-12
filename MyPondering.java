import java.awt.*;

public class MyPondering extends MyDrawing{
    

    MyPondering(int xpt, int ypt){
        super(100, 100, Color.black, Color.black);
        setLocation(xpt, ypt);
        setSize(0, 0);
    }
    MyPondering(int xpt, int ypt, int wpt, int hpt){
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
        
        int n = 11;
        int rad = 360/n;
        int xli[] = new int[n];
        int yli[] = new int[n];
        int xli2[] = new int[n];
        int yli2[] = new int[n];
        
        if(w < 0){
            x += w;
            w *= -1;
        }
        if(h < 0){
            y += h;
            h *= -1;
        }
        int w2 = (int)w/3;
        int h2 = (int)h/3;

        Graphics2D g5 = (Graphics2D)g;
        
        g5.setStroke(new BasicStroke(getLineWidth()));
        
        for(int i = 0; i < n; i++){
            xli[i] = x + w2  + (int)(w2 * Math.cos(rad * i));
            yli[i] = y + h2 + (int)(h2 * Math.sin(rad * i));
            if(shadow){
                xli2[i] = xli[i] + 10;
                yli2[i] = yli[i] + 10;
                g5.setColor(Color.black);
                g5.fillOval(xli2[i], yli2[i], w2, h2);
                g5.drawOval(xli2[i], yli2[i], w2, h2);
            }
            g5.setColor(getFillColor());
            g5.fillOval(xli[i], yli[i], w2, h2);
            g5.setColor(getLineColor());
            g5.drawOval(xli[i], yli[i], w2, h2);
        }
        super.draw(g5);
    }
}
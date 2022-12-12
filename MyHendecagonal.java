import java.awt.*;

public class MyHendecagonal extends MyDrawing {
    public MyHendecagonal(int xpt, int ypt){
        super(50, 50, Color.black, Color.black);
        setLocation(xpt, ypt);
        setSize(0, 0);
    }
    public MyHendecagonal(int xpt, int ypt, int wpt, int hpt){
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
        // 高さや横幅が負の時のための処理
        if(w < 0){
            x += w;
            w *= -1;
        }
        if(h < 0){
            y += h;
            h *= -1;
        }
        int w2 = (int)w/2;
        int h2 = (int)h/2;

        for(int i = 0; i < n; i++){
            xli[i] = x + w2 + (int)(w2 * Math.cos(rad * i));
            yli[i] = y + h2 + (int)(h2 * Math.sin(rad * i));
        }

        Graphics2D g4 = (Graphics2D)g;
        if(shadow){
            int xli2[] = new int[n];
            int yli2[] = new int[n];
            for(int i = 0; i < 11; i++){
                xli2[i] = xli[i] + 10;
                yli2[i] = yli[i] + 10;
            }
            g4.setColor(Color.black);
            g4.fillPolygon(xli2, yli2, n);
        }
        g4.setStroke(new BasicStroke(getLineWidth()));
        g4.setColor(getFillColor());
        g4.fillPolygon(xli, yli, n);
        g4.setColor(getLineColor());
        g4.drawPolygon(xli, yli, n);
        super.draw(g4);
    }

    
}

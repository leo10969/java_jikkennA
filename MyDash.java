import java.awt.*;

public class MyDash extends BasicStroke{
    private static float pattern[] = {10.0f, 10.0f};
    public MyDash(float lineWidth){
        super(lineWidth, CAP_BUTT, JOIN_BEVEL, 1.0f, pattern, 0);
    }
}

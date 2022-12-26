import javax.swing.*;
import java.awt.event.*;

public class ResizeCanvas extends JTextField{
    MyCanvas canvas;

    ResizeCanvas(MyCanvas canvas){
        super("1000", 5);
        this.canvas = canvas;

        addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String strsize = getText();
                try{
                    int x = Integer.parseInt(strsize);
                    int y = Integer.parseInt(strsize);
                    if(Integer.parseInt(strsize) > 1000){
                        setText("1000");
                        x = y = 1000;
                    }
                    if(Integer.parseInt(strsize) < 0){
                        setText("1");
                        x = y = 1;
                    }
                    canvas.x = x;
                    canvas.y = y;
                    canvas.setBounds((1000-canvas.x)/2, (950-canvas.y)/2, canvas.x, canvas.y);
                }
                catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "1~1000の整数値を入力してください。");
                }
            }
        });
    }
}

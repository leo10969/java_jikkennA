import javax.swing.JComboBox;

public class DashMenu extends JComboBox{
    static String[] dashes = {"9.0f, 3.0f", "10.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f"};
    DashMenu(){
        super(dashes);
    }
}

import javax.swing.*;

public class LineColorMenu extends JComboBox{
    static String[] colors = {"black", "white", "red", "orange", "green",
    "blue", "pink", "other colors"};
	LineColorMenu() {
		super(colors);
	}
}

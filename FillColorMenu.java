import javax.swing.*;

public class FillColorMenu extends JComboBox{
    static String[] colors = {"black", "white", "red", "orange", "green",
			"blue", "pink", "other colors"};
	FillColorMenu() {
		super(colors);
	}
}

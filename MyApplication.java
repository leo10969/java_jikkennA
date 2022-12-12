import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MyApplication extends JFrame{
    StateManager stateManager;
    MyCanvas canvas;
    Mediator mediator;

    private JMenuBar menuBar;
    private FillColorMenu fillColorMenu;
    private LineColorMenu lineColorMenu;
    private LineWidthMenu lineWidthMenu;

    public MyApplication(){
        super("My Paint Application");
        MyCanvas canvas = new MyCanvas();
        canvas.setBackground(Color.white);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());

        stateManager = new StateManager(canvas);
		CutButton cutButton = new CutButton(canvas.mediator);
		jp.add(cutButton);
		CopyButton copyButton = new CopyButton(canvas.mediator);
		jp.add(copyButton);
		PasteButton pasteButton = new PasteButton(stateManager);
		jp.add(pasteButton);
		ShadowButton shadowButton = new ShadowButton(canvas.mediator);
		jp.add(shadowButton);
        DeleteButton deleteButton = new DeleteButton(canvas.mediator);
		jp.add(deleteButton);
        SelectButton selectButton = new SelectButton(stateManager);
        jp.add(selectButton);
        RectButton rectButton = new RectButton(stateManager);
        jp.add(rectButton);
        OvalButton ovalButton = new OvalButton(stateManager);
        jp.add(ovalButton);
        PondeButton pondeButton = new PondeButton(stateManager);
        jp.add(pondeButton);
        HendeButton hendeButton = new HendeButton(stateManager);
        jp.add(hendeButton);

        

        // メニューバーの設定
		JLabel fillColorLabel = new JLabel("fillColor");
		fillColorMenu = new FillColorMenu();
		fillColorMenu.addActionListener(new FillColorListener(this));
		menuBar.add(fillColorLabel);
		menuBar.add(fillColorMenu);
		JLabel lineColorLabel = new JLabel("lineColor");
		lineColorMenu = new LineColorMenu();
		lineColorMenu.addActionListener(new LineColorListener(this));
		menuBar.add(lineColorLabel);
		menuBar.add(lineColorMenu);
		JLabel lineWidthLabel = new JLabel("lineWidth");
		lineWidthMenu = new LineWidthMenu();
		lineWidthMenu.addActionListener(new LineWidthListener());
		menuBar.add(lineWidthLabel);
		menuBar.add(lineWidthMenu);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jp, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);

        canvas.addMouseListener(new MouseAdapter(){
            //現在の状態のmouseDown処理の呼び出し
            public void mousePressed(MouseEvent e){
                stateManager.mouseDown(e.getX(), e.getY());
                stateManager.mediator.repaint();
            }
            public void mouseReleased(MouseEvent e){
                stateManager.mouseUp(e.getX(), e.getY());
                stateManager.mediator.repaint();
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                stateManager.mouseDrag(e.getX(), e.getY());
                stateManager.mediator.repaint();
            }
        });

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });
        
    }
    // fillColorMenuのactionListener
	class FillColorListener implements ActionListener {
		MyApplication app;
		public FillColorListener(MyApplication app) {
			this.app = app;
		}
		public void actionPerformed(ActionEvent e) {
            if (fillColorMenu.getSelectedItem() == "black") {
				stateManager.mediator.setFillColor(Color.black);
				stateManager.mediator.repaint();
			}
			else if (fillColorMenu.getSelectedItem() == "white") {
				stateManager.mediator.setFillColor(Color.white);
				stateManager.mediator.repaint();
			}
            else if (fillColorMenu.getSelectedItem() == "red") {
				stateManager.mediator.setFillColor(Color.red);
				stateManager.mediator.repaint();
			}
			else if (fillColorMenu.getSelectedItem() == "orange") {
				stateManager.mediator.setFillColor(Color.orange);
				stateManager.mediator.repaint();
			}
			else if (fillColorMenu.getSelectedItem() == "green") {
				stateManager.mediator.setFillColor(Color.green);
				stateManager.mediator.repaint();
			}
			else if (fillColorMenu.getSelectedItem() == "blue") {
				stateManager.mediator.setFillColor(Color.blue);
				stateManager.mediator.repaint();
			}
			else if (fillColorMenu.getSelectedItem() == "pink") {
				stateManager.mediator.setFillColor(Color.pink);
				stateManager.mediator.repaint();
			}
			else if (fillColorMenu.getSelectedItem() == "other colors") {
				 JColorChooser colorChooser = new JColorChooser();
				 Color color = colorChooser.showDialog(app, "other colors", Color.black);
				 if (color != null)
					 stateManager.mediator.setFillColor(color);
				 stateManager.mediator.repaint();
			}
			else {
				stateManager.mediator.setFillColor(Color.white);
				stateManager.mediator.repaint();
			}
		}
	}
	
	//lineColorMenuのActionListener
	class LineColorListener implements ActionListener {
		MyApplication app;
		public LineColorListener(MyApplication app) {
			this.app = app;
		}
		public void actionPerformed(ActionEvent e) {
            if (lineColorMenu.getSelectedItem() == "black") {
				stateManager.mediator.setLineColor(Color.black);
				stateManager.mediator.repaint();
			}
            else if (lineColorMenu.getSelectedItem() == "white") {
				stateManager.mediator.setLineColor(Color.white);
				stateManager.mediator.repaint();
			}
			else if (lineColorMenu.getSelectedItem() == "red") {
				stateManager.mediator.setLineColor(Color.red);
				stateManager.mediator.repaint();
			}
			else if (lineColorMenu.getSelectedItem() == "orange") {
				stateManager.mediator.setLineColor(Color.orange);
				stateManager.mediator.repaint();
			}
			else if (lineColorMenu.getSelectedItem() == "green") {
				stateManager.mediator.setLineColor(Color.green);
				stateManager.mediator.repaint();
			}
			else if (lineColorMenu.getSelectedItem() == "blue") {
				stateManager.mediator.setLineColor(Color.blue);
				stateManager.mediator.repaint();
			}
			else if (lineColorMenu.getSelectedItem() == "pink") {
				stateManager.mediator.setLineColor(Color.pink);
				stateManager.mediator.repaint();
			}
			else if (lineColorMenu.getSelectedItem() == "other colors") {
				 JColorChooser colorChooser = new JColorChooser();
				 Color color = colorChooser.showDialog(app, "other colors", Color.black);
				 if (color != null)
					 stateManager.mediator.setLineColor(color);
				 stateManager.mediator.repaint();
			}
			else {
				stateManager.mediator.setLineColor(Color.black);
				stateManager.mediator.repaint();
			}
		}
	}
	
	//lineWidthMenuのActionListener
	class LineWidthListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String str = (String)lineWidthMenu.getSelectedItem();
			int width = Integer.parseInt(str);
			stateManager.mediator.setLineWidth(width);
			stateManager.mediator.repaint();
		}
	}
    public static void main(String[] args){
        MyApplication ma = new MyApplication();
        ma.setSize(1000, 1000);
        ma.setVisible(true);
    }

}


public class StateManager {
    State state = new RectState(this);
    MyCanvas canvas;
    Mediator mediator;

    public StateManager(MyCanvas canvas){
        this.mediator = canvas.getMediator();
    }
    // Stateをセットする
    public void setState(State state) {
        this.state = state;
    }
    //描画
    public void mouseDown(int x, int y) {
        state.mouseDown(x, y);
    }
    public void mouseUp(int x, int y) {
        state.mouseUp(x, y);
    }
    public void mouseDrag(int x, int y) {
        state.mouseDrag(x, y);
    }

    //Rectangle
    public void addDrawing(MyDrawing d) {
        this.mediator.addDrawing(d);
    }
    
    public void removeDrawing(MyDrawing d) {
        this.mediator.removeDrawing(d);
    }
}
import java.awt.event.*;

public class FunkyHandler implements MouseListener {
	private FunkyScene Scene;
	public FunkyHandler(FunkyScene scene) {
		Scene = scene;
	}
	public void mouseClicked(MouseEvent e) {
		Scene.addObject(e.getX(), e.getY());
	}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}

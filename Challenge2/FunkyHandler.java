import java.awt.event.MouseEvent;

public class FunkyHandler {
	public FunkyCoordinate offsets;
	public void mouseMoved(MouseEvent event) {
		offsets.move(event.getX(), event.getY());
	}
}

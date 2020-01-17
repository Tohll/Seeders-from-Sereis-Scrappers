package catapults;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Catapult extends JLabel {

	/**
	 *
	 */
	private static final long serialVersionUID = -750438745352999626L;
	private Point dockLocation;

	public Catapult() {
		this.setSize(new Dimension(40, 30));
		final ImageIcon icon = new ImageIcon("resources/img/MainHub.png");
		this.setIcon(icon);
	}

	public Point getDockLocation() {
		return this.dockLocation;
	}

	@Override
	public void setLocation(final Point point) {
		super.setLocation(point);
		this.dockLocation = new Point(point.x + 20, point.y + 15);
	}

}

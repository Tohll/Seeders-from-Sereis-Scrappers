package stations;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import rawResources.AbsResource;

public class MainHub extends JLabel {

	/**
	 *
	 */
	private static final long serialVersionUID = -1596623896869475072L;

	private Point dockLocation;

	public MainHub() {
		this.setSize(new Dimension(40, 30));
		final ImageIcon icon = new ImageIcon("resources/img/MainHub.png");
		this.setIcon(icon);
	}

	public Point getDockLocation() {
		return this.dockLocation;
	}

	public int sellToHub(final AbsResource resourceStack) {
		return (resourceStack.getPricePerUnit() * resourceStack.getStackSize());
	}

	@Override
	public void setLocation(final Point point) {
		super.setLocation(point);
		this.dockLocation = new Point(point.x + 20, point.y + 15);
	}
}

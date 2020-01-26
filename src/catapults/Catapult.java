package catapults;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import interfaces.Dockable;

public class Catapult extends JLabel implements Dockable {

	/**
	 *
	 */
	private static final long serialVersionUID = -750438745352999626L;
	private List<String> children;
	private Point dockLocation;
	private final String name;

	public Catapult(final String name, final int x, final int y, final List<String> children) {
		super();
		if (!children.isEmpty()) {
			this.children = children;
		} else {
			this.children = new ArrayList<>();
		}
		this.name = name;
		this.setSize(new Dimension(40, 30));
		this.setLocation(new Point(x, y));
		this.setName(name);
		final ImageIcon icon = new ImageIcon("resources/img/MainHub.png");
		this.setIcon(icon);
		this.setToolTipText(name);
	}

	public List<String> getChildren() {
		return this.children;
	}

	@Override
	public String getDockableName() {
		return this.name;
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

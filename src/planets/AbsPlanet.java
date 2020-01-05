package planets;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.ImageIcon;

public abstract class AbsPlanet extends ImageIcon {

	public static final String L = "Large";
	public static final String M = "Medium";
	public static final String S = "Small";
	private static final long serialVersionUID = 8042374841747079229L;
	protected String planetSize;
	protected Dimension size;
	protected Point position;

	protected AbsPlanet(final String filePath, final String planetSize, Point position) {
		super(filePath);
		this.position = position;
		this.planetSize = planetSize;
		switch (planetSize) {
		case S:
			this.size = new Dimension(50, 50);
			break;
		case M:
			this.size = new Dimension(100, 100);
			break;
		case L:
			this.size = new Dimension(150, 150);
			break;
		default:
			throw new IllegalArgumentException("Incorrect planet size used");
		}
	}

	public Dimension getSize() {
		return this.size;
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public String getPlanetSize () {
		return this.planetSize;
	}
}

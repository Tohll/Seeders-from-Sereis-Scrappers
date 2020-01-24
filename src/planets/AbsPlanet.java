package planets;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controlers.PlayerControler;
import progressBars.HaulerBuildProgress;
import ships.AbsShip;
import stations.regulars.AbsStation;

public abstract class AbsPlanet extends JLabel {

	public static final String L = "Large";
	public static final String M = "Medium";
	public static final String S = "Small";
	private static final long serialVersionUID = 8042374841747079229L;
	protected HaulerBuildProgress haulerBuildProgress;
	protected String planetSize;
	private final List<AbsShip> ships;
	protected AbsStation station;
	protected String type;

	protected AbsPlanet(final int number, final String filePath, final String planetSize, final Point position,
			final String type) {
		this.ships = new ArrayList<>();
		this.type = type;
		this.station = null;
		this.haulerBuildProgress = null;
		this.setName(String.valueOf(number));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				PlayerControler._getInstance().setSelectedPlanet((AbsPlanet) e.getComponent());
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(final MouseEvent e) {
				AbsPlanet.this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(final MouseEvent e) {
				AbsPlanet.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		this.setLocation(position);
		this.planetSize = planetSize;
		ImageIcon imageicon;
		switch (planetSize) {
		case S:
			this.setSize(new Dimension(25, 25));
			imageicon = new ImageIcon(filePath);
			imageicon = new ImageIcon(imageicon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
			this.setIcon(imageicon);
			break;
		case M:
			this.setSize(new Dimension(50, 50));
			imageicon = new ImageIcon(filePath);
			imageicon = new ImageIcon(imageicon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			this.setIcon(imageicon);
			break;
		case L:
			this.setSize(new Dimension(75, 75));
			imageicon = new ImageIcon(filePath);
			imageicon = new ImageIcon(imageicon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
			this.setIcon(imageicon);
			break;
		default:
			throw new IllegalArgumentException("Incorrect planet size used");
		}
	}

	public void addShip(final AbsShip ship) {
		this.ships.add(ship);
	}

	public HaulerBuildProgress getHaulerBuildProgress() {
		return this.haulerBuildProgress;
	}

	public String getPlanetSize() {
		return this.planetSize;
	}

	public String getPlanetType() {
		return this.type;
	}

	public List<AbsShip> getShips() {
		return this.ships;
	}

	public AbsStation getStation() {
		return this.station;
	}

	public void receiveShip(final AbsShip ship) {
		switch (ship.getType()) {
		case AbsShip.HAULER:
			this.haulerBuildProgress = null;
			break;
		default:
			throw new IllegalArgumentException("Planet received a bad type of ship");
		}
		this.ships.add(ship);
		ship.start();
	}

	public void setHaulerBuildProgress(final HaulerBuildProgress haulerBuildProgress) {
		this.haulerBuildProgress = haulerBuildProgress;
	}

	public void setStation(final AbsStation station) {
		this.station = station;
	}
}

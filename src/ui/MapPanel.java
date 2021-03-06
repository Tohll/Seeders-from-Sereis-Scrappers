package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controlers.CatapultsControler;
import controlers.DataControler;
import controlers.PlanetsAndHubControler;
import controlers.PlayerControler;
import controlers.ShipsControler;
import planets.AbsPlanet;
import ships.AbsShip;
import tools.Line;

public class MapPanel extends JPanel {

	private static final long serialVersionUID = -7273880090886312807L;
	private final ImageIcon bg;
	private Point boardLocation;
	private final Dimension boardSize;
	private final CatapultsControler catapultControler;
	private final Font defaultFont;
	private final Color greenMonitor;
	private final Point mouseCoordinates;
	private final PlanetsAndHubControler planetsControler;

	public MapPanel() throws IOException {
		this.defaultFont = new Font("Arial", Font.PLAIN, 11);
		this.catapultControler = CatapultsControler._getInstance();
		this.mouseCoordinates = new Point(0, 0);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.planetsControler = PlanetsAndHubControler._getInstance();
		this.greenMonitor = new Color(0.3f, 1f, 0, 0.45f);
		this.boardSize = new Dimension(Integer.parseInt(DataControler._getInstance().getConfigProperty("boardWidth")),
				Integer.parseInt(DataControler._getInstance().getConfigProperty("boardHeight")));
		this.setLayout(null);
		this.bg = new ImageIcon("resources/img/fond2.png");
		this.setSize(this.boardSize);
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(final MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					PlayerControler._getInstance().setSelectedPlanet(null);
				}
			}

		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					MapPanel.this.boardLocation = e.getPoint();
					MapPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				}
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(final MouseEvent e) {
				MapPanel.this.mouseCoordinates.x = e.getX();
				MapPanel.this.mouseCoordinates.y = e.getY();
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(final MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {

					MapPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(final MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {

					final int boardSizeY = (int) MapPanel.this.boardSize.getHeight();
					final int boardSizeX = (int) MapPanel.this.boardSize.getWidth();
					int x = MapPanel.this.getX() + e.getX() - (int) (MapPanel.this.boardLocation.getX());
					int y = MapPanel.this.getY() + e.getY() - (int) (MapPanel.this.boardLocation.getY());
					final int yOffSet = (-boardSizeY + MapPanel.this.getParent().getHeight()) - 2;
					final int xOffSet = (-boardSizeX + MapPanel.this.getParent().getWidth()) - 2;
					if (x > 0) {
						x = 0;
					} else if (x < xOffSet) {
						x = xOffSet;
					}
					if (y > 0) {
						y = 0;
					} else if (y < yOffSet) {
						y = yOffSet;
					}
					MapPanel.this.setLocation(x, y);
				}
			}
		});
		for (final AbsPlanet planet : this.planetsControler.getPlanets()) {
			this.add(planet);
		}
		final Set<String> keys = this.catapultControler.getCatapults().keySet();
		for (final String key : keys) {
			this.add(this.catapultControler.getCatapults().get(key));
		}
	}

	private void drawBackGround(final Graphics g) {
		g.drawImage(this.bg.getImage(), 0, 0, (int) this.boardSize.getWidth(), (int) this.boardSize.getHeight(), null);
	}

	private void drawShips(final Graphics g) {
		Polygon triangle;
		g.setFont(this.defaultFont);
		for (final AbsShip ship : ShipsControler._getInstance().getShips()) {
			if (!ship.isDocked()) {
				triangle = new Polygon(
						new int[] { (int) (ship.getPosition().x), (int) (ship.getPosition().x + 5),
								(int) ship.getPosition().x - 5 },
						new int[] { (int) ship.getPosition().y - 5, (int) (ship.getPosition().y + 5),
								(int) (ship.getPosition().y + 5) },
						3);
				g.setColor(Color.GREEN);
				g.fillPolygon(triangle);
				g.setColor(Color.WHITE);
				g.drawString(ship.getCurrentOrder(),
						(int) ship.getPosition().x - (int) (ship.getCurrentOrder().length() * 2.5f),
						(int) ship.getPosition().y - 10);
			}
		}
	}

	private void drawUI(final Graphics g) {
		g.setColor(Color.GREEN);
		for (final Line line : this.catapultControler.getCatapultsPath()) {
			g.drawLine(line.getBegin().x, line.getBegin().y, line.getEnd().x, line.getEnd().y);
		}
		for (final AbsPlanet planet : PlanetsAndHubControler._getInstance().getPlanets()) {
			if (planet.getStation() != null) {
				g.fillOval(planet.getLocation().x - 15, planet.getLocation().y - 15, 10, 10);
			}
		}
		g.setColor(this.greenMonitor);
		g.drawRect(1, 1, (int) this.boardSize.getWidth() - 4, (int) this.boardSize.getHeight() - 4);
		g.drawRect(2, 2, (int) this.boardSize.getWidth() - 6, (int) this.boardSize.getHeight() - 6);
		g.setColor(new Color(0.3f, 1f, 0, 0.25f));
		g.drawRect(4, 4, (int) this.boardSize.getWidth() - 10, (int) this.boardSize.getHeight() - 10);
		g.drawRect(6, 6, (int) this.boardSize.getWidth() - 14, (int) this.boardSize.getHeight() - 14);
		g.setColor(new Color(0.3f, 1f, 0, 0.15f));
		g.drawRect(8, 8, (int) this.boardSize.getWidth() - 18, (int) this.boardSize.getHeight() - 18);
		// selector
		final AbsPlanet selectedPlanet = PlayerControler._getInstance().getSelectedPlanet();
		if (selectedPlanet != null) {
			g.setColor(this.greenMonitor);
			g.drawRect(selectedPlanet.getLocation().x, selectedPlanet.getLocation().y, selectedPlanet.getSize().width,
					selectedPlanet.getSize().height);
		}
	}

	public Point getMouseLocationOnBoard() {
		return this.mouseCoordinates;
	}

	@Override
	public Dimension getPreferredSize() {
		return this.boardSize;
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		this.drawBackGround(g);
		this.drawUI(g);
		this.drawShips(g);
	}
}

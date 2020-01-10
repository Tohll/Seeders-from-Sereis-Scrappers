package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controlers.PlanetsControler;
import controlers.PlayerControler;
import planets.AbsPlanet;
import tools.ConfigFileReader;

public class Map extends JPanel {

	private static final long serialVersionUID = -7273880090886312807L;
	private final ImageIcon bg;
	private Point boardLocation;
	private final Dimension boardSize;
	private final ConfigFileReader configFileReader = new ConfigFileReader();
	private final Color greenMonitor;
	private final PlanetsControler planetsControler;

	public Map() throws IOException {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.planetsControler = PlanetsControler._getInstance();
		this.greenMonitor = new Color(0.3f, 1f, 0, 0.45f);
		this.boardSize = new Dimension(Integer.parseInt(this.configFileReader.getPropertieValue("boardWidth")),
				Integer.parseInt(this.configFileReader.getPropertieValue("boardHeight")));
		this.setLayout(null);
		this.bg = new ImageIcon("resources/img/fond.png");
		this.setSize(this.boardSize);
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(final MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					PlayerControler._getInstance().setSelectedPlanet(null);
				}
			}

			@Override
			public void mousePressed(final MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					Map.this.boardLocation = e.getPoint();
					Map.this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				}
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(final MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {

					Map.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(final MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {

					final int boardSizeY = (int) Map.this.boardSize.getHeight();
					final int boardSizeX = (int) Map.this.boardSize.getWidth();
					int x = Map.this.getX() + e.getX() - (int) (Map.this.boardLocation.getX());
					int y = Map.this.getY() + e.getY() - (int) (Map.this.boardLocation.getY());
					final int yOffSet = (-boardSizeY + Map.this.getParent().getHeight()) - 2;
					final int xOffSet = (-boardSizeX + Map.this.getParent().getWidth()) - 2;
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
					Map.this.setLocation(x, y);
				}
			}
		});
		for (final AbsPlanet planet : this.planetsControler.getPlanets()) {
			this.add(planet);
		}
	}

	private void drawBackGround(final Graphics g) {
		g.drawImage(this.bg.getImage(), 0, 0, (int) this.boardSize.getWidth(), (int) this.boardSize.getHeight(), null);
	}

	private void drawUI(final Graphics g) {
		g.setColor(Color.GREEN);
		for (final AbsPlanet planet : PlanetsControler._getInstance().getPlanets()) {
			if (planet.getStation() != null) {
				g.fillOval(planet.getLocation().x - 30, planet.getLocation().y - 30, 15, 15);
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

	@Override
	public Dimension getPreferredSize() {
		return this.boardSize;
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		this.drawBackGround(g);
		this.drawUI(g);
	}
}

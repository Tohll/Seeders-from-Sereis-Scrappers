package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlers.PlanetsControler;
import controlers.PlayerControler;
import planets.AbsPlanet;
import tools.ConfigFileReader;

public class GraphicEngine extends JPanel implements Runnable {

	private static final long serialVersionUID = -7273880090886312807L;
	private final ImageIcon bg;
	private Point boardLocation;
	private final Dimension boardSize;
	private final ConfigFileReader configFileReader = new ConfigFileReader();
	private final Font defaultFont;
	private final int delay;
	private final Color greenMonitor;
	private final boolean hasToDisplay;
	private final PlanetsControler planetsControler;
	private final Color uiBackgroundColor;

	public GraphicEngine() throws IOException {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.planetsControler = PlanetsControler._getInstance();
		this.greenMonitor = new Color(0.3f, 1f, 0, 0.45f);
		this.boardSize = new Dimension(Integer.parseInt(this.configFileReader.getPropertieValue("boardWidth")),
				Integer.parseInt(this.configFileReader.getPropertieValue("boardHeight")));
		this.setLayout(null);
		this.defaultFont = new Font("Arial", Font.BOLD, 18);
		this.uiBackgroundColor = new Color(0, 0, 0, 0.3f);
		this.bg = new ImageIcon("resources/img/fond.png");
		this.delay = 25;
		this.hasToDisplay = true;
		this.setSize(this.boardSize);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				GraphicEngine.this.boardLocation = e.getPoint();
				GraphicEngine.this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(final MouseEvent e) {
				GraphicEngine.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(final MouseEvent e) {
				final int boardSizeY = (int) GraphicEngine.this.boardSize.getHeight();
				final int boardSizeX = (int) GraphicEngine.this.boardSize.getWidth();
				int x = GraphicEngine.this.getX() + e.getX() - (int) (GraphicEngine.this.boardLocation.getX());
				int y = GraphicEngine.this.getY() + e.getY() - (int) (GraphicEngine.this.boardLocation.getY());
				final int yOffSet = (-boardSizeY + GraphicEngine.this.getRootPane().getHeight()) - 2;
				final int xOffSet = (-boardSizeX + GraphicEngine.this.getRootPane().getWidth()) - 2;
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
				GraphicEngine.this.setLocation(x, y);
			}
		});
		for (final AbsPlanet planet : this.planetsControler.getPlanets()) {
			this.add(planet);
		}
	}

	@Override
	public void addNotify() {
		super.addNotify();
		Thread animator;
		animator = new Thread(this);
		animator.start();
	}

	private void drawBackGround(final Graphics g) {
		g.drawImage(this.bg.getImage(), 0, 0, (int) this.boardSize.getWidth(), (int) this.boardSize.getHeight(), null);
	}

	private void drawUI(final Graphics g) {
		g.setColor(this.uiBackgroundColor);
		g.fillRect(15, 20, 122, 50);
		g.setFont(this.defaultFont);
		g.setColor(Color.WHITE);
		g.drawString(String.format("Width : %d", this.getWidth()), 20, 40);
		g.drawString(String.format("Height : %d", this.getHeight()), 20, 62);
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

	@Override
	public void run() {
		long beforeTime;
		long timeDiff;
		long sleep;
		beforeTime = System.currentTimeMillis();
		while (this.hasToDisplay) {
			this.repaint();
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = this.delay - timeDiff;

			if (sleep < 0) {
				sleep = 2;
			}
			try {
				Thread.sleep(sleep);
			} catch (final InterruptedException e) {
				final String msg = String.format("Thread interrupted: %s", e.getMessage());
				JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
				Thread.currentThread().interrupt();
			}
			beforeTime = System.currentTimeMillis();
		}
	}
}

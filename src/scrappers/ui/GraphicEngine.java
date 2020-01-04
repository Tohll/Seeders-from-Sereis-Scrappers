package scrappers.ui;

import java.awt.Color;
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

public class GraphicEngine extends JPanel implements Runnable {

	private static final long serialVersionUID = -7273880090886312807L;
	private final ImageIcon bg;
	private Point boardLocation;
	private final Font defaultFont;
	private final int delay;
	private final boolean hasToDisplay;
	private final Color uiBackgroundColor;

	public GraphicEngine() throws IOException {
		this.setLayout(null);
		this.defaultFont = new Font("Arial", Font.BOLD, 18);
		this.uiBackgroundColor = new Color(0, 0, 0, 0.3f);
		this.bg = new ImageIcon("resources/img/fond.png");
		this.delay = 25;
		this.setSize(this.getPreferredSize());
		this.hasToDisplay = true;
		this.setSize(new Dimension(1920, 1080));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				GraphicEngine.this.boardLocation = e.getPoint();
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(final MouseEvent e) {
				GraphicEngine.this.setLocation(
						GraphicEngine.this.getX() + e.getX() - (int) (GraphicEngine.this.boardLocation.getX()),
						GraphicEngine.this.getY() + e.getY() - (int) (GraphicEngine.this.boardLocation.getY()));
			}
		});
	}

	@Override
	public void addNotify() {
		super.addNotify();
		Thread animator;
		animator = new Thread(this);
		animator.start();
	}

	private void drawBackGround(final Graphics g) {
		g.drawImage(this.bg.getImage(), 0, 0, 3840, 2160, null);
	}

	private void drawUI(final Graphics g) {
		g.setColor(this.uiBackgroundColor);
		g.fillRect(15, 20, 122, 50);
		g.setFont(this.defaultFont);
		g.setColor(Color.WHITE);
		g.drawString(String.format("Width : %d", this.getWidth()), 20, 40);
		g.drawString(String.format("Height : %d", this.getHeight()), 20, 62);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(3840, 2160);
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

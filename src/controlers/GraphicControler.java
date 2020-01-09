package controlers;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GraphicControler implements Runnable {

	private final int delay;
	private boolean hasToDisplay;
	private final JPanel infoPanel;
	private final JPanel map;

	public GraphicControler(final JPanel infoPanel, final JPanel map) {
		this.delay = 25;
		this.infoPanel = infoPanel;
		this.map = map;
		this.hasToDisplay = true;
	}

	@Override
	public void run() {
		long beforeTime;
		long timeDiff;
		long sleep;
		beforeTime = System.currentTimeMillis();
		while (this.hasToDisplay) {
			this.map.repaint();
			this.infoPanel.repaint();
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = this.delay - timeDiff;

			if (sleep < 0) {
				sleep = 2;
			}
			try {
				Thread.sleep(sleep);
			} catch (final InterruptedException e) {
				final String msg = String.format("Thread interrupted: %s", e.getMessage());
				JOptionPane.showMessageDialog(this.map, msg, "Error", JOptionPane.ERROR_MESSAGE);
				this.hasToDisplay = false;
				Thread.currentThread().interrupt();
			}
			beforeTime = System.currentTimeMillis();
		}
	}

}

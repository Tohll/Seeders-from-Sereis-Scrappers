package progressBars;

import java.io.Serializable;

import controlers.PlayerControler;
import planets.AbsPlanet;
import ships.Hauler;

public class HaulerBuildProgress extends Thread implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6842277403610838950L;
	protected int currentTimer;
	protected AbsPlanet hostPlanet;
	protected int maxTimer;
	protected Runnable runnable;

	public HaulerBuildProgress(final AbsPlanet hostPlanet) {
		super("Hauler_Buld_Progress");
		this.hostPlanet = hostPlanet;
		this.maxTimer = 50000;
		this.runnable = new Runnable() {

			@Override
			public void run() {
				PlayerControler._getInstance().removeCreditsFromPlayer(200);
				for (int i = 0; i <= HaulerBuildProgress.this.maxTimer; i = i + 2) {
					HaulerBuildProgress.this.currentTimer = i;
					try {
						sleep(1);
					} catch (final InterruptedException e) {
						e.printStackTrace();
						HaulerBuildProgress.this.interrupt();
					}
				}

				HaulerBuildProgress.this.hostPlanet.receiveShip(new Hauler(HaulerBuildProgress.this.hostPlanet));
			}
		};
	}

	public int getCurrentTimer() {
		return this.currentTimer;
	}

	public int getMaxTimer() {
		return this.maxTimer;
	}

	@Override
	public void run() {
		if (this.runnable != null) {
			this.runnable.run();
		}
	}
}

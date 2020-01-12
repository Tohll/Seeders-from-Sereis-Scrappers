package ships;

import controlers.PlanetsAndHubControler;
import controlers.PlayerControler;
import planets.AbsPlanet;
import raw_resources.AbsResource;
import raw_resources.GenericResource;

public class Hauler extends AbsShip {

	/**
	 *
	 */
	private static final long serialVersionUID = 9025375384783918279L;

	public Hauler(final AbsPlanet homeLand) {
		super(homeLand, 5);
		this.type = AbsShip.HAULER;
		this.life = 100;
	}

	@Override
	protected void act() {
		while (!this.isInterrupted()) {
			try {
				this.genericLoad();
				this.move();
				this.sellToHub();
				this.move();
			} catch (final InterruptedException e) {
				e.printStackTrace();
				this.interrupt();
			}
		}
	}

	private void genericLoad() throws InterruptedException {
		this.docked = true;
		for (int i = 1; i <= 5; i++) {
			this.load(new GenericResource(5));
			sleep(500);
		}
		this.docked = false;
		this.setTarget(PlanetsAndHubControler._getInstance().getMainHub().getDockLocation());
	}

	private void sellToHub() throws InterruptedException {
		this.docked = true;
		for (final AbsResource resource : this.storage) {
			PlayerControler._getInstance()
			.addCreditsToPlayer(PlanetsAndHubControler._getInstance().getMainHub().sellToHub(resource));
			sleep(750);
		}
		this.storage.clear();
		this.docked = false;
		this.setTarget(this.homeLand.getLocation());
	}

}

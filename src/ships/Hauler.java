package ships;

import controlers.PlanetsAndHubControler;
import controlers.PlayerControler;
import planets.AbsPlanet;
import raw_resources.AbsResource;
import raw_resources.GenericResource;

public class Hauler extends AbsShip {

	public Hauler(final AbsPlanet homeLand) {
		super(homeLand);
		this.type = "Hauler";
		this.life = 100;
	}

	@Override
	protected void act() {
		while (!this.isInterrupted()) {
			this.setTarget(PlanetsAndHubControler._getInstance().getMainHub().getLocation());
			try {
				this.genericLoad();
				this.move();
				this.sellToHub();
				this.setTarget(this.homeLand.getLocation());
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
	}

}

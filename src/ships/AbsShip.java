package ships;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import planets.AbsPlanet;
import raw_resources.AbsResource;

public abstract class AbsShip extends Thread {

	protected boolean docked;
	protected AbsPlanet homeLand;

	protected int life;

	protected Point position;
	protected Runnable runnable;
	protected long speedIndex;
	protected List<AbsResource> storage;
	protected Point target;
	protected String type;

	public AbsShip(final AbsPlanet homeLand) {
		super();
		this.docked = true;
		this.storage = new ArrayList<>();
		this.homeLand = homeLand;
		this.position = homeLand.getLocation();
		this.speedIndex = 1;
		this.runnable = new Runnable() {

			@Override
			public void run() {
				AbsShip.this.act();

			}
		};
	}

	protected abstract void act();

	public Point getPosition() {
		return this.position;
	}

	public List<AbsResource> getStorage() {
		return this.storage;
	}

	public boolean isDocked() {
		return this.docked;
	}

	protected void load(final AbsResource resourceStack) {
		this.storage.add(resourceStack);
	}

	protected void move() throws InterruptedException {
		while ((this.position.x != this.target.x || this.position.y != this.target.y) && this.life > 0) {
			if (this.position.x > this.target.x) {
				this.position.x = this.position.x - 1;
			} else if (this.position.x < this.target.x) {
				this.position.x = this.position.x + 1;
			}
			if (this.position.y > this.target.y) {
				this.position.y = this.position.y - 1;
			} else if (this.position.y < this.target.y) {
				this.position.y = this.position.y + 1;
			}
			Thread.sleep(this.speedIndex * 3);
		}
	}

	@Override
	public void run() {
		if (this.runnable != null) {
			this.runnable.run();
		}
	}

	public void setTarget(final Point target) {
		this.target = target;
	}
}

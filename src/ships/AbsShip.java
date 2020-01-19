package ships;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import catapults.Catapult;
import raw_resources.AbsResource;
import tools.Vector2;

public abstract class AbsShip extends Thread implements Serializable {

	public static final String HAULER = "Hauler";

	/**
	 *
	 */
	private static final long serialVersionUID = 8482016902961145447L;

	protected Vector2 direction;
	protected float distance;

	protected boolean docked;

	protected Catapult homeLand;
	protected int life;
	protected Vector2 position;
	protected Runnable runnable;
	protected float speed;
	protected Vector2 start;
	protected List<AbsResource> storage;
	protected Vector2 target;
	protected String type;

	public AbsShip(final Catapult homeLand, final float speed) {
		super("Ship_Thread");
		this.docked = true;
		this.storage = new ArrayList<>();
		this.homeLand = homeLand;
		this.position = new Vector2(homeLand.getDockLocation().x, homeLand.getDockLocation().y);
		this.start = new Vector2(this.position.x, this.position.y);
		this.speed = speed;
		this.runnable = new Runnable() {

			@Override
			public void run() {
				AbsShip.this.act();

			}
		};
	}

	protected abstract void act();

	public Vector2 getPosition() {
		return this.position;
	}

	public List<AbsResource> getStorage() {
		return this.storage;
	}

	public String getType() {
		return this.type;
	}

	public boolean isDocked() {
		return this.docked;
	}

	protected void load(final AbsResource resourceStack) {
		this.storage.add(resourceStack);
	}

	protected void move() throws InterruptedException {
		while (Vector2.distance(this.start, this.position) < this.distance) {
			this.position.x += this.direction.x * this.speed;
			this.position.y += this.direction.y * this.speed;
			Thread.sleep(10);
		}
	}

	@Override
	public void run() {
		if (this.runnable != null) {
			this.runnable.run();
		}
	}

	public void setTarget(final Vector2 target) {
		this.target = target;
		this.start = new Vector2(this.position.x, this.position.y);
		this.distance = Vector2.distance(this.start, target);
		this.direction = Vector2.direction(target.x - this.start.x, target.y - this.start.y);
	}
}

package ships;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import catapults.Catapult;
import interfaces.Dockable;
import interfaces.OrderInterface;
import rawResources.AbsResource;
import tools.Vector2;

public abstract class AbsShip extends Thread implements Serializable {

	public static final String HAULER = "Hauler";

	/**
	 *
	 */
	private static final long serialVersionUID = 8482016902961145447L;

	private OrderInterface currentOrder;
	protected Vector2 direction;

	protected float distance;

	protected Dockable dockedStation;
	protected Catapult homeLand;
	protected int life;
	protected Map<Integer, OrderInterface> orders;
	protected Vector2 position;
	protected Runnable runnable;
	protected long speed;
	protected Vector2 start;
	protected List<AbsResource> storage;
	protected Vector2 target;
	protected String type;

	public AbsShip(final Catapult homeLand, final long speed) {
		super("Ship_Thread");
		this.dockedStation = homeLand;
		this.orders = new HashMap<>();
		this.storage = new ArrayList<>();
		this.homeLand = homeLand;
		this.position = new Vector2(homeLand.getDockLocation().x, homeLand.getDockLocation().y);
		this.start = new Vector2(this.position.x, this.position.y);
		this.speed = speed;
		this.runnable = new Runnable() {

			@Override
			public void run() {
				while (!AbsShip.this.isInterrupted()) {
					while (AbsShip.this.orders.isEmpty()) {
						try {
							sleep(1000);
						} catch (final InterruptedException e) {
							e.printStackTrace();
							AbsShip.this.interrupt();
						}
					}
					for (int i = 0; i < AbsShip.this.orders.size(); i++) {
						AbsShip.this.currentOrder = AbsShip.this.orders.get(i);
						AbsShip.this.currentOrder.executeOrder();
					}
				}
			}
		};
	}

	public String getCurrentOrder() {
		return this.currentOrder != null ? this.currentOrder.getComment() : "Iddle.";
	}

	public Dockable getDockedStation() {
		return this.dockedStation;
	}

	public Catapult getHomeLand() {
		return this.homeLand;
	}

	public Map<Integer, OrderInterface> getOrders() {
		return this.orders;
	}

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
		return this.dockedStation != null ? true : false;
	}

	protected void load(final AbsResource resourceStack) {
		this.storage.add(resourceStack);
	}

	public void move() throws InterruptedException {
		while (Vector2.distance(this.start, this.position) < this.distance) {
			this.position.x += this.direction.x;
			this.position.y += this.direction.y;
			Thread.sleep(this.speed);
		}
	}

	@Override
	public void run() {
		if (this.runnable != null) {
			this.runnable.run();
		}
	}

	public void setDockedStation(final Dockable dockedStation) {
		this.dockedStation = dockedStation;
	}

	public void setTarget(final Vector2 target) {
		this.target = target;
		this.start = new Vector2(this.position.x, this.position.y);
		this.distance = Vector2.distance(this.start, target);
		this.direction = Vector2.direction(target.x - this.start.x, target.y - this.start.y);
	}
}

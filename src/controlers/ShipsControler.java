package controlers;

import java.util.ArrayList;
import java.util.List;

import orders.MoveToCatapultOrder;
import orders.UnloadOrder;
import ships.AbsShip;
import ships.Hauler;

public class ShipsControler {

	private static class SingletonHolder {
		private static final ShipsControler INSTANCE = new ShipsControler();
	}

	public static ShipsControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private final List<AbsShip> ships;

	private ShipsControler() {
		int index = 0;
		this.ships = new ArrayList<>();
		AbsShip ship = new Hauler(CatapultsControler._getInstance().getCatapults().get("SES-001"));
		ship.getOrders().put(index, new MoveToCatapultOrder("MAT-001", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder("ACA-001", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder("AES-001", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder(ship.getHomeLand().getName(), ship));
		this.ships.add(ship);
		ship.start();

		index = 0;
		ship = new Hauler(CatapultsControler._getInstance().getCatapults().get("MAT-001"));
		ship.getOrders().put(index, new MoveToCatapultOrder("LAV-001", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder("LAV-002", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder("EFI-001", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder("ACA-001", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder(ship.getHomeLand().getName(), ship));
		this.ships.add(ship);
		ship.start();

		index = 0;
		ship = new Hauler(CatapultsControler._getInstance().getCatapults().get("MAT-001"));
		ship.getOrders().put(index, new MoveToCatapultOrder("LAV-001", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder("ACA-001", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder(ship.getHomeLand().getName(), ship));
		this.ships.add(ship);
		ship.start();

		index = 0;
		ship = new Hauler(CatapultsControler._getInstance().getCatapults().get("MAT-001"));
		ship.getOrders().put(index, new MoveToCatapultOrder("AES-001", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder("AES-002", ship));
		index++;
		ship.getOrders().put(index, new UnloadOrder(ship));
		index++;
		ship.getOrders().put(index, new MoveToCatapultOrder(ship.getHomeLand().getName(), ship));
		this.ships.add(ship);
		ship.start();
	}

	public void addShip(final AbsShip ship) {
		this.ships.add(ship);
	}

	public List<AbsShip> getShips() {
		return this.ships;
	}
}

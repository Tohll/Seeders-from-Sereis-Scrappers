package controlers;

import java.util.ArrayList;
import java.util.List;

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
		this.ships = new ArrayList<>();
		final AbsShip ship = new Hauler(CatapultsControler._getInstance().getCatapults().get("SES-001"));
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

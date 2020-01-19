package controlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import catapults.Catapult;
import tools.Line;

public class CatapultsControler implements Serializable {

	private static class SingletonHolder {
		private static final CatapultsControler INSTANCE = new CatapultsControler();
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 3633697174887429592L;

	public static CatapultsControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private Map<String, Catapult> catapults;
	private final List<Line> catapultsPath;

	private CatapultsControler() {
		this.catapultsPath = new ArrayList<>();
		this.initCatapults();
		this.initPaths();
	}

	public Map<String, Catapult> getCatapults() {
		return this.catapults;
	}

	public List<Line> getCatapultsPath() {
		return this.catapultsPath;
	}

	private void initCatapults() {
		Catapult catapult;
		int x;
		int y;
		List<String> children;
		String rawData;
		String[] substr;
		final Set<String> keys = DataControler._getInstance().getCatapults().stringPropertyNames();
		this.catapults = new HashMap<>();
		for (final String key : keys) {
			children = new ArrayList<>();
			rawData = DataControler._getInstance().getCatapults().getProperty(key);
			substr = rawData.split(",");
			x = Integer.parseInt(substr[0]);
			y = Integer.parseInt(substr[1]);
			if (substr.length > 2) {
				for (int i = 2; i < substr.length; i++) {
					children.add(substr[i]);
				}
			}
			catapult = new Catapult(key, x, y, children);
			this.catapults.put(key, catapult);
		}
	}

	private void initPaths() {
		Catapult childCatapult;
		for (final Map.Entry<String, Catapult> entry : this.catapults.entrySet()) {
			if (!entry.getValue().getChildren().isEmpty()) {
				for (final String child : entry.getValue().getChildren()) {
					childCatapult = this.catapults.get(child);
					this.catapultsPath
					.add(new Line(entry.getValue().getDockLocation(), childCatapult.getDockLocation()));
				}
			}
		}
	}

}

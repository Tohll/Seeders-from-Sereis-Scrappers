package controlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import catapults.Catapult;

public class CatapultsControler {

	private static class SingletonHolder {
		private static final CatapultsControler INSTANCE = new CatapultsControler();
	}

	public static CatapultsControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private Map<String, Catapult> catapults;

	private CatapultsControler() {
		this.initCatapults();
	}

	public Map<String, Catapult> getCatapults() {
		return this.catapults;
	}

	private void initCatapults() {
		Catapult catapult;
		int x;
		int y;
		final List<String> children = new ArrayList<>();
		String rawData;
		String[] substr;
		final Set<String> keys = DataControler._getInstance().getCatapults().stringPropertyNames();
		this.catapults = new HashMap<>();
		for (final String key : keys) {
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

}

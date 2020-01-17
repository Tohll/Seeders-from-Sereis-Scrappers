package controlers;

public class CatapultsControler {

	private static class SingletonHolder {
		private static final CatapultsControler INSTANCE = new CatapultsControler();
	}

	public static CatapultsControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private CatapultsControler() {

	}

}

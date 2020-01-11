package stations.regulars;

import java.io.Serializable;

public abstract class AbsStation implements Serializable {

	private static final long serialVersionUID = 9002374639240506733L;
	protected String name;
	protected String type;

	public AbsStation(final String name, final String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

}

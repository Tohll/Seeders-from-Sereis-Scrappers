package stations.regulars;

import java.io.Serializable;

import interfaces.Dockable;

public abstract class AbsStation implements Serializable, Dockable {

	private static final long serialVersionUID = 9002374639240506733L;
	protected String name;
	protected String type;

	public AbsStation(final String name, final String type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String getDockableName() {
		return this.getName();
	}

	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}

}

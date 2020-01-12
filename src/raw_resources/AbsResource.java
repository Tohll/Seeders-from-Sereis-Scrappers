package raw_resources;

public class AbsResource {

	protected int pricePerUnit;
	protected int stackSize;
	protected String type;

	public AbsResource(final int stackSize) {
		this.stackSize = stackSize < 1 ? 1 : stackSize;
	}

	public int getPricePerUnit() {
		return this.pricePerUnit;
	}

	public int getStackSize() {
		return this.stackSize;
	}

	public String getType() {
		return this.type;
	}

}

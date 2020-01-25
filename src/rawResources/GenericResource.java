package rawResources;

public class GenericResource extends AbsResource {

	public GenericResource(final int stackSize) {
		super(stackSize);
		this.type = "Generic resource";
		this.pricePerUnit = 10;
	}

}

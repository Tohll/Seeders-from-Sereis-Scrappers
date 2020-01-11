package interfaces;

import java.io.IOException;

public interface ExternalDataProvider {
	public String getPropertieValue(final String key) throws IOException;

}

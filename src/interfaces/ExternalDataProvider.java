package interfaces;

import java.io.IOException;
import java.util.Properties;

public interface ExternalDataProvider {
	public Properties getProperties() throws IOException;

}

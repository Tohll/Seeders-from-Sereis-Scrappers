package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import tools.ConfigFileReader;

/**
 * @author Seldan
 *
 */
public class Application extends JFrame {

	private static final long serialVersionUID = 6424756108494662748L;
	private final ConfigFileReader configFileReader = new ConfigFileReader();

	public Application() {
		this.setResizable(false);
		this.setBackground(new Color(0, 0, 0));
		this.setTitle("Seeders from Sereïs : Scrappers");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane jsp = null;
		try {
			jsp = new JScrollPane(new GraphicEngine(), ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		} catch (NumberFormatException | IOException e2) {
			e2.printStackTrace();
		}
		this.getContentPane().add(jsp);
		try {
			this.setSize(new Dimension(Integer.parseInt(this.configFileReader.getPropertieValue("width")),
					Integer.parseInt(this.configFileReader.getPropertieValue("height"))));
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		this.setLocationRelativeTo(null);
	}
}

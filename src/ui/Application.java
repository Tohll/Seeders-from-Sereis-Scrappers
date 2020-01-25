package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

import controlers.DataControler;
import controlers.GraphicControler;

/**
 * @author Seldan
 *
 */
public class Application extends JFrame {

	private static final long serialVersionUID = 6424756108494662748L;

	public Application() {
		this.setResizable(true);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(0, 0, 0));
		this.setTitle("Vision from Sereïs : Empire");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JSplitPane splitPlane = new JSplitPane();
		splitPlane.setContinuousLayout(true);
		splitPlane.resetToPreferredSizes();
		splitPlane.setDividerLocation(300);
		splitPlane.setDividerSize(0);
		JScrollPane jsp = null;
		MapPanel map = null;
		try {
			map = new MapPanel();
			jsp = new JScrollPane(map, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		final JPanel infoPanel = new InfoPanel(map);
		final JScrollPane infoScrollPane = new JScrollPane(infoPanel);
		this.getContentPane().add(splitPlane);
		splitPlane.setLeftComponent(infoScrollPane);
		splitPlane.setRightComponent(jsp);
		try {
			this.setSize(new Dimension(Integer.parseInt(DataControler._getInstance().getConfigProperty("width")),
					Integer.parseInt(DataControler._getInstance().getConfigProperty("height"))));
		} catch (final NumberFormatException e) {
			e.printStackTrace();
		}
		this.setLocationRelativeTo(null);
		final Thread graphicControler = new Thread(new GraphicControler(infoPanel, map));
		graphicControler.setName("Graphic_controler");
		graphicControler.start();
	}
}

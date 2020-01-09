package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controlers.DataControler;
import controlers.PlayerControler;
import interfaces.ObsInterface;
import planets.AbsPlanet;

public class InfoPanel extends JPanel implements ObsInterface {

	/**
	 *
	 */
	private static final long serialVersionUID = 1895792502596588154L;
	private final ImageIcon bg;
	private final Font defaultFont;
	//	private final Color uiBackgroundColor;
	private AbsPlanet selectedPlanet;

	public InfoPanel() {
		this.defaultFont = new Font("Arial", Font.BOLD, 18);
		//		this.uiBackgroundColor = new Color(0, 0, 0, 0.3f);
		this.bg = new ImageIcon("resources/img/infoPanel.jpeg");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		DataControler._getInstance().observePlayer(this);
	}

	private void drawBackGround(final Graphics g) {
		g.drawImage(this.bg.getImage(), 0, 0, null);
	}

	private void drawUI(final Graphics g) {
		//		g.setColor(this.uiBackgroundColor);
		//		g.fillRect(15, 20, 122, 50);
		if (this.selectedPlanet != null) {
			g.setFont(this.defaultFont);
			g.setColor(Color.WHITE);
			g.drawString(String.format("Planet number : %s", this.selectedPlanet.getName()), 20, 40);
			g.drawString(String.format("Planet type : %s", this.selectedPlanet.getPlanetType()), 20, 60);
			g.drawString(String.format("Planet size : %s", this.selectedPlanet.getPlanetSize()), 20, 80);
		}
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		this.drawBackGround(g);
		this.drawUI(g);
	}

	@Override
	public void update() {
		this.selectedPlanet = PlayerControler._getInstance().getSelectedPlanet();
	}

}

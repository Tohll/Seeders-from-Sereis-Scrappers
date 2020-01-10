package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controlers.DataControler;
import controlers.PlayerControler;
import interfaces.ObsInterface;
import planets.AbsPlanet;
import stations.Outpost;

public class InfoPanel extends JPanel implements ObsInterface {

	/**
	 *
	 */
	private static final long serialVersionUID = 1895792502596588154L;
	private final JButton addStation;
	private final ImageIcon bg;
	private final Font defaultFont;
	private AbsPlanet selectedPlanet;

	public InfoPanel() {
		// Add station Button
		this.addStation = new JButton("Add station");
		this.addStation.setBounds(20, 100, 100, 20);
		this.addStation.setVisible(false);
		this.addStation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (InfoPanel.this.selectedPlanet != null) {
					InfoPanel.this.selectedPlanet.setStation(new Outpost(InfoPanel.this.selectedPlanet.getName()));
				}
			}
		});
		// rest of constructor
		this.setLayout(null);
		this.add(this.addStation);
		this.defaultFont = new Font("Arial", Font.BOLD, 18);
		this.bg = new ImageIcon("resources/img/infoPanel.jpeg");
		DataControler._getInstance().observePlayer(this);
	}

	private void drawBackGround(final Graphics g) {
		g.drawImage(this.bg.getImage(), 0, 0, null);
	}

	private void drawUI(final Graphics g) {
		if (this.selectedPlanet != null) {
			if (this.selectedPlanet.getStation() != null) {
				this.addStation.setVisible(false);

			} else {
				this.addStation.setVisible(true);
			}
			g.setFont(this.defaultFont);
			g.setColor(Color.WHITE);
			g.drawString(String.format("Planet number : %s", this.selectedPlanet.getName()), 20, 40);
			g.drawString(String.format("Planet type : %s", this.selectedPlanet.getPlanetType()), 20, 60);
			g.drawString(String.format("Planet size : %s", this.selectedPlanet.getPlanetSize()), 20, 80);
		} else {
			this.addStation.setVisible(false);
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

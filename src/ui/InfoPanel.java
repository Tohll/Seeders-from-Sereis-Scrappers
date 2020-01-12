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
import ships.AbsShip;
import ships.Hauler;
import stations.regulars.Outpost;

public class InfoPanel extends JPanel implements ObsInterface {

	/**
	 *
	 */
	private static final long serialVersionUID = 1895792502596588154L;
	private final JButton addHauler;
	private final JButton addStation;
	private final ImageIcon bg;
	private final Font defaultFont;
	private AbsPlanet selectedPlanet;

	public InfoPanel() {
		// Add station Button
		this.addStation = new JButton("Add station");
		this.addStation.setBounds(20, 150, 100, 20);
		this.addStation.setVisible(false);
		this.addStation.setToolTipText("Cost: �1000");
		this.addStation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (InfoPanel.this.selectedPlanet != null) {
					InfoPanel.this.selectedPlanet.setStation(new Outpost(InfoPanel.this.selectedPlanet.getName()));
					PlayerControler._getInstance().removeCreditsFromPlayer(1000);
				}
			}
		});
		// rest of constructor
		// Add hauler Button
		this.addHauler = new JButton("Add hauler");
		this.addHauler.setBounds(20, 230, 100, 20);
		this.addHauler.setVisible(false);
		this.addHauler.setToolTipText("Cost: �200");
		this.addHauler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final AbsShip hauler = new Hauler(InfoPanel.this.selectedPlanet);
				InfoPanel.this.selectedPlanet.addShip(hauler);
				hauler.start();
				PlayerControler._getInstance().removeCreditsFromPlayer(200);
			}
		});
		// rest of constructor
		this.setLayout(null);
		this.add(this.addStation);
		this.add(this.addHauler);
		this.defaultFont = new Font("Arial", Font.PLAIN, 17);
		this.bg = new ImageIcon("resources/img/infoPanel.jpeg");
		DataControler._getInstance().observePlayer(this);
	}

	private void drawBackGround(final Graphics g) {
		g.drawImage(this.bg.getImage(), 0, 0, null);
	}

	private void drawUI(final Graphics g) {
		g.setFont(this.defaultFont);
		g.setColor(Color.WHITE);
		if (this.selectedPlanet != null) {
			if (this.selectedPlanet.getStation() != null) {
				if (PlayerControler._getInstance().getPlayerAccount() >= 200) {
					this.addHauler.setEnabled(true);
				} else {
					this.addHauler.setEnabled(false);
				}
				this.addStation.setVisible(false);
				this.addHauler.setVisible(true);
				g.setFont(new Font("Arial", Font.BOLD, 17));
				g.drawString("[-STATION-]", 20, 170);
				g.setFont(this.defaultFont);
				g.drawString(String.format("Type : %s", this.selectedPlanet.getStation().getType()), 20, 190);
				g.drawString(String.format("Name : %s", this.selectedPlanet.getStation().getName()), 20, 210);
				g.drawString(String.format(" %d", this.selectedPlanet.getShips().size()), 125, 245);

			} else {
				this.addStation.setVisible(true);
				this.addHauler.setVisible(false);
				if (PlayerControler._getInstance().getPlayerAccount() >= 1000) {
					this.addStation.setEnabled(true);
				} else {
					this.addStation.setEnabled(false);
				}
			}
			g.drawString(String.format("Planet number : %s", this.selectedPlanet.getName()), 20, 80);
			g.drawString(String.format("Planet type : %s", this.selectedPlanet.getPlanetType()), 20, 100);
			g.drawString(String.format("Planet size : %s", this.selectedPlanet.getPlanetSize()), 20, 120);
		} else {
			this.addStation.setVisible(false);
			this.addHauler.setVisible(false);
		}
		g.drawString(String.format("� : %d", PlayerControler._getInstance().getPlayerAccount()), 20, 40);
		g.drawLine(0, 50, this.getParent().getWidth(), 50);
		g.drawLine(0, 140, this.getParent().getWidth(), 140);
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

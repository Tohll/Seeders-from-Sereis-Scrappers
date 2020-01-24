package ui.popups;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import controlers.DataControler;

public class ShipsListPopup extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 6334502614269535763L;

	public ShipsListPopup() {
		super();
		this.setPreferredSize(new Dimension(300, 200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setModal(true);
		final JTable table = new JTable(DataControler._getInstance().getShipsListModel());
		this.add(new JScrollPane(table));
		this.pack();
		this.setVisible(true);
	}
}

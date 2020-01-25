package ui.popups;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import controlers.DataControler;
import ui.cellRenderers.TableButtonRenderer;
import ui.tableListeners.TableButtonListerner;

public class ShipsListPopup extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 6334502614269535763L;

	public ShipsListPopup() {
		super();
		this.setTitle("Ships list");
		this.setPreferredSize(new Dimension(600, 200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setModal(true);
		final JTable table = new JTable(DataControler._getInstance().getShipsListModel());
		table.getColumn("+").setCellRenderer(new TableButtonRenderer());
		table.addMouseListener(new TableButtonListerner(table));
		this.add(new JScrollPane(table));
		this.pack();
		this.setVisible(true);
	}
}

package ui.popups;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import dataModels.OrdersListModel;
import ships.AbsShip;
import ui.cellRenderers.TableButtonRenderer;
import ui.tableListeners.TableButtonListerner;

public class OrdersListPopup extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 272659051354707368L;

	public OrdersListPopup(final AbsShip ship, final String name) {
		super();
		this.setTitle(name + " orders list");
		this.setPreferredSize(new Dimension(450, 200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setModal(true);
		final JTable table = new JTable(new OrdersListModel(ship));
		table.getColumn("+").setCellRenderer(new TableButtonRenderer());
		table.addMouseListener(new TableButtonListerner(table));
		final JSplitPane splitPlane = new JSplitPane(SwingConstants.HORIZONTAL, new JScrollPane(table), new JPanel());
		splitPlane.setContinuousLayout(true);
		splitPlane.resetToPreferredSizes();
		splitPlane.setDividerLocation(300);
		splitPlane.setDividerSize(0);
		this.add(splitPlane);
		this.pack();
		this.setVisible(true);
	}

}

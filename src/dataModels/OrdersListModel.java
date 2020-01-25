package dataModels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import ships.AbsShip;

public class OrdersListModel extends AbstractTableModel {

	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { Integer.class, String.class, JButton.class };

	private static final String[] HEADERS = { "Index", "Name", "+" };

	/**
	 *
	 */
	private static final long serialVersionUID = -8134999168759915155L;
	private final AbsShip ship;

	public OrdersListModel(final AbsShip ship) {
		super();
		this.ship = ship;
	}

	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		return COLUMN_TYPES[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return OrdersListModel.HEADERS.length;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		return OrdersListModel.HEADERS[columnIndex];
	}

	@Override
	public int getRowCount() {
		return this.ship.getOrders().size();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return this.ship.getOrders().get(rowIndex).getComment();
		case 2:
			final JButton button = new JButton("Orders");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent arg0) {
					JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(button),
							"Button clicked for row " + rowIndex);
				}
			});
			return button;
		default:
			return null;
		}
	}

}

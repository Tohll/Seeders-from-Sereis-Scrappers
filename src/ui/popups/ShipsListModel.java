package ui.popups;

import javax.swing.table.AbstractTableModel;

import controlers.ShipsControler;

public class ShipsListModel extends AbstractTableModel {

	private static final String[] headers = { "Name", "Homeland", "Current order" };

	/**
	 *
	 */
	private static final long serialVersionUID = -8134999168759915155L;

	public ShipsListModel() {
		super();
	}

	@Override
	public int getColumnCount() {
		return ShipsListModel.headers.length;
	}

	@Override
	public String getColumnName(final int columnIndex) {
		return ShipsListModel.headers[columnIndex];
	}

	@Override
	public int getRowCount() {
		return ShipsControler._getInstance().getShips().size();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ShipsControler._getInstance().getShips().get(rowIndex).getType() + " " + (rowIndex + 1);
		case 1:
			return ShipsControler._getInstance().getShips().get(rowIndex).getHomeLand().getName();
		case 2:
			return ShipsControler._getInstance().getShips().get(rowIndex).getCurrentOrder();
		default:
			return null;
		}
	}
}

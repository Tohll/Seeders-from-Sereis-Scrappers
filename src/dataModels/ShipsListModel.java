package dataModels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import controlers.ShipsControler;
import ui.popups.OrdersListPopup;

public class ShipsListModel extends AbstractTableModel {

	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { String.class, String.class, String.class,
		JButton.class };

		private static final String[] HEADERS = { "Name", "Homeland", "Current order", "+" };

		/**
		 *
		 */
		private static final long serialVersionUID = -8134999168759915155L;

		public ShipsListModel() {
			super();
		}

		@Override
		public Class<?> getColumnClass(final int columnIndex) {
			return COLUMN_TYPES[columnIndex];
		}

		@Override
		public int getColumnCount() {
			return ShipsListModel.HEADERS.length;
		}

		@Override
		public String getColumnName(final int columnIndex) {
			return ShipsListModel.HEADERS[columnIndex];
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
			case 3:
				final JButton button = new JButton("Orders");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent arg0) {
						new OrdersListPopup(ShipsControler._getInstance().getShips().get(rowIndex),
								ShipsControler._getInstance().getShips().get(rowIndex).getType() + " " + (rowIndex + 1));
					}
				});
				return button;
			default:
				return null;
			}
		}
}

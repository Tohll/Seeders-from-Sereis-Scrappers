package ui.tableListeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTable;

public class TableButtonListerner extends MouseAdapter {

	private final JTable table;

	public TableButtonListerner(final JTable table) {
		this.table = table;
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		final int column = this.table.getColumnModel().getColumnIndexAtX(e.getX());
		final int row = e.getY() / this.table.getRowHeight();
		if (row < this.table.getRowCount() && row >= 0 && column < this.table.getColumnCount() && column >= 0) {
			final Object value = this.table.getValueAt(row, column);
			if (value instanceof JButton) {
				((JButton) value).doClick();
			}
		}
	}
}

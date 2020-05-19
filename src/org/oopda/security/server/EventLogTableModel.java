package org.oopda.security.server;

import javax.swing.table.AbstractTableModel;

/**
 * A table model backed by the event log.
 * 
 *
 */
public class EventLogTableModel extends AbstractTableModel 
{

	private static final long serialVersionUID = -2422223868007625746L;

	private final String[] columns = { "Room", "Name", "Date", "Old Status", "New Status"};

	/**
	 * Constructs an EventLogTableModel
	 */
	public EventLogTableModel() 
	{
		EventLog.getInstance().addPropertyChangeListener("events", evt -> fireTableDataChanged());
	}

	public int getColumnCount() 
	{
		return columns.length;
	}

	public int getRowCount() 
	{
		return EventLog.getInstance().getEvents().size();
	}

	/**
	 * This method takes two int values. The first value is used to select an event from the event log ArrayList
	 * using the the inherited method get(param int). The second int parameter is used to invoke a certain method
	 * based upon what the inputted int was dictated by case statements. 
	 */
	public Object getValueAt(int arg0, int arg1) 
	{
		Event event = EventLog.getInstance().getEvents().get(arg0);
		return switch (arg1) {
			case 0 -> event.getAlarm().getLocation();
			case 1 -> event.getAlarm().getName();
			case 2 -> event.getDate();
			case 3 -> event.getOldStatus();
			case 4 -> event.getNewStatus();
			default -> null;
		};
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) 
	{
		return false;
	}

	@Override
	public String getColumnName(int arg0) 
	{
		return columns[arg0];
	}
}

package org.oopda.security.server;

import javax.swing.table.AbstractTableModel;

import org.oopda.security.alarm.Alarm;

/**
 * A table model using an AlarmList for all relevant data.
 * 
 * 
 *
 */
public class AlarmTableModel extends AbstractTableModel 
{

	private static final long serialVersionUID = -6410581838445924565L;

	private final AlarmList alarms;
	private Alarm[] data;
	private final String[] columns = { "Name", "Sensor Type", "Status" };

	/**
	 * Constructs an AlarmTableModel. 
	 * 
	 * @param alarms
	 *            the alarm list
	 */
	public AlarmTableModel(AlarmList alarms) 
	{
		this.alarms = alarms;
		this.data = this.alarms.getAlarms();

		this.alarms.addPropertyChangeListener("alarms", update -> update());
	}

	/**
	 * Updates the data and table with relevant alarm data.
	 */
	private void update() 
	{
		this.data = this.alarms.getAlarms();
		this.fireTableDataChanged();
	}

	/**
	 * A simple 'getter' method that returns an int displaying the amount of columns.
	 */
	public int getColumnCount() 
	{
		return columns.length;
	}

	/**
	 * A simple 'getter' method that returns an int displaying the amount of rows.
	 */
	public int getRowCount() 
	{
		return data.length;
	}

	/**
	 * Utilizing polymorphism, this method can return nothing, a String, or an enum, based upon what
	 * parameters are passed to it. 
	 */
	public Object getValueAt(int arg0, int arg1) 
	{
		Alarm selected = data[arg0];
		return switch (arg1) {
			case 0 -> selected.getName();
			case 1 -> selected.getType();
			case 2 -> selected.getStatus();
			default -> null;
		};
	}

	/**
	 * A boolean 'getter' method that will take two int parameters but will always return false.
	 */
	public boolean isCellEditable(int arg0, int arg1) 
	{
		return false;
	}

	/**
	 * A 'getter' method that takes a int parameter and returns the String in 
	 * the column stated by the parameter.
	 */
	public String getColumnName(int arg0) 
	{
		return columns[arg0];
	}
}

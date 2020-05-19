package org.oopda.security.server;

import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import org.oopda.security.alarm.Alarm;
import org.oopda.security.alarm.AlarmStatus;

/**
 * A table model using a RoomList as backing data 
 * 
 * 
 *
 */
public class RoomTableModel extends AbstractTableModel 
{

	private static final long serialVersionUID = 8117790686432109843L;

	private final RoomList rooms;
	private Room[] data;
	private final String[] columns = {"Name", "Alarms", "Abnormal Alarms", "Inspection"};

	/**
	 * Constructs a RoomTableModel
	 * 
	 * @param rooms the RoomList to use for data
	 */
	public RoomTableModel(RoomList rooms) 
	{
		this.rooms = rooms;
		this.data = this.rooms.getRooms();

		this.rooms.addPropertyChangeListener("rooms", arg0 -> {
			update();

			Room changedRoom = (Room)arg0.getNewValue();
			int roomIndex = Arrays.asList(data).indexOf(changedRoom);

			fireTableRowsUpdated(roomIndex, roomIndex);
		});
	}

	/**
	 * Updates the table with new data
	 */
	private void update() 
	{
		this.data = this.rooms.getRooms();
	}


	@Override
	public int getColumnCount() 
	{
		return columns.length;
	}


	@Override
	public int getRowCount() 
	{
		return this.data.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) 
	{
		Room selectedRoom = data[arg0];
		switch(arg1) 
		{
		case 0:
			return selectedRoom.getName();
		case 1:
			return selectedRoom.getAlarms().length;
		case 2:
			int nonArmedAlarms = 0;
			for(Alarm alarm : selectedRoom.getAlarms()) 
			{
				if(alarm.getStatus() != AlarmStatus.ARMED) 
				{
					nonArmedAlarms++;
				}
			}
			return nonArmedAlarms;
		case 3:
			RoomInspection latestInspection = selectedRoom.getLatestInspection();
			if(latestInspection == null) return "None found";
			return String.format("On %s by %s", latestInspection.getDateAsString(), latestInspection.getInitial());
		}
		return null;
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

	/**
	 * Gets the room specified at the row
	 * 
	 * @param row the row index in the table
	 * @return the Room used for that row
	 */
	public Room getRoom(int row) 
	{
		return data[row];
	}
}

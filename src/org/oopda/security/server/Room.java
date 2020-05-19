package org.oopda.security.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.oopda.security.alarm.Alarm;

/**
 * A location that holds data for a name, multiple alarms, and an inspection. 
 * 
 * 
 *
 */
public class Room 
{
	private String name;
	private final AlarmList alarms;
	private final List<RoomInspection> inspections;

	/**
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * Constructs a new room with the specified name
	 * 
	 * @param name
	 *            the name of the room
	 */
	public Room(String name) 
	{
		this.name = name;
		alarms = new AlarmList();
		inspections = new ArrayList<>();
	}

	/**
	 * @return the inspections
	 */
	public List<RoomInspection> getInspections()
	{
		return inspections;
	}

	/**
	 * Gets the latest inspection from the list of room inspections or null if
	 * no inspections exists
	 * 
	 * @return an inspection or null
	 */
	public RoomInspection getLatestInspection() 
	{
		if (inspections.size() == 0)
			return null;
		return Collections.max(inspections);
	}

	/**
	 * Updates an alarm in the AlarmList
	 * 
	 * @param alarm
	 *            the alarm to update
	 */
	public void updateAlarm(Alarm alarm) 
	{
		alarms.updateAlarm(alarm);
	}

	/**
	 * @return the alarms
	 */
	public Alarm[] getAlarms() 
	{
		return alarms.getAlarms();
	}

	/**
	 * @return the alarm list
	 */
	public AlarmList getAlarmList() 
	{
		return this.alarms;
	}
}

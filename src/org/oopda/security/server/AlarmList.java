package org.oopda.security.server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.UUID;

import org.oopda.security.alarm.Alarm;
import org.oopda.security.alarm.AlarmStatus;

/**
 * A list of alarms that logs and fires events when an alarm is modified
 * 
 * 
 *
 */
public class AlarmList 
{
	private final HashMap<UUID, Alarm> alarms;
	private final PropertyChangeSupport propChangeSupport = new PropertyChangeSupport(this);

	/**
	 * Constructs a new AlarmList
	 */
	public AlarmList() 
	{
		alarms = new HashMap<>();
	}

	/**
	 * Updates or adds an alarm. Logs an event if created or changed.
	 * 
	 * @param alarm
	 *            the alarm to update/add
	 */
	public void updateAlarm(Alarm alarm) 
	{
		Alarm oldAlarm = alarms.get(alarm.getUuid());
		alarms.put(alarm.getUuid(), alarm);
		if (oldAlarm == null) 
		{
			EventLog.getInstance().log(alarm, AlarmStatus.CREATED, alarm.getStatus());
		}
		else 
		{
			if (oldAlarm.getStatus() != alarm.getStatus()) 
			{
				EventLog.getInstance().log(alarm, oldAlarm.getStatus(), alarm.getStatus());
			}
		}
		propChangeSupport.firePropertyChange("alarms", oldAlarm, alarm);
	}
	
	/**
	 * Gets all alarms
	 * 
	 * @return the alarms
	 */
	public Alarm[] getAlarms() 
	{
		return alarms.values().toArray(new Alarm[0]);
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) 
	{
		propChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

}

package org.oopda.security.server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.oopda.security.alarm.Alarm;
import org.oopda.security.alarm.AlarmStatus;

/**
 * Dispatches and tracks events
 * Information logged is held by an ArrayList of particular events.
 * 
 * 
 *
 */
public class EventLog 
{
	private static final EventLog instance = new EventLog();

	/**
	 * Gets the single instance of the EventLog
	 * 
	 * @return the EventLog
	 */
	public static EventLog getInstance() 
	{
		return instance;
	}

	private final ArrayList<Event> events;
	private final PropertyChangeSupport propChangeSupport = new PropertyChangeSupport(this);

	private EventLog() 
	{
		events = new ArrayList<>();
	}

	/**
	 * Logs an event and notifies the listeners
	 * 
	 * @param alarm the alarm that changed
	 * @param oldStatus the old status of the alarm
	 * @param newStatus the new status of the alarm
	 */
	public void log(Alarm alarm, AlarmStatus oldStatus, AlarmStatus newStatus) 
	{
		Event newEvent = new Event(GregorianCalendar.getInstance().getTime(), alarm, oldStatus, newStatus);
		events.add(newEvent);
		
		propChangeSupport.firePropertyChange("events", null, newEvent);
	}

	/**
	 * @return the events
	 */
	public ArrayList<Event> getEvents() 
	{
		return events;
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) 
	{
		propChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) 
	{
		propChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) 
	{
		propChangeSupport.removePropertyChangeListener(listener);
	}
}

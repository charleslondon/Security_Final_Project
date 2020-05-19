package org.oopda.security.server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

import org.oopda.security.alarm.Alarm;

/**
 * A list of rooms that fires events when a room is modified. It also has the ability to throw a custom exception.
 * 
 * 
 *
 */
public class RoomList 
{

	private final HashMap<String, Room> rooms;
	private final PropertyChangeSupport propChangeSupport = new PropertyChangeSupport(this);

	/**
	 * Constructs a RoomList
	 */
	public RoomList() 
	{
		rooms = new HashMap<>();
	}

	/**
	 * Gets the rooms
	 * 
	 * @return the rooms
	 */
	public Room[] getRooms() 
	{
		return rooms.values().toArray(new Room[0]);
	}

	/**
	 * While it does not return anything, this method will attempt to change the state of an alarm in a particular room.
	 * If that room is not found, a custom exception is thrown.
	 * @param alarm a
	 */
	public void updateAlarm(Alarm alarm) 
	{
		if (!rooms.containsKey(alarm.getLocation())) 
		{
			throw new RoomNotExistException(alarm.getLocation());
		}

		Room currentRoom = rooms.get(alarm.getLocation());

		currentRoom.updateAlarm(alarm);

		propChangeSupport.firePropertyChange("rooms", null, currentRoom);
	}

	/**
	 * Adds a Room with the specified name to the list
	 * 
	 * @param name
	 *            the name of the new room
	 */
	public void addRoom(String name) 
	{
		rooms.put(name, new Room(name));
	}

	/**
	 * Adds an inspection to the room inspected
	 * 
	 * @param inspection
	 *            the inspection to add
	 */
	public void addInspection(RoomInspection inspection) 
	{
		if (!rooms.containsKey(inspection.getRoomName())) 
		{
			throw new RoomNotExistException(inspection.getRoomName());
		}

		rooms.get(inspection.getRoomName()).getInspections().add(inspection);
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

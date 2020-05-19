package org.oopda.security.client;

import javax.swing.JFrame;

import org.oopda.security.alarm.DoorAlarm;

/**
 * A single Alarm for use outside of the main Alarm-Array setup.
 * 
 */
public class Single extends JFrame 
{
	private static final long serialVersionUID = -4909560538229625052L;

	/**
	 * A main method that is used to instantiate a new 'Single' object.
	 * @param args a
	 */
	public static void main(String[] args) 
	{
		new Single();
	}
	
	/**
	 * A constructor for the 'Single' that generates a singular instance of an Alarm and then presents it
	 * in it's own, separate GUI.
	 */
	public Single() 
	{
		add(new AlarmPanel(new DoorAlarm("Patio", "Sliding door", false)));

		setTitle("Single Alarm");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
}

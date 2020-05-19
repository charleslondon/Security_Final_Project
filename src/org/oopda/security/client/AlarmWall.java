package org.oopda.security.client;

import java.awt.GridLayout;

import javax.swing.JFrame;

import org.oopda.security.alarm.ChemicalAlarm;
import org.oopda.security.alarm.DoorAlarm;
import org.oopda.security.alarm.FireAlarm;
import org.oopda.security.alarm.SmokeAlarm;
import org.oopda.security.alarm.WindowAlarm;

/**
 * A wall of alarms to easily change and test alarms. 
 * A GUI that controls and displays many alarms at once.
 * 
 */
public class AlarmWall extends JFrame 
{

	private static final long serialVersionUID = 2398071356088276165L;
	
	public static void main(String[] args) 
	{
		new AlarmWall();
	}
	
	/**
	 * Constructs an AlarmWall GUI. Several different types of alarms are added to various locations
	 * and have their critical booleans set. Finally the information is 'packed' which
	 * causes this window to be sized to fit the preferred size and layouts of its sub-components.
	 */
	public AlarmWall() 
	{
		setLayout(new GridLayout(5, 2, 5, 5));

		add(new AlarmPanel(new FireAlarm("Bedroom", "Above bed")));
		add(new AlarmPanel(new FireAlarm("Living Room", "Beside TV")));
		
		add(new AlarmPanel(new ChemicalAlarm("Bathroom", "Below sink")));
		add(new AlarmPanel(new ChemicalAlarm("Patio", "On roof")));

		add(new AlarmPanel(new DoorAlarm("Patio", "Front door", true)));
		add(new AlarmPanel(new DoorAlarm("Living Room", "Basement door", false)));
		
		add(new AlarmPanel(new SmokeAlarm("Bathroom", "Above sink")));
		add(new AlarmPanel(new SmokeAlarm("Bedroom", "Above bed")));

		add(new AlarmPanel(new WindowAlarm("Living Room", "Large window", false)));
		add(new AlarmPanel(new WindowAlarm("Living Room", "Small window", true)));

		setTitle("Alarm Wall");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
}

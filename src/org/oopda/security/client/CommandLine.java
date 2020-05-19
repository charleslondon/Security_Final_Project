package org.oopda.security.client;

import java.util.Scanner;

import org.oopda.security.alarm.Alarm;
import org.oopda.security.alarm.AlarmStatus;
import org.oopda.security.alarm.ChemicalAlarm;
import org.oopda.security.alarm.Client;
import org.oopda.security.alarm.DoorAlarm;
import org.oopda.security.alarm.FireAlarm;
import org.oopda.security.alarm.SmokeAlarm;
import org.oopda.security.alarm.WindowAlarm;

/**
 * Control of an alarm by command line, user is capable of using a scanner to manually enter commands.
 * These commands can be used to manipulate the the state of the alarms. 
 * 
 */
public class CommandLine 
{
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);

		System.out.print("Type of alarm (chemical, door, fire, smoke, window): ");
		String type = in.nextLine();

		System.out.print("Location of alarm: ");
		String location = in.nextLine();

		System.out.print("Name of alarm: ");
		String name = in.nextLine();

		boolean critical = false;
		if (type.equals("door"))
		{
			System.out.print("Critical (y/n): ");
			critical = in.nextLine().equals("y");
		}

		Alarm alarm = null;

		switch (type.toLowerCase()) {
			case "chemical" -> alarm = new ChemicalAlarm(location, name);
			case "door" -> alarm = new DoorAlarm(location, name, critical);
			case "fire" -> alarm = new FireAlarm(location, name);
			case "smoke" -> alarm = new SmokeAlarm(location, name);
			case "window" -> alarm = new WindowAlarm(location, name, critical);
			default -> {
				System.out.println("Invalid input");
				System.exit(1);
			}
		}
		
		Thread client = new Client(alarm);
		client.start();
		
		System.out.println("c - change status");
		System.out.println("q - quit");
		
		boolean run = true;
		while(run)
		{
			System.out.printf("Alarm status: %s\n", alarm.getStatus());
			System.out.print("Command: ");
			
			String nextCommand = in.nextLine();
			if(nextCommand.equals("q")) 
			{
				System.out.println("Bye!");
				run = false;
			} 
			else if(nextCommand.equals("c")) 
			{
				
				switch(alarm.getStatus()) 
				{
					case PREPARING, ACTIVATED -> alarm.setStatus(AlarmStatus.ARMED);
					case ARMED -> alarm.setStatus(AlarmStatus.ACTIVATED);
					default -> {
						System.out.println("Invalid Alarm Type");
						System.exit(1);
					}
				}
			}
		}
		in.close();
	}
}

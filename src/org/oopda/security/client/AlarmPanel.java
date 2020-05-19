package org.oopda.security.client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.oopda.security.alarm.Alarm;
import org.oopda.security.alarm.AlarmStatus;
import org.oopda.security.alarm.Client;

public class AlarmPanel extends JPanel 
{

	private static final long serialVersionUID = -3720080153781672043L;
	private final Alarm alarm;
	private final StatusPanel status;
	private final JButton activateButton;

	/**
	 * Constructs an AlarmPanel
	 * Takes the form of a gui that creates an instance of a 'Client' thread that takes an
	 * 'Alarm' object for a parameter thus connecting it to the server. This GUI also displays all relevant data
	 * for the alarm. The alarm can be manipulated from this GUI as it's phase/status can be altered.
	 * 
	 * @param alarm
	 *            the alarm to manipulate
	 */
	public AlarmPanel(Alarm alarm) 
	{
		setLayout(new BorderLayout());

		this.alarm = alarm;

		Client clientThread = new Client(alarm);
		clientThread.start();

		setBorder(new TitledBorder(alarm.getName()));
		activateButton = new JButton("Arm");
		activateButton.addActionListener(update -> update());
		add(activateButton);

		status = new StatusPanel(alarm);
		add(status, BorderLayout.PAGE_END);

		setSize(new Dimension(450, 150));
	}

	/**
	 * Updates the alarm based on the current alarm status
	 * Case statements utilized as there are a finite amount of ways the alarm can be set. 
	 */
	public void update() 
	{
		switch (alarm.getStatus()) 
		{
			case PREPARING:
			case ACTIVATED:
				alarm.setStatus(AlarmStatus.ARMED);
				activateButton.setText("Activate");
				break;
			case ARMED:
				alarm.setStatus(AlarmStatus.ACTIVATED);
				activateButton.setText("Deactivate");
				break;
		default:
			break;
		}
		status.update();
	}
}

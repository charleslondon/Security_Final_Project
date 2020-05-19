package org.oopda.security.client;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.oopda.security.alarm.Alarm;

/**
 * A panel that displays the status of a single Alarm.
 * 
 * 
 *
 */
public class StatusPanel extends JPanel 
{

	private static final long serialVersionUID = -2584044758061291526L;

	private final Alarm alarm;

	private final JLabel type;
	private final JLabel location;
	private final JLabel status;

	/**
	 * Constructs a new StatusPanel, this GUI contains relevant data to the Alarm being passed into
	 * the constructor as a parameter. 
	 * 
	 * @param alarm
	 *            the alarm to monitor
	 */
	public StatusPanel(Alarm alarm) 
	{
		this.alarm = alarm;

		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		add(Box.createHorizontalGlue());
		add(new JLabel("Type: "));

		type = new JLabel();
		add(type);

		add(Box.createHorizontalGlue());
		add(new JLabel("Location: "));

		location = new JLabel();
		add(location);

		add(Box.createHorizontalGlue());
		add(new JLabel("Status: "));

		status = new JLabel();
		add(status);

		add(Box.createHorizontalGlue());

		update();
	}

	/**
	 * Updates the panel with the current alarm information.
	 */
	public void update() 
	{
		type.setText(alarm.getType().toString());
		location.setText(alarm.getLocation());
		status.setText(alarm.getStatus().toString());
	}
}

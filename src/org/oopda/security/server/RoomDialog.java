package org.oopda.security.server;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

/**
 * A dialog showing the status of alarms in a room and all of it's relevant data. 
 */
public class RoomDialog extends JDialog 
{

	private static final long serialVersionUID = -3043668843840084256L;

	/**
	 * Constructs a RoomFrame GUI. This GUI creates a JTable of several alarms across rooms and places them in 
	 * one large model to view.  
	 * 
	 * @param owner
	 *            the owner of the dialog
	 * @param room
	 *            the room to detail
	 */
	public RoomDialog(Frame owner, Room room) 
	{
		super(owner);
		setModalityType(ModalityType.APPLICATION_MODAL);

		JTable table = new JTable(new AlarmTableModel(room.getAlarmList()));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowSorter(new TableRowSorter<>((AlarmTableModel) table.getModel()));

		Box tableBox = Box.createVerticalBox();
		tableBox.add(table.getTableHeader());
		tableBox.add(new JScrollPane(table));

		add(tableBox, BorderLayout.CENTER);

		setTitle("Room: " + room.getName());
		setSize(350, 350);
		setLocationRelativeTo(owner);
		setVisible(true);
	}
}

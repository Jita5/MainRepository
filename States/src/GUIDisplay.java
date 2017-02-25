import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * GUI to implement the RefrigeratorDisplay interface .
 */
public class GUIDisplay extends RefrigeratorDisplay implements ActionListener {

	/** The frame. */
	private static SimpleDisplay frame;

	/**
	 * Makes it a singleton.
	 */
	private GUIDisplay() {
		frame = new SimpleDisplay();
		initialize();
	}

	/**
	 * This class has most of the widgets.
	 */
	@SuppressWarnings("serial")
	private class SimpleDisplay extends JFrame {

		/** The main panel. */
		JPanel mainPanel = new JPanel(new FlowLayout());

		/** The temp panel. */
		JPanel tempPanel = new JPanel(new GridLayout(3, 3));

		/** The status panel. */
		JPanel statusPanel = new JPanel(new GridLayout(3, 3));

		/** The room temp. */
		private JLabel roomTemp = new JLabel("Room temp");

		/** The room temp input. */
		private JTextField roomTempInput = new JTextField();

		/** The set room temp. */
		private JButton setRoomTemp = new JButton("Set room temp");

		/** The fridge temp. */
		private JLabel fridgeTemp = new JLabel("Desired fridge temp");

		/** The fridge temp input. */
		private JTextField fridgeTempInput = new JTextField();

		/** The set fridge temp. */
		private JButton setFridgeTemp = new JButton("Set desired fridge temp");

		/** The freezer temp. */
		private JLabel freezerTemp = new JLabel("Desired freezer temp");

		/** The freezer temp input. */
		private JTextField freezerTempInput = new JTextField();

		/** The set freezer temp. */
		private JButton setFreezerTemp = new JButton("Set desired freezer temp");

		/** The fridge door closer. */
		private JButton fridgeDoorCloser = new JButton("close fridge door");

		/** The fridge door opener. */
		private JButton fridgeDoorOpener = new JButton("open fridge door");

		/** The fridge door status. */
		private JLabel fridgeDoorStatus = new JLabel("Fridge Door Closed");

		/** The fridge temp value. */
		private JLabel fridgeTempValue = new JLabel("            ");

		/** The fridge light status. */
		private JLabel fridgeLightStatus = new JLabel("Fridge Light Off");

		/** The fridge run status. */
		private JLabel fridgeRunStatus = new JLabel("Fridge idle");

		/** The freezer door closer. */
		private JButton freezerDoorCloser = new JButton("close freezer door");

		/** The freezer door opener. */
		private JButton freezerDoorOpener = new JButton("open freezer door");

		/** The freezer door status. */
		private JLabel freezerDoorStatus = new JLabel("Freezer Door Closed");

		/** The freezer temp value. */
		private JLabel freezerTempValue = new JLabel(" x           ");

		/** The freezer light status. */
		private JLabel freezerLightStatus = new JLabel("Freezer Light Off");

		/** The freezer run status. */
		private JLabel freezerRunStatus = new JLabel("Freezer idle");

		/**
		 * Sets up the interface.
		 */
		private SimpleDisplay() {
			super("Refrigerator");
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent event) {
					System.exit(0);
				}
			});
			getContentPane().setLayout(new FlowLayout());

			tempPanel.add(roomTemp);
			tempPanel.add(roomTempInput);
			tempPanel.add(setRoomTemp);
			tempPanel.add(fridgeTemp);
			tempPanel.add(fridgeTempInput);
			tempPanel.add(setFridgeTemp);
			tempPanel.add(freezerTemp);
			tempPanel.add(freezerTempInput);
			tempPanel.add(setFreezerTemp);
			statusPanel.add(fridgeDoorStatus);
			statusPanel.add(fridgeLightStatus);
			statusPanel.add(fridgeDoorOpener);
			statusPanel.add(fridgeDoorCloser);
			statusPanel.add(freezerDoorStatus);
			statusPanel.add(freezerLightStatus);
			statusPanel.add(freezerDoorOpener);
			statusPanel.add(freezerDoorCloser);
			statusPanel.add(fridgeTempValue);
			statusPanel.add(freezerTempValue);
			statusPanel.add(fridgeRunStatus);
			statusPanel.add(freezerRunStatus);
			mainPanel.add(tempPanel);
			mainPanel.add(statusPanel);

			getContentPane().add(mainPanel);

			fridgeDoorCloser.addActionListener(GUIDisplay.this);
			fridgeDoorOpener.addActionListener(GUIDisplay.this);
			freezerDoorCloser.addActionListener(GUIDisplay.this);
			freezerDoorOpener.addActionListener(GUIDisplay.this);
			setRoomTemp.addActionListener(GUIDisplay.this);
			setFridgeTemp.addActionListener(GUIDisplay.this);
			setFreezerTemp.addActionListener(GUIDisplay.this);

			pack();
			setVisible(true);
		}
	}

	/**
	 * Handles the clicks.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(frame.fridgeDoorCloser)) {
			FridgeContext.instance().processFridgeDoorEvent(
					FridgeContext.FridgeDoorEvents.FRIDGE_DOOR_CLOSED_EVENT);
		} else if (event.getSource().equals(frame.fridgeDoorOpener)) {
			FridgeContext.instance().processFridgeDoorEvent(
					FridgeContext.FridgeDoorEvents.FRIDGE_DOOR_OPENED_EVENT);
		} else if (event.getSource().equals(frame.freezerDoorCloser)) {
			FreezerContext.instance().processFreezerDoorEvent(
					FreezerContext.FreezerDoorEvents.FREEZER_DOOR_CLOSED_EVENT);
		} else if (event.getSource().equals(frame.freezerDoorOpener)) {
			FreezerContext.instance().processFreezerDoorEvent(
					FreezerContext.FreezerDoorEvents.FREEZER_DOOR_OPENED_EVENT);
		} else if (event.getSource().equals(frame.setRoomTemp)) {
			if (Integer.parseInt(frame.roomTempInput.getText()) >= Configuration.ROOM_MIN
					&& Integer.parseInt(frame.roomTempInput.getText()) <= Configuration.ROOM_MAX) {
				FridgeContext.instance().setFridgeTemp(
						Integer.parseInt(frame.roomTempInput.getText()));
				FreezerContext.instance().setFreezerTemp(
						Integer.parseInt(frame.roomTempInput.getText()));
			} else {
				JOptionPane.showMessageDialog(null,
						"Unacceptable room temperature", "Entry error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (event.getSource().equals(frame.setFridgeTemp)) {
			if (Integer.parseInt(frame.fridgeTempInput.getText()) >= Configuration.FRIDGE_MIN
					&& Integer.parseInt(frame.fridgeTempInput.getText()) <= Configuration.FRIDGE_MAX) {
				FridgeContext.instance().setDesiredFridgeTemp(
						Integer.parseInt(frame.fridgeTempInput.getText()));
			} else {
				JOptionPane.showMessageDialog(null,
						"Unacceptable fridge temperature", "Entry error",
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (event.getSource().equals(frame.setFreezerTemp)) {
			if (Integer.parseInt(frame.freezerTempInput.getText()) >= Configuration.FREEZER_MIN
					&& Integer.parseInt(frame.freezerTempInput.getText()) <= Configuration.FREEZER_MAX) {
				FreezerContext.instance().setDesiredFreezerTemp(
						Integer.parseInt(frame.freezerTempInput.getText()));
			} else {
				JOptionPane.showMessageDialog(null,
						"Unacceptable freezer temperature", "Entry error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Indicate that the fridge light is on.
	 */
	@Override
	public void turnFridgeLightOn() {
		frame.fridgeLightStatus.setText("Fridge Light On");
	}

	/**
	 * Indicate that the fridge light is off.
	 */
	@Override
	public void turnFridgeLightOff() {
		frame.fridgeLightStatus.setText("Fridge Light Off");
	}

	/**
	 * Indicate that the fridge door is closed.
	 */
	@Override
	public void fridgeDoorClosed() {
		frame.fridgeDoorStatus.setText("Fridge Door Closed");
	}

	/**
	 * Indicate that the fridge door is opened.
	 */
	@Override
	public void fridgeDoorOpened() {
		frame.fridgeDoorStatus.setText("Fridge Door Opened");
	}

	/**
	 * display the remaining time.
	 *
	 * @param value
	 *            the value
	 */
	@Override
	public void displayFridgeTemp(int value) {
		frame.fridgeTempValue.setText(" " + value);
	}

	/**
	 * Indicate that it is cooling.
	 */
	@Override
	public void fridgeRunning() {
		frame.fridgeRunStatus.setText("Fridge Running");
	}

	/**
	 * Indicate that cooling is done.
	 */
	@Override
	public void fridgeNotRunning() {
		frame.fridgeRunStatus.setText("Fridge Idle");
	}

	/**
	 * Indicate that the freezer light is on.
	 */
	@Override
	public void turnFreezerLightOn() {
		frame.freezerLightStatus.setText("Freezer Light On");
	}

	/**
	 * Indicate that the freezer light is off.
	 */
	@Override
	public void turnFreezerLightOff() {
		frame.freezerLightStatus.setText("Freezer Light Off");
	}

	/**
	 * Indicate that the freezer door is closed.
	 */
	@Override
	public void freezerDoorClosed() {
		frame.freezerDoorStatus.setText("Freezer Door Closed");
	}

	/**
	 * Indicate that the freezer door is opened.
	 */
	@Override
	public void freezerDoorOpened() {
		frame.freezerDoorStatus.setText("Freezer Door Opened");
	}

	/**
	 * display the remaining time.
	 *
	 * @param value
	 *            the value
	 */
	@Override
	public void displayFreezerTemp(int value) {
		frame.freezerTempValue.setText(" " + value);
	}

	/**
	 * Indicate that it is cooling.
	 */
	@Override
	public void freezerRunning() {
		frame.freezerRunStatus.setText("Freezer Running");
	}

	/**
	 * Indicate that cooling is done.
	 */
	@Override
	public void freezerNotRunning() {
		frame.freezerRunStatus.setText("Freezer Idle");
	}

	/**
	 * The main method. Creates the interface
	 * 
	 * @param s
	 *            not used
	 */
	public static void main(String[] s) {

//		Configuration config = new Configuration();
//		config.readConfigFile(s[0]);
		@SuppressWarnings("unused")
		RefrigeratorDisplay display = new GUIDisplay();

	}
}
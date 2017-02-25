/**
 * Configuration Class 
 * Imports fridge and freezer parameters from configuration file Config.txt
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Class Configuration.
 */
public class Configuration {

	/** The fridge min. */
	static int FRIDGE_MIN;

	/** The fridge max. */
	static int FRIDGE_MAX;

	/** The freezer min. */
	static int FREEZER_MIN;

	/** The freezer max. */
	static int FREEZER_MAX;

	/** The room min. */
	static int ROOM_MIN;

	/** The room max. */
	static int ROOM_MAX;

	/** The fridge closed time. */
	static int FRIDGE_CLOSED_TIME;

	/** The fridge open time. */
	static int FRIDGE_OPEN_TIME;

	/** The freezer closed time. */
	static int FREEZER_CLOSED_TIME;

	/** The freezer open time. */
	static int FREEZER_OPEN_TIME;

	/** The fridge temp until active. */
	static int FRIDGE_TEMP_UNTIL_ACTIVE;

	/** The freezer temp until active. */
	static int FREEZER_TEMP_UNTIL_ACTIVE;

	/** The fridge cooling minutes. */
	static int FRIDGE_COOLING_MINUTES;

	/** The freezer cooling minutes. */
	static int FREEZER_COOLING_MINUTES;

	/**
	 * Read config file.
	 *
	 * @param configFileName
	 *            the config file name
	 */
	public void readConfigFile(String configFileName) {

		BufferedReader reader = null;

		try {

			String line;

			reader = new BufferedReader(new FileReader(configFileName));
			int count = 1;
			while ((line = reader.readLine()) != null) {
				switch (count) {
				case 1:
					FRIDGE_MIN = Integer.parseInt(line);
					count++;
					break;
				case 2:
					FRIDGE_MAX = Integer.parseInt(line);
					count++;
					break;
				case 3:
					FREEZER_MIN = Integer.parseInt(line);
					count++;
					break;
				case 4:
					FREEZER_MAX = Integer.parseInt(line);
					count++;
					break;
				case 5:
					ROOM_MIN = Integer.parseInt(line);
					count++;
					break;
				case 6:
					ROOM_MAX = Integer.parseInt(line);
					count++;
					break;
				case 7:
					FRIDGE_CLOSED_TIME = Integer.parseInt(line);
					count++;
					break;
				case 8:
					FRIDGE_OPEN_TIME = Integer.parseInt(line);
					count++;
					break;
				case 9:
					FREEZER_CLOSED_TIME = Integer.parseInt(line);
					count++;
					break;
				case 10:
					FREEZER_OPEN_TIME = Integer.parseInt(line);
					count++;
					break;
				case 11:
					FRIDGE_TEMP_UNTIL_ACTIVE = Integer.parseInt(line);
					count++;
					break;
				case 12:
					FREEZER_TEMP_UNTIL_ACTIVE = Integer.parseInt(line);
					count++;
					break;
				case 13:
					FRIDGE_COOLING_MINUTES = Integer.parseInt(line);
					count++;
					break;
				case 14:
					FREEZER_COOLING_MINUTES = Integer.parseInt(line);
					count++;
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}

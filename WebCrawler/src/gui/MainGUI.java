package gui;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;

//import ProgressBarDemo.Task;
import core.*;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

// MainGUI class declaration.
public class MainGUI extends JFrame {


	// Define logging
	//static Logger log = Logger.getLogger(MainGUI.class);

	//create blank labels and fields for alignment purposes.
 	private ImageIcon logo = new ImageIcon (getResourceImage("/images/new-logo.png"));
 	private JLabel logoIcon = new JLabel(logo);

 	private JLabel blankLabel = new JLabel(" ");
	private JLabel blankLabel4 = new JLabel(" ");
	private JLabel blankLabel5 = new JLabel(" ");
	private JLabel blankLabel6 = new JLabel(" ");
	private JLabel blankLabel7 = new JLabel(" ");
	private JLabel blankLabel9 = new JLabel(" ");
	private JLabel blankLabel21 = new JLabel(" ");


	// JCombox for language
	private JLabel langName = new JLabel(" ");
	private JLabel langCbox = new JLabel("*Crawler App is multi-language supported*");
	//private String[] langSelection = {"English","Telugu"};
	//private JComboBox<String> langCbox = new JComboBox<String>(langSelection);


	// Url text field display area
	private JLabel labelEnterUrl = new JLabel("Enter Url to crawl:");
	private JTextArea urlSearchAreaField = new JTextArea(9, 40);

	private JTextArea urlTextArea = new JTextArea(9, 40);


	// JCombox for url depth
	private JLabel labelDepthToSearch = new JLabel("Url depth:");
	private String[] urlDepth = {"1","2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private JComboBox<String> depthCbox = new JComboBox<String>(urlDepth);


	// JCombox for reviste time
	private JLabel labelUrlRevisitTime = new JLabel("Time Limit:");
	private String[] revisitTime = {"Immediate", "1 hour", "12 hours", "1 day", "1 week", "1 month", "1 year"};
	private JComboBox<String> revisitComboBox = new JComboBox<String>(revisitTime);


	// Crawl button
	JLabel buttonLabel = new JLabel(" ");
	JLabel buttonLabel2 = new JLabel(" ");
	JLabel buttonLabel3 = new JLabel(" ");

	private ImageIcon iconCrawl = new ImageIcon(getResourceImage("/images/crawl_btn.png"));
	JButton crawlButton = new JButton(iconCrawl);



	/* Instance variables for font and colors
	 * 1. Create crawlBtnColor var
	 * 2. Create font var
	 * 3. Create color var
	 * 4. Create rounded corners var
	 */
    private  Color crawlBtnColor = new Color(250, 51, 5);
    private  Color separatorColor = new Color(78, 78, 78);
    private String Arial;
    private Color color = new Color(77, 197, 172);
	Border roundedTextArea = new LineBorder(Color.WHITE, 3, true);


	/* Create list of instance variables for crawling information.
	 * 1. Create progress bar var
	 * 2. Create crawlingUrl label var
	 * 3. Create crawledUrlLabel var
	 * 4. Create urlAvailableToCraw var
	 * 5. Create crawlingWordsLabel var
	 * 6. Create crawledWordsLabel var
	 * 7. Create wordsAvailableToCrawlLabel var
	 * 8. Define urlHistoryTable var.
	 */
	private JProgressBar crawlingProgressLabel;
	private JLabel crawlingUrlLabel;
	private JLabel crawledUrlLabel;
	private JLabel urlAvailabelToCrawlLabel;
	private JLabel crawlingWordLabel;
	private JLabel crawledWordsLabel;
	//private Task task;


	// Declare JMenuBar, JMenu, and JMenuItem
	/* Declaration of menu components.
	 * 1. Define menuBar for menu
	 * 2. Define fileMenu for menu items
	 * 3. Define menu item save to save crawled urls history to file.
	 * 4. Define menu item load to load crawled saved urls history back to display area.
	 * 5. Define menu item exit to exit out of the program.
	 * 6. Defne file menu icon image
	 */
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("Menu");
	private JMenuItem save = new JMenuItem("Save crawled urls");
	private JMenuItem load = new JMenuItem("View crawled urls");
	private JMenuItem exit = new JMenuItem("Exit");

	private JMenu helpFileMenu = new JMenu();
	private JMenuItem helpFile = new JMenuItem("Need help");
	private JMenu infoFileMenu = new JMenu();
	private JMenuItem infoD = new JMenuItem("More Info");
	private JMenu configFileMenu = new JMenu();
 	private ImageIcon menuIcon = new ImageIcon(getResourceImage("/images/menu.png"));
 	private ImageIcon helpIcon = new ImageIcon(getResourceImage("/images/help.png"));
 	private ImageIcon infoIcon = new ImageIcon(getResourceImage("/images/info.png"));
 	private ImageIcon advancedIcon = new ImageIcon(getResourceImage("/images/setting.png"));

 	// Loading message icon
 	private ImageIcon loadingIcon = new ImageIcon(getResourceImage("/images/crawlWait_btn.gif"));


 	// Set errors list
    ArrayList errorList = new ArrayList();



    // Cursor wait as an indication of in progress crawling
    private Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    private boolean crawling;
    private JLabel crawlingMsg;
    //private String crawlingMsg = "Crawling in progress...";

    // saved urls file path
    private JTextField urlTextPath;



	/* Main method
	 * This method runs the program.
	 */
    public static void main(String[] args) {

    	 //Logging message
        //PropertyConfigurator.configure("Log4j.properties");

        new MainGUI();
        //log.info("New GUI created");

    }




    /* Main GUI constructor
     * 1. Construct GUI.
     * 2. Override run method.
     */
    public MainGUI() {

        Config.GUI = this; // Set the config gui to this new GUI

        EventQueue.invokeLater(new Runnable() {

			@Override
            public void run() {
                try {
                	File instanceCheck = new File("Running" + ".txt");
					if (instanceCheck.exists()) {
						showError("You are already running the Web Crawler.");
						System.exit(0);
					}
					try {
						PrintWriter running = new PrintWriter(instanceCheck);
						running.write("True");
						running.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                try {
                    BackgroundPane background = new BackgroundPane();
                    //background.setBackground(ImageIO.read(new File("bin/images/crawl-bg-copy.png")));
                    background.setBackground((BufferedImage) getResourceImage("/images/crawl-bg-copy.png"));


                    /* frame, and labels declaration. */
                    final JFrame frame = new JFrame("Crawler App vi4.15");
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setSize(1300, 1000);
        			frame.setLocation(200, 50);
                    frame.setVisible(true);
                    frame.setJMenuBar(menuBar);




            		/* Adding some actions to text fields and buttons.
            		 * 1. Register input urls to al.
            		 * 2. Register revisit button to al.
            		 * */
            		crawlButton.addActionListener(new ActionListener() {

            			public void actionPerformed(ActionEvent e) {





                            String inputURLs = urlSearchAreaField.getText();
                            String revisit = revisitComboBox.getSelectedItem().toString();


                            if( inputURLs.equals("Please enter your urls here, one per line!"))
                            {
                                showError("Please enter a URL!");
                                return;
                            }

                            /* Revisit time conditional statements.
                             * Added 12 hour, 1 month, 1 year statements below and also in Config, and Crawler files.
                             * 12 hour, 1 month, and 1 year were added to the revisit drop down list.
                             */
                            Config.resetRevisitTimes(); // Reset possible changes from previous run
                            if (revisit.equals("1 hour"))
								Config.REVISIT_HOURS = 1;
                            else if (revisit.equals("Immediate"))
                                Config.REVISIT_HOURS = 0;
                            else if (revisit.equals("12 hours"))
                            	Config.REVISIT_HOURS = 12;
							else if (revisit.equals("1 day"))
								Config.REVISIT_DAYS = 1;
							else if (revisit.equals("1 week"))
								Config.REVISIT_DAYS = 7;
							else if(revisit.equals("1 month"))
								Config.REVISIT_DAYS = 30;
							else if (revisit.equals("1 year"))
								Config.REVISIT_YEARS = 1;

							else{
                                showError("Please select a revisit time!");
                                return;
                            }

                            /* Ensure url depth has been selected
                             * Force the user to pick a depth setting before
                             * crawling can continue.
                             */
							int depth = Integer.parseInt(depthCbox.getSelectedItem().toString());
							// Check for weird depth numbers
							if (depth < 0) {
                                showError("Please select a valid depth");
                                return;
							}

                            inputURLs = inputURLs.replaceAll("\n", "|");
                            inputURLs = inputURLs.replaceAll(" ", "");
                            urlTextArea.setText("");

                            // Separate URLs
                            String[] seedURLs = inputURLs.split("\\|");

                            if(seedURLs != null && seedURLs[0].length() > 0){
                                try {
                                    /* Setting spinning/hour glass
                                     * Set crawling to true when crawlButton is clicked
                                     * Set cursor to waitCursor on crawlButton click.
                                     */
                                    crawling = true;
                                    frame.setCursor(waitCursor);

                                    /* Display components while crawling is in progress.
                                     * And disable other components
                                     * Enable/display crawlingMsg
                                     */
                                      urlSearchAreaField.setEnabled(false);
                                      revisitComboBox.setEnabled(false);
                                      crawlButton.setEnabled(false);
                                      crawlingMsg.setVisible(true);
                                      depthCbox.setEnabled(false);

                                    Crawler.handleInput(seedURLs, depth);

                                    /* Start new session at each url crawled
                                     * Reset count values of each crawled.
                                     */
                                    crawledUrlLabel.setText("");
                                    crawledWordsLabel.setText("");



                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else{
                                showError("Please enter a URL!");
                                return;
                            }


                            // Display complete when crawling is done.
                            crawlingUrlLabel.setText("Completed!");
                            crawlingWordLabel.setText("Completed!");


                            // Display values of each url crawled.
                            crawledUrlLabel.setText(""+Crawler.getCrawledURLs());
                            crawledWordsLabel.setText(""+ Config.crawlerWords.getWordsMap().size());


                            /* Show hour glass cursor while crawling is in progress.
                             * Enable other components when crawling done/null
                             * Disable/do not display crawling message when crawling not in progress.
                             * Set Cursor to null when crawling is complete.
                             */
                            frame.setCursor(null);
                            urlSearchAreaField.setEnabled(true);
                            revisitComboBox.setEnabled(true);
                            crawlButton.setEnabled(true);
                            crawlingMsg.setVisible(false);
                            depthCbox.setEnabled(true);

                        }


                    });





            		// Window closing confirmation
            		frame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					        if (JOptionPane.showConfirmDialog(frame,
					            "Are you sure to close this window?", "Really Closing?",
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

					        	// FIXME: crashing does not delete running.txt properly
					    		File instanceCheck = new File("Running" + ".txt");

					    		if (instanceCheck.exists()) {
					    			instanceCheck.delete();
					    		}
					            System.exit(0);
					        }
					    }
					});


            		// Save urls history for later use.

            		/*
            		load.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	if(e.getSource() == load) {
                        		urlTextArea.setText(""+Crawler.getLoadURLs());
                        		//urlTextArea.setText(""+ Config.crawlerWords.getWordsMap().size());
								}
                        	}
                        //}
            		});
            		*/



            		 /* Show errors, if any, and return.
            		  * Setting this method to consolidate all of the error messages and to provide a simple way.
            		  * Just use showError() functions to set your error message rather JOp. etc.
            		  * For example, showError("Enter your error message here");
            		  *
            		  * */
                    if (errorList.size() > 0) {
                        StringBuffer message = new StringBuffer();
                        // Concatenate errors into single message.
                        for (int i = 0; i < errorList.size(); i++) {

                            message.append(errorList.get(i));
                            if (i + 1 < errorList.size()) {
                                message.append("\n");
                            }
                        }
                        showError(message.toString());
                        return;
                    }


            		// Set the background image to frame.
            		frame.setContentPane(background);


            		/* File menu items to be added to menu item list.
            		 * 1. Add menu item save to fileMenu
            		 * 2. Add menu item load to fileMenu
            		 * 3. Add menu item exit to fileMenu
            		 * */
            		//fileMenu.add(save);
            		//fileMenu.add(load);
            		fileMenu.add(exit);
            		helpFileMenu.add(helpFile);
            		infoFileMenu.add(infoD);


            		/* Add fileMenu to menuBar
            		 * Set menu icon to fileMenu
            		 */
            		menuBar.add(fileMenu);
            		fileMenu.setIcon(menuIcon);
            		menuBar.add(helpFileMenu);
            		helpFileMenu.setIcon(helpIcon);
            		menuBar.add(infoFileMenu);
            		infoFileMenu.setIcon(infoIcon);
            		//menuBar.add(configFileMenu);
            		configFileMenu.setIcon(advancedIcon);
            		fileMenu.setFont(new Font("Arial", Font.PLAIN, 17));


            		// Register menu items to action listener and perform action
            		exit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	if(e.getSource() == exit) {
                                if (JOptionPane.showConfirmDialog(frame,
                                    "Are you sure you want to exit?", "Really Closing?",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

                                    // FIXME: Crashing does not delete running.txt properly
                                    File instanceCheck = new File("Running" + ".txt");

                                    if (instanceCheck.exists()) {
                                        instanceCheck.delete();
                                    }
                                    System.exit(0);
                                }
                        	}
                        }
            		});




            		/* Register helpFileMenu to action listener and perform action
            		 * FAQ info to help new users how to crawl.
            		 *
            		 */
            		helpFile.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	if(e.getSource() == helpFile) {
        						JOptionPane.showMessageDialog(null, "<html><body style='width: 500px;'><h1>"
        								+ "What is Crawler App vi4.15?</h1><p>Crawler App is application that scans the web, reading everything it finds. "
        								+ "Crawler App scans web pages to see what"
        								+ "words they contain, and where those words are used</p><h1>How Crawler App works?</h1>"
        								+ "<p>Crawler App starts with a list of URLs to visit, called the seeds. As the Crawler App visits "
        								+ "these URLs, it identifies all the hyperlinks in the page and adds them to the list of URLs to visit,"
        								+ " called the crawl frontier.<p/><h1>How to start crawling?</h1>"
        								+ "<ol><li>Launch the Crawler App.</li><li>Select your language from the drop down list.</li>"
        								+ "<li>Enter the url or urls you would like to crawl</li><li>Specify your url depth</li>"
        								+ "<li>Select url revisit time.</li><li>Click on the 'Crawl' button</li>"
        								+ "<li>If all goes well, crawling will begin.</li></ol><h1>How do I know if my url is crawling?</h1>"
        								+ "<p>Crawling updates are provided below the Crawl button. You should see live updates of url"
        								+ " being crawled. If no updates are provided, please repeat step 1 to 5.</p>"
        								+ "<h1>Are my crawled urls saved elsewhere?</h1><p>Yes. Crawled urls are saved in a text file (urls.txt)"
        								+ " on your local machine. Please check your home directory for urls.text file. Also, crawled urls will "
        								+ " be saved in the text area below the Crawl button.</p>"
        								+ "<h1>Will Crawler extract words found in each url?</h1>"
        								+ "<p>Yes. Crawler goal is to make you happy. Crawler will extract every word found in each url"
        								+ " and save them on your local machine. Please check your home directory for CrawledWords.txt file.</p>"
        								+ "<h1>Can I crawl other non English websites?</h1><p>Absolutely, yes. You can crawl any website you"
        								+ " desire. Crawler has multi-language capability. Whatever you can do in English, you can do in other"
        								+ " languages as well, including Telugu.</p>"
        								+ "<h1>Will I get a Pizza from crawling?</h1><p>Great question! But Crawler does not extract"
        								+ " pizza from any of your favorite Pizza huts.</p></body>"
        								+ "</html>"+ "Crawler Help window",
        								"Crawler App. vi4.15", + JOptionPane.INFORMATION_MESSAGE);
                        	}
                        }
            		});



            		/* Register infoFileMenu to action listener and perform action
            		 * Setting up information for disclaimer more info menu.
            		 */
            		infoD.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	if(e.getSource() == infoD) {
        						JOptionPane.showMessageDialog(null, "<html><body style='width: 500px;'>"
        								+ "<h1>Disclaimer!</h1><p>Web Crawler Group provides the Crawler App vi4.15 as "
        								+ " a service to the public, site owners, and individual users.</p><br /> "
        								+ "<p>Web Crawler Group is not responsible for, and expressly disclaims all liability for, damages, and "
        								+ "injury of any kind arising out of use, reference to, or reliance on any information contained within the App. "
        								+ "While the information contained within the App is periodically updated, no guarantee is given that the "
        								+ " information provided in this Crawler App is correct, complete, and up-to-date.</p> <br /> "
        								+ "<p>Although the Crawler App vi4.15 may crawl links providing direct access to other resources, "
        								+ "including Web sites, Keywords, Web Crawler Group is not responsible for the accuracy or content of "
        								+ "information contained in these urls and keywords that been crawled and saved.</p> <br />"
        								+ "<p>Crawling third-party sites do not constitute an endorsement by Web Crawler Group of the parties or their "
        								+ "products and services. The appearance on the Web site of advertisements and product or service information "
        								+ "does not constitute an endorsement by Web Crawler Group, and Web Crawler Group has not investigated the "
        								+ "claims made by any advertiser. Product information is based solely on material received from suppliers.</p><br />"
        								+ "<p style='color:red; font-style:italic; font-weight:bold;'>Although Crawler App vi4.15 crawls urls, Crawler App may have limitations as to which urls are capable of "
        								+ "crawling. Crawler App may not crawl all urls or provide results for crawled urls.</p> <br />"
        								+ "<p>If you have any questions or concerns, please contact Web Crawler Group at webCrawlerGroup@crawler.com. "
        								+ "Thank you.</p>"
        								+ "</body>"
        								+ "</html>" + "Crawler Disclaimer Window",
        								"Disclaimer", + JOptionPane.WARNING_MESSAGE);
                        	}
                        }
            		});




            		/* Panel and layout config settings for Crawler components.
            		 * 1. Create panel1 to hold components.
            		 * 2. Declare GridBagLayout object for lay out purposes.
            		 * 3. Set panel1 to layout.
            		*/
                    JPanel panel1 = new JPanel();
                    GridBagConstraints gridBag;
                    GridBagLayout layout = new GridBagLayout();
                    panel1.setLayout(layout);


                    /* Urls history display area panel setting.
                     * 1. Create urlHistoryPanel
                     * 2. Set urlHistoryPanel to titleBorder.
                     * 3. Set urlHistoryPanel to layout.
                     * 4. Add urlHistoryPanel to layout.
                     */
                    JPanel urlHistoryPanel = new JPanel();
                    urlHistoryPanel.setBorder(BorderFactory.createTitledBorder("Crawling status"));
                    urlHistoryPanel.setLayout(new BorderLayout());
                    urlHistoryPanel.setForeground(Color.BLACK);
                    urlHistoryPanel.add(new JScrollPane(urlTextArea),BorderLayout.CENTER);
                    urlTextArea.setEditable(false);
                    urlTextArea.append("**Welcome to Web Crawler!**\n");


                    // Set fg color and font for depth field
            		labelDepthToSearch.setForeground(Color.WHITE);
            		labelDepthToSearch.setFont(new Font("Arial", Font.BOLD, 16));


            		/* Set bg and font for url entered label and text field */
            		labelEnterUrl.setForeground(Color.WHITE);
            		labelEnterUrl.setFont(new Font("Arial", Font.BOLD, 16));
            		urlSearchAreaField.setText("Please enter your urls here, one per line!");
            		urlSearchAreaField.setFont(new Font("Arial", Font.PLAIN, 16));
            		//urlSearchAreaField.setBorder(roundedTextArea);

            		// Crawler app is multi-language
            		langCbox.setFont(new Font("Arial", Font.PLAIN, 20));
            		langCbox.setForeground(crawlBtnColor);

            		// Set bg and font for revisit time
            		labelUrlRevisitTime.setForeground(Color.WHITE);
            		labelUrlRevisitTime.setFont(new Font("Arial", Font.BOLD, 16));


            		// Set bg and font for language
            		langName.setForeground(Color.WHITE);
            		langName.setFont(new Font("Arial", Font.BOLD, 16));


            		/* Adding blankLabel 7 for top padding of logo image
            		 * Add blankLabel 7 to layout
            		 * Add blankLabel 7 to panel1.
            		 */
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(blankLabel7,gridBag);
                    panel1.add(blankLabel7);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 0);
            		layout.setConstraints(blankLabel7,gridBag);


            		// Add logo to panel1.
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(logoIcon,gridBag);
                    panel1.add(logoIcon);
                    logoIcon.setPreferredSize(new Dimension(719, 118));
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 0);
            		layout.setConstraints(logoIcon,gridBag);


            		// Add blank labels and text fields to panel1.
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(blankLabel,gridBag);
                    panel1.add(blankLabel);
                    blankLabel.setPreferredSize(new Dimension(150, 20));
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 0);
            		layout.setConstraints(blankLabel,gridBag);


            		// Add language comboBox and label to panel1.
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(langName,gridBag);
                    panel1.add(langName);
                    langCbox.setPreferredSize(new Dimension(10, 20));
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(langCbox,gridBag);
                    //panel1.add(langCbox);



            		// Blank label 14
                    gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(blankLabel4,gridBag);
                    panel1.add(blankLabel4);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 0);
            		layout.setConstraints(blankLabel4,gridBag);


                    // Add search field label and text field to panel1.
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(labelEnterUrl,gridBag);
                    panel1.add(labelEnterUrl);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 5);
                    layout.setConstraints(urlSearchAreaField,gridBag);
                    panel1.add(urlSearchAreaField);
                    //urlSearchAreaField.setBackground( new Color(162, 166, 128, 100) );


            		// Add url depth comboBox and label to panel
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(labelDepthToSearch,gridBag);
                    panel1.add(labelDepthToSearch);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 5);
                    layout.setConstraints(depthCbox,gridBag);
                    panel1.add(depthCbox);
                    depthCbox.setEditable(true);


            		/* Add revisit time comboBox and label to panel1
            		 * 1. Add revisit time comBox to layout.
            		 * 2. Add labelUrlRevisitTime to layout.
            		 * 3. Set revisitComBox dimension.
            		 */
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(0, 0, 0, 0);
                    layout.setConstraints(labelUrlRevisitTime,gridBag);
                    panel1.add(labelUrlRevisitTime);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(0, 0, 0, 0);
                    layout.setConstraints(revisitComboBox,gridBag);
                    panel1.add(revisitComboBox);
                    revisitComboBox.setPreferredSize(new Dimension(4, 40));
                    //revisitComboBox.setEditable(true);


                    gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(blankLabel21,gridBag);
                    panel1.add(blankLabel21);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 0);
            		layout.setConstraints(blankLabel21,gridBag);



                    /* Add crawl button and label to panel1
                     * 1. Add crawl button to layout
                     * 2. Set crawlButton dimension
                     * 3. Set crawlButton font
                     * 4. Add buttonLabel1-3 to panel1
                     */
            		gridBag = new GridBagConstraints();
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 5, 5);
                    layout.setConstraints(crawlButton,gridBag);
                    panel1.add(crawlButton);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 5, 5);
            		panel1.add(buttonLabel);
            		panel1.add(buttonLabel2);
            		panel1.add(buttonLabel3);
            		panel1.add(crawlButton);
            		crawlButton.setPreferredSize(new Dimension(200, 45));
                    crawlButton.setFont(new Font(Arial, Font.BOLD, 40));



                    JLabel crawlingMsg1 = new JLabel(" ");
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(crawlingMsg1,gridBag);
                    panel1.add(crawlingMsg1);
                    crawlingMsg = new JLabel("Please wait, crawling is in progress...");
                    crawlingMsg1.setFont(new Font(Arial, Font.PLAIN, 12));
                    crawlingMsg.setFont(new Font(Arial, Font.BOLD, 25));
                    crawlingMsg1.setForeground(Color.WHITE);
                    crawlingMsg.setForeground(Color.GREEN);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 5);
                    layout.setConstraints(crawlingMsg,gridBag);
                    panel1.add(crawlingMsg);
                    crawlingMsg.setVisible(false);






                    /* Blank space add between Crawl button and footer section.
                     * 1. Add blankLabel6 to layout.
                     * 2. Add blankLabel6 to panel1
                     */
                    gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(blankLabel6,gridBag);
                    panel1.add(blankLabel6);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 0);
            		layout.setConstraints(blankLabel6,gridBag);


            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(blankLabel9,gridBag);
                    panel1.add(blankLabel9);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 0);
            		layout.setConstraints(blankLabel9,gridBag);


            		 //JSeperator
            		/*
                    JSeparator separator = new JSeparator();
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 5, 5);
                    layout.setConstraints(separator,gridBag);
                    panel1.add(separator);
                    separator.setBackground(separatorColor);
                    separator.setForeground(separatorColor);
                    */



                    /* Define label for UI/GUI components and add it to panel.
            		 * 1. Create log file label
            		 * 2. Add it to layout and position its alignment.
            		*/
            		JLabel logLabel = new JLabel("Crawled urls saved to:");
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(logLabel,gridBag);
                    panel1.add(logLabel);
                    String file = System.getProperty("user.dir") +
                    System.getProperty("file.separator") +"urls.txt";
                    urlTextPath = new JTextField(file);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 5);
                    layout.setConstraints(urlTextPath,gridBag);
                    panel1.add(urlTextPath);
                    //logLabel.setFont(new Font(Arial, Font.BOLD, 15));
                    //logLabel.setVisible(false);
                    urlTextPath.setEditable(false);
                    logLabel.setForeground(Color.WHITE);
                    urlTextPath.setBackground(Color.GRAY);



            		/* Add crawling urls label info to layout and panel1.
            		 * 1. Create crawlUrlLabel1
            		 * 2. Add crawlingUrlLabel to layout.
            		 * 3. Add crawlingUrlLabel to panel1.
            		 * 4. Set font and color for crawlingUrlLabel
            		 * 5. Set font
            		 * 6. Set bg color.
            		 */
            		JLabel crawlingUrlLabel1 = new JLabel("Crawling urls:");
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(crawlingUrlLabel1,gridBag);
                    panel1.add(crawlingUrlLabel1);
                    crawlingUrlLabel = new JLabel();
                    crawlingUrlLabel1.setFont(new Font(Arial, Font.PLAIN, 15));
                    crawlingUrlLabel1.setForeground(Color.WHITE);
                    crawlingUrlLabel.setForeground(Color.GREEN);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 5);
                    layout.setConstraints(crawlingUrlLabel,gridBag);
                    panel1.add(crawlingUrlLabel);


                    /* Add total crawled urls label info to layout and panel1.
                     * 1. Create crawledUrlLabel1
                     * 2. Add crawledUrlLabel to layout.
                     * 3. Add crawledUrlLabel to layout
                     * 4. Add crawledUrlLabel to panel
                     * 5. Add crawledUrlLabel1 to panel
                     * 6. Set font
            		 * 7. Set bg color.
                     */

                    JLabel crawledUrlLabel1 = new JLabel("Unique URLs crawled:");
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(crawledUrlLabel1,gridBag);
                    panel1.add(crawledUrlLabel1);
                    crawledUrlLabel = new JLabel();
                    crawledUrlLabel1.setFont(new Font(Arial, Font.PLAIN, 15));
                    crawledUrlLabel1.setForeground(Color.WHITE);
                    crawledUrlLabel.setForeground(Color.GREEN);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 5);
                    panel1.add(crawledUrlLabel);
                    layout.setConstraints(crawledUrlLabel,gridBag);


            		/* Available urls to crawl label information
            		 * 1. Create urlAvailabelToCrawlLabel1
            		 * 2. Add urlAvailableToCrawlLabel1 to layout.
            		 * 3. Add urlAvailableToCrawlLabel1 to panel1
            		 * 4. Add urlAvailabelToCrawlLabel to layout
            		 * 5. Add urlAvailabelToCrawlLabel to panel1
            		 * 6. Set font
            		 * 7. Set bg color.
            		 */
                    /*
                    JLabel urlAvailabelToCrawlLabel1 = new JLabel("Total URLs discovered:");
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(urlAvailabelToCrawlLabel1,gridBag);
                    panel1.add(urlAvailabelToCrawlLabel1);
                    urlAvailabelToCrawlLabel = new JLabel();
                    urlAvailabelToCrawlLabel1.setFont(new Font(Arial, Font.PLAIN, 15));
                    urlAvailabelToCrawlLabel1.setForeground(Color.WHITE);
                    urlAvailabelToCrawlLabel.setForeground(Color.GREEN);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 5);
                    layout.setConstraints(urlAvailabelToCrawlLabel,gridBag);
                    panel1.add(urlAvailabelToCrawlLabel);
					*/

                    /* Crawling words label information
            		 * 1. Create crawlingWordsLabel1
            		 * 2. Add crawlingWordLabel1 to layout.
            		 * 3. Add crawlingWordLabel1 to panel1
            		 * 4. Add crawlingWordLabel to layout
            		 * 5. Add crawlingWordLabel to panel1
            		 * 6. Set font
            		 * 7. Set bg color.
            		 */
                    JLabel crawlingWordLabel1 = new JLabel("Crawling words:");
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(crawlingWordLabel1,gridBag);
                    panel1.add(crawlingWordLabel1);
                    crawlingWordLabel = new JLabel();
                    crawlingWordLabel1.setFont(new Font(Arial, Font.PLAIN, 15));
                    crawlingWordLabel1.setForeground(Color.WHITE);
                    crawlingWordLabel.setForeground(Color.GREEN);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 5);
                    layout.setConstraints(crawlingWordLabel,gridBag);
                    panel1.add(crawlingWordLabel);


                    /* Crawling words label information
            		 * 1. Create crawlingWordsLabel1
            		 * 2. Add crawledWordsLabel1 to layout.
            		 * 3. Add crawledWordsLabel1 to panel1
            		 * 4. Add crawledWordsLabel to layout
            		 * 5. Add crawledWordsLabel to panel1
            		 * 6. Set font
            		 * 7. Set bg color.
            		 */
                    JLabel crawledWordLabel1 = new JLabel("Total words extracted:");
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(crawledWordLabel1,gridBag);
                    panel1.add(crawledWordLabel1);
                    crawledWordsLabel = new JLabel();
                    crawledWordLabel1.setFont(new Font(Arial, Font.PLAIN, 15));
                    crawledWordLabel1.setForeground(Color.WHITE);
                    crawledWordsLabel.setForeground(Color.GREEN);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 5);
                    layout.setConstraints(crawledWordsLabel,gridBag);
                    panel1.add(crawledWordsLabel);


                    /* Crawling progress label to information
            		 * 1. Create crawlingProgressLabel1
            		 * 2. Add crawlingProgressLabel1 to layout.
            		 * 3. Add crawlingProgressLabel1 to panel1
            		 * 4. Add crawlingProgressLabel to layout
            		 * 5. Add crawlingProgressLabel to panel1
            		 * 6. Set font
            		 * 7. Set bg color.
            		 */

                    /*
            		JLabel crawlingProgressLabel1 = new JLabel("Crawling progress status:");
            		gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    layout.setConstraints(crawlingProgressLabel1,gridBag);
                    panel1.add(crawlingProgressLabel1);
                    crawlingProgressLabel = new JProgressBar(0, 100);
                    crawlingProgressLabel.setValue(0);
                    crawlingProgressLabel.setStringPainted(true);
                    //crawlingProgressLabel.setString("0%");
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 5);
                    layout.setConstraints(crawlingProgressLabel,gridBag);
                    panel1.add(crawlingProgressLabel);
                    crawlingProgressLabel1.setFont(new Font(Arial, Font.PLAIN, 15));
                    crawlingProgressLabel1.setForeground(Color.WHITE);
                    */


                    /* Blank label 15 to provide space between progress bar and urls history display
                     * 1. Add blankLabel15 to layout
                     * 2. Add blankLabel15 to panel1.
                     */
                    gridBag = new GridBagConstraints();
            		gridBag.anchor = GridBagConstraints.EAST;
            		gridBag.insets = new Insets(5, 5, 0, 0);
                    panel1.add(blankLabel5);
            		gridBag = new GridBagConstraints();
            		gridBag.fill = GridBagConstraints.HORIZONTAL;
            		gridBag.gridwidth = GridBagConstraints.REMAINDER;
            		gridBag.insets = new Insets(5, 5, 0, 0);
            		layout.setConstraints(blankLabel5,gridBag);



                    /* Panel1 additional setting.
                     * 1. Set background color for panel1.
                     * 2. Set panel opaque to false to get rid of default background color.
                     */
            		panel1.setBackground(color);
            		panel1.setOpaque(false);



            		/* Adding panel1 and other panels to frame.
            		 * 1. Add panel 1 to frame
            		 * 2. Add urlHistoryPanel to frame.
            		 */
            		frame.getContentPane().setLayout(new BorderLayout());
            		frame.add(panel1, BorderLayout.NORTH);
                    frame.getContentPane().add(urlHistoryPanel, BorderLayout.CENTER);



                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
        });

    }




    /*
     * Retrieve an image file with relative path
     * Should work from within packed JARs
     */
    public Image getResourceImage(String fileName) {
          URL imgURL = getClass().getResource(fileName);
          Image image = null;
          try {
             image = ImageIO.read(imgURL);
           } catch (IOException e) { e.printStackTrace(); }
          return image;
     }


    /*
     * Used by other classes to print messages
     * to the GUI status textfield area.
     */
    public void logMessage(String msg) {
        if( null != msg && !msg.isEmpty() )
            urlTextArea.append(msg + "\n");
    }


    // Show dialog box with error message.
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Oop! Please read the message below.", JOptionPane.ERROR_MESSAGE);
    }



	// Background image class
    // Everything else below is for background image setting.
    public class BackgroundPane extends JPanel {
		private static final long serialVersionUID = 1L;
		private BufferedImage img;
        private BufferedImage scaled;

        public BackgroundPane() {
        }

        @Override
        public Dimension getPreferredSize() {
            return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(), img.getHeight());
        }

        public void setBackground(BufferedImage value) {
            if (value != img) {
                this.img = value;
                repaint();
            }
        }

        @Override
        public void invalidate() {
            super.invalidate();
            if (getWidth() > img.getWidth() || getHeight() > img.getHeight()) {
                scaled = getScaledInstanceToFill(img, getSize());
            } else {
                scaled = img;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (scaled != null) {
                int x = (getWidth() - scaled.getWidth()) / 2;
                int y = (getHeight() - scaled.getHeight()) / 2;
                g.drawImage(scaled, x, y, this);
            }
        }

    }

    public static BufferedImage getScaledInstanceToFill(BufferedImage img, Dimension size) {

        double scaleFactor = getScaleFactorToFill(img, size);

        return getScaledInstance(img, scaleFactor);

    }

    public static double getScaleFactorToFill(BufferedImage img, Dimension size) {

        double dScale = 1;

        if (img != null) {

            int imageWidth = img.getWidth();
            int imageHeight = img.getHeight();

            double dScaleWidth = getScaleFactor(imageWidth, size.width);
            double dScaleHeight = getScaleFactor(imageHeight, size.height);

            dScale = Math.max(dScaleHeight, dScaleWidth);

        }

        return dScale;

    }

    public static double getScaleFactor(int iMasterSize, int iTargetSize) {

        double dScale = (double) iTargetSize / (double) iMasterSize;

        return dScale;

    }

    public static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor) {

        return getScaledInstance(img, dScaleFactor, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);

    }

    protected static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {

        BufferedImage imgScale = img;

        int iImageWidth = (int) Math.round(img.getWidth() * dScaleFactor);
        int iImageHeight = (int) Math.round(img.getHeight() * dScaleFactor);

//        System.out.println("Scale Size = " + iImageWidth + "x" + iImageHeight);
        if (dScaleFactor <= 1.0d) {

            imgScale = getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        } else {

            imgScale = getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        }

        return imgScale;

    }

    protected static BufferedImage getScaledDownInstance(BufferedImage img,
            int targetWidth,
            int targetHeight,
            Object hint,
            boolean higherQuality) {

        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;
        if (targetHeight > 0 || targetWidth > 0) {
            int w, h;
            if (higherQuality) {
                // Use multi-step technique: start with original size, then
                // scale down in multiple passes with drawImage()
                // until the target size is reached
                w = img.getWidth();
                h = img.getHeight();
            } else {
                // Use one-step technique: scale directly from original
                // size to target size with a single drawImage() call
                w = targetWidth;
                h = targetHeight;
            }

            do {
                if (higherQuality && w > targetWidth) {
                    w /= 2;
                    if (w < targetWidth) {
                        w = targetWidth;
                    }
                }

                if (higherQuality && h > targetHeight) {
                    h /= 2;
                    if (h < targetHeight) {
                        h = targetHeight;
                    }
                }

                BufferedImage tmp = new BufferedImage(Math.max(w, 1), Math.max(h, 1), type);
                Graphics2D g2 = tmp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
                g2.drawImage(ret, 0, 0, w, h, null);
                g2.dispose();

                ret = tmp;
            } while (w != targetWidth || h != targetHeight);
        } else {
            ret = new BufferedImage(1, 1, type);
        }
        return ret;
    }

    protected static BufferedImage getScaledUpInstance(BufferedImage img,
            int targetWidth,
            int targetHeight,
            Object hint,
            boolean higherQuality) {

        int type = BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w < targetWidth) {
                w *= 2;
                if (w > targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h < targetHeight) {
                h *= 2;
                if (h > targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
            tmp = null;

        } while (w != targetWidth || h != targetHeight);
        return ret;
    }



}


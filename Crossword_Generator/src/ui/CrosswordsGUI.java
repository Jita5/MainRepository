package ui;

import java.awt.EventQueue;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingWorker.StateValue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.SwingConstants;

import core.API;
import core.Config;
import core.HtmlCreator;

public class CrosswordsGUI {

	//private
	private JTextField textMinWords;
	private JTextField textMaxWords;
	private JTextField gridRowSize;
	private JTextField gridColSize;
	private Font font;
	@SuppressWarnings("rawtypes")
	private JComboBox selectInput;
	private JLabel lblCluesByTopic;
	private JCheckBox selectCluesByTopic;
	@SuppressWarnings("rawtypes")
	private JComboBox selectNumPuzzles;
	@SuppressWarnings("rawtypes")
	private JComboBox selectMaxAllowedTime;

	//package
	JFrame frame;
	JPanel panelPreview;
	JLabel loadingIconLabel;
	Panel previewGrid;
	@SuppressWarnings("rawtypes")
	JComboBox selectPuzzleToPreview;
	JTabbedPane tabStrip;
	ArrayList<API> puzzles;
	JCheckBox chec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrosswordsGUI window = new CrosswordsGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrosswordsGUI() {
		initialize();
		frame.setIconImage(getResourceImage(Config.AppIcon16));
	}

	/**
	 * Gets Image from resources
	 */
	public Image getResourceImage(String fileName) {
		URL imgURL = getClass().getResource(fileName);
		Image image = null;
		try {
			image = ImageIO.read(imgURL);
		} catch (IOException e) {}
		return image;
	}

	/**
	 * Binds an API to the preview grid
	 */
	void BindGrid(API api){

		previewGrid.removeAll();

		String[][] grid = api.GetGrid();

		for(int i=0; i<Config.GridRows; i++){
			for(int j=0; j<Config.GridColumns; j++){

				JTextField field = new JTextField(grid[i][j]);
				field.setHorizontalAlignment(JTextField.CENTER);
				field.setFont(font);

				if(field.getText().isEmpty()){
					field.setBackground(Color.BLACK);
				}

				previewGrid.add(field);
			}
		}
	}

	/**
	 * ValidConfiguration - Attempts to set configuration and then checks if it's valid
	 */
	Boolean ValidConfiguration(){

		//Grid Row
		if(gridRowSize.getText().isEmpty()){
			return false;
		}
		Config.GridRows = Integer.valueOf(gridRowSize.getText());

		//max grid
		if(gridColSize.getText().isEmpty()){
			return false;
		}
		Config.GridColumns = Integer.valueOf(gridColSize.getText());		

		//min words
		if(textMinWords.getText().isEmpty()){
			return false;
		}
		Config.MinWordsPlaced = Integer.valueOf(textMinWords.getText());

		//max words
		if(textMaxWords.getText().isEmpty()){
			return false;
		}
		Config.MaxWordsPlaced = Integer.valueOf(textMaxWords.getText());

		//number of puzzle to generate
		Config.NumPuzzlesToGenerate = Integer.valueOf((String) selectNumPuzzles.getSelectedItem());

		//maximum allowed time
		Config.MaxAllowedTime = Integer.valueOf((String) selectMaxAllowedTime.getSelectedItem());

		return true;
	}

	/*
			Config.GridRows = 10;
			Config.GridColumns = 10;*/

	@SuppressWarnings("rawtypes")
	JComboBox GetPuzzleSelector(){
		JComboBox box = new JComboBox();
		box.setBounds(116, 11, 53, 20);
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox comboBox = (JComboBox) event.getSource();
				Object selected = comboBox.getSelectedItem();
				int x = (int)selected;
				BindGrid(puzzles.get(x-1));
				comboBox.requestFocusInWindow();
				frame.setVisible(true);
			}
		});
		return box;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		//main window
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 524, 590);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//Font
		font = null;
		InputStream fontFile = getClass().getResourceAsStream(Config.FontFile);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, 24);
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);

		//init welcome panel
		JPanel panelWelcome = new ImageJPanel(getResourceImage(Config.AppBackground));
		panelWelcome.setBounds(10, 51, 424, 431);
		panelWelcome.setLayout(null);

		//init config panel
		JPanel panelConfig = new ImageJPanel(getResourceImage(Config.AppBackground));
		panelConfig.setBounds(10, 51, 424, 431);
		panelConfig.setLayout(null);

		//------------------------------------------------------
		//------------------PREVIEW PANEL-----------------------
		//------------------------------------------------------

		//init preview panel
		panelPreview = new ImageJPanel(getResourceImage(Config.AppBackground));
		panelPreview.setBounds(10, 51, 424, 431);

		//preview grid
		previewGrid = new Panel();
		previewGrid.setLayout(new GridLayout(Config.GridRows, Config.GridColumns));
		previewGrid.setBounds(10, 49, 473, 452);
		panelPreview.add(previewGrid);

		//selectList preview puzzle #
		selectPuzzleToPreview = GetPuzzleSelector();
		panelPreview.add(selectPuzzleToPreview);

		//label preview puzzle #
		JLabel lblPreviewPuzzle = new JLabel("Preview Puzzle #");
		lblPreviewPuzzle.setBounds(10, 14, 108, 14);
		panelPreview.add(lblPreviewPuzzle);

		//generate this puzzle button
		JButton btnGenerateThisPuzzle = new JButton("Generate HTML");
		btnGenerateThisPuzzle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object selected = selectPuzzleToPreview.getSelectedItem();
				if(selected != null){
					int x = (int)selected;
					API currentPuzzle = puzzles.get(x-1);
					try {
						boolean cluesByTopic = Config.InputSource == 0 && 
								CrosswordsGUI.this.selectCluesByTopic.isSelected();
						@SuppressWarnings("unused")
						HtmlCreator html = new HtmlCreator(
								currentPuzzle.GetCrosswordHtml(true, cluesByTopic));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(frame, "Why not create some puzzles to preview first?");
				}
			}
		});
		btnGenerateThisPuzzle.setBounds(357, 10, 126, 23);
		panelPreview.add(btnGenerateThisPuzzle);

		panelPreview.setLayout(null);

		//------------------------------------------------------
		//----------------END PREVIEW PANEL---------------------
		//------------------------------------------------------

		//tab strip
		tabStrip = new JTabbedPane(JTabbedPane.TOP);
		tabStrip.setBorder(null);
		tabStrip.setBounds(10, 11, 498, 539);
		tabStrip.addTab( "Welcome", panelWelcome );
		tabStrip.addTab( "Config", panelConfig );
		tabStrip.addTab( "Preview", panelPreview );
		frame.getContentPane().add(tabStrip);

		//------------------------------------------------------
		//---------------------CONFIG PANEL---------------------
		//------------------------------------------------------



		//Grid Column
		gridColSize = new JTextField();
		gridColSize.setText("10");
		gridColSize.setBounds(280, 118, 28, 20);
		panelConfig.add(gridColSize);
		gridColSize.setColumns(10);

		//Grid Width
		gridRowSize = new JTextField();
		gridRowSize.setText("10");
		gridRowSize.setBounds(318, 118, 28, 20);
		panelConfig.add(gridRowSize);
		gridRowSize.setColumns(10);

		//input
		selectInput = new JComboBox(Config.OptionsWordSource);
		selectInput.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				boolean visible = selectInput.getSelectedItem().equals("File");
				CrosswordsGUI.this.selectCluesByTopic.setVisible(visible);
				CrosswordsGUI.this.lblCluesByTopic.setVisible(visible);
			}
		});
		selectInput.setBounds(280, 149, 66, 20);
		panelConfig.add(selectInput);

		//minimum words
		textMinWords = new JTextField();
		textMinWords.setText("10");
		textMinWords.setBounds(280, 180, 28, 20);
		panelConfig.add(textMinWords);
		textMinWords.setColumns(10);

		//maximum words
		textMaxWords = new JTextField();
		textMaxWords.setText("10");
		textMaxWords.setBounds(318, 180, 28, 20);
		panelConfig.add(textMaxWords);
		textMaxWords.setColumns(10);

		//number of puzzles to generate
		selectNumPuzzles = new JComboBox(Config.OptionsNumberOfPuzzles);
		selectNumPuzzles.setBounds(280, 211, 66, 20);
		panelConfig.add(selectNumPuzzles);

		//max elapsed time
		selectMaxAllowedTime = new JComboBox(Config.OptionsWaitTime);
		selectMaxAllowedTime.setBounds(280, 242, 66, 20);
		panelConfig.add(selectMaxAllowedTime);

		//clues by topic
		selectCluesByTopic = new JCheckBox();
		selectCluesByTopic.setSelected(true);
		selectCluesByTopic.setBounds(280, 273, 28, 28);
		panelConfig.add(selectCluesByTopic);

		//label rows / columns
		JLabel lblRowsColumns = new JLabel("Length/Width Grid Size");
		lblRowsColumns.setForeground(Color.DARK_GRAY);
		lblRowsColumns.setBounds(119, 121, 151, 14);
		panelConfig.add(lblRowsColumns);

		//label input source
		JLabel lblInput = new JLabel("Word Source");
		lblInput.setForeground(Color.DARK_GRAY);
		lblInput.setBounds(119, 152, 151, 14);
		panelConfig.add(lblInput);

		//label min max words
		JLabel lblMinMaxWords = new JLabel("Min/Max Words Placed");
		lblMinMaxWords.setForeground(Color.DARK_GRAY);
		lblMinMaxWords.setBounds(119, 183, 151, 14);
		panelConfig.add(lblMinMaxWords);

		//label # puzzles to generate
		JLabel lblPuzzlesToGen = new JLabel("# Puzzles To Generate");
		lblPuzzlesToGen.setForeground(Color.DARK_GRAY);
		lblPuzzlesToGen.setBounds(119, 214, 151, 14);
		panelConfig.add(lblPuzzlesToGen);

		//label time elapsed
		JLabel lblElapsedTime = new JLabel("Max Elapsed Time (Secs)");
		lblElapsedTime.setForeground(Color.DARK_GRAY);
		lblElapsedTime.setBounds(119, 245, 151, 14);
		panelConfig.add(lblElapsedTime);

		//label clues by topic
		lblCluesByTopic = new JLabel("Clues by Topic");
		lblCluesByTopic.setForeground(Color.DARK_GRAY);
		lblCluesByTopic.setBounds(119, 276, 151, 14);
		panelConfig.add(lblCluesByTopic);

		//label config tab header
		JLabel lblConfigHeader = new JLabel("Setup Configuration");
		lblConfigHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfigHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		lblConfigHeader.setBounds(0, 25, 493, 35);
		panelConfig.add(lblConfigHeader);


		//button generate puzzles
		final JButton btnGeneratePuzzles = new JButton("Generate Puzzles");
		btnGeneratePuzzles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//gui words list input
				if((String)selectInput.getSelectedItem() == "GUI") {
					Config.InputSource = 1;
					frame.setEnabled(false);
					final InputJFrame inputFrame = new InputJFrame();
					inputFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					inputFrame.setSize(580,480);
					inputFrame.setVisible(true);
					inputFrame.textArea.setFont(font);
					inputFrame.setResizable(false);
					inputFrame.setLocationRelativeTo(frame);
					inputFrame.button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {

							Config.ManualWordsList = new HashMap<String, String>();
							String inputText = inputFrame.textArea.getText();
							inputText = inputText.replaceAll("\n", "|");
							String[] splitLines = inputText.split("\\|");

							Boolean validInput = true;

							for(String line : splitLines){
								String[] splitWords = line.split(",");
								if(splitWords.length < 2){
									validInput = false;
									break;
								}
								Config.ManualWordsList.put(splitWords[0].replace(" ", ""), splitWords[1]);
							}

							if(validInput){
								frame.setEnabled(true);
								inputFrame.setVisible(false);

								final CrosswordWorker gen = new CrosswordWorker(CrosswordsGUI.this);
								gen.addPropertyChangeListener(new PropertyChangeListener() {

									@Override
									public void propertyChange(PropertyChangeEvent evt) {
										// TODO Auto-generated method stub
										Enum<StateValue> state = (Enum<StateValue>) evt.getNewValue();

										if(state.name() == "STARTED"){
											loadingIconLabel.setVisible(true);
											btnGeneratePuzzles.setEnabled(false);
										}

										if(state.name() == "DONE"){
											loadingIconLabel.setVisible(false);
											btnGeneratePuzzles.setEnabled(true);
											gen.cancel(true);
										}
									}
								});
								gen.execute();
							}
							else{
								JOptionPane.showMessageDialog(frame, "Input is invalid. Please check your input and try again.");
							}
						}
					});
					inputFrame.addWindowListener(new java.awt.event.WindowAdapter() {
						@Override
						public void windowClosing(java.awt.event.WindowEvent windowEvent) {
							frame.setEnabled(true);
						}
					});
				}
				//file words list input
				else {
					Config.InputSource = 0;
					final CrosswordWorker gen = new CrosswordWorker(CrosswordsGUI.this);
					gen.addPropertyChangeListener(new PropertyChangeListener() {

						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							// TODO Auto-generated method stub
							Enum<StateValue> state = (Enum<StateValue>) evt.getNewValue();

							if(state.name() == "STARTED"){
								loadingIconLabel.setVisible(true);
								btnGeneratePuzzles.setEnabled(false);
							}

							if(state.name() == "DONE"){
								loadingIconLabel.setVisible(false);
								btnGeneratePuzzles.setEnabled(true);
								gen.cancel(true);
							}
						}
					});
					gen.execute();
				}
			}
		});
		btnGeneratePuzzles.setBounds(168, 336, 140, 23);
		panelConfig.add(btnGeneratePuzzles);

		//loading icon
		ImageIcon loadingIcon = new ImageIcon(getClass().getResource(Config.LoadingIcon));
		loadingIconLabel = new JLabel("");
		loadingIconLabel.setBounds(206, 361, 64, 64);
		loadingIconLabel.setIcon(loadingIcon);
		loadingIcon.setImageObserver(loadingIconLabel);
		loadingIconLabel.setVisible(false);
		panelConfig.add(loadingIconLabel);

		//------------------------------------------------------
		//-----------------END CONFIG PANEL---------------------
		//------------------------------------------------------

		//------------------------------------------------------
		//--------------------WELCOME PANEL---------------------
		//------------------------------------------------------

		//crossword logo image icon
		ImageIcon crosswordsIcon = new ImageIcon(getClass().getResource(Config.AppIcon32));
		JLabel cwIconLabel = new JLabel("");
		cwIconLabel.setBounds(95, 25, 32, 32);
		cwIconLabel.setIcon(crosswordsIcon);
		cwIconLabel.setVisible(true);
		panelWelcome.add(cwIconLabel);

		//button let's make some puzzles
		JButton btnLetsMakeSome = new JButton("Let's Make Some Puzzles!");
		btnLetsMakeSome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabStrip.setSelectedIndex(1);
			}
		});
		btnLetsMakeSome.setBounds(138, 295, 228, 23);
		panelWelcome.add(btnLetsMakeSome);

		//label welcome header
		JLabel lblWelcomeHeader = new JLabel("Crosswords Admin");
		lblWelcomeHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		lblWelcomeHeader.setBounds(35, 25, 458, 29);
		panelWelcome.add(lblWelcomeHeader);

		//label welcome instructions
		JLabel lblWelcomeInstructions = new JLabel("<html><b>Instructions</b><br>1) Click the button below to get started.<br>2) Configure your puzzle options.<br>3) Generate and preview puzzles.<br>4) Choose a puzzle you wish to generate.<br>5) Click the generate puzzle button.<br>6) Print your puzzle and enjoy!</html>");
		lblWelcomeInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeInstructions.setForeground(Color.DARK_GRAY);
		lblWelcomeInstructions.setBounds(0, 100, 493, 142);
		panelWelcome.add(lblWelcomeInstructions);

		//------------------------------------------------------
		//----------------END-WELCOME PANEL---------------------
		//------------------------------------------------------
	}
}
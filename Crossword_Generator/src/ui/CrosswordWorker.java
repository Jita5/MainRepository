package ui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import core.API;
import core.Config;
import core.StopWatch;

public class CrosswordWorker extends SwingWorker<Void, Void>
{
	private CrosswordsGUI gui;
	
	public CrosswordWorker(CrosswordsGUI gui){
		this.gui = gui;
	}
	
    @SuppressWarnings("unchecked")
	protected Void doInBackground() throws InterruptedException
    {
    	//turn on loading gif
		gui.loadingIconLabel.setVisible(true);
		
		//check if configuration is valid
		if(gui.ValidConfiguration())
		{
			//track if successful
			Boolean success = true;
			
			//start timer
			StopWatch watch = StopWatch.start();
			
			//clear grid and resize
			gui.previewGrid.removeAll();
			gui.previewGrid.setLayout(new GridLayout(Config.GridRows, Config.GridColumns));
			
			//generate puzzle collection
			gui.puzzles = new ArrayList<API>();
			
			while(true){
				
				//check timer
				long passedTimeInSeconds = watch.time(TimeUnit.SECONDS);
				if(passedTimeInSeconds > Config.MaxAllowedTime){
					success = false;
					break;
				}
				
				//attempt to generate puzzle
				
					API api = new API(Config.GridRows, Config.GridColumns, Config.InputSource, Config.MinWordsPlaced, Config.NumPuzzlesToGenerate);
					api.GenerateCrosswordPuzzle();
					if(api.GetPlacedWordCount() >= Config.MinWordsPlaced){
						gui.puzzles.add(api);
					}	
				
				if(gui.puzzles.size() == Config.NumPuzzlesToGenerate){
					break;
				}
			}
			
			if(success){
				//populate puzzle select
				gui.panelPreview.remove(gui.selectPuzzleToPreview);
				gui.selectPuzzleToPreview = gui.GetPuzzleSelector();
				for(int i = 0;i<gui.puzzles.size();i++){
					gui.selectPuzzleToPreview.addItem(i+1);
				}
				gui.panelPreview.add(gui.selectPuzzleToPreview);
				
				//bind the 1st puzzle
				gui.BindGrid(gui.puzzles.get(0));
				
				//change to preview tab
				gui.tabStrip.setSelectedIndex(2);
			}
			else{
				JOptionPane.showMessageDialog(gui.frame,
					    "Unable to generate puzzles in the alloted time. Consider trying a new configuration.");
			}
		}
		else{
			JOptionPane.showMessageDialog(gui.frame, "Be sure to set all configuration values before attempting to generate puzzles.");
		}
		
		//turn off loading gif
		gui.loadingIconLabel.setVisible(false);

		return null;
    }

    protected void done()
    {
    	
    }
}

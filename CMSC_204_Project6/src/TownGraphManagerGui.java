import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * User interface supports functions to add town, add road, find paths, and read data from file
 * 
 * @author Derek Luong
 *
 */
public class TownGraphManagerGui extends Application {
	
	private TownGraphManager townGraphManager;

	@Override
	public void start(Stage stage) throws Exception {
		townGraphManager = new TownGraphManager();
		ArrayList<String> allTowns = townGraphManager.allTowns();
		
		VBox mainPane = new VBox(10);
		
		// town pane
		GridPane townPane = new GridPane();
		townPane.setHgap(5);
		townPane.setVgap(5);
		
		Label townLbl = new Label("Town Name:");
		townPane.add(townLbl, 1, 0);
		
		TextField townNameTxt = new TextField();
		townPane.add(townNameTxt, 2, 0);
		
		Button addTownBtn = new Button("Add _Town");
		addTownBtn.setTooltip(new Tooltip("Add Town into application."));
		addTownBtn.setMnemonicParsing(true);
		townPane.add(addTownBtn, 4, 0);
		
		TitledPane townTitle = new TitledPane("Add Town",
				townPane);
		townTitle.setCollapsible(false);
		mainPane.getChildren().add(townTitle);
		
		// road add pane
		GridPane roadPane = new GridPane();
		roadPane.setHgap(5);
		roadPane.setVgap(5);
		
		Label roadLbl = new Label("Road Name:");
		roadPane.add(roadLbl, 2, 0);
		
		TextField roadNameTxt = new TextField();
		roadPane.add(roadNameTxt, 3, 0);
		
		Label roadLbl2 = new Label("Select towns for road");
		roadPane.add(roadLbl2, 3, 3);
		
		ComboBox<String> townFromAddCbo = new ComboBox<String>();
		townFromAddCbo.getItems().addAll(allTowns);
		roadPane.add(townFromAddCbo, 0, 4);
		
		ComboBox<String> townToAddCbo = new ComboBox<String>();
		townToAddCbo.getItems().addAll(allTowns);
		roadPane.add(townToAddCbo, 1, 4);

		Label roadLbl3 = new Label("Distance");
		roadPane.add(roadLbl3, 2, 4);
		
		TextField roadDistanceTxt = new TextField();
		roadPane.add(roadDistanceTxt, 3, 4);
		
		Button addRoadBtn = new Button("Add _Road");
		addRoadBtn.setTooltip(new Tooltip("Add Road into application."));
		addRoadBtn.setMnemonicParsing(true);
		roadPane.add(addRoadBtn, 4, 4);
		
		TitledPane roadTitle = new TitledPane("Add Road",
				roadPane);
		roadTitle.setCollapsible(false);
		mainPane.getChildren().add(roadTitle);
		
		// path pane
		GridPane pathPane = new GridPane();
		pathPane.setHgap(5);
		pathPane.setVgap(5);
		
		Label pathLbl1 = new Label("Find Connection From");
		pathPane.add(pathLbl1, 0, 1);
		
		ComboBox<String> townFromSearchCbo = new ComboBox<String>();
		townFromSearchCbo.getItems().addAll(allTowns);
		pathPane.add(townFromSearchCbo, 1, 1);
		
		Label pathLbl2 = new Label("To");
		pathPane.add(pathLbl2, 2, 1);
		
		ComboBox<String> townToSearchCbo = new ComboBox<String>();
		townToSearchCbo.getItems().addAll(allTowns);
		pathPane.add(townToSearchCbo, 3, 1);

		Button findConnectionBtn = new Button("Find _Connection");
		findConnectionBtn.setTooltip(new Tooltip("Find Conection between 2 towns."));
		findConnectionBtn.setMnemonicParsing(true);
		pathPane.add(findConnectionBtn, 4, 1);
		
		TextArea outputTxt = new TextArea();
		outputTxt.setPrefRowCount(15);
		outputTxt.setPrefColumnCount(100);
		outputTxt.setWrapText(true);
		outputTxt.setPrefWidth(150);
		outputTxt.setEditable(false);
		pathPane.add(outputTxt, 0, 3, 6, 3);
		
		TitledPane pathTitle = new TitledPane("Find Connection",
				pathPane);
		pathTitle.setCollapsible(false);
		mainPane.getChildren().add(pathTitle);
		
		// button pane
		GridPane buttonPane = new GridPane();
		buttonPane.setHgap(5);
		buttonPane.setVgap(5);
		
		Button readFileBtn = new Button("Read _File");
		readFileBtn.setTooltip(new Tooltip("Read data from file."));
		readFileBtn.setMnemonicParsing(true);
		buttonPane.add(readFileBtn, 3, 1);
		
		Button exitBtn = new Button("E_xit");
		exitBtn.setTooltip(new Tooltip("Exit"));
		exitBtn.setMnemonicParsing(true);
		buttonPane.add(exitBtn, 4, 1);
		
		mainPane.getChildren().add(buttonPane);
		
		addTownBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
            public void handle(ActionEvent e) {
    			String townName = townNameTxt.getText();
    			if (townName == null || townName.length() == 0) {
    				showAlertMessage(AlertType.ERROR, "Add Town Error", "Town Name must not be blank.");
    				return;
    			}
    			townGraphManager.addTown(townName);
    			townNameTxt.setText("");
    			// Reload road comboboxes 
                ArrayList<String> allTowns = townGraphManager.allTowns();
                townFromAddCbo.getItems().setAll(allTowns);
                townToAddCbo.getItems().setAll(allTowns);
                townFromSearchCbo.getItems().setAll(allTowns);
                townToSearchCbo.getItems().setAll(allTowns);
    		}
        });
		
		addRoadBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
            public void handle(ActionEvent e) {
    			String roadName = roadNameTxt.getText();
    			if (roadName == null || roadName.length() == 0) {
    				showAlertMessage(AlertType.ERROR, "Add Road Error", "Road Name must not be blank.");
    				return;
    			}
    			String townFrom = townFromAddCbo.getValue();
    			if (townFrom == null || townFrom.length() == 0) {
    				showAlertMessage(AlertType.ERROR, "Add Road Error", "Town From must not be empty.");
    				return;
    			}
    			String townTo = townToAddCbo.getValue();
    			if (townTo == null || townTo.length() == 0) {
    				showAlertMessage(AlertType.ERROR, "Add Road Error", "Town To must not be empty.");
    				return;
    			}
    			Integer distance = 1;
    			String distanceStr = roadDistanceTxt.getText();
    			try {
    				distance = Integer.parseInt(distanceStr);
    			} catch (Exception ex) {
    				distance = 1;
    			}
    			townGraphManager.addRoad(townFrom, townTo, roadName, distance);
    			roadNameTxt.setText("");
    			townFromAddCbo.setValue("");
    			townToAddCbo.setValue("");
    			roadDistanceTxt.setText("");
    			// Reload road comboboxes 
                ArrayList<String> allTowns = townGraphManager.allTowns();
                townFromAddCbo.getItems().setAll(allTowns);
                townToAddCbo.getItems().setAll(allTowns);
                townFromSearchCbo.getItems().setAll(allTowns);
                townToSearchCbo.getItems().setAll(allTowns);
    		}
        });
		
		findConnectionBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
            public void handle(ActionEvent e) {
    			String townFrom = townFromSearchCbo.getValue();
    			if (townFrom == null || townFrom.length() == 0) {
    				showAlertMessage(AlertType.ERROR, "Find Connection Error", "Town From must not be empty.");
    				return;
    			}
    			String townTo = townToSearchCbo.getValue();
    			if (townTo == null || townTo.length() == 0) {
    				showAlertMessage(AlertType.ERROR, "Find Connection Error", "Town To must not be empty.");
    				return;
    			}
    			ArrayList<String> paths = townGraphManager.getPath(townFrom, townTo);
    			if (paths != null && paths.size() > 0) {
    				String result = "";
    				for (String path : paths) {
    					result += path + "\n";
    				}
    				outputTxt.setText(result);
    			}
    		}
        });
		
		readFileBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
            public void handle(ActionEvent e) {
    			FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("Select Input File");
            	File inputFile = fileChooser.showOpenDialog(stage);
                if (inputFile != null) {
                	try {
						// FileReader reads text files in the default encoding.
						FileReader fileReader = new FileReader(inputFile);
						// Always wrap FileReader in BufferedReader.
	                    BufferedReader bufferedReader = new BufferedReader(fileReader);

	                    String line = null;
	                    while((line = bufferedReader.readLine()) != null) {
	                    	String[] parts = line.split(";");
	                    	String roadInfo = parts[0];
	                    	String fromTown = parts[1];
	                    	String toTown = parts[2];
	                    	String[] roadParts = roadInfo.split(",");
	                    	String roadName = roadParts[0];
	                    	Integer roadWeight = Integer.parseInt(roadParts[1]);
	                    	townGraphManager.addTown(fromTown);
	                    	townGraphManager.addTown(toTown);
	                    	townGraphManager.addRoad(fromTown, toTown, roadName, roadWeight);
	                    }   

	                    // Always close files.
	                    bufferedReader.close();
	                    fileReader.close();
	                    // Reload road comboboxes 
	                    ArrayList<String> allTowns = townGraphManager.allTowns();
	                    townFromAddCbo.getItems().setAll(allTowns);
	                    townToAddCbo.getItems().setAll(allTowns);
	                    townFromSearchCbo.getItems().setAll(allTowns);
	                    townToSearchCbo.getItems().setAll(allTowns);
					} catch (FileNotFoundException e1) {
						showAlertMessage(AlertType.ERROR, "Reading input file", e1.getMessage());
					} catch (IOException e1) {
						showAlertMessage(AlertType.ERROR, "Reading input file", e1.getMessage());
					}
                }
    		}
        });
		
		exitBtn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
            public void handle(ActionEvent e) {
    			Platform.exit();
             	System.exit(0);
    		}
        });
		
		Scene scene = new Scene(mainPane, 600, 600);
		stage.setScene(scene);
		// Set stage title and show the stage.
		stage.setTitle("Travelling Student");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void showAlertMessage(AlertType alertType, String title, String content) {
    	Alert alert = new Alert(alertType);
    	alert.setResizable(true);
    	alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
    }

}

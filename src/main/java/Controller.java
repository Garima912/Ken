import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller implements EventHandler{
    Pane welcomeScene;
    VBox gameScene;
    Stage primaryStage;
    Menu newLookMenu;
    Button startGameBtn;

    public Controller(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.setSceneUp();
        startGameBtn.setOnAction(this);

        //TODO: delete these 2 lines of code. They make it so
        // I don't have to click to go tho game screen
        primaryStage.setScene(new Scene(gameScene,Util.width,Util.height));
        GameSceneController gameSceneController = new GameSceneController(gameScene);
    }


    private void setSceneUp(){
        welcomeScene = new AnchorPane();
        gameScene = new VBox();
        newLookMenu = new Menu("New Look");
        newLookMenu.setId("newLookMenu");


        startGameBtn = new Button();
        startGameBtn.setText("Start Game");
        welcomeScene.getChildren().add(startGameBtn);
        AnchorPane.setLeftAnchor(startGameBtn, 325.0);
        AnchorPane.setTopAnchor(startGameBtn, 225.0);


        welcomeScene.getChildren().add(this.createMenuBar());

        // create menuBar for main game scene, and add to scene
        MenuBar gameMenuBar = this.createMenuBar();
        gameMenuBar.getMenus().add(newLookMenu);
        gameScene.getChildren().add(gameMenuBar);

        primaryStage.setTitle("Keno Game");
        primaryStage.setScene(new Scene(welcomeScene, Util.width, Util.height));
        primaryStage.show();
    }

    //
    public void handle(Event event) {

        if (event.getSource() == startGameBtn){
            primaryStage.setScene(new Scene(gameScene,Util.width,Util.height));

        }
        else if (((MenuItem)event.getSource()).getId() == "rulesBtn"){
            displayGameInfo("Rules for the game:",Util.gameRules );
        }
        else if (((MenuItem)event.getSource()).getId() == "oddsBtn"){
            displayGameInfo("Odds of Winning the Game:",Util.oddsOfWinning);
        }

        else {
            primaryStage.close();
        }

    }

    private MenuBar createMenuBar(){
        // create menu items
        MenuItem rulesMenuItem = new MenuItem("Display the rules of the game");
        MenuItem oddsMenuItem = new MenuItem("Display the odds of winning");
        MenuItem exitMenuItem = new MenuItem("Exit game");

        rulesMenuItem.setId("rulesBtn");
        oddsMenuItem.setId("oddsBtn");
        exitMenuItem.setId("exitBtn");


        rulesMenuItem.setOnAction(this);
        oddsMenuItem.setOnAction(this);
        exitMenuItem.setOnAction(this);

        Menu welcomeMenu = new Menu("Menu");
        // add the menu items to menu
        welcomeMenu.getItems().addAll(rulesMenuItem, oddsMenuItem, exitMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(welcomeMenu);

        return menuBar;
        // commit

    }

    //Display the Rules and Odds of winning using an alert
    public void displayGameInfo(String message, String menuInfo){

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(message);
        alert.setContentText(menuInfo);
        alert.show();
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);

    }


}
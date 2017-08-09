package main.mvp.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class IViewImplementation extends Application implements IView{

	private Stage stage;
	private Parent root;
	private Scene scene;
	private MediaPlayer mediaplayer;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		root = FXMLLoader.load(getClass().getResource("IViewFXML.fxml"));
		
		scene = new Scene(root, 600, 600);
		scene.getStylesheets().add(getClass().getResource("css/IViewFXML.css").toExternalForm());
		stage.setTitle("MVP Alarm");
		stage.setScene(scene);
		stage.show();
	}
	
	/*
	 * Getters Setters
	 */
	public MediaPlayer getMediaplayer() {
		return mediaplayer;
	}
	
}

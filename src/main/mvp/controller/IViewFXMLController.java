package main.mvp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import main.mvp.model.TimeSaver;

public class IViewFXMLController {
	@FXML BorderPane root;
	@FXML Text hourText;
	@FXML Text minuteText;
	@FXML Text secondText;
	@FXML Text dotText;
	@FXML Button startButton;
	@FXML Button stopButton;
	@FXML Button replayButton;
	@FXML Label label;
	@FXML Spinner<Integer> hourSpinner;
	@FXML Spinner<Integer> minuteSpinner;
	@FXML Spinner<Integer> secondSpinner;
	private TimeSaver timesaver;
	private int hour, min, sec;
	private TimeCounter timecounter;
	private boolean counting;
	private MediaPlayer mediaplayer;
	
	
	public IViewFXMLController() {
		
	}
	
	public void initialize() {
		timesaver= new TimeSaver();
		hour = timesaver.getHour();
		min = timesaver.getMinute();
		sec = timesaver.getSecond();
		applyTimeToDisplay();
		applyTimeToSpinner();
		
		counting = false;
		Media media = new Media(getClass().getResource("/sound/railroad_crossing_bell-Brylon_Terry-1551570865.mp3").toExternalForm());
		mediaplayer = new MediaPlayer(media);
		
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ESCAPE)){
					Platform.exit();
				}
			}
		});
		System.out.println("INIT");
	}
	
	@FXML protected void startTimer(){
		mediaplayer.stop();
		if(!counting){
			setTimerValue();
			startCounting();
		}
	}
	
	@FXML protected void stopTimer(){
		mediaplayer.stop();
		stopCounting();
		resetTimerValue();
		applyTimeToDisplay();
	}
	
	@FXML protected void replayTimer(){
		stopCounting();
		resetTimerValue();
		startCounting();
	}
	
	private void setTimerValue(){
		hour = hourSpinner.getValue();
		min = minuteSpinner.getValue();
		sec = secondSpinner.getValue();
		
		timesaver.setTime(hour, min, sec);
		
		applyTimeToDisplay();
		
	}
	
	private void resetTimerValue(){
		hour = timesaver.getHour();
		min = timesaver.getMinute();
		sec = timesaver.getSecond();
	}
	private void startCounting(){
		timecounter = new TimeCounter(this);
		timecounter.start();
		counting = true;
	}
	
	private void stopCounting(){
		if(timecounter != null){
			timecounter.terminate();
		}
	}
	public void applyTimeToDisplay(){
		if(hour < 10){
			hourText.setText("0" + Integer.toString(hour));
		}else{
			hourText.setText(Integer.toString(hour));
		}
		
		if(min < 10){
			minuteText.setText("0" + Integer.toString(min));
		}else{
			minuteText.setText(Integer.toString(min));
		}
		
		if(sec < 10){
			secondText.setText("0" + Integer.toString(sec));
		}else{
			secondText.setText(Integer.toString(sec));
		}
	}
	
	public void applyTimeToSpinner(){
		hourSpinner.getValueFactory().setValue(hour);
		minuteSpinner.getValueFactory().setValue(min);
		secondSpinner.getValueFactory().setValue(sec);
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	public boolean isCounting() {
		return counting;
	}

	public void setCounting(boolean counting) {
		this.counting = counting;
	}

	public void playAlarm() {
		mediaplayer.play();
	}
	
	public List<File> getFileInDirectory(String dirPath){
		List<File> filelist = new ArrayList<>();
		for(File file : new File(dirPath).listFiles()){
			if(!file.isDirectory()){
				filelist.add(file);
			}
		}
		return filelist;
	}
}

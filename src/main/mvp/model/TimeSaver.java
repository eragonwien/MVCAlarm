package main.mvp.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TimeSaver {
	private int hour;
	private int minute;
	private int second;
	private String saverpath;
	
	public TimeSaver() {
		this.saverpath = "save";
		readTime();
	}
	
	public TimeSaver(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	
	public void setTime(int hour, int minute, int second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		saveTime();
	}
	
	public String getSaverpath() {
		return saverpath;
	}

	public void setSaverpath(String saverpath) {
		this.saverpath = saverpath;
	}
	
	public void saveTime(){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(getSaverpath(), "UTF-8");
			writer.println(Integer.toString(getHour()));
			writer.println(Integer.toString(getMinute()));
			writer.println(Integer.toString(getSecond()));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
	}
	
	public void readTime(){
		if(!new File(saverpath).exists()){
			setTime(0, 0, 0);
			saveTime();
		}
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(saverpath)));
			setHour(Integer.parseInt(reader.readLine()));
			setMinute(Integer.parseInt(reader.readLine()));
			setSecond(Integer.parseInt(reader.readLine()));
		} catch (Exception e) {
			e.printStackTrace();
			setTime(0, 0, 0);
		}finally {
			System.out.println(toString());
		}
	}
	
	@Override
	public String toString() {
		return "MVPTimer [hour=" + hour + ", minute=" + minute + ", second=" + second + "]";
	}
}

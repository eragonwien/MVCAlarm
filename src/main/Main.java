package main;

import javafx.application.Application;
import main.mvp.view.IViewImplementation;

public class Main{

  public static void main(String[] args) {
    Application.launch(((Application)new IViewImplementation()).getClass());
  }
}
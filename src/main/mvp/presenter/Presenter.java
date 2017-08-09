package main.mvp.presenter;

import main.mvp.view.IView;

public class Presenter implements IPresenter{

	private IView view;
	
	public Presenter(IView view) {
		// TODO Auto-generated constructor stub
		this.view = view;
	}
	
}

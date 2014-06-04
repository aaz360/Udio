package eu.udio.ui;

import com.google.gwt.core.client.EntryPoint;

public class ApplicationController implements EntryPoint{
	private MainView mainView;
	
	@Override
	public void onModuleLoad() {
		mainView = new MainView("Udio");
		mainView.draw();
		
	}

}

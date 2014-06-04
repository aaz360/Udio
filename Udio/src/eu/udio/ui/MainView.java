package eu.udio.ui;

import xq.gwt.ui.widgets.feedback.FeedbackPresenter;
import xq.gwt.ui.widgets.feedback.FeedbackService;

import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

public class MainView extends VLayout implements FeedbackPresenter{	
	private HStack displayStack = new HStack();
	private ToolStrip topBar = new ToolStrip(); 
	private ToolStrip toolBar = new ToolStrip();
	private Label feedbackLabel;
	private Button upload = new Button("Upload");
	
	public MainView(String applicationTitle){		
		setWidth100();
		setHeight100();
		setStyleName("tabSetContainer");
				
        topBar.setHeight(33);
        topBar.setWidth100();
        //Add Image here
        topBar.addSpacer(6);
        Label title = new Label(applicationTitle);
        title.setWidth(300);
        topBar.addMember(title);
        topBar.addFill();
        topBar.setStyleName("topBar");
        
        toolBar.addMember(upload);
        toolBar.addFill();
        toolBar.setWidth100();
        toolBar.setStyleName("topBar");
        
		setMembers(topBar, toolBar,displayStack);
		FeedbackService.setFeedbackPresenter(this);
	}
		
	public ToolStrip getTopBar(){
		return topBar;
	}
		
	public ToolStrip getToolBar() {
		return toolBar;
	}
	
	public Button getUploadButton(){
		return upload;
	}

	public void displayView(Canvas view){
		Canvas[] members = displayStack.getMembers();
		for (Canvas member : members) {
			member.destroy();
		}
		
		displayStack.setMembers(view);
	}

	@Override
	public void showFeedback(String feedback) {
		if(feedback == null){
			hideFeedback();
			return;
		}
		
		if(feedbackLabel == null)
			feedbackLabel = buildFeedbackLabel();
		
		feedbackLabel.setContents(feedback);
		this.addChild(feedbackLabel);
		
	}

	private void hideFeedback(){
		if(feedbackLabel == null)
			return;
		feedbackLabel.setContents(null);
		this.removeChild(feedbackLabel);
	}
	
	private  Label buildFeedbackLabel(){
		Label feedBackLabel = new Label();
		feedBackLabel.setHeight(5);
		feedBackLabel.setWidth(250);
		feedBackLabel.setBackgroundColor("yellow");
		feedBackLabel.setLeft("45%");
		feedBackLabel.setTop(10);
		return feedBackLabel;
	}	
}

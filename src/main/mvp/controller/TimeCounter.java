package main.mvp.controller;

public class TimeCounter extends Thread{

	private IViewFXMLController controller;
	private boolean terminated;
	
	public TimeCounter(IViewFXMLController controller) {
		this.controller = controller;
		this.terminated = false;
	}


	@Override
	public void run() {
		while(!terminated){
			try {
				Thread.currentThread();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			boolean timeover = lowerOneSecond();
			if(!terminated){
				controller.applyTimeToDisplay();
			}
			if(timeover){
				controller.playAlarm();
				break;
			}
		}
		controller.setCounting(false);
	}
	
	private boolean lowerOneSecond() {
		int hour = controller.getHour();
		int min = controller.getMin();
		int sec = controller.getSec();
		if(sec > 0){
			controller.setSec(--sec);
		}
		else if(min > 0){
			controller.setMin(--min);
			controller.setSec(59);
		}
		else if(hour > 0){
			controller.setHour(--hour);
			controller.setMin(59);
		}
		return (hour == 0 && min == 0 && sec == 0);
	}


	public void terminate(){
		this.terminated = true;
	}
}

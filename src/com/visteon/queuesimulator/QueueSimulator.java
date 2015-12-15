package com.visteon.queuesimulator;

public class QueueSimulator {

	public static void main(String[] args) {
		
		// main arguments
		// number of employees
		// randomeTimeWhenToGo	
		// time to process
		// retry time
		// toleratedQueueSize
		// visibitly of the queue
		// is concious how long the queue will take and if it's worth to go back
		// supervised when to go
		// visibe queue size limit
		// standing queue size limit
		
		
		DataCollector collector=new DataCollector();
		Model model=new Model(collector);
		MainWindow window=new MainWindow(model);
		window.show();

	}

}

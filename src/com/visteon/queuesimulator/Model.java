package com.visteon.queuesimulator;

import java.util.List;

public class Model {

	Simulation simulation;
	DataCollector mCollector;
	public Model(DataCollector collector) {
		mCollector=collector;
	}

	public void setUseApp(boolean selected) {
		// TODO Auto-generated method stub
		
	}

	public void start(ModelView panel) {
		Parameter param;
		int employees;
		int i;
		int processingTime;
		int toleratedQueueStanding;
		double timePlanned;
		for(employees=100;employees<301;employees+=100)			
			for(processingTime=1;processingTime<65;processingTime+=10)
			{
				for(toleratedQueueStanding=1;toleratedQueueStanding<10;toleratedQueueStanding+=1)
					for(timePlanned=0.7;timePlanned<1.5;timePlanned+=0.1)
					{
						for(i=0;i<10;i++)
						{
						param=new Parameter();
						param.numEmployees=employees;
						param.timeToProcess=processingTime;
						param.toleratedQueueStanding=toleratedQueueStanding;
						param.randomTimeWhenToGo=(int)Math.round(employees*processingTime*timePlanned);
						simulation= new Simulation(null,mCollector, param);
						simulation.start();
						
						param=new Parameter();
						param.numEmployees=employees;
						param.queueVisible=false;
						param.timeToProcess=processingTime;
						param.toleratedQueueStanding=toleratedQueueStanding;
						param.randomTimeWhenToGo=(int)Math.round(employees*processingTime*timePlanned);
						simulation= new Simulation(null,mCollector, param);
						simulation.start();
						}
					}
				

				for(i=0;i<10;i++)
				{
				param=new Parameter();
				param.superVisedGo=true;
				param.numEmployees=employees;
				param.timeToProcess=processingTime;
				param.toleratedQueueStanding=100;				
				param.randomTimeWhenToGo=1;
				simulation= new Simulation(null,mCollector, param);
				simulation.start();
				}
			}


	}

	public void stop() {
		simulation.stopSimulation();
		
	}

	

	public List<Employee> getEmployee() {
		return simulation.getOffice().getEmployee();
		
	}

}

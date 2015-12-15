package com.visteon.queuesimulator;

public class Parameter {
	// number of employees
	int randomTimeWhenToGo;
	int timeToProcess;
	int retryTimeout;
	int toleratedQueueVisible;
	int toleratedQueueStanding;
	int numEmployees;
	
	boolean queueVisible;
	boolean superVisedGo;
	Parameter()
	{
	
	timeToProcess=60;
	retryTimeout=60*5;
	toleratedQueueVisible=1;
	toleratedQueueStanding=10;
	numEmployees=200;
	randomTimeWhenToGo=numEmployees*timeToProcess;
	
	queueVisible=true;
	superVisedGo=false;
	}
}

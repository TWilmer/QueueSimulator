package com.visteon.queuesimulator;

import java.util.Random;

public class RandomParameter extends Parameter {
	
	RandomParameter()
	{
	
		Random rand=new Random();
		numEmployees=100+rand.nextInt(3)*100;
		timeToProcess=1+rand.nextInt(6)*10;
		randomTimeWhenToGo=numEmployees*timeToProcess*(int)((1+rand.nextInt(4))*0.5);		
		retryTimeout=60*(1+rand.nextInt(5));
		toleratedQueueVisible=1+rand.nextInt(numEmployees);
		toleratedQueueStanding=toleratedQueueVisible+rand.nextInt(numEmployees-toleratedQueueVisible);
		
		
		queueVisible=rand.nextInt()%2==0;		
		superVisedGo=rand.nextInt()%100==0;
	}
}

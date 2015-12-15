package com.visteon.queuesimulator;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.BufferUnderflowException;

public class DataCollector {
	OutputStreamWriter out;
	DataCollector()
	{
		try {
			FileOutputStream of=new FileOutputStream("/tmp/data.csv");
			//BufferedOutputStream bo =new BufferedOutputStream(of);
			out=new OutputStreamWriter(of);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized void collectData(Parameter param, Results result)
	{
		try {
			
			out.append(""+param.randomTimeWhenToGo+",");
			out.append(""+param.timeToProcess+",");
			out.append(""+param.retryTimeout+",");
			out.append(""+param.toleratedQueueVisible+",");
			out.append(""+param.toleratedQueueStanding+",");
			out.append(""+param.numEmployees+",");
			
			out.append(""+param.queueVisible+",");
			out.append(""+param.superVisedGo+",");
			
			out.append(""+result.totalTimeSpent+",");
			out.append(""+result.clientTimeSpent+",");
			out.append(""+result.serverTimeSpent+",");
			out.append(""+result.numberInterruptions+",");
			
			out.append(""+result.maxQueueLength+",");
			out.append(""+result.queueTime+",");
			out.append("\n");
			out.flush();
			/*
			System.out.println("Total Time"+result.totalTimeSpent);
			System.out.println("clientTimeSpent"+result.clientTimeSpent);
			System.out.println("serverTimeSpent"+result.serverTimeSpent);
			System.out.println("numberInterruptions"+result.numberInterruptions);
			System.out.println("maxQueueLength"+result.maxQueueLength);
			System.out.println("queueTime"+result.queueTime);
			*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}

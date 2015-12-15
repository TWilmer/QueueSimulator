package com.visteon.queuesimulator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.visteon.queuesimulator.Employee.EState;

public class Office {
	int mNumOfEmployees;
	int mWidthDm;
	
	boolean done;
    ArrayList<Employee> mEmployees;
    Queue<Employee> mQueue;
    Employee mCurrentServiced;
    int mCurrentServiceTime;
    int toFinish;
	private int quemax;
	
	private Results mResult;
	Parameter param;
    public Office(Parameter param) {
		this.param=param;
	}
	void fillOffice()
    {
    	mResult=new Results();
    	done=false;
    	mEmployees=new ArrayList<Employee>();
    	mCurrentServiceTime=0;
    	mQueue=new ArrayDeque<Employee>();
    	mWidthDm=(int)Math.round(Math.sqrt(param.numEmployees))*3;
    
    	
    	mNumOfEmployees=param.numEmployees;
    	int i;
    	int curX=3;
    	int curY=3;
    	Point queuePosition=new Point(1, 1);
    	for(i=0;i<mNumOfEmployees;i++)
    	{
    		Employee e=new Employee(this,curX,curY);
    		e.mDesiredQueue=queuePosition;
    		curX+=3;
    		if(curX>mWidthDm){
    			curX=3;
    			curY=curY+3;
    		}
    		
    		mEmployees.add(e);
    	}
    	quemax=0;
    	toFinish=mEmployees.size();
    	if(param.superVisedGo)
    	{
    		int time=0;
    		for (Employee employee : mEmployees) {
    			employee.whenToGo=time;
    			time+=param.timeToProcess;
    		}
    	}
    }
    public boolean simulate()
    {
    	if(done)
    		return true;
    	if(mQueue.size()>0 && mCurrentServiceTime==0)
    	{
    		mCurrentServiced=mQueue.remove();
    		mCurrentServiceTime=60;
    	}
    	if(mCurrentServiceTime>0)
    	{
    		mCurrentServiceTime--;
    		if(mCurrentServiceTime==0)
    		{
    			mCurrentServiced.serviced();
    		}
    	}
    	for (Employee employee : mEmployees) {
			employee.simulate();

			
		}
    	mResult.serverTimeSpent++;
    	
    	if(toFinish==0)
		{
			int timeSpent=0;
			for (Employee employee2 : mEmployees) {
				timeSpent+=employee2.timeToFinish;
				mResult.queueTime+=employee2.timeInQueue;
			}
			mResult.clientTimeSpent=timeSpent;
			mResult.maxQueueLength=quemax;
			mResult.totalTimeSpent=mResult.serverTimeSpent+mResult.clientTimeSpent;
			
			
			
			
			done=true;
		}
    	if(quemax<mQueue.size())
    		quemax=mQueue.size();
    	
    	return false;
    }
	public void waitInQueue(Employee employee) {
		mQueue.add(employee);
		
	}
	public int getQueuSize() {
		return mQueue.size();
	}
	public List<Employee> getEmployee() {

		return Collections.unmodifiableList(mEmployees);
	}
	public void finishedEmployee(){
		toFinish--;
	}
	public Results getResult() {
		// TODO Auto-generated method stub
		return mResult;
	}
}

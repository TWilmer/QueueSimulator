package com.visteon.queuesimulator;

import java.util.Random;

public class Employee {
	Point mDeskposition;
	Point mDesiredQueue;
	int toleratedMaxQueue;
	int whenToGo;
	Point mCurrentDestination;
	Point mCurrentPosition;
	int timeToFinish;
	int interruptions;
	int timeInQueue;
	

	enum EState {
		STATE_WAIT_FOR_GO,
		STATE_GOTO_QUEUE,
		STATE_WAIT_IN_QUEUE,
		STATE_GOTO_DESK,
		STATE_DONE,

	};
	boolean mServiced;

	EState mCurState;
	private Office mOffice;
	Random rand;
	private boolean mCanSeeQueue;


	public Employee(Office office,int x, int y) {
		mOffice = office;

		timeToFinish=0;
		mServiced=false;
		rand= new Random();
		interruptions=0;
		timeInQueue=0;

		mCurState=EState.STATE_WAIT_FOR_GO;
		whenToGo=rand.nextInt(mOffice.param.randomTimeWhenToGo);
		
		mCurrentPosition=new Point(x, y);			
		mDeskposition=new Point(x,y);
		mCanSeeQueue=mOffice.param.queueVisible;
	}
	void simulate()
	{
		switch (mCurState) {
		case STATE_DONE:			
			break;
		case STATE_WAIT_FOR_GO:
		{
			if(whenToGo>0)
				whenToGo--;
			else{
				mCurrentDestination=mDesiredQueue;
				mCurState=EState.STATE_GOTO_QUEUE;
				interruptions++;
			}
			break;
		}
		case STATE_GOTO_QUEUE:
		{
			if(mOffice.getQueuSize()<(mOffice.param.toleratedQueueVisible) || !mCanSeeQueue)
			{
				timeToFinish++;
				moveToDestination();
				if(mCurrentDestination.equals(mCurrentPosition))
				{
					if(mOffice.getQueuSize()<mOffice.param.toleratedQueueStanding)
					{
						mOffice.waitInQueue(this);
						mCurState=EState.STATE_WAIT_IN_QUEUE;
					}else{
						mCurState=EState.STATE_GOTO_DESK;
						mCurrentDestination=mDeskposition;
					}
				}	
			}else{
				mCurState=EState.STATE_WAIT_FOR_GO;
				whenToGo=rand.nextInt(mOffice.param.retryTimeout); // wait 5 minutes
			}

			break;
		}
		case STATE_GOTO_DESK:
		{
			timeToFinish++;
			moveToDestination();
			if(mCurrentDestination.equals(mCurrentPosition))
			{
				if(mServiced)
				{
					mOffice.finishedEmployee();
					mCurState=EState.STATE_DONE;
				}else{
					mCurState=EState.STATE_WAIT_FOR_GO;
					whenToGo=rand.nextInt(mOffice.param.retryTimeout); // wait 5 minutes
				}
			}			
		}
		break;
		case STATE_WAIT_IN_QUEUE:
			timeToFinish++;
			timeInQueue++;
			break;
		default:
			break;
		}
	}
	private void moveToDestination() {
		int x=mCurrentPosition.diffX(mCurrentDestination);
		int y=mCurrentPosition.diffY(mCurrentDestination);
		if(Math.abs(x)>Math.abs(y))
		{
			if(x>0)
				move(1,0);
			else
				move(-1,0);

		}else{
			if(y>0)
				move(0,1);
			else
				move(0,-1);
		}


	}
	private void move(int i, int j) {

		mCurrentPosition.move(i,j);


	}
	void serviced()
	{
		mCurrentDestination=mDeskposition;
		mServiced=true;
		mCurState=EState.STATE_GOTO_DESK;
	}
	public boolean isServiced() {
		// TODO Auto-generated method stub
		return mServiced;
	}
	public int getX() {
		return mCurrentPosition.x;
	}
	public int getY() {
		return mCurrentPosition.y;
	}
	public void dump() {
		//System.out.println("E: "+mCurrentPosition.x+" "+mCurrentPosition.y);

	}
}

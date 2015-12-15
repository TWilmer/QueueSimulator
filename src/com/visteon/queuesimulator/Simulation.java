package com.visteon.queuesimulator;

import javax.swing.SwingUtilities;

public class Simulation extends Thread {

	ModelView mView;
	Office mOffice;
	private boolean mRun;
	 DataCollector mCollector;
	 Parameter mParam;
	public Simulation(ModelView v, DataCollector collector, Parameter param) {
		mView=v;
		mOffice=new Office(param);
		mRun=true;
		mOffice.fillOffice();
		this.mCollector=collector;
		mParam=param;
	}

	@Override
	public void run()
	{
		int count=0;
		while(mRun)
		{
			mRun=!mOffice.simulate();
			count++;
			if(mView!=null)
			{

				if(count%100==0 || !mRun)
				{
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {

							mView.show();
						}
					});
				}
				if(false)
				{
					try {
						this.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		mCollector.collectData(mParam, mOffice.getResult());
	}

	public void stopSimulation()
	{
		interrupt();
		mRun=false;
	}

	public Office getOffice() { 
		return mOffice;
	}
}

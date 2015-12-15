package com.visteon.queuesimulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.List;

import javax.swing.JPanel;

public class ModelView extends JPanel {

	Model mModel;
	
	public ModelView() {
		super();
		setMinimumSize(new Dimension(100,100));
		setBackground(new java.awt.Color(255, 255, 255));
	}
	void setModel(Model m)
	{
		mModel=m;
	}

	public void show() {		
		repaint();		
	}
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, 100, 100);
		if(mModel!=null)
		{
			List<Employee> e = mModel.getEmployee();
			for (Employee employee : e) {
				if(employee.isServiced())
				{
					g.setColor(Color.GREEN);
				}else{
					g.setColor(Color.RED);
				}

				g.drawRect(employee.getX()*4, employee.getY()*4, 2, 2);
				employee.dump();
			}
		}
	
	}

}

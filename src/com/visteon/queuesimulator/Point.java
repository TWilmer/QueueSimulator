package com.visteon.queuesimulator;

public class Point {
	public Point(int curX, int curY) {
		x=curX;
		y=curY;
	}
	int x;
	int y;
	public void move(int i, int j) {
		x=x+i;
		y=y+j;
		
	}
	public int diffY(Point b) {
		return b.y-y;

	}
	public int diffX(Point b) {
		return b.x-x;

	}	

	boolean equals(Point b)
	{
		return b.x==x && b.y==y;
	}
}	

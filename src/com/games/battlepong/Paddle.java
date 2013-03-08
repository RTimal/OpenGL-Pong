package com.games.battlepong;


public class Paddle extends PongObject{
	float height = 10;
	float width=190;
	
	public Paddle(){
		vel.xvalue=500;
		vel.yvalue=500;
		position.xvalue=0;
		position.yvalue=0;
		height=10;
		width=190;
	}
	

}
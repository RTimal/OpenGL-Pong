
package com.games.battlepong; 
public class Point extends PongObject{
	public Point(){
		vel.xvalue=500;
		vel.yvalue=500;
		position.xvalue=0;
		position.yvalue=0;
	}
	
	public void updatePosition(float time, boolean collided){
		
		if(collided==false){//if no collision
		//reflection
		position.xvalue+=vel.xvalue*time;
		position.yvalue+=vel.yvalue*time;
		if(position.xvalue>480){
			position.xvalue=480;
			vel.xvalue=-(vel.xvalue);
		}
		
		if(position.yvalue>800){
			position.yvalue=800;
			vel.yvalue=-(vel.yvalue);
		}
		
		if(position.yvalue<0){
		position.yvalue=0;
		vel.yvalue=-(vel.yvalue);
		}
		
		if(position.xvalue<0){
		position.xvalue=0;
		vel.xvalue=-(vel.xvalue);
		}
	}else if (collided==true)
	{
		position.xvalue=40;
		vel.xvalue=-(vel.xvalue);
		//vel.yvalue=-(vel.yvalue);
	}
	}

}

package com.games.battlepong;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import android.view.MotionEvent;
import android.util.Log;


public class Screen {
	float deltaTime;//time from last screen update
	float time; //current time
	ArrayList<Point> points = new ArrayList<Point>();
	ArrayList<Paddle> paddles = new ArrayList<Paddle>();
	touchHandler touch;
	int pointerids[]={0,0};


	Paddle paddle;
	GL10 gl;
	Point curpoint;

	public Screen(GL10 gl, touchHandler touchevents){
		//create new points, add them to array list
		pointerids[0]=999;
		pointerids[1]=999;
		points.add(new Point());
		points.add(new Point());
		points.add(new Point());
		//create new paddle, add them to array list
		paddles.add(new Paddle());
		paddles.add(new Paddle());//second paddle
		paddles.get(0).position.xvalue=30;
		paddles.get(0).position.yvalue=300;
		paddles.get(1).position.xvalue=450;
		paddles.get(1).position.yvalue=300;
		//set initial x value of paddle
		//set velocities of points 1 and 2
		points.get(1).vel.xvalue=200;
		points.get(1).vel.yvalue=250;
		points.get(2).vel.xvalue=300;
		points.get(2).vel.yvalue=200;
		this.gl=gl;
		this.touch=touchevents;
	}
	
	public void MultiUpdate(){
		
		
	}
	
	public void update(float time){
		points.get(0).updatePosition(time,false);//no collisions yet
		points.get(1).updatePosition(time,false);
		points.get(2).updatePosition(time,false);
		
		this.processTouchSlider();	
		//check if any points intersect with the box, then update again
		//check if all points in box
		if(points.get(0).position.xvalue>=paddles.get(0).position.xvalue&&(points.get(0).position.xvalue<=paddles.get(0).position.xvalue+paddles.get(0).height)&&(points.get(0).position.yvalue>=paddles.get(0).position.yvalue&&(points.get(0).position.yvalue<=paddles.get(0).position.yvalue+paddles.get(0).width))){
			points.get(0).updatePosition(time,true);
		}
		
		
		if(points.get(1).position.xvalue>=paddles.get(0).position.xvalue&&(points.get(1).position.xvalue<=paddles.get(0).position.xvalue+paddles.get(0).height)&&(points.get(1).position.yvalue>=paddles.get(0).position.yvalue&&(points.get(1).position.yvalue<=paddles.get(0).position.yvalue+paddles.get(0).width))){
			points.get(1).updatePosition(time,true);
		}
		
		
		if(points.get(2).position.xvalue>=paddles.get(0).position.xvalue&&(points.get(2).position.xvalue<=paddles.get(0).position.xvalue+paddles.get(0).height)&&(points.get(2).position.yvalue>=paddles.get(0).position.yvalue&&(points.get(2).position.yvalue<=paddles.get(0).position.yvalue+paddles.get(0).width))){
			points.get(2).updatePosition(time,true);
		}
		
	}
	
public void processTouchSlider(){
	//only process 20 events per frame
	for(int i =0;i<=3;i++){
			if(touch.size()>1){
				MotionEvent thisevent=touch.removeTouch();
				//if action is down, generate new pointer ids

				switch (thisevent.getActionMasked()) {
				case MotionEvent.ACTION_DOWN:{
					//Log.d("Down","getActionIndex()="+thisevent.getActionIndex());
					//Log.d("Pressure","getPressure"+thisevent.getPressure());
					//Log.d("PointerCount","getPointerCount()"+thisevent.getPointerCount());
					if(thisevent.getX()<=300)
					{pointerids[0]=thisevent.getPointerId(thisevent.getActionIndex());
					Log.d("Down<300","pointerID()="+pointerids[0]);}
					

					if(thisevent.getX()>=300){pointerids[1]=thisevent.getPointerId(thisevent.getActionIndex());
					Log.d("Down>300","pointerID()="+pointerids[1]);
					}
					break;
				}
		
				
				case MotionEvent.ACTION_POINTER_DOWN:{
					Log.d("ActionPointerDown","getActionIndex()="+thisevent.getActionIndex());
					//Log.d("Pressure","getPressure"+thisevent.getPressure());
					Log.d("PointerCount","getPointerCount()"+thisevent.getPointerCount());
					//if pointer count is 2, and first pointer is set, set id of second pointer
					if(thisevent.getX(thisevent.getActionIndex())>300){pointerids[1]=thisevent.getPointerId(thisevent.getActionIndex());
					Log.d("Downpointer>300","actionpointerdown");
					}
					
					if(thisevent.getX(thisevent.getActionIndex())<300){pointerids[0]=thisevent.getPointerId(thisevent.getActionIndex());
					Log.d("Downpointer<300","actionpointerdown");
					}
					//if pointer count is 2 and second pointer is set, set id of first pointer
				}
				
				case MotionEvent.ACTION_MOVE: {
				   // Log.d("COORDS","  getActionIndex()="+thisevent.getActionIndex()); 
					//Log.d("Pressure","getPressure"+thisevent.getPressure());
					if(pointerids[1]!=999){
						paddles.get(1).position.yvalue=800-thisevent.getY(thisevent.findPointerIndex(pointerids[1]))*(2);}
					
					if(pointerids[0]!=999){
						paddles.get(0).position.yvalue=800-thisevent.getY(thisevent.findPointerIndex(pointerids[0]))*(2);
					}
				    break;
				   }
				
				
				case MotionEvent.ACTION_UP:{
			//Log.d("ActionUp","getActionIndex()="+thisevent.getActionIndex())
					if (thisevent.getActionIndex()==thisevent.findPointerIndex(pointerids[0])){
					pointerids[0]=999;	
				}
					
					if(thisevent.getActionIndex()==thisevent.findPointerIndex(pointerids[1])){
						pointerids[1]=999;
					}
					break;
				}
				    
				
				case MotionEvent.ACTION_POINTER_UP:{
					if (thisevent.getActionIndex()==thisevent.findPointerIndex(pointerids[0])){
						pointerids[0]=999;	
					}
						
						if(thisevent.getActionIndex()==thisevent.findPointerIndex(pointerids[1])){
							pointerids[1]=999;
						}
						break;
				}				
			}
				
		}
	}
}
	public void present(){
	 	gl.glClearColor(0,0,0,1);
    	gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    	gl.glMatrixMode(GL10.GL_PROJECTION);
    	gl.glLoadIdentity();
    	gl.glOrthof(0, 480, 0, 800, 1, -1);
    	gl.glMatrixMode(GL10.GL_MODELVIEW);
    	gl.glLoadIdentity();
    	//draw paddles
    	gl.glTranslatef(paddles.get(0).position.xvalue, paddles.get(0).position.yvalue, 0);
    	ByteBuffer bufferPaddle = ByteBuffer.allocateDirect(6*2*4);
    	bufferPaddle.order(ByteOrder.nativeOrder());
    	FloatBuffer paddleFloatBuffer=bufferPaddle.asFloatBuffer();
    	float [] quadvertices={0,0,10,0,0,100,10,100,0,200,10,200};
    	paddleFloatBuffer.clear();
    	paddleFloatBuffer.put(quadvertices);
    	paddleFloatBuffer.flip();
    	gl.glColor4f(.3f, .1f, .5f, .0f);
    	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    	gl.glVertexPointer(2,GL10.GL_FLOAT,0,paddleFloatBuffer);
    	gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,6);
    	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    	gl.glLoadIdentity();
    
    	gl.glTranslatef(paddles.get(1).position.xvalue, paddles.get(1).position.yvalue, 0);
    	bufferPaddle.order(ByteOrder.nativeOrder());
    	paddleFloatBuffer.clear();
    	paddleFloatBuffer.put(quadvertices);
    	paddleFloatBuffer.flip();
    	gl.glColor4f(.3f, .1f, .5f, .0f);
    	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    	gl.glVertexPointer(2,GL10.GL_FLOAT,0,paddleFloatBuffer);
    	gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,6);
    	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    	

    	gl.glLoadIdentity();
    	
    	//draw points
    	ByteBuffer buffer = ByteBuffer.allocateDirect(1*2*4);
    	buffer.order(ByteOrder.nativeOrder());
    	FloatBuffer floatBuffer = buffer.asFloatBuffer();
    	gl.glPointSize(10);
    	float[] vertices = {0.0f,0.0f};
    	floatBuffer.clear();
    	floatBuffer.put(vertices);
    	floatBuffer.flip();
    	
    	gl.glPushMatrix();
      	gl.glColor4f(.5f, .5f, .1f, .0f);
    	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    	gl.glVertexPointer(2, GL10.GL_FLOAT, 0,floatBuffer);
    	gl.glTranslatef(points.get(0).position.xvalue,points.get(0).position.yvalue,0);
    	gl.glDrawArrays(GL10.GL_POINTS,0,1);
    	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

    	gl.glPopMatrix();
    	gl.glPushMatrix();
    	gl.glColor4f(.5f, .5f, .1f, .0f);
    	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    	gl.glVertexPointer(2, GL10.GL_FLOAT, 0,floatBuffer);
    	gl.glTranslatef(points.get(1).position.xvalue,points.get(1).position.yvalue,0);
    	gl.glDrawArrays(GL10.GL_POINTS,0,1);
    	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    	
    	gl.glPopMatrix();
    	gl.glPushMatrix();
      	gl.glColor4f(.5f, .5f, .1f, .0f);
       	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    	gl.glVertexPointer(2, GL10.GL_FLOAT, 0,floatBuffer);
    	gl.glTranslatef(points.get(2).position.xvalue,points.get(2).position.yvalue,0);
    	gl.glDrawArrays(GL10.GL_POINTS,0,1);
    	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    	
    	gl.glPopMatrix();
	}
}
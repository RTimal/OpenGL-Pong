package com.games.battlepong;
import java.util.ArrayList;
import android.view.MotionEvent;
import java.util.Queue;
import java.util.LinkedList;


public class touchHandler {
	Queue<MotionEvent> touchevents = new LinkedList<MotionEvent>();
	public touchHandler(){
	}
	
	public void addTouch(MotionEvent event){
		touchevents.add(event);
	}
	
	public MotionEvent removeTouch(){
		return touchevents.remove();
	}
	
	
	public int size(){
		return touchevents.size();
	}
}

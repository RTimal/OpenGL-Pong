package com.games.battlepong;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class gameController extends Activity implements GLSurfaceView.Renderer  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){String value = extras.getString("ip");}
        mGLView = new GLSurfaceView(this);
        mGLView.setRenderer(this);
        mGLView.requestFocus();
        setContentView(mGLView);
    }
    
    public boolean onTouchEvent(final MotionEvent event) {
    touch.addTouch(event);
            return true;
        }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }
    
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    	this.Currentscreen = new Screen(gl,touch);

    }

    public void onSurfaceChanged(GL10 gl, int w, int h) {
        gl.glViewport(0, 0, w, h);
    }

    public void onDrawFrame(GL10 gl) {
    	float deltaTime = System.nanoTime()/1000000000.0f - lastFrameTime;
    	lastFrameTime = System.nanoTime()/1000000000.0f;
       Currentscreen.update(deltaTime);
       Currentscreen.present();
    }
    
    Network network;
    touchHandler touch = new touchHandler();
    public GLSurfaceView mGLView;
    GL10 gl;
    Screen Currentscreen;
	float lastFrameTime=System.nanoTime()/1000000000.0f;
}

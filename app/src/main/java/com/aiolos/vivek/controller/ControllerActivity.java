package com.aiolos.vivek.controller;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ControllerActivity extends Activity {
    private GLSurfaceView mGLView;
    private MySensorEventListener mSensorListener;
    private SensorManager mSensorManager;
    private final MyGLRenderer mRenderer;

    public ControllerActivity() {
        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = new MyGLSurfaceView(this, mRenderer);
        mSensorListener = new MySensorEventListener(mRenderer, mGLView);
        addSensor();
        setContentView(mGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorListener);
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView.onResume();
    }

    private void addSensor() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mSensorListener.mAccel = 0.00f;
        mSensorListener.mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mSensorListener.mAccelLast = SensorManager.GRAVITY_EARTH;
    }
}

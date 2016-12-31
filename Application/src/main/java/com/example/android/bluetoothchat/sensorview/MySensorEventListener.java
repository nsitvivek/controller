package com.example.android.bluetoothchat.sensorview;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.opengl.GLSurfaceView;

public class MySensorEventListener implements SensorEventListener {

    public float mAccel; // acceleration apart from gravity
    public float mAccelCurrent; // current acceleration including gravity
    public float mAccelLast; // last acceleration including gravity
    private final MyGLRenderer mRenderer;
    private final GLSurfaceView mSurfaceView;
    float [] history = new float[3];
    String[] direction = {"NONE","NONE", "NONE"};

    public MySensorEventListener(MyGLRenderer renderer, GLSurfaceView surfaceView) {
        mRenderer = renderer;
        mSurfaceView = surfaceView;
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        algo2(se);
    }
    private void algo2(SensorEvent event){

        float xChange = history[0] - event.values[0];
        float yChange = history[1] - event.values[1];
        float zChange = history[2] - event.values[2];

        history[0] = event.values[0];
        history[1] = event.values[1];
        history[2] = event.values[2];

        if (xChange > 2){
            direction[0] = "LEFT";
            mRenderer.setAngle(-100);
            mSurfaceView.requestRender();
        }
        else if (xChange < -2){
            direction[0] = "RIGHT";
            mRenderer.setAngle(100);
            mSurfaceView.requestRender();
        }

        if (yChange > 2){
            direction[1] = "DOWN";
        }
        else if (yChange < -2){
            direction[1] = "UP";
        }

        if (zChange > 2){
            direction[1] = "FRONT";
            mRenderer.setZ(-3);
            mSurfaceView.requestRender();
        }
        else if (zChange < -2){
            direction[1] = "BACK";
            mRenderer.setZ(-4);
            mSurfaceView.requestRender();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

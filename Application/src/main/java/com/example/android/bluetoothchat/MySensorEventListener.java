package com.example.android.bluetoothchat;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class MySensorEventListener implements SensorEventListener {

    public float mAccel; // acceleration apart from gravity
    public float mAccelCurrent; // current acceleration including gravity
    public float mAccelLast; // last acceleration including gravity
    float [] history = new float[3];
    String[] direction = {"NONE","NONE", "NONE"};
    private final BluetoothChatFragment bluetoothChatFragment;

    public MySensorEventListener(BluetoothChatFragment fragment) {
        bluetoothChatFragment = fragment;
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
            bluetoothChatFragment.sendMessage("left");
        }
        else if (xChange < -2){
            direction[0] = "RIGHT";
            bluetoothChatFragment.sendMessage("right");
        }

        if (yChange > 2){
            direction[1] = "DOWN";
        }
        else if (yChange < -2){
            direction[1] = "UP";
        }

        if (zChange > 2){
            direction[1] = "FRONT";
            bluetoothChatFragment.sendMessage("front");
        }
        else if (zChange < -2){
            direction[1] = "BACK";
            bluetoothChatFragment.sendMessage("back");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
package de.andipaetzold.dodgeit.game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import de.andipaetzold.dodgeit.App;

public class InputEngine implements SensorEventListener {
    private static InputEngine ourInstance = new InputEngine();
    private final SensorManager sensorManager;
    private final Sensor accelerometer;
    private final Sensor magnetometer;

    public static InputEngine getInstance() {
        return ourInstance;
    }

    public InputEngine() {
        sensorManager = (SensorManager) App.getContext().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void resume() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public void pause() {
        sensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    float[] gravity;
    float[] geomagnetic;
    float[] orientation = new float[3];

    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                gravity = event.values;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                geomagnetic = event.values;
                break;
        }

        if (gravity != null && geomagnetic != null) {
            float R[] = new float[9];

            if (SensorManager.getRotationMatrix(R, null, gravity, geomagnetic)) {
                SensorManager.getOrientation(R, orientation);
            }
        }
    }

    public float[] getOrientation() {
        return orientation;
    }

}

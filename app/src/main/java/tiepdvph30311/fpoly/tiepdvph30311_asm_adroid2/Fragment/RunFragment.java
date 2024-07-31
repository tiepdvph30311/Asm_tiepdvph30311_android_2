package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao.HoatDongVanDongDao;

public class RunFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private boolean isSensorPresent;
    private int stepCount;
    private TextView stepCountTextView;
    private int userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_run, container, false);

        stepCountTextView = view.findViewById(R.id.stepCountTextView);

        // Get the user ID from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1); // Assuming -1 is an invalid user ID

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent = true;
        } else {
            isSensorPresent = false;
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isSensorPresent) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isSensorPresent) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            stepCount = (int) event.values[0];
            stepCountTextView.setText(String.valueOf(stepCount));
            // Save stepCount to the database
            saveStepCountToDatabase(stepCount);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }

    private void saveStepCountToDatabase(int stepCount) {
        // Call the method to save stepCount to the SQLite database
        HoatDongVanDongDao hoatDongVanDongDao = new HoatDongVanDongDao(getActivity());
        hoatDongVanDongDao.insertStepCount(stepCount, userId);
    }
}

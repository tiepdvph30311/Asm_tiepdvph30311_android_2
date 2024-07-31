package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Adapter.HoatDongVanDongAdapter;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao.HoatDongVanDongDao;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.HoatDongVanDong;

public class HoatDongVanDongFragment extends Fragment {

    private Button btnRunStop;
    private TextView tvSteps, tvTimer;
    private ListView lvResults;
    private boolean isRunning = false;
    private int stepCount = 0;
    private long startTime = 0L;
    private Handler handler = new Handler();
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private SensorEventListener stepDetector;

    private HoatDongVanDongDao hoatDongVanDongDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoat_dong_van_dong, container, false);

        btnRunStop = view.findViewById(R.id.btnRunStop);
        tvSteps = view.findViewById(R.id.tvSteps);
        tvTimer = view.findViewById(R.id.tvTimer);
        lvResults = view.findViewById(R.id.lvResults);

        hoatDongVanDongDao = new HoatDongVanDongDao(getContext());

        // Lấy ID người dùng từ SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int userId = sharedPreferences.getInt("user_id", -1); // -1 là giá trị mặc định nếu không có ID

        // Tải kết quả chỉ cho người dùng hiện tại
        loadResults(userId);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (isRunning) {
                    stepCount++;
                    tvSteps.setText("Steps: " + stepCount);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        btnRunStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    stopRun();
                } else {
                    startRun();
                }
            }
        });

        return view;
    }

    private void startRun() {
        isRunning = true;
        stepCount = 0;
        startTime = System.currentTimeMillis();
        btnRunStop.setText("Stop");
        sensorManager.registerListener(stepDetector, stepSensor, SensorManager.SENSOR_DELAY_UI);
        handler.postDelayed(timerRunnable, 0);
    }

    private void stopRun() {
        isRunning = false;
        btnRunStop.setText("Run");
        sensorManager.unregisterListener(stepDetector);
        handler.removeCallbacks(timerRunnable);
        // Lưu dữ liệu vào database
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int userId = sharedPreferences.getInt("user_id", -1);
        if (userId != -1) {
            hoatDongVanDongDao.insertStepCount(stepCount, userId);
        }
        loadResults(userId);
    }

    private void loadResults(int userId) {
        List<HoatDongVanDong> results = hoatDongVanDongDao.getAllHoatDongVanDong(userId);
        HoatDongVanDongAdapter adapter = new HoatDongVanDongAdapter(getContext(), results);
        lvResults.setAdapter(adapter);
    }

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            tvTimer.setText(String.format("Time: %02d:%02d", minutes, seconds));
            handler.postDelayed(this, 500);
        }
    };
}

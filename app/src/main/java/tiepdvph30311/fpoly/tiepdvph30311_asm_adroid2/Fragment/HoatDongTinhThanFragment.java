
//package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Fragment;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao.HoatDongTinhThanDao;
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao.UserDao;
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Adapter.HoatDongTinhThanAdapter;
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.HoatDongTinhThan;
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.User;
//
//public class HoatDongTinhThanFragment extends Fragment {
//
//    private EditText editTextContent;
//    private Button buttonAdd;
//    private ListView listViewActivities;
//    private HoatDongTinhThanDao hoatDongTinhThanDao;
//    private UserDao userDao;
//    private User currentUser;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_book, container, false);
//
//        editTextContent = view.findViewById(R.id.editTextContent);
//        buttonAdd = view.findViewById(R.id.buttonAdd);
//        listViewActivities = view.findViewById(R.id.listViewActivities);
//        hoatDongTinhThanDao = new HoatDongTinhThanDao(getContext());
//        userDao = new UserDao(getContext());
//
//        // Lấy user_id từ intent của MainActivity
//        int userId = getActivity().getIntent().getIntExtra("user_id", -1);
//        if (userId != -1) {
//            currentUser = userDao.getUserById(userId);
//        }
//
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String content = editTextContent.getText().toString();
//                if (!content.isEmpty() && currentUser != null) {
//                    HoatDongTinhThan hoatDongTinhThan = new HoatDongTinhThan();
//                    hoatDongTinhThan.setMaNguoiDung(currentUser.getId());
//
//                    // Lấy thời gian hiện tại
//                    String currentDateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
//                    hoatDongTinhThan.setNgayThucHien(currentDateAndTime);
//
//                    hoatDongTinhThan.setNoiDung(content);
//                    hoatDongTinhThanDao.addHoatDongTinhThan(hoatDongTinhThan);
//                    loadActivities();
//                }
//                editTextContent.setText("");
//            }
//        });
//
//        loadActivities();
//        return view;
//    }
//
//    private void loadActivities() {
//        List<HoatDongTinhThan> hoatDongTinhThanList = hoatDongTinhThanDao.getAllHoatDongTinhThan();
//        HoatDongTinhThanAdapter adapter = new HoatDongTinhThanAdapter(getContext(), hoatDongTinhThanList, currentUser != null ? currentUser.getUsername() : "Unknown");
//        listViewActivities.setAdapter(adapter);
//    }
//}
//
package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao.HoatDongTinhThanDao;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Adapter.HoatDongTinhThanAdapter;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.HoatDongTinhThan;

public class HoatDongTinhThanFragment extends Fragment {

    private EditText editTextContent;
    private Button buttonAdd;
    private ListView listViewActivities;
    private HoatDongTinhThanDao hoatDongTinhThanDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        editTextContent = view.findViewById(R.id.editTextContent);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        listViewActivities = view.findViewById(R.id.listViewActivities);
        hoatDongTinhThanDao = new HoatDongTinhThanDao(getContext());

        // Lấy ID người dùng từ SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int userId = sharedPreferences.getInt("user_id", -1);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editTextContent.getText().toString();
                if (!content.isEmpty() && userId != -1) {
                    HoatDongTinhThan hoatDongTinhThan = new HoatDongTinhThan();
                    hoatDongTinhThan.setMaNguoiDung(userId);

                    // Lấy ngày giờ hiện tại
                    String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    hoatDongTinhThan.setNgayThucHien(currentDate);

                    hoatDongTinhThan.setNoiDung(content);
                    hoatDongTinhThanDao.addHoatDongTinhThan(hoatDongTinhThan);
                    loadActivities(userId);
                }
            }
        });
        editTextContent.setText("");

        if (userId != -1) {
            loadActivities(userId);
        }
        return view;
    }

    private void loadActivities(int userId) {
        List<HoatDongTinhThan> hoatDongTinhThanList = hoatDongTinhThanDao.getAllHoatDongTinhThan(userId);
        HoatDongTinhThanAdapter adapter = new HoatDongTinhThanAdapter(getContext(), hoatDongTinhThanList);
        listViewActivities.setAdapter(adapter);
    }
}


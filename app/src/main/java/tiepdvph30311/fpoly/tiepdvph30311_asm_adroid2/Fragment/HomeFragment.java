package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.Message;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Adapter.MessageAdapter;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);

        loadMessages();

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                Fragment selectedFragment = null;

                if (itemId == R.id.navigation_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.navigation_run) {
                    selectedFragment = new RunFragment();
                } else if (itemId == R.id.navigation_profile) {
                    selectedFragment = new ProfileFragment();
                } else if (itemId == R.id.navigation_book) {
                    selectedFragment = new HoatDongTinhThanFragment();
                } else if (itemId == R.id.navigation_setting) {
                    selectedFragment = new SettingFragment();
                }

                if (selectedFragment != null) {
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, selectedFragment);
                    transaction.commit();
                }

                return true;
            }
        });

        return view;
    }

    private void loadMessages() {
        // Mock data
        messageList.add(new Message("Nguyễn Văn A", "Chế độ ăn uống ngủ nghỉ sinh hoạt hợp lí"));
        messageList.add(new Message("Nguyễn Thị B", "Thông báo! Để đảm bảo cho hội thao sắp tới..."));
        messageAdapter.notifyDataSetChanged();
    }
}

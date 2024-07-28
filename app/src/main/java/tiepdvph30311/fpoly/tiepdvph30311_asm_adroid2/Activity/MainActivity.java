package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao.UserDao;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Fragment.HoatDongTinhThanFragment;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Fragment.HomeFragment;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Fragment.ProfileFragment;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Fragment.SettingFragment;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.User;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        // Setup the drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.nav_gallery) {
                    selectedFragment = new ProfileFragment();
                }
                else if (itemId == R.id.nav_setting) {
                    selectedFragment = new SettingFragment();
                }else if (itemId == R.id.nav_book) {
                    selectedFragment = new HoatDongTinhThanFragment();
                }


                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            }
        });

        // Set initial fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        // Load user information into the header
        loadUserInformation();
    }

    private void loadUserInformation() {
        // Assuming you have a method to get the current logged-in user
        userDao = new UserDao(this);
        User currentUser = getCurrentUser();

        View headerView = navigationView.getHeaderView(0);
        TextView tvUsernameHeader = headerView.findViewById(R.id.tvUsernameHeader);
        TextView tvEmailHeader = headerView.findViewById(R.id.tvEmailHeader);

        if (currentUser != null) {
            tvUsernameHeader.setText(currentUser.getUsername());
            tvEmailHeader.setText(currentUser.getEmail());
        } else {
            tvUsernameHeader.setText("Username: N/A");
            tvEmailHeader.setText("Email: N/A");
        }
    }

    // Mock method to get the current logged-in user
    private User getCurrentUser() {
        // Replace this with your actual logic to get the current user
        // For example, you might fetch the user from the database or shared preferences
        return userDao.getUserById(1); // Assuming 1 is the user ID
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

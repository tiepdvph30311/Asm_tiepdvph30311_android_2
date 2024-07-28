//package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Activity;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao.UserDao;
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;
//import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.User;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private EditText edtUsername, edtPassword;
//    private Button btnLogin;
//    private TextView tvRegister;
//    private UserDao userDao;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        edtUsername = findViewById(R.id.etUsername);
//        edtPassword = findViewById(R.id.etPassword);
//        btnLogin = findViewById(R.id.btnLogin);
//        tvRegister = findViewById(R.id.tvRegister);
//
//        userDao = new UserDao(this);
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = edtUsername.getText().toString().trim();
//                String password = edtPassword.getText().toString().trim();
//
//                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
//                    Toast.makeText(LoginActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                User user = userDao.getUser(username, password);
//
//                if (user != null) {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra("user_id", user.getId());
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    public void onRegisterClick(View view) {
//        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//        startActivity(intent);
//    }
//}
package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao.UserDao;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private UserDao userDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.etUsername);
        edtPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        userDao = new UserDao(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = userDao.getUser(username, password);

                if (user != null) {
                    // Lưu ID người dùng vào SharedPreferences
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("user_id", user.getId());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onRegisterClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}

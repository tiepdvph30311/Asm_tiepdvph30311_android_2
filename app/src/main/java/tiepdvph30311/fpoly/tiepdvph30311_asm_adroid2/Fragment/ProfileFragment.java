package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao.UserDao;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.User;

public class ProfileFragment extends Fragment {

    private TextView tvFullName, tvGender, tvDob, tvAddress, tvEmail, tvHeight, tvWeight, tvBMI, tvNote, tvAdvice;
    private UserDao userDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvFullName = view.findViewById(R.id.tvFullName);
        tvGender = view.findViewById(R.id.tvGender);
        tvDob = view.findViewById(R.id.tvDob);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvHeight = view.findViewById(R.id.tvHeight);
        tvWeight = view.findViewById(R.id.tvWeight);
        tvBMI = view.findViewById(R.id.tvBMI);
        tvNote = view.findViewById(R.id.tvNote);
        tvAdvice = view.findViewById(R.id.tvAdvice);

        userDao = new UserDao(getContext());

        // Assuming the logged-in user has an ID of 1
        User user = userDao.getUserById(1);

        if (user != null) {
            tvFullName.setText("Họ và tên: " + user.getUsername());
            tvGender.setText("Giới tính: " + user.getGender());
            tvDob.setText("Ngày tháng năm sinh: " + user.getDob());
            tvAddress.setText("Địa chỉ: " + user.getAddress());
            tvEmail.setText("Email: " + user.getEmail());
            tvHeight.setText("Chiều cao: " + user.getHeight() + " cm");
            tvWeight.setText("Cân nặng: " + user.getWeight() + " kg");

            double bmi = calculateBMI(user.getHeight(), user.getWeight());
            tvBMI.setText("Chỉ số BMI ≈ " + String.format("%.2f", bmi));

            if (bmi < 18.5) {
                tvNote.setText("Nhắc nhở: Với chỉ số BMI khoảng " + String.format("%.2f", bmi) + ", bạn thuộc nhóm gầy.");
                tvAdvice.setText("Lời khuyên: Tăng cường chế độ ăn uống với thực phẩm giàu dinh dưỡng và calo như các loại hạt, bơ, thịt, ngũ cốc, v.v. Kết hợp tập luyện sức mạnh để tăng khối lượng cơ bắp và cải thiện sức khỏe tổng thể.");
            } else {
                // Add more conditions for other BMI ranges if needed
                tvNote.setText("Nhắc nhở: Chỉ số BMI bình thường.");
                tvAdvice.setText("Lời khuyên: Duy trì chế độ ăn uống và luyện tập hiện tại.");
            }
        }

        return view;
    }

    private double calculateBMI(double height, double weight) {
        height = height / 100; // convert cm to meters
        return weight / (height * height);
    }
}

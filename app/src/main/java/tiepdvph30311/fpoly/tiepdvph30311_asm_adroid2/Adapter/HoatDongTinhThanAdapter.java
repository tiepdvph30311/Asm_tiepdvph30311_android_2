package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Dao.UserDao;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.HoatDongTinhThan;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.User;

public class HoatDongTinhThanAdapter extends BaseAdapter {

    private Context context;
    private List<HoatDongTinhThan> hoatDongTinhThanList;
    private UserDao userDao;

    public HoatDongTinhThanAdapter(Context context, List<HoatDongTinhThan> hoatDongTinhThanList) {
        this.context = context;
        this.hoatDongTinhThanList = hoatDongTinhThanList;
        this.userDao = new UserDao(context); // Initialize UserDao
    }

    @Override
    public int getCount() {
        return hoatDongTinhThanList.size();
    }

    @Override
    public Object getItem(int position) {
        return hoatDongTinhThanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hoatdongtinhthan, parent, false);
        }

        HoatDongTinhThan hoatDongTinhThan = hoatDongTinhThanList.get(position);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);
        TextView textViewContent = convertView.findViewById(R.id.textViewContent);

        // Giả sử có một phương thức để lấy ảnh đại diện của người dùng
        // imageView.setImageResource(hoatDongTinhThan.getImageResource());

        // Lấy tên người dùng từ UserDao
        User user = userDao.getUserById(hoatDongTinhThan.getMaNguoiDung());
        if (user != null) {
            textViewName.setText(user.getUsername()); // Đặt tên người dùng tương ứng
        } else {
            textViewName.setText("Unknown User"); // Đặt tên người dùng mặc định nếu không tìm thấy
        }
        textViewDate.setText(hoatDongTinhThan.getNgayThucHien());
        textViewContent.setText(hoatDongTinhThan.getNoiDung());

        return convertView;
    }
}

package tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.R;
import tiepdvph30311.fpoly.tiepdvph30311_asm_adroid2.model.HoatDongVanDong;

public class HoatDongVanDongAdapter extends ArrayAdapter<HoatDongVanDong> {
    private Context context;
    private List<HoatDongVanDong> hoatDongVanDongList;

    public HoatDongVanDongAdapter(Context context, List<HoatDongVanDong> hoatDongVanDongList) {
        super(context, 0, hoatDongVanDongList);
        this.context = context;
        this.hoatDongVanDongList = hoatDongVanDongList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hoat_dong_van_dong, parent, false);
        }

        HoatDongVanDong hoatDongVanDong = hoatDongVanDongList.get(position);

        TextView tvDate = convertView.findViewById(R.id.tvDate);
        TextView tvSteps = convertView.findViewById(R.id.tvSteps);

        tvDate.setText("Date: " + hoatDongVanDong.getNgayThucHien());
        tvSteps.setText("Steps: " + hoatDongVanDong.getSoBuocChan());

        return convertView;
    }
}

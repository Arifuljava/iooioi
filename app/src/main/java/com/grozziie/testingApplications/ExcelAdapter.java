package com.grozziie.testingApplications;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Map;


public class ExcelAdapter extends  BaseQuickAdapter<Map<Integer, Object>, BaseViewHolder> {
    ExcelAdapter(@Nullable List<Map<Integer, Object>> data) {
        super(R.layout.template1_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Map<Integer, Object> item) {
        LinearLayout view = (LinearLayout) helper.itemView;
        view.removeAllViews();//清除全部View
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                300,
                LinearLayout.LayoutParams.MATCH_PARENT);

        for (int i = 0; i < item.size(); i++) {
            TextView textView = new TextView(mContext);
            textView.setTextSize(13);
            textView.setText(item.get(i) + "");
            textView.setLayoutParams(layoutParams);
            textView.setPadding(10, 10, 10, 10);
            textView.setBackground(mContext.getResources().getDrawable(R.drawable.textview_border));
            view.addView(textView);
        }

    }
}
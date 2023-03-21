package com.grozziie.testingApplications;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.myview> {
    public List<textModel> data;
    FirebaseFirestore firebaseFirestore;
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    public TextAdapter(List<textModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TextAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.excelshett, parent, false);
        return new TextAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextAdapter.myview holder, final int position) {

holder.tvDeviceName.setText(data.get(position).getText());
       /// holder.lokk.setAnimation(endAnimation);








    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder {
        TextView tvDeviceName,tvDeviceAddress,textView;
        LinearLayout lokk;
        CardView dailyCheckCard;

        public myview(@NonNull View itemView) {
            super(itemView);
            tvDeviceName=itemView.findViewById(R.id.excel_content_rv);
            lokk=itemView.findViewById(R.id.lokk);

        }
    }
}

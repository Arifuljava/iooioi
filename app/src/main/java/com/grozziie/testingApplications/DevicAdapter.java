package com.grozziie.testingApplications;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DevicAdapter  extends RecyclerView.Adapter<DevicAdapter.myview> {
    public List<DeviceModel> data;
    FirebaseFirestore firebaseFirestore;
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    public DevicAdapter(List<DeviceModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DevicAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paied_devices, parent, false);
        return new DevicAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DevicAdapter.myview holder, final int position) {
       try {
           int myposition=Integer.parseInt(data.get(position).toString());
           topAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.splash_top_animation);
           bottomAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.splash_bottom_animation);
           startAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.splash_start_animation);
           endAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.splash_end_animation);
           if (myposition%4==0)
           {
               holder.container.setAnimation(startAnimation);
           }
           else if (myposition%4==1)
           {
               holder.container.setAnimation(endAnimation);
           }
           else if (myposition%4==2)
           {
               holder.container.setAnimation(topAnimation);
           }
           else if (myposition%4==3)
           {
               holder.container.setAnimation(bottomAnimation);
           }
           holder.tvDeviceAddress.setText(data.get(position).getDeviceaddress());
           holder.tvDeviceName.setText(data.get(position).getDevicename());
           holder.textView.setText(data.get(position).getConnectddate());
           holder.container.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String message="Device Name : "+data.get(position).getDevicename()+"" +
                           "\nMAC Address : "+data.get(position).getDeviceaddress().toUpperCase().toString()+"\n" +
                           "Connected Date : "+data.get(position).getConnectddate();
                   AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                   builder.setTitle("Device Details")
                           .setMessage(message)
                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   dialog.dismiss();
                               }
                           }).create().show();

               }
           });
       }catch (Exception e) {
           e.printStackTrace();
       }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder {
        TextView tvDeviceName,tvDeviceAddress,textView;
        RelativeLayout container;

        public myview(@NonNull View itemView) {
            super(itemView);
            tvDeviceName=itemView.findViewById(R.id.tvDeviceName);
            tvDeviceAddress=itemView.findViewById(R.id.tvDeviceAddress);
            textView=itemView.findViewById(R.id.textView);
            container=itemView.findViewById(R.id.container);


        }
    }
}

package com.grozziie.testingApplications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ForSliderPurpose extends AppCompatActivity {
    private SliderView sliderView;
    private List<SliderItem>list;
    FirebaseFirestore firebaseFirestore;
    SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_slider_purpose);
        sliderView=findViewById(R.id.imageSlider);
        list=new ArrayList<>();
        firebaseFirestore=FirebaseFirestore.getInstance();firebaseFirestore.collection("ImageData")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int count = 0;
                        for (DocumentSnapshot documentSnapshot: task.getResult()) {
                            count=count+1;

                        }
                        Toast.makeText(ForSliderPurpose.this, ""+count, Toast.LENGTH_SHORT).show();


                    }
                });
        sliderAdapter=new SliderAdapter(ForSliderPurpose.this,list);
        loadImages();


    }

    private void loadImages() {
        firebaseFirestore.collection("ImageData")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
    SliderItem sliderItem=queryDocumentSnapshot.toObject(SliderItem.class);
    SliderItem sliderItem1=new SliderItem();
    sliderItem1.setImage(sliderItem.getImage());
    list.add(sliderItem1);
    sliderAdapter=new SliderAdapter(ForSliderPurpose.this,list);
    sliderView.setSliderAdapter(sliderAdapter);
    //  sliderview2.setSliderAdapter(adapter1);

    // below line is for setting animation to our slider.
    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    // sliderview2.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

    // below line is for setting auto cycle duration.
    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
    // sliderview2.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);

    // below line is for setting
    // scroll time animation
    sliderView.setScrollTimeInSec(3);
    // sliderview2.setScrollTimeInSec(3);

    // below line is for setting auto
    // cycle animation to our slider
    sliderView.setAutoCycle(true);
    //  sliderview2.setAutoCycle(true);

    // below line is use to start
    // the animation of our slider view.
    sliderView.startAutoCycle();
    //sliderview2.startAutoCycle();

}
                    }
                });
    }
}
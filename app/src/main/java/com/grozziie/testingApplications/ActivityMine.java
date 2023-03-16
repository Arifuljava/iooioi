package com.grozziie.testingApplications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.print.PrintHelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMine extends AppCompatActivity {

    View mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        //get the layout of visiting card
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.identity_card, null);

        //get the main view which will be printed
        RelativeLayout view = (RelativeLayout) contentView.findViewById(R.id.relative);

        //get all the dynamic views which will be changed programmatically
        TextView tv = (TextView) view.findViewById(R.id.textView3);

        //change name
        tv.setText("Titir Mukherjee");
        view.setDrawingCacheEnabled(true);
        // this is the important code
        // Without it the view will have a dimension of 0,0 and the bitmap will be null
        //set view hight, width
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //layout(left, top, size from left to right, size from top to bottom)
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        //build the cache with autoscale enabled
        view.buildDrawingCache(true);

        //store the view for further use
        mView = view;
    }
    private void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);

        //this is used for print drawable image
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.image);

        // get the layout in a bitmap
        Bitmap bitmap = mView.getDrawingCache();

        //print
        photoPrinter.printBitmap("image.png_test_print", bitmap, new PrintHelper.OnPrintFinishCallback() {
            @Override
            public void onFinish() {
                Toast.makeText(ActivityMine.this, "Thank you!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // code for button click
    public void onClick(View view) {
        doPhotoPrint();
    }
}
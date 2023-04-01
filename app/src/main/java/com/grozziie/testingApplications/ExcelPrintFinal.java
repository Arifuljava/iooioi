package com.grozziie.testingApplications;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ExcelPrintFinal extends AppCompatActivity {
    EditText xcelshetttextview, resulttext;
    Button pickexcel;
    Button import_excel_btn, export_excel_btn;
    AssetManager assetManager;
    public static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    private int FILE_SELECTOR_CODE = 10000;
    private int DIR_SELECTOR_CODE = 20000;
    private List<Map<Integer, Object>> readExcelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ExcelAdapter excelAdapter;
    String model_1;
    UUID uuid = UUID.randomUUID();

    FirebaseFirestore firebaseFirestore;

    RecyclerView relay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel_print_final);
        ConnectivityPurposeGetIp connectivityPurposeGetIp=new ConnectivityPurposeGetIp(ExcelPrintFinal.this);
connectivityPurposeGetIp.getip(ExcelPrintFinal.this,"io");



        resulttext = findViewById(R.id.resulttext);
        firebaseFirestore = FirebaseFirestore.getInstance();
        assetManager = getAssets();

        xcelshetttextview = findViewById(R.id.xcelshetttextview);
        pickexcel = findViewById(R.id.pickexcel);
        import_excel_btn = findViewById(R.id.import_excel_btn);
        export_excel_btn = findViewById(R.id.export_excel_btn);
        relay = findViewById(R.id.relay);

        pickexcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xcelshetttextview.getText().toString().contains("99")) {
                    try {
                        InputStream inputStream;
                        inputStream = assetManager.open("esc_chat.xlsx");  //creating a new file instance
                        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
                        XSSFSheet sheet = wb.getSheetAt(0);
                        Iterator<Row> itr = sheet.iterator();
                        while (itr.hasNext()) {
                            Row row = itr.next();
                            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                            while (cellIterator.hasNext()) {
                                Cell cell = cellIterator.next();
                                switch (cell.getCellType()) {
                                    case Cell.CELL_TYPE_STRING:
                                        message = message + "\n" + cell.getStringCellValue();
                                        //Toast.makeText(ExcelPrintFinal.this, ""+cell.getStringCellValue(), Toast.LENGTH_SHORT).show();//field that represents string cell type
                                        //Toast.makeText(ExcelPrintFinal.this, ""+message, Toast.LENGTH_SHORT).show();
                                        resulttext.setText(message);
                                        /*
                                        System.out.print(cell.getStringCellValue() + "\t\t\t");
                                        long time=System.currentTimeMillis()/1000;

                                        textModel textModel=new textModel(cell.getStringCellValue(),model_1,""+time);
                                        firebaseFirestore.collection("LIts")
                                                .document("List")
                                                .collection(xcelshetttextview.getText().toString().toLowerCase().toString())
                                                .document(UUID.randomUUID().toString())
                                                .set(textModel)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toasty.success(getApplicationContext(),"Data Get : "+cell.getStringCellValue(),Toasty.LENGTH_SHORT,true).show();
                                                            DocumentReference documentReference;
                                                            TextAdapter getDataAdapter1;
                                                            List<textModel> getList;



                                                            getList = new ArrayList<>();
                                                            getDataAdapter1 = new TextAdapter(getList);
                                                            firebaseFirestore = FirebaseFirestore.getInstance();
                                                            documentReference =  firebaseFirestore.collection("LIts")
                                                                    .document("List")
                                                                    .collection(xcelshetttextview.getText().toString().toLowerCase().toString())
                                                                    .document();

                                                            relay.setHasFixedSize(true);
                                                            relay.setLayoutManager(new LinearLayoutManager(ExcelPrintFinal.this));
                                                            relay.setAdapter(getDataAdapter1);


                                                            firebaseFirestore.collection("LIts")
                                                                    .document("List")
                                                                    .collection(xcelshetttextview.getText().toString().toLowerCase().toString())
                                                                    .orderBy("roomid", Query.Direction.ASCENDING)
                                                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                                        @Override
                                                                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                                                            for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                                                                                if (ds.getType() == DocumentChange.Type.ADDED) {
                                                                                    textModel get = ds.getDocument().toObject(textModel.class);
                                                                                    getList.add(get);
                                                                                    getDataAdapter1.notifyDataSetChanged();
                                                                                }

                                                                            }
                                                                        }
                                                                    });


                                                        }
                                                    }
                                                });


                                        break;
                                    case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                                        System.out.print(cell.getNumericCellValue() + "\t\t\t");
                                        break;
                                    default:
                                }
                            }
                            System.out.println("");

                        }

                        Toast.makeText(ExcelPrintFinal.this, "Done", Toast.LENGTH_SHORT).show();

                    }catch (Exception e) {
                        Toast.makeText(ExcelPrintFinal.this, "Error on :  \n"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else   if (xcelshetttextview.getText().toString().contains("33")) {
                    try {
                        InputStream inputStream;
                        inputStream=assetManager.open("hell.xlsx");  //creating a new file instance
                        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
                        XSSFSheet sheet = wb.getSheetAt(0);
                        Iterator<Row> itr = sheet.iterator();
                        while (itr.hasNext())
                        {
                            Row row = itr.next();
                            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                            while (cellIterator.hasNext())
                            {
                                Cell cell = cellIterator.next();
                                switch (cell.getCellType())
                                {
                                    case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                                        System.out.print(cell.getStringCellValue() + "\t\t\t");

                                        long time=System.currentTimeMillis()/1000;

                                        textModel textModel=new textModel(cell.getStringCellValue(),model_1,""+time);
                                        firebaseFirestore.collection("LIts")
                                                .document("List")
                                                .collection(xcelshetttextview.getText().toString().toLowerCase().toString())
                                                .document(UUID.randomUUID().toString())
                                                .set(textModel)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toasty.success(getApplicationContext(),"Data Get : "+cell.getStringCellValue(),Toasty.LENGTH_SHORT,true).show();
                                                            DocumentReference documentReference;
                                                            TextAdapter getDataAdapter1;
                                                            List<textModel> getList;



                                                            getList = new ArrayList<>();
                                                            getDataAdapter1 = new TextAdapter(getList);
                                                            firebaseFirestore = FirebaseFirestore.getInstance();
                                                            documentReference =  firebaseFirestore.collection("LIts")
                                                                    .document("List")
                                                                    .collection(xcelshetttextview.getText().toString().toLowerCase().toString())
                                                                    .document();

                                                            relay.setHasFixedSize(true);
                                                            relay.setLayoutManager(new LinearLayoutManager(ExcelPrintFinal.this));
                                                            relay.setAdapter(getDataAdapter1);


                                                            firebaseFirestore.collection("LIts")
                                                                    .document("List")
                                                                    .collection(xcelshetttextview.getText().toString().toLowerCase().toString())
                                                                    .orderBy("roomid", Query.Direction.ASCENDING)
                                                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                                        @Override
                                                                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                                                            for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                                                                                if (ds.getType() == DocumentChange.Type.ADDED) {
                                                                                    textModel get = ds.getDocument().toObject(textModel.class);
                                                                                    getList.add(get);
                                                                                    getDataAdapter1.notifyDataSetChanged();
                                                                                }

                                                                            }
                                                                        }
                                                                    });


                                                        }
                                                    }
                                                });
                                         */


                                        break;
                                    case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                                        System.out.print(cell.getNumericCellValue() + "\t\t\t");
                                        message = message + "\n" + cell.getNumericCellValue();
                                        

                                        //
                                        // Toast.makeText(ExcelPrintFinal.this, ""+cell.getNumericCellValue(), Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(ExcelPrintFinal.this, ""+message, Toast.LENGTH_SHORT).show();
                                        resulttext.setText(message);

                                        break;
                                    default:
                                }
                            }
                            System.out.println("");

                        }

                        Toast.makeText(ExcelPrintFinal.this, "Done", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(ExcelPrintFinal.this, "Error on :  \n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


    }

    String message = "Geting Data : \n";

    public void importsection(View view) {
        resulttext.setText("");
        message="";
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.setType("application/*");
        intent.putExtra(Intent.EXTRA_TITLE,
                System.currentTimeMillis() + ".xlsx");
        startActivityForResult(intent, DIR_SELECTOR_CODE);
    }

    Uri fileuri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_SELECTOR_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Toast.makeText(ExcelPrintFinal.this, "Data 1 : \n"+data, Toast.LENGTH_SHORT).show();
        }
        if (requestCode == DIR_SELECTOR_CODE && resultCode == Activity.RESULT_OK) {
             uri = data.getData();
            if (uri == null) return;
            Log.i(TAG, "onActivityResult: " + "filePath：" + uri.getPath());
           /// Toast.makeText(mContext, "Exporting...", Toast.LENGTH_SHORT).show();
            //you can modify readExcelList, then write to excel.
            //Toast.makeText(ExcelPrintFinal.this, "Data 2 : \n"+data, Toast.LENGTH_SHORT).show();
           // ExcelUtil.writeExcelNew(this, readExcelList, uri);
            try {
                resulttext.setText("");

                pleaseGetExtention(data.getData());
                pleasePrint(data.getData());
            }catch (Exception e) {
            }



        }
    }
    String finalentention;

    private void pleaseGetExtention(Uri data) {
        String extension;
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        extension= mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
        finalentention=extension;
       // Toast.makeText(ExcelPrintFinal.this, ""+extension, Toast.LENGTH_SHORT).show();
    }

    private void pleasePrint(Uri data) {
        if (finalentention.toLowerCase().length()==4) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data);
                XSSFWorkbook wb = new XSSFWorkbook(inputStream);
                XSSFSheet sheet = wb.getSheetAt(0);
                // XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
                Iterator<Row> itr = sheet.iterator();
                while (itr.hasNext())
                {
                    Row row = itr.next();
                    Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                    while (cellIterator.hasNext())
                    {
                        Cell cell = cellIterator.next();
                        switch (cell.getCellType())
                        {
                            case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                               // System.out.print(cell.getStringCellValue() + "\t\t\t");
                              //  Toast.makeText(ExcelPrintFinal.this, ""+cell.getStringCellValue(), Toast.LENGTH_SHORT).show();
                                message = message + "\n" + cell.getStringCellValue();
                                resulttext.setText(message);
                                break;
                            case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                                //System.out.print(cell.getNumericCellValue() + "\t\t\t");
                                message = message + "\n" + cell.getNumericCellValue();
                                resulttext.setText(message);
                                break;
                            default:
                        }
                    }
                    System.out.println("");

                }
               /// Toast.makeText(ExcelPrintFinal.this, "Done : "+inputStream, Toast.LENGTH_SHORT).show();
            }catch (Exception e) {
                Toast.makeText(ExcelPrintFinal.this, "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            try {
                InputStream fileInputStream=getContentResolver().openInputStream(data);
                HSSFWorkbook wb=new HSSFWorkbook(fileInputStream);
                HSSFSheet sheet=wb.getSheetAt(0);
                FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();
                for(Row row: sheet)
                {
                    for(Cell cell: row)
                    {
                        switch(formulaEvaluator.evaluateInCell(cell).getCellType())
                        {
                            case Cell.CELL_TYPE_NUMERIC:

                                System.out.print(cell.getNumericCellValue()+ "\t\t");
                                break;
                            case Cell.CELL_TYPE_STRING:

                                System.out.print(cell.getStringCellValue()+ "\t\t");
                                break;
                        }
                    }
                    System.out.println();
                }

            }catch (Exception e) {
            }
        }

    }

    Uri uri;
}
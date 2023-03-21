package com.grozziie.testingApplications;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import jxl.Workbook;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

public class ExcelSheetActivity extends AppCompatActivity {
    TextView xcelshetttextview;
    Button pickexcel;
    AssetManager assetManager;
    public static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    private int FILE_SELECTOR_CODE = 10000;
    private int DIR_SELECTOR_CODE = 20000;
    private List<Map<Integer, Object>> readExcelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ExcelAdapter excelAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel_sheet);
        xcelshetttextview=findViewById(R.id.xcelshetttextview);
        pickexcel=findViewById(R.id.pickexcel);
        assetManager=getAssets();
        pickexcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream inputStream;
                    inputStream=assetManager.open("hell.xlsx");  //creating a new file instance
                   // FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
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
                                    System.out.print(cell.getStringCellValue() + "\t\t\t");
                                    Toast.makeText(ExcelSheetActivity.this, ""+cell.getStringCellValue(), Toast.LENGTH_SHORT).show();
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                                    System.out.print(cell.getNumericCellValue() + "\t\t\t");
                                    break;
                                default:
                            }
                        }
                        System.out.println("");

                }

                Toast.makeText(ExcelSheetActivity.this, "Done", Toast.LENGTH_SHORT).show();

                }catch (Exception e) {
                    Toast.makeText(ExcelSheetActivity.this, "Error on :  \n"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

             /*
                Intent intent=new Intent();
                intent.setType("");
                intent.putExtra(Intent.EXTRA_AUTO_LAUNCH_SINGLE_CHOICE,true);
                intent.setAction(Intent.ACTION_GET_CONTENT);

                ActivityCompat.requestPermissions(ExcelSheetActivity.this,new String[] {WRITE_EXTERNAL_STORAGE},102);
                startActivity(intent);
              */
              /*


               */
             /*
                try {
                    InputStream inputStream;
                    inputStream=assetManager.open("esc_chat.xlsx");
                    POIFSFileSystem poifsFileSystem=new POIFSFileSystem(inputStream);
                    Toast.makeText(ExcelSheetActivity.this, ""+poifsFileSystem, Toast.LENGTH_SHORT).show();

                }catch (Exception e) {
                    Toast.makeText(ExcelSheetActivity.this, "Error on :  \n"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
              */
                /*
                try {
                    AssetManager assetManager = getAssets();
                    InputStream inputStream = assetManager.open("test.xlsx");
                    POIFSFileSystem myFileSystem = new POIFSFileSystem(inputStream);

                    //Workbook workbook = WorkbookFactory.create(inputStream);
                    Toast.makeText(ExcelSheetActivity.this, "done", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ExcelSheetActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                 */
            }
        });

        mContext = this;

        initViews();
    }

    Uri fileuri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && data!=null){
            fileuri=data.getData();
            xcelshetttextview.setText(readCSVFile(getFilePathFromUri(fileuri)));
        }
    }


    // this method is used for getting file path from uri
    public String getFilePathFromUri(Uri uri){
        String[] filename1;
        String fn;
        String filepath=uri.getPath();
        String filePath1[]=filepath.split(":");
        filename1 =filepath.split("/");
        fn=filename1[filename1.length-1];
        return Environment.getExternalStorageDirectory().getPath()+"/"+filePath1[1];
    }

    //reading file data

    public String readCSVFile(String path){
        String filedata = null;
        File file=new File(path);
        try {

            Scanner scanner=new Scanner(file);
            while (scanner.hasNextLine()){

                String line=scanner.nextLine();
                String [] splited=line.split(",");
                String row="";
                for (String s:splited){

                    row=row+s+"  ";

                }

                filedata=filedata+row+"\n";

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(ExcelSheetActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }

        return filedata;

    }
    private void initViews() {
        recyclerView = findViewById(R.id.excel_content_rv);
        excelAdapter = new ExcelAdapter(readExcelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(excelAdapter);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.import_excel_btn:
                openFileSelector();
                break;

            case R.id.export_excel_btn:
                if (readExcelList.size() > 0) {
                    openFolderSelector();
                } else {
                    Toast.makeText(mContext, "please import excel first", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * open local filer to select file
     */
    private void openFileSelector() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/*");
        startActivityForResult(intent, FILE_SELECTOR_CODE);
    }

    /**
     * open the local filer and select the folder
     */
    private void openFolderSelector() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.setType("application/*");
        intent.putExtra(Intent.EXTRA_TITLE,
                System.currentTimeMillis() + ".xlsx");
        startActivityForResult(intent, DIR_SELECTOR_CODE);
    }

   /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_SELECTOR_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (uri == null) return;
            Log.i(TAG, "onActivityResult: " + "filePath：" + uri.getPath());
            //select file and import
            importExcelDeal(uri);
        } else if (requestCode == DIR_SELECTOR_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (uri == null) return;
            Log.i(TAG, "onActivityResult: " + "filePath：" + uri.getPath());
            Toast.makeText(mContext, "Exporting...", Toast.LENGTH_SHORT).show();
            //you can modify readExcelList, then write to excel.
            ExcelUtil.writeExcelNew(this, readExcelList, uri);
        }
    }
    */

    private void importExcelDeal(final Uri uri) {
        new Thread(() -> {
            Log.i(TAG, "doInBackground: Importing...");
            runOnUiThread(() -> Toast.makeText(mContext, "Importing...", Toast.LENGTH_SHORT).show());

            List<Map<Integer, Object>> readExcelNew = ExcelUtil.readExcelNew(mContext, uri, uri.getPath());

            Log.i(TAG, "onActivityResult:readExcelNew " + ((readExcelNew != null) ? readExcelNew.size() : ""));
            ///Toast.makeText(ExcelSheetActivity.this, ""+readExcelNew, Toast.LENGTH_SHORT).show();

            if (readExcelNew != null && readExcelNew.size() > 0) {
                readExcelList.clear();
                readExcelList.addAll(readExcelNew);
                updateUI();

                Log.i(TAG, "run: successfully imported");
                runOnUiThread(() -> Toast.makeText(mContext, "successfully imported", Toast.LENGTH_SHORT).show());
            } else {
                runOnUiThread(() -> Toast.makeText(mContext, "no data"+uri.getPath(), Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    /**
     * refresh RecyclerView
     */
    private void updateUI() {
        runOnUiThread(() -> {
            if (readExcelList != null && readExcelList.size() > 0) {
                excelAdapter.notifyDataSetChanged();
            }
        });
    }

}
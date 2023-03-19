package com.grozziie.testingApplications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.google.android.gms.common.util.Hex;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppe);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        try {
           /*
            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("My_pdf.pdf"));
            document.open();
            document.add(new Paragraph("Hello THT;"));
            document.close();
            Toasty.success(getApplicationContext(), "Saved", Toasty.LENGTH_SHORT, true).show();
            return;
            */
            ///another way
            /// PrintedPdfDocument printedPdfDocument=new PrintedPdfDocument()

            ///  Document doc = new Document();

            try {
                //  String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Dir";

                // File dir = new File(path);
                //  if (!dir.exists())
                //  dir.mkdirs();

                //  File file = new File(dir, "newFile.pdf");
                // FileOutputStream fOut = new FileOutputStream(file);

                // PdfWriter.getInstance(doc, fOut);

                //open the document
                //doc.open();

                //  Paragraph p1 = new Paragraph("text");
                //  p1.setAlignment(Paragraph.ALIGN_CENTER);
                // p1.setFont(paraFont);

                //add paragraph to document
                //doc.add(p1);

                // } catch (DocumentException de) {
                //      Log.e("PDFCreator", "DocumentException:" + de);
                // } catch (IOException e) {
                //  Log.e("PDFCreator", "ioException:" + e);
                //} finally {
                //  doc.close();
                // }

            } catch (Exception e) {
            }

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://partner.shopeemobile.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            /// Toast.makeText(this, ""+retrofit.hashCode(), Toast.LENGTH_SHORT).show();
            ShopeeApi shopeeApi = retrofit.create(ShopeeApi.class);

            long shopId = 12345L;
            int partnerId = 123456;
            long timestamp = System.currentTimeMillis() / 1000L;
            // String sign = generateSign(shopId, partnerId, timestamp, "apiKey");
            /// Call<ShopResponse> call = shopeeApi.getShopInfo(shopId, partnerId, timestamp, sign);
            //        call.enqueue(new Callback<ShopResponse>() {
            //            @Override
            //            public void onResponse(Call<ShopResponse> call, Response<ShopResponse> response) {
            //                if (response.isSuccessful()) {
            //                    ShopResponse shop = response.body();
            //                    // Do something with the shop info
            //                } else {
            //                    // Handle the error response
            //                }
            //            }
            //
            //            @Override
            //            public void onFailure(Call<ShopResponse> call, Throwable t) {
            //                // Handle the network error
            //            }
            //        });
            //    }
            String sign=getdataFromApi(shopId,partnerId,timestamp,"");;
            Call<Response>responseCall=shopeeApi.getAllInforThere(shopId,partnerId,timestamp,sign);
            responseCall.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.isSuccessful()) {
                        Response shopeeApi1=response.body();

                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                   try {
                       call.execute();
                   }catch (Exception e)

                    {
                        e.printStackTrace();
                    }


                }
            });

        /*
        Call<ShopResponse> call = shopeeApi.getShopInfo(shopId, partnerId, timestamp, sign);
        call.enqueue(new Callback<ShopResponse>() {
            @Override
            public void onResponse(Call<ShopResponse> call, Response<ShopResponse> response) {
                if (response.isSuccessful()) {
                    ShopResponse shop = response.body();
                    // Do something with the shop info
                } else {
                    // Handle the error response
                }
            }

            @Override
            public void onFailure(Call<ShopResponse> call, Throwable t) {
                // Handle the network error
            }
        });
    }
    private String generateSign(long shopId, int partnerId, long timestamp, String apiKey) {
        String baseString = String.format("%d%d%d", partnerId, shopId, timestamp);
        String hmac = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(apiKey.getBytes(), "HmacSHA256");
            mac.init(secret);
            hmac = Hex.encodeHexString(mac.doFinal(baseString.getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return hmac;
    }
         */

        } catch (Exception e) {
        }
    }
    public  String getdataFromApi(long shopId,long partnerId,long timestamp,String apiKey) {
       try {
           String basestring=String.format("%d%d%d",partnerId,shopId,timestamp);
           String hmac=null;
           Mac mac=Mac.getInstance("HmacSHA256");
           SecretKeySpec spec=new SecretKeySpec(apiKey.getBytes(),"HmacSHA256");
           mac.init(spec);
           hmac=Hex.bytesToStringLowercase(basestring.getBytes());



           return hmac;
       }catch (NoSuchAlgorithmException | InvalidKeyException e) {
           e.printStackTrace();
       }
       return null;
    }

    }

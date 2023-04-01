package com.grozziie.testingApplications;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CountryResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConnectivityPurposeGetIp {
    Context context;
    String userIpaddress;

    public ConnectivityPurposeGetIp(Context context) {
        this.context = context;
    }
    public  void getchecking(Context context)

    {
        Toast.makeText(context, "Long", Toast.LENGTH_LONG).show();
    }
    public  static  void mmm() {

    }
    public void getip(Context context, String userIpaddress) {
        String value = null;
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> result = es.submit(new Callable<String>() {
            public String call() throws Exception {
                try {
                    URL url = new URL("http://whatismyip.akamai.com/");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        BufferedReader r = new BufferedReader(new InputStreamReader(in));
                        StringBuilder total = new StringBuilder();
                        String line;
                        while ((line = r.readLine()) != null) {
                            total.append(line).append('\n');
                        }
                        urlConnection.disconnect();
                        return total.toString();
                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (IOException e) {
                    Log.e("Public IP: ", e.getMessage());
                }
                return null;
            }
        });
        try {
            value = result.get();
        } catch (Exception e) {
            // failed
        }
        Toast.makeText(context, ""+value, Toast.LENGTH_SHORT).show();
      try {
          iptocountryname(value);
      }catch (Exception e) {
          e.printStackTrace();
      }
        es.shutdown();
    }

    private void iptocountryname(String ipaddress) throws IOException {



    }


}

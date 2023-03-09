package com.grozziie.testingApplications;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.widget.Toast;

import java.util.UUID;

public class CPClActivity extends AppCompatActivity {
    String uuidurl="00001101-0000-1000-8000-00805F9B34FB";
    String bluetoothaddress="F7:85:ED:28:B4:F0";
    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice bluetoothDevice;
    BluetoothSocket bluetoothSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_p_cl);
        try {
            bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
            bluetoothDevice=bluetoothAdapter.getRemoteDevice(bluetoothaddress);
            bluetoothSocket=bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(uuidurl));
            bluetoothSocket.connect();;
            if (bluetoothAdapter==null) {
                Toast.makeText(getApplicationContext(), "Please connected with bluetooth", Toast.LENGTH_SHORT).show();
            }
            else {
                if (bluetoothSocket.isConnected()) {
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();

                }
            }

        }catch (Exception e) {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.receptorllamadas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final int RC_READ_PHONE_STATE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] perms = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG};

        Log.d("Rodrigo", "onCreate");
        // Check and request the permission
        if (EasyPermissions.hasPermissions(this, perms)) {
            // todo
            Log.d("Rodrigo", "EasyPermissions");

            // Permission already granted, perform actions
        } else {
            Log.d("Rodrigo", "ELSE");
            // Request the permission
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, RC_READ_PHONE_STATE,
                            Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG)
                            .setRationale("This permission is needed to read phone state")
                            .setPositiveButtonText("Yes")
                            .setNegativeButtonText("No")
                            .setTheme(R.style.Theme_ReceptorLlamadas)
                            .build());
        }
    }

    // This method is called when the user responds to the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("Rodrigo", "onRequestPermissionsResult");
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

        Log.d("Rodrigo", "onPermissionsGranted");

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d("Rodrigo", "onPermissionsDenied");

    }
}

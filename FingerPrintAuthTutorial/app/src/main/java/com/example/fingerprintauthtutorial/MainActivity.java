package com.example.fingerprintauthtutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    Button btn_fp,btn_fppin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_fp = findViewById(R.id.btn_fp);
        btn_fppin = findViewById(R.id.btn_fppin);
        checkBioMetricSupported();
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt= new BiometricPrompt(MainActivity.this, executor,
                new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(MainActivity.this, "Auth error: "+ errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(MainActivity.this, "Auth Succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(MainActivity.this, "Auth Failed", Toast.LENGTH_SHORT).show();
            }
        });
        //for fingerprint only
        btn_fp.setOnClickListener(view -> {
            BiometricPrompt.PromptInfo.Builder promtInfo = dialogMetric();
            promtInfo.setNegativeButtonText("Cancel");
            biometricPrompt.authenticate(promtInfo.build());
        });
        //for button fingerprint or pattern or pin
        btn_fppin.setOnClickListener(view -> {
            BiometricPrompt.PromptInfo.Builder promtInfo = dialogMetric();
            promtInfo.setDeviceCredentialAllowed(true);
            biometricPrompt.authenticate(promtInfo.build());
        });
    }
    BiometricPrompt.PromptInfo.Builder dialogMetric(){
        return new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login")
                .setSubtitle("Login using your biometric crendential");

    }

    private void checkBioMetricSupported() {
        String info="";
        BiometricManager manager = BiometricManager.from(this);
        switch (manager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK|
                BiometricManager.Authenticators.BIOMETRIC_STRONG))
        {
            case BiometricManager.BIOMETRIC_SUCCESS:
                info = "App can authenticate using biometrics";
                enableButton(true);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                info = "No biometrics available on this device";
                enableButton(false);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                info = "Biometrics features are currently unavailable";
                enableButton(false);
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                info = "Need register at least on fingerprint";
                enableButton(false,true);
                break;
            default:
                info = "Unknow cause";
                break;
        }
        TextView txtinfo = findViewById(R.id.txt_info);
        txtinfo.setText(info);
    }
    void enableButton(boolean enable){
        btn_fp.setEnabled(enable);
        btn_fppin.setEnabled(true);

    }
    void enableButton(boolean enable,boolean enroll){
        enableButton(enable);
        if(!enroll) return;
        Intent enrollIntent= new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
        enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                BiometricManager.Authenticators.BIOMETRIC_STRONG|BiometricManager.Authenticators.BIOMETRIC_WEAK);
        startActivity(enrollIntent);


    }
}
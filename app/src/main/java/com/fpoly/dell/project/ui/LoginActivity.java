package com.fpoly.dell.project.ui;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.provider.Settings;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fpoly.dell.project.database.SqliteHelper;
import com.fpoly.dell.project.model.User;
import com.fpoly.dell.project1.R;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {


    EditText editTextEmail;
    EditText editTextPassword;

    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    Button buttonLogin;

    SqliteHelper sqliteHelper;

    ImageButton btn_fp,btn_fppin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();
        //btn_fp = findViewById(R.id.btn_fp);
        btn_fppin = findViewById(R.id.btn_fppin);

        checkBioMetricSupported();
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt= new BiometricPrompt(LoginActivity.this, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        Toast.makeText(LoginActivity.this, "Auth error: "+ errString, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Toast.makeText(LoginActivity.this, "Auth Succeeded!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(LoginActivity.this, "Auth Failed", Toast.LENGTH_SHORT).show();
                    }
                });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (validate()) {


                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();



                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));


                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();


                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                    } else {


                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });
//        btn_fp.setOnClickListener(view -> {
//            androidx.biometric.BiometricPrompt.PromptInfo.Builder promtInfo = dialogMetric();
//            promtInfo.setNegativeButtonText("Cancel");
//            biometricPrompt.authenticate(promtInfo.build());
//        });
        //for button fingerprint or pattern or pin
        btn_fppin.setOnClickListener(view -> {
            androidx.biometric.BiometricPrompt.PromptInfo.Builder promtInfo = dialogMetric();
            promtInfo.setDeviceCredentialAllowed(true);
            biometricPrompt.authenticate(promtInfo.build());
        });
    }
    androidx.biometric.BiometricPrompt.PromptInfo.Builder dialogMetric(){
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
                info = "Đăng nhập bằng sinh trắc học";
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
        //btn_fp.setEnabled(enable);
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


    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>Bạn chưa có tài khoản. </font><font color='#0c0099'>Tạo tài khoản</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }


    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }


    public boolean validate() {
        boolean valid = false;


        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            //textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }


        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }

        return valid;
    }


}

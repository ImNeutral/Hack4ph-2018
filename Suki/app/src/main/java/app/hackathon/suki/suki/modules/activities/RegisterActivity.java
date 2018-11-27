package app.hackathon.suki.suki.modules.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.hackathon.suki.suki.R;
import app.hackathon.suki.suki.databinding.ActivityRegistrationBinding;
import app.hackathon.suki.suki.databinding.ActivitySplashScreenBinding;
import app.hackathon.suki.suki.helpers.ErrorHandler;
import app.hackathon.suki.suki.helpers.MySharedPreference;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegistrationBinding mBinding;
    private FirebaseAuth auth;
    String email, password,repass;

    String TAG = "RegisterActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        auth = FirebaseAuth.getInstance();

//        if (auth.getCurrentUser() !=null){
//            startActivity(new Intent(RegisterActivity.this, MainMenuActivity.class));
//            finish();
//        }

        mBinding.buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class );
                startActivity(intent);
                finish();
            }
        });

        mBinding.buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideKeyboard();
                email  = mBinding.editTextEmail.getInputString().trim();
                password = mBinding.editTextPassword.getInputString().trim();
                repass = mBinding.editTextConfPass.getInputString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please enter email address.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please enter password.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(repass)) {
                    Toast.makeText(getApplicationContext(), "Please enter password.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password should be greater than 5 characters.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isEmailValid(email)){
                    Toast.makeText(getApplicationContext(), "Please enter valid email address.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(repass)){
                    Toast.makeText(getApplicationContext(), "Your password and confirm password did not match.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e(email,password);

                showLoadingPrompt(true);
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e(TAG,"createUserWithEmail:onComplete"+task.isSuccessful());
                        showLoadingPrompt(false);
                        if (!task.isSuccessful()) {
                            ErrorHandler.showMessageDialogError("Please try again.\n"+task.getException(),RegisterActivity.this);
                        } else {
//                    successDialog();

                            int userType = mBinding.rgAccountType.getCheckedRadioButtonId();
                            if (userType != -1) {
                                RadioButton selectedRadioButton = findViewById(userType);
                                MySharedPreference.setUserType(getApplicationContext(),selectedRadioButton.getText().toString());
                            }
                            Toast.makeText(getApplicationContext(), "Successfully Registered.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,SetUpAccountActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }

                    }
                });

            }
        });
    }
    protected void hideKeyboard() {
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(
                mBinding.hehe.getWindowToken(), 0);
    }
    private void showLoadingPrompt(boolean isShown) {
        if (isShown) {
            mBinding.buttonSign.setEnabled(false);
            mBinding.buttonCreate.setEnabled(false);
            mBinding.progressBarLoading.bringToFront();
            mBinding.progressBarLoading.setVisibility(View.VISIBLE);
        } else {
            mBinding.buttonSign.setEnabled(true);
            mBinding.buttonCreate.setEnabled(true);
            mBinding.progressBarLoading.setVisibility(View.GONE);
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

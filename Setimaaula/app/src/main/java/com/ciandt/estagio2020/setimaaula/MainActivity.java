package com.ciandt.estagio2020.setimaaula;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {


    private EditText email;
    private EditText password;
    private Button button;
    private ProgressBar progress;
    private View progressBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_email);
        button = findViewById(R.id.button_login);
        progress = findViewById(R.id.progressBar);
        progressBack = findViewById(R.id.progress_back);

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //enableButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        email.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                //enableButton();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }


    private void enableButton() {
        if (email.getText().length() > 0 &&
                password.getText().length() > 0) {
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Invalid password or email!");
        dialogBuilder.setTitle("Login error");
        dialogBuilder.setIcon(R.drawable.ic_alert);
        dialogBuilder.setNegativeButton("Cancel", null);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToNextScreen();
            }
        });
        dialogBuilder.show();
    }

    private void sendMessage() {
        loading(true);
        Log.d("tag", "Step 1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("tag", "Step 2");
                callAPI();
                Log.d("tag", "Step 3");
            }
        }).start();
        Log.d("tag", "Step 4");
    }

    private void callbackAPI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loading(false);
                showDialog();
            }
        });
    }

    private void callAPI() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callbackAPI();
    }

    private void loading(boolean isLoading)  {
        if (isLoading) {
            progress.setVisibility(View.VISIBLE);
            progressBack.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.GONE);
            progressBack.setVisibility(View.GONE);
        }
    }


    private void goToNextScreen() {
        Intent intent = new Intent(this, MainDataBindingActivity.class);
        startActivity(intent);
    }

}

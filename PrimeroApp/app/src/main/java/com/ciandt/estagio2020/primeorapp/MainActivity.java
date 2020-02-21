package com.ciandt.estagio2020.primeorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    private int qtd = 0;

    private View.OnClickListener outroClique = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto.setText("Clicou - "+qtd);
                qtd += 1;

                Random rand = new Random();
                int cor = Color.BLUE;
                int rand_int1 = rand.nextInt(0xFFFFFFF);
                findViewById(R.id.fundo).setBackgroundColor(rand_int1);
            }
        };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dois);

        texto = findViewById(R.id.text_view);
        texto.setText("AHUAHUAHAUH");

        Log.d("MainActivity","onCreate");
        ImageButton btn = findViewById(R.id.btn_main);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                texto.setText("Clicou - "+qtd);
//                qtd += 1;
//            }
//        });
    }

    public void clique(View v) {
        texto.setText("AAAAAAA - "+qtd);
        qtd += 2;
        ImageButton btn = findViewById(R.id.btn_main);
        btn.setOnClickListener(outroClique);
        Log.d("MainActivity","onClick");
    }

    @Override
    protected void onStart() {
        super.onStart();
        texto.setText("onStrt");
        Log.d("MainActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        texto.setText("onResume");
        Log.d("MainActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        texto.setText("onPause");
        Log.d("MainActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","onDestroy");
    }
}

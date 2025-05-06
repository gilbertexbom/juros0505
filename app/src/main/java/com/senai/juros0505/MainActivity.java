package com.senai.juros0505;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Objetos
    EditText txtCapital;
    EditText txtJuros;
    RadioButton rbtJuros;
    RadioButton rbtCapital;
    Button btnCalcular;
    Button btnLimpar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Instância
        txtJuros = (EditText) findViewById(R.id.txtJuros);
        txtCapital = (EditText) findViewById(R.id.txtCapital);
        rbtJuros = (RadioButton) findViewById(R.id.rbtJuros);
        rbtCapital = (RadioButton) findViewById(R.id.rbtCapital);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);

        rbtCapital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherOpcao();
            }
        });

        rbtJuros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolherOpcao();
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbtJuros.isChecked()){
                    txtJuros.setText("600");
                } else {
                    txtCapital.setText("2000");
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
            }
        });

    }

    public void escolherOpcao(){
        if(rbtJuros.isChecked()){
            txtJuros.setEnabled(false);
            txtCapital.setEnabled(true);
            txtJuros.setTextSize(30);
        }else{
            txtCapital.setEnabled(false);
            txtJuros.setEnabled(true);
            txtCapital.setTextSize(30);
        }

        limpar();
    }

    public void limpar(){
        txtJuros.setText(null);
        txtCapital.setText(null);
        txtJuros.setTextSize(18);
        txtCapital.setTextSize(18);
    }

}
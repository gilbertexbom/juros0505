package com.senai.juros0505;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Objetos
    EditText txtCapital;
    EditText txtJuros;
    RadioButton rbtJuros;
    RadioButton rbtCapital;
    Button btnCalcular;
    Button btnLimpar;

    private AlertDialog alerta;

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
                gerarAlerta();
                //gerarAlertaCustom();
                //gerarAlertaLista();
                //gerarAlertaListaCheque();
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

    public void gerarAlerta(){
        //Instância
        AlertDialog.Builder caixaDialogo = new AlertDialog.Builder(this);

        caixaDialogo.setTitle("Atenção!");
        caixaDialogo.setMessage("Presta atenção, mano!");
        caixaDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(
                        getApplicationContext(),
                        "Tem certeza?!",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        caixaDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(
                        getApplicationContext(),
                        "Forte abraço!",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        caixaDialogo.setCancelable(false);

        caixaDialogo.setIcon(android.R.drawable.ic_dialog_dialer);

        alerta = caixaDialogo.create();

        alerta.show();

    }

    public void gerarAlertaCustom(){
        LayoutInflater layout = getLayoutInflater();

        View view = layout.inflate(R.layout.alerta_tio, null);

        view.findViewById(R.id.btnSair).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        MainActivity.this,
                        "Opa!",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        AlertDialog.Builder caixaMensagem2 = new AlertDialog.Builder(this);

        caixaMensagem2.setTitle("Ei!");

        caixaMensagem2.setView(view);

        alerta = caixaMensagem2.create();

        alerta.show();
    }

    public void gerarAlertaLista(){
        ArrayList<String> itens = new ArrayList<>();

        itens.add("Outono");
        itens.add("Inverno");
        itens.add("Primavera");
        itens.add("Verão");

        ArrayAdapter adaptador = new ArrayAdapter(
                this,
                R.layout.alerta_tio2,
                itens
        );

        AlertDialog.Builder caixaMensagem = new AlertDialog.Builder(this);
        caixaMensagem.setTitle("Estação do Ano");

        caixaMensagem.setSingleChoiceItems(adaptador, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(
                        MainActivity.this,
                        "Estação Selecionada " + dialog.toString(),
                        Toast.LENGTH_SHORT
                ).show();
                alerta.dismiss();
            }
        });
        alerta = caixaMensagem.create();
        alerta.show();
    }

    public void gerarAlertaListaCheque(){
        CharSequence[] charSequences = new CharSequence[]{"Banana", "Laranja", "Melão", "Uva"};

        final boolean[] checados = new boolean[charSequences.length];

        AlertDialog.Builder caixaDialogo = new AlertDialog.Builder(this);

        caixaDialogo.setTitle("Qual sua fruta favorita?");

        caixaDialogo.setMultiChoiceItems(charSequences, checados, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checados[which] = isChecked;
            }
        });

        caixaDialogo.setPositiveButton("Confirma?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder texto = new StringBuilder("Checados: ");
                for(boolean ch:checados){
                    texto.append(ch).append("; ");
                }
                Toast.makeText(
                        MainActivity.this,
                        texto.toString(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        alerta = caixaDialogo.create();
        alerta.show();
    }

}
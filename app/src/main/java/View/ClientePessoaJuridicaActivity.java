package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import Model.Cliente;
import Model.ClientePJ;
import Util.AppUtil;
import app.daazi.v1.appclientevip.R;

public class ClientePessoaJuridicaActivity extends AppCompatActivity {

    Cliente novoVIP;
    ClientePJ novoClientePJ;
    private SharedPreferences preferences;

    EditText editCnpj;
    EditText editRazaoSocial;
    EditText editDataAbertura;
    CheckBox ckSimplesNacional;
    CheckBox ckMei;
    
    Button btnSalvareConcluirPJ;
    Button btnVoltar;
    Button btnCancelar;

    boolean isFormularioOK;
    boolean isSimplesNacional;
    boolean isMei;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_juridica);

        initFormulario();

        btnSalvareConcluirPJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()){
                    novoClientePJ.setCnpj(editCnpj.getText().toString());
                    novoClientePJ.setRazaoSocial((editRazaoSocial.getText().toString()));
                    novoClientePJ.setDataAbertura(editDataAbertura.getText().toString());
                    novoClientePJ.setSimplesNacional(isSimplesNacional);
                    novoClientePJ.setMei(isMei);

                    salvarSharedPreferences();

                    Intent intent = new Intent(ClientePessoaJuridicaActivity.this,
                            LoginActivity.class);
                    startActivity(intent);

                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientePessoaJuridicaActivity.this,
                        LoginActivity.class);
            }
        });


    }

    public void simplesNacional(View view){

        isSimplesNacional = ckSimplesNacional.isChecked();

    }

    public void Mei(View view){

        isMei = ckMei.isChecked();

    }

    private void initFormulario() {
        editCnpj= findViewById(R.id.editCnpj);
        editRazaoSocial= findViewById(R.id.editRazaoSocial);
        editDataAbertura= findViewById(R.id.editDataAbertura);
        btnSalvareConcluirPJ = findViewById(R.id.btnSalvareConcluirPJ);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnVoltar = findViewById(R.id.btnVoltar);
        ckSimplesNacional= findViewById(R.id.cKSimplesNacional);
        ckMei= findViewById(R.id.cKMei);

        novoClientePJ = new ClientePJ();
        novoVIP = new Cliente();

        restaurarSharedPreferences();
    }

    private boolean validarFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(editCnpj.getText().toString())) {
            editCnpj.setError("*");
            editCnpj.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editRazaoSocial.getText().toString())) {
            editRazaoSocial.setError("*");
            editRazaoSocial.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editDataAbertura.getText().toString())) {
            editDataAbertura.setError("*");
            editDataAbertura.requestFocus();
            retorno = false;
    }

        return retorno;
    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
    }
}
package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Model.Cliente;
import Model.ClientePF;
import Util.AppUtil;
import app.daazi.v1.appclientevip.R;

public class ClientePessoaFisicaActivity extends AppCompatActivity {

    Cliente novoVIP;
    ClientePF novoClientePF;
    private SharedPreferences preferences;

    EditText editCpf;
    EditText editNomeCompleto;
    Button btnSalvareConcluirPF;
    Button btnVoltar;
    Button btnCancelar;

    boolean isFormularioOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_pessoa_fisica);

        initFormulario();

        btnSalvareConcluirPF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {
                    novoClientePF.setCpf(editCpf.getText().toString());
                    novoClientePF.setNomeCompleto((editNomeCompleto.getText().toString()));

                    salvarSharedPreferences();

                    Intent intent = new Intent(ClientePessoaFisicaActivity.this,
                            LoginActivity.class);
                    startActivity(intent);

                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientePessoaFisicaActivity.this, LoginActivity.class);
            }
        });

    }

    private void initFormulario() {

        editCpf = findViewById(R.id.editCpf);
        editNomeCompleto = findViewById(R.id.editNomeCompleto);
        btnSalvareConcluirPF = findViewById(R.id.btnSalvareConcluirPF);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnVoltar = findViewById(R.id.btnVoltar);
        novoClientePF = new ClientePF();
        novoVIP = new Cliente();

        restaurarSharedPreferences();
    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
    }

    private boolean validarFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(editCpf.getText().toString())) {
            editCpf.setError("*");
            editCpf.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editNomeCompleto.getText().toString())) {
            editNomeCompleto.setError("*");
            editNomeCompleto.requestFocus();
            retorno = false;
        }
        return retorno;
    }
}


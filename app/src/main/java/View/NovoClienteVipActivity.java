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
import Util.AppUtil;
import app.daazi.v1.appclientevip.R;

public class NovoClienteVipActivity extends AppCompatActivity {

    //declarar objetos

    Cliente novoVip;
    private SharedPreferences preferences;

    //declarar layout.

    EditText editPrimeiroNome;
    EditText editSobrenome;
    CheckBox ckPessoaFisica;
    Button btnSalvarEContinuar;
    Button btnCancelar;

    boolean isFormularioOK;
    boolean isPessoaFisica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_cliente_vip);


        initFormulario();


        btnSalvarEContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {

                    novoVip.setPrimeiroNome(editPrimeiroNome.getText().toString());
                    novoVip.setSobrenome(editSobrenome.getText().toString());
                    novoVip.setPessoaFisica(isPessoaFisica);

                    salvarSharedPreferences();

                    if (isPessoaFisica) {
                        Intent intent = new Intent(NovoClienteVipActivity.this,
                                ClientePessoaFisicaActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(NovoClienteVipActivity.this,
                                ClientePessoaJuridicaActivity.class);
                        startActivity(intent);
                    }

                }
            }


            private boolean validarFormulario() {
                boolean retorno = true;
                if (TextUtils.isEmpty(editPrimeiroNome.getText().toString())) {
                    editPrimeiroNome.setError("*");
                    editPrimeiroNome.requestFocus();
                    retorno = false;
                }
                if (TextUtils.isEmpty(editSobrenome.getText().toString())) {
                    editSobrenome.setError("*");
                    editSobrenome.requestFocus();
                    retorno = false;
                }

                return retorno;
            }
        });

    }

        public void pessoaFisica (View view){

            isPessoaFisica = ckPessoaFisica.isChecked();
        }

        private void restaurarSharedPreferences() {
            preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        }

        private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("primeiroNome", novoVip.getPrimeiroNome());
        dados.putString("Sobrenome", novoVip.getSobrenome());
        dados.putBoolean("pessoaFisica", novoVip.isPessoaFisica());
        dados.apply();
    }

        private void initFormulario() {

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenome = findViewById(R.id.editSobrenome);
        ckPessoaFisica = findViewById(R.id.cKPessoaFisica);
        btnSalvarEContinuar = findViewById(R.id.btnSalvarEContinuar);
        btnCancelar = findViewById(R.id.btnCancelar);
        isFormularioOK = false;
        novoVip = new Cliente();

        restaurarSharedPreferences();

    }


}




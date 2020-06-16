package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

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

                        Intent intent = new Intent(NovoClienteVipActivity.this,
                                ClientePessoaFisicaActivity.class);
                        startActivity(intent);

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

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FancyAlertDialog.Builder(NovoClienteVipActivity.this)
                        .setTitle("Confirma o cancelamento")
                        .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                        .setMessage("Deseja realmente cancelar o cadastro de um novo Cliente Vip?")
                        .setNegativeBtnText("NÂO")
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                        .setPositiveBtnText("SIM")
                        .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                        .setAnimation(Animation.POP)
                        .isCancellable(true)
                        .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                        .OnPositiveClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getApplicationContext(),"Cancelado com Sucesso...",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getApplicationContext(),"Continue com seu Cadastro...",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();


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

        dados.putString("PrimeiroNome", novoVip.getPrimeiroNome());
        dados.putString("Sobrenome", novoVip.getSobrenome());
        dados.putBoolean("PessoaFisica", novoVip.isPessoaFisica());
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




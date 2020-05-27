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
                            CredencialDeAcessoActivity.class);
                    startActivity(intent);

                }
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

        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientePessoaJuridicaActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FancyAlertDialog.Builder(ClientePessoaJuridicaActivity.this)
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

    public void mei (View view){

        isMei = ckMei.isChecked();
    }

    public void simplesNacional (View view){

        isSimplesNacional = ckSimplesNacional.isChecked();
    }

    private void restaurarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("Cnpj", editCnpj.getText().toString());
        dados.putString("RazaoSocial", editRazaoSocial.getText().toString());
        dados.putBoolean("simplesNacional", isSimplesNacional);
        dados.putBoolean("Mei", isMei);
        dados.putString("dataAbertura",editDataAbertura.getText().toString());
        dados.apply();
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

}
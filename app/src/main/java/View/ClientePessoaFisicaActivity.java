package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

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
    boolean isPessoaFisica;

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

                    Intent intent;

                    if (isPessoaFisica)
                        intent = new Intent(ClientePessoaFisicaActivity.this,
                                CredencialDeAcessoActivity.class);

                    else

                        intent = new Intent(ClientePessoaFisicaActivity.this,
                                ClientePessoaJuridicaActivity.class);

                    startActivity(intent);
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientePessoaFisicaActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FancyAlertDialog.Builder(ClientePessoaFisicaActivity.this)
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
                                Toast.makeText(getApplicationContext(), "Cancelado com Sucesso...", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getApplicationContext(), "Continue com seu Cadastro...", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();


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
        isPessoaFisica = preferences.getBoolean("pessoaFisica", true);
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("cpf", editCpf.getText().toString());
        dados.putString("nomeCompleto", editNomeCompleto.getText().toString());
        dados.apply();
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


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

import Util.AppUtil;
import app.daazi.v1.appclientevip.R;

public class CredencialDeAcessoActivity extends AppCompatActivity {

    /**
     * 1º Passo: Declarar todos os objetos que precisavamos e está na tela.
     **/

    Button btnCadastrar;
    EditText editNome;
    EditText editEmail;
    EditText editSenhaA;
    EditText editSenhaB;
    CheckBox ckTermo;
    boolean isFormularioOK;
    boolean isPessoaFisica;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        /**
         2º Passo: Criar o unit formulario
         **/
        initFormulario();

        /**
         3º Passo: Obter o evento do cada botão e validar os dados digitados.
         **/
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isFormularioOK = true;

                if (TextUtils.isEmpty(editNome.getText().toString())) {
                    editNome.setError("!");
                    editNome.requestFocus();
                    isFormularioOK = false;
                }

                if (TextUtils.isEmpty(editEmail.getText().toString())) {
                    editEmail.setError("!");
                    editEmail.requestFocus();
                    isFormularioOK = false;
                }

                if (TextUtils.isEmpty(editSenhaA.getText().toString())) {
                    editSenhaA.setError("!");
                    editSenhaA.requestFocus();
                    isFormularioOK = false;
                }
                if (TextUtils.isEmpty(editSenhaB.getText().toString())) {
                    editSenhaB.setError("!");
                    editSenhaB.requestFocus();
                    isFormularioOK = false;
                }
                if (!ckTermo.isChecked()) {
                    isFormularioOK = false;
                }

                if (isFormularioOK) {

                    if (!validarSenha()) {
                        editSenhaA.setError("*");
                        editSenhaB.setError("b");
                        editSenhaA.requestFocus();
                        new FancyAlertDialog.Builder(CredencialDeAcessoActivity.this)
                                .setTitle("ATENÇÃO")
                                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                                .setMessage("As senhas digitas não conferem, por favor verificar!")
                               // .setNegativeBtnText("NÂO")
                                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                                .setPositiveBtnText("OK")
                             //  .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                                .setAnimation(Animation.POP)
                                .isCancellable(true)
                                .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                                .OnPositiveClicked(new FancyAlertDialogListener() {
                                    @Override
                                    public void OnClick() {
                                    }
                                })
                                .build();


                    }



                        /**
                         4º passo: Mudar de tela.
                         **/

                     else {

                         salvarSharedPreferences();

                    Intent iMenuPrincipal = new Intent(CredencialDeAcessoActivity.this, LoginActivity.class);
                    startActivity(iMenuPrincipal);
                    finish();
                    return;

                    }
                }


            }
        });

    }

    public void validarTermo(View view) {

        if (!ckTermo.isChecked()) {

            Toast.makeText(getApplicationContext(),
                    "É necessário aceitar os termos de uso para continuar com o cadastro...",
                    Toast.LENGTH_LONG).show();

        }
    }

    private void initFormulario() {

        btnCadastrar = findViewById(R.id.btnCadastrarNovoUsuario);
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenhaA = findViewById(R.id.editSenha);
        editSenhaB = findViewById(R.id.editSenhaB);
        ckTermo = findViewById(R.id.ckTermo);

        isFormularioOK = false;

        restaurarSharedPreferences();

    }

    public boolean validarSenha() {
        boolean retorno = false;
/**
 * parseInt transforma uma sequencia de numeros para inteiro.
 */
        int senhaA, senhaB;

        senhaA = Integer.parseInt(editSenhaA.getText().toString());
        senhaB = Integer.parseInt(editSenhaB.getText().toString());

        retorno = (senhaA == senhaB);
        return retorno;

    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("Email", editEmail.getText().toString());
        dados.putString("Senha", editSenhaA.getText().toString());
        dados.apply();
    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("PessoaFisica", true);
        if (isPessoaFisica)
            editNome.setText(preferences.getString("NomeCompleto", "Verifique os dados"));
        else
            editNome.setText(preferences.getString("RazaoSocial", "Verifique os dados"));

    }

}



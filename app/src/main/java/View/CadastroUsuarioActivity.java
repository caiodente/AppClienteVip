package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import app.daazi.v1.appclientevip.R;

public class CadastroUsuarioActivity extends AppCompatActivity {

    /**
     1º Passo: Declarar todos os objetos que precisavamos e está na tela.
     **/

    Button btnCadastrar;
    EditText editNome;
    EditText editEmail;
    EditText editSenhaA;
    EditText editSenhaB;
    CheckBox ckTermo;
    boolean isFormularioOK;


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

                if(TextUtils.isEmpty(editNome.getText().toString())){
                    editNome.setError(" ");
                    editNome.requestFocus();
                    isFormularioOK = false;
                }

                if(TextUtils.isEmpty(editEmail.getText().toString())){
                    editEmail.setError(" ");
                    editEmail.requestFocus();
                    isFormularioOK = false;
                }

                if(TextUtils.isEmpty(editSenhaA.getText().toString())){
                    editSenhaA.setError(" ");
                    editSenhaA.requestFocus();
                    isFormularioOK = false;
                }
                if(TextUtils.isEmpty(editSenhaB.getText().toString())){
                    editSenhaB.setError(" ");
                    editSenhaB.requestFocus();
                    isFormularioOK = false;
                }
                if (!ckTermo.isChecked()){
                    isFormularioOK = false;
                }

                if (isFormularioOK){

                    if (!validarSenha()){
                        editSenhaA.setError("*");
                        editSenhaB.setError("b");
                        editSenhaA.requestFocus();

                        Toast.makeText(getApplicationContext(),
                                "As senhas digitas não conferen...",
                                Toast.LENGTH_LONG).show();
                    /**
                     4º passo: Mudar de tela.
                     **/

                    } else {
                        Intent iMenuPrincipal = new Intent(CadastroUsuarioActivity.this, MainActivity.class);
                        startActivity(iMenuPrincipal);

                    }
                }


        }
        });

    }

    public void validarTermo(View view) {

        if (!ckTermo.isChecked()){

            Toast.makeText(getApplicationContext(),
                    "É necessário aceitar os termos de uso para continuar com o cadastro...",
                    Toast.LENGTH_LONG).show();

        }
    }

    private void initFormulario() {

        btnCadastrar = findViewById(R.id.btnCadastrarNovoUsuario);
        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        editSenhaA= findViewById(R.id.editSenha);
        editSenhaB = findViewById(R.id.editSenhaB);
        ckTermo =findViewById(R.id.ckTermo);

    }
    public boolean validarSenha(){
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




}



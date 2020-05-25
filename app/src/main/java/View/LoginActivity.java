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
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import Controller.ClienteController;
import Model.Cliente;
import Util.AppUtil;
import app.daazi.v1.appclientevip.R;

public class LoginActivity extends AppCompatActivity {

    //declarar objetos
    Cliente cliente;

    private SharedPreferences preferences;

    //declarar
    TextView txtRecuperarSenha;
    TextView txtLerPolitica;
    EditText editEmail;
    EditText editSenha;
    CheckBox ckLembrar;
    Button btnAcessar;
    Button btnSejaVip;

    boolean isFormularioOK;
    boolean isLembrarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initFormulario();
        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(isFormularioOK = validarFormulario()){

                   if(validarDadosDoUsuario()){

                       salvarSharedPreferences();

                       Intent intent = new Intent(LoginActivity.this,
                              MainActivity.class);

                       startActivity(intent);
                       finish();
                       return;
                   }
                   else{
                       Toast.makeText(getApplicationContext(),"Verifique os Dados...",
                               Toast.LENGTH_LONG).show();
                   }
               }

            }
        });

        btnSejaVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, NovoClienteVipActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


        //Obtendo eventos ao clicar nos botões

    txtRecuperarSenha.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intenet = new Intent(LoginActivity.this,RecuperarSenhaActivity.class);
            startActivity(intenet);


         /*   Toast.makeText(getApplicationContext(),
                    "Carregando tela de recuperação de senha...",
                    Toast.LENGTH_LONG).show();
         */
        }
    });






    txtLerPolitica.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            new FancyAlertDialog.Builder(LoginActivity.this)
                    .setTitle("Politica de privacidade $ termos de Uso")
                    .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                    .setMessage("O mundo é um lugar perigoso de se viver, não por causa daqueles que fazem o mal, " +
                            "mas sim por causa daqueles que observam e deixam o mal acontecer.")
                    .setNegativeBtnText("Discordo")
                    .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                    .setPositiveBtnText("Concordo")
                    .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                    .setAnimation(Animation.POP)
                    .isCancellable(true)
                    .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                    .OnPositiveClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {
                            Toast.makeText(getApplicationContext(),"Obrigado! Vamos, lá!",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .OnNegativeClicked(new FancyAlertDialogListener() {
                        @Override
                        public void OnClick() {
                            Toast.makeText(getApplicationContext(),"Desculpe não te atender :(",Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                        }
                    })
                    .build();
        }
    });




    }
    //validação de os camps digitados pelo usuários estão corretos e todos preenchidos.
    private boolean validarFormulario(){

        boolean retorno = true;

        if (TextUtils.isEmpty(editEmail.getText().toString())){
            editEmail.setError("*");
            editEmail.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(editSenha.getText().toString())){
            editSenha.setError("*");
            editSenha.requestFocus();
            retorno = false;
        }
        return retorno;
    }

    private void initFormulario() {


        txtRecuperarSenha = findViewById(R.id.txtRecuperarSenha);
        txtLerPolitica = findViewById(R.id.txtLerPolitica);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        ckLembrar = findViewById(R.id.cKLembrar);
        btnAcessar = findViewById(R.id.btnAcessar);
        btnSejaVip = findViewById(R.id.btnSejaVip);

        isFormularioOK = false;

        cliente = new Cliente();

        restaurarSharedPreferences();


    }

    public void lembrarSenha(View view) {
        isLembrarSenha = ckLembrar.isChecked();


    }

    public boolean validarDadosDoUsuario(){

        return ClienteController.validarDadosDoCliente(cliente,
                editEmail.getText().toString(),
                editSenha.getText().toString());
    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putBoolean("loginAutomatico", isLembrarSenha);
        dados.putString("emailCliente", editEmail.getText().toString());
        dados.apply();
    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

        cliente.setEmail(preferences.getString("email" ,"teste@teste.com.br"));
        cliente.setSenha(preferences.getString("senha" ,"@abc1234"));
        cliente.setPrimeiroNome(preferences.getString("primeironome" ,"Cliente"));
        cliente.setSobrenome(preferences.getString("sobrenome" ,"Fake"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoafisica" ,true));


        isLembrarSenha = preferences.getBoolean("loginAutomatico", false);
    }
}


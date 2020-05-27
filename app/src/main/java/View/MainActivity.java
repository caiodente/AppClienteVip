package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import Model.Cliente;
import Model.ClientePF;
import Model.ClientePJ;
import Util.AppUtil;
import app.daazi.v1.appclientevip.R;
/**
 * Tela principal, temos a hora e data atual no momento do App usando o Get no App util que controla dias e horas.
 * Temos a intent para tela de Cadastro de usuário.
 **/
public class MainActivity extends AppCompatActivity {

    Cliente cliente;
    ClientePJ clientePJ;
    ClientePF clientePF;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFormulario();


    }

    private void initFormulario() {

        cliente = new Cliente();
        clientePF = new ClientePF();
        clientePJ = new ClientePJ();

        restaurarSharedPreferences();

    }

    private void salvarSharedPreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

    }

    private void restaurarSharedPreferences() {

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);

        // Objeto Cliente
        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "nulo"));
        cliente.setSobrenome(preferences.getString("sobrenome", "nulo"));
        cliente.setEmail(preferences.getString("email", "nulo"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica", true));

        // Objeto cliente pessoa fisica

        clientePF.setCpf(preferences.getString("Cpf", "nulo"));
        clientePF.setNomeCompleto(preferences.getString("nomeCompleto", "nulo"));

        // Objeto cliente pessoa Juridica

        clientePJ.setCnpj(preferences.getString("Cnpj", "nulo"));
        clientePJ.setRazaoSocial(preferences.getString("razaoSocial", "nulo"));
        clientePJ.setSimplesNacional(preferences.getBoolean("simplesNscional", false));
        clientePJ.setMei(preferences.getBoolean("Mei", false));

        int teste =0;
    }
    public void meusDados(View view) {
    }

    public void atualizarMeusDados(View view) {
    }

    public void excluirMinhaConta(View view) {
    }

    public void consultarClientesVip(View view) {
    }

    public void sairDoAplicativo(View view) {

        new FancyAlertDialog.Builder(MainActivity.this)
                .setTitle("SAIR DO APLICATIVO")
                .setBackgroundColor(Color.parseColor("#303F9F"))  //Don't pass R.color.colorvalue
                .setMessage("Deseja realmente sair do aplicativo ?")
                .setNegativeBtnText("NÃO")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("SIM")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_star_border_black_24dp, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome()+", volte sempre!" ,
                                Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),"Ai sim, continue com a gente!",Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }

}

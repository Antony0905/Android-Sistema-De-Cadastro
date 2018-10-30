package usuario.app.sistemadecadastro;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import usuario.app.sistemadecadastro.dao.Create;
import usuario.app.sistemadecadastro.dao.Read;

public class MainActivity extends Activity {
    private ArrayList<Registro> aRegistro;

    TelaPrincipal tela_principal;
    TelaCadastroUsuario tela_cadastro;
    TelaListagemUsuarios tela_listagem;
    TelaEditar tela_editar;
    TelaExcluir tela_excluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aRegistro = new ArrayList<Registro>();
        tela_principal = new TelaPrincipal(this);
        tela_cadastro = new TelaCadastroUsuario(this, tela_principal);
        tela_editar = new TelaEditar(this, tela_principal);
        tela_excluir = new TelaExcluir(this, tela_principal);
        tela_listagem = new TelaListagemUsuarios(this, tela_principal,tela_editar);
        tela_principal.setTelaCadastro(tela_cadastro);
        tela_principal.setTelaListagem(tela_listagem);
        tela_principal.setTelaEditar(tela_editar);
        tela_principal.setTelaExcluir(tela_excluir);

        Create c = new Create(getApplicationContext());
        c.createTable();

        tela_principal.CarregarTela();
    }

    public ArrayList<Registro> getRegistros() {
        Read read = new Read(getApplicationContext());
        ArrayList<Registro> rArray = read.getPessoas();
        return rArray;
    }

    public void ExibirMensagem(String msg) {
        AlertDialog.Builder d = new
                AlertDialog.Builder(MainActivity.this);
        d.setTitle("Aviso");
        d.setMessage(msg);
        d.setNeutralButton("OK", null);
        d.show();
    }
}
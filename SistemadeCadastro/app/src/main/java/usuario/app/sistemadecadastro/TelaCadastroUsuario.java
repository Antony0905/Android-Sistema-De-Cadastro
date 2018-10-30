package usuario.app.sistemadecadastro;

import android.app.*;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import usuario.app.sistemadecadastro.dao.Delete;
import usuario.app.sistemadecadastro.dao.Read;
import usuario.app.sistemadecadastro.dao.Update;

public class TelaCadastroUsuario extends MainActivity {
    MainActivity act;
    EditText ednome, edendereco, edtelefone;
    Button btcadastrar, btcancelar_cadastro;
    TelaPrincipal tela_principal;

    public TelaCadastroUsuario(MainActivity act, TelaPrincipal
            tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void CarregarTela() {
        act.setContentView(R.layout.cadastro_de_usuario);
        ednome = (EditText) act.findViewById(R.id.ednome);
        edtelefone = (EditText) act.findViewById(R.id.edtelefone);
        edendereco = (EditText) act.findViewById(R.id.edendereco);
        btcadastrar = (Button) act.findViewById(R.id.btcadastrar);
        btcancelar_cadastro = (Button) act.findViewById(R.id.btcancelar_cadastro);


        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder dialogo = new
                        AlertDialog.Builder(act);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Cadastrar Usuário ?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Registro r = new Registro();
                                r.setNome(ednome.getText().toString());
                                r.setEndereco(edendereco.getText().toString());
                                r.setTelefone(edtelefone.getText().toString());

                                Update u = new Update(act);
                                if (u.insertPessoa(r)) {
                                    Toast.makeText(act, "Usuário Inserido com Sucesso", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(act, "Erro ao Inserir Usuário", Toast.LENGTH_SHORT).show();
                                }
                                //act.ExibirMensagem("Cadastro efetuado com sucesso.");
                                //tela_principal.CarregarTela();
                                CarregarTela();
                            }
                        });
                dialogo.show();
            }
        });

        btcancelar_cadastro.setOnClickListener(new
                                                       View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View view) {
                                                               /*AlertDialog.Builder dialogo = new
                                                                       AlertDialog.Builder(act);
                                                               dialogo.setTitle("Aviso");
                                                               dialogo.setMessage("Sair do cadastro ?");
                                                               dialogo.setNegativeButton("Não", null);
                                                               dialogo.setPositiveButton("Sim", new
                                                                       DialogInterface.OnClickListener() {
                                                                           @Override
                                                                           public void onClick(DialogInterface dialog,
                                                                                               int which) {
                                                                               tela_principal.CarregarTela();
                                                                           }
                                                                       });
                                                               dialogo.show();*/
                                                               tela_principal.CarregarTela();
                                                           }
                                                       });
    }
}
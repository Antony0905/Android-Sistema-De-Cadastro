package usuario.app.sistemadecadastro;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import usuario.app.sistemadecadastro.dao.Delete;
import usuario.app.sistemadecadastro.dao.Read;

public class TelaListagemUsuarios {
    MainActivity act;
    TelaPrincipal tela_principal;
    TelaEditar tela_editar;
    Button btanterior, btproximo, btfechar, btexcluir, bteditar;
    TextView txtid, txtnome, txttelefone, txtendereco, txtstatus;
    int index;

    public TelaListagemUsuarios(MainActivity act, TelaPrincipal
            tela_principal, TelaEditar tela_editar) {
        this.act = act;
        this.tela_principal = tela_principal;
        this.tela_editar = tela_editar;
        index = 0;
    }

    public void CarregarTela() {

        //Antes de carregar a tela, verifica se existe registros inseridos
        if (act.getRegistros().size() == 0) {
            (new AlertDialog.Builder(act))
                    .setTitle("Aviso")
                    .setMessage("Não existe nenhum registro cadastrado.")
                    .setNeutralButton("OK", null)
                    .show();
            return;
        }


        act.setContentView(R.layout.listagem_usuarios_cadastros);
        btanterior = (Button) act.findViewById(R.id.btanterior);
        btproximo = (Button) act.findViewById(R.id.btproximo);
        btfechar = (Button) act.findViewById(R.id.btfechar);
        btexcluir = (Button) act.findViewById(R.id.btexcluir);
        bteditar = (Button) act.findViewById(R.id.bteditar);
        txtnome = (TextView) act.findViewById(R.id.txtnome);
        txtid = (TextView) act.findViewById(R.id.txtid);
        txtendereco = (TextView) act.findViewById(R.id.txtendereco);
        txttelefone = (TextView) act.findViewById(R.id.txttelefone);
        txtstatus = (TextView) act.findViewById(R.id.txtstatus);
        PreencheCampos(index);
        AtualizaStatus(index);
        btanterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index > 0) {
                    index--;
                    PreencheCampos(index);
                    AtualizaStatus(index);
                }
            }
        });
        btproximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index < act.getRegistros().size() - 1) {
                    index++;
                    PreencheCampos(index);
                    AtualizaStatus(index);
                }
            }
        });
        btfechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela_principal.CarregarTela();
            }
        });

        btexcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete d = new Delete(act);
                boolean delete = false;
                delete = d.deletePessoa(act.getRegistros().get(index));
                if (index == act.getRegistros().size()){
                    index--;
                }

                if (act.getRegistros().size() == 0) {
                    if (delete) {
                        Toast.makeText(act, "Pessoa Excluída com Sucesso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(act, "Erro ao Excluir Pessoa", Toast.LENGTH_SHORT).show();
                    }
                    tela_principal.CarregarTela();
                    index = 0;
                }else{
                    if (delete) {
                        Toast.makeText(act, "Pessoa Excluída com Sucesso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(act, "Erro ao Excluir Pessoa", Toast.LENGTH_SHORT).show();
                    }
                    CarregarTela();
                }
            }
        });

        bteditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tela_editar.CarregarTela(act.getRegistros().get(index));
            }
        });
    }

    private void PreencheCampos(int idx) {
        txtid.setText(String.valueOf(act.getRegistros().get(idx).getId()));
        txtnome.setText(act.getRegistros().get(idx).getNome());
        txttelefone.setText(act.getRegistros().get(idx).getTelefone());
        txtendereco.setText(act.getRegistros().get(idx).getEndereco());
    }

    private void AtualizaStatus(int idx) {
        int total = act.getRegistros().size();
        txtstatus.setText("Registros : " + (idx + 1) + "/" + total);
    }
}
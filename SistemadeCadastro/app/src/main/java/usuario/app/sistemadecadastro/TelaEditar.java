package usuario.app.sistemadecadastro;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import usuario.app.sistemadecadastro.dao.Read;
import usuario.app.sistemadecadastro.dao.Update;

public class TelaEditar {

    MainActivity act;
    TelaPrincipal tela_principal;
    Button bt_listagem, bt_atualizar, bt_pesquisar, bt_limpar;
    EditText ednome, edendereco, edtelefone, edpesquisar;
    TextView txtid;
    String nome;

    public TelaEditar(MainActivity act, TelaPrincipal
            tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void CarregarTela(final Registro r) {
        act.setContentView(R.layout.tela_editar);
        bt_listagem = (Button) act.findViewById(R.id.btlistagem);
        bt_atualizar = (Button) act.findViewById(R.id.btatualizar);
        bt_pesquisar = (Button) act.findViewById(R.id.bt_pesquisar);
        bt_limpar = (Button) act.findViewById(R.id.btlimpar);
        edpesquisar = (EditText) act.findViewById(R.id.edpesquisar);
        ednome = (EditText) act.findViewById(R.id.ednome);
        edendereco = (EditText) act.findViewById(R.id.edendereco);
        edtelefone = (EditText) act.findViewById(R.id.edtelefone);
        txtid = (TextView) act.findViewById(R.id.txtid);

        txtid.setText(String.valueOf(r.getId()));
        ednome.setText(r.getNome());
        edendereco.setText(r.getEndereco());
        edtelefone.setText(r.getTelefone());
        bt_listagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tela_principal.CarregarTela();
            }
        });

        bt_pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = edpesquisar.getText().toString();
                Read read = new Read(act);
                Registro r = new Registro();
                r = read.findUsuarioByName(nome);

                if (r.getNome() == null) {
                    Toast.makeText(act, "Usuário não encontrado :(", Toast.LENGTH_SHORT).show();
                } else {
                    txtid.setText(String.valueOf(r.getId()));
                    ednome.setText(r.getNome());
                    edendereco.setText(r.getEndereco());
                    edtelefone.setText(r.getTelefone());
                }
            }
        });

        bt_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Registro r = new Registro();
                Update u = new Update(act);
                Read read = new Read(act);
                r.setId(Integer.parseInt(txtid.getText().toString()));
                r.setNome(ednome.getText().toString());
                r.setEndereco(edendereco.getText().toString());
                r.setTelefone(edtelefone.getText().toString());

                if (read.findUsuarioById(r.getId())) {
                    if (u.updatePessoa(r)) {
                        Toast.makeText(act, "Usuário Atualizado com Sucesso", Toast.LENGTH_SHORT).show();
                        txtid.setText("0");
                        ednome.setText("");
                        edendereco.setText("");
                        edtelefone.setText("");
                    } else {
                        Toast.makeText(act, "Erro ao Atualizar Usuário", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(act, "Usuário Não Encontrado Para Atualizar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtid.setText("0");
                ednome.setText("");
                edendereco.setText("");
                edtelefone.setText("");
            }
        });

    }
}

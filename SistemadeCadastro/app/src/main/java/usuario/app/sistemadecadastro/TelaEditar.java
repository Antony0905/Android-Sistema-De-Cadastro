package usuario.app.sistemadecadastro;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import usuario.app.sistemadecadastro.dao.Update;

public class TelaEditar {

    MainActivity act;
    TelaPrincipal tela_principal;
    Button bt_listagem, bt_atualizar;
    EditText ednome, edendereco, edtelefone;
    TextView txtid;

    public TelaEditar(MainActivity act, TelaPrincipal
            tela_principal) {
        this.act = act;
        this.tela_principal = tela_principal;
    }

    public void CarregarTela(final Registro r) {
        act.setContentView(R.layout.tela_editar);
        bt_listagem = (Button) act.findViewById(R.id.btlistagem);
        bt_atualizar = (Button) act.findViewById(R.id.btatualizar);
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
                tela_principal.CarregarTelaListagem();
            }
        });

        bt_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Registro r = new Registro();
                Update u = new Update(act);

                r.setId(Integer.parseInt(txtid.getText().toString()));
                r.setNome(ednome.getText().toString());
                r.setEndereco(edendereco.getText().toString());
                r.setTelefone(edtelefone.getText().toString());
                if (u.updatePessoa(r)) {
                    Toast.makeText(act, "Pessoa Atualizada com Sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(act, "Erro ao Atualizar Pessoa", Toast.LENGTH_SHORT).show();
                }

                tela_principal.CarregarTelaListagem();

            }
        });

    }
}

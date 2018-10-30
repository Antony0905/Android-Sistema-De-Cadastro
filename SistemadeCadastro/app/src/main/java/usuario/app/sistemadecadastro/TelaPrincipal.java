package usuario.app.sistemadecadastro;

import android.view.View;
import android.widget.Button;

public class TelaPrincipal {
    MainActivity act;
    Button btcadastrar_usuario;
    Button bt_listar_usuarios_cadastrados, bt_atualizar, bt_excluir;
    TelaCadastroUsuario tela_cadastro;
    TelaListagemUsuarios tela_listagem;
    TelaEditar telaEditar;
    TelaExcluir telaExcluir;

    public TelaPrincipal(MainActivity act) {
        this.act = act;
    }

    public void CarregarTela() {
        act.setContentView(R.layout.tela_principal);
        btcadastrar_usuario = (Button)
                act.findViewById(R.id.btcadastrar_usuario);
        bt_listar_usuarios_cadastrados = (Button)
                act.findViewById(R.id.bt_listar_usuarios_cadastrados);
        bt_atualizar = (Button) act.findViewById(R.id.btatualizar);
        bt_excluir = (Button) act.findViewById(R.id.btexcluir);

        btcadastrar_usuario.setOnClickListener(new
                                                       View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View view) {
                                                               tela_cadastro.CarregarTela();
                                                           }
                                                       });
        bt_listar_usuarios_cadastrados.setOnClickListener(new
                                                                  View.OnClickListener() {
                                                                      @Override
                                                                      public void onClick(View view) {
                                                                          tela_listagem.CarregarTela();
                                                                      }
                                                                  });
        bt_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registro r = new Registro();
                telaEditar.CarregarTela(r);
            }
        });

        bt_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registro r = new Registro();
                telaExcluir.CarregarTela(r);
            }
        });


    }

    public void setTelaCadastro(TelaCadastroUsuario tela_cadastro) {
        this.tela_cadastro = tela_cadastro;
    }

    public void setTelaListagem(TelaListagemUsuarios tela_listagem) {
        this.tela_listagem = tela_listagem;
    }

    public void setTelaEditar(TelaEditar telaEditar) {
        this.telaEditar = telaEditar;
    }

    public void setTelaExcluir(TelaExcluir telaExcluir) {
        this.telaExcluir = telaExcluir;
    }

    public void CarregarTelaListagem() {
        tela_listagem.CarregarTela();
    }
}
package br.com.darisson.agenda.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import br.com.darisson.agenda.R
import br.com.darisson.agenda.business.AgendaBusiness
import br.com.darisson.agenda.model.Contato
import br.com.darisson.agenda.model.User
import kotlinx.android.synthetic.main.activity_contatos.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_contatos.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        criandoConta()
        botaoLogin()
        criandoContato()
    }

    private fun botaoLogin() {
        button_login.setOnClickListener {
            var user = User()
            user.email = campo_email.text.toString()
            user.password = campo_senha.text.toString()

            AgendaBusiness.buscarUsuario(user, {

                Snackbar.make(button_login, getString(R.string.msg_login), Snackbar.LENGTH_SHORT).show()
            }, { messageResource ->

                Snackbar.make(button_login, messageResource, Snackbar.LENGTH_SHORT).show()
            })
        }
    }

    private fun criandoConta(){
        button_criar_Conta.setOnClickListener {
            var user = User()
            user.email = campo_email.text.toString()
            user.password = campo_senha.text.toString()

            AgendaBusiness.criarConta(user,{
                Snackbar.make(button_login, getString(R.string.msg_conta_criada), Snackbar.LENGTH_SHORT).show()
            }, { messageResource ->

                Snackbar.make(button_criar_Conta, messageResource, Snackbar.LENGTH_SHORT).show()
            })
        }

    }

    private fun criandoContato(){
        botao_Add.setOnClickListener {
            var contato = Contato()
            contato.email = text_email.text.toString()
            contato.name = text_nome.text.toString()
            contato.phone = text_telefone.text.toString()

            AgendaBusiness.criandoContato(contato,{
                Snackbar.make(button_login, getString(R.string.msg_conta_criada), Snackbar.LENGTH_SHORT).show()
            }, { messageResource ->

                Snackbar.make(button_criar_Conta, messageResource, Snackbar.LENGTH_SHORT).show()
            })
        }

    }
}

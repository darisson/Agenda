package br.com.darisson.agenda.business

import br.com.darisson.agenda.R
import br.com.darisson.agenda.database.AgendaDatabase
import br.com.darisson.agenda.model.Contato
import br.com.darisson.agenda.model.User
import br.com.darisson.agenda.network.AgendaNetwork

object AgendaBusiness {

    fun buscarUsuario(user: User, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {

        AgendaNetwork.entrar(user, { user: User ->
            user.let {
                AgendaDatabase.salvaUsuario(it) {
                    onSuccess()
                }
            }

        }, {
            onError(R.string.erro_busca_usuario)
        })
    }

    fun criarConta(user: User, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {
        AgendaNetwork.criarNovoUsuario(user, {
            onSuccess()
        }, {
            onError(R.string.msg_conta_criada)
        })
    }

    fun fazerLogout(user: User, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {
        AgendaNetwork.logout(user, {
            AgendaDatabase.limparBanco()
        }, {
            onError(R.string.logout_error)
        })

    }

    fun criandoContato(contato: Contato, onSuccess: () -> Unit, onError: (message: Int) -> Unit) {
        AgendaNetwork.criarContato(contato, {
            contato.let {
                AgendaDatabase.salvaContato(it) {
                    onSuccess()
                }
            }

        }, {
            onError
        })
    }
}

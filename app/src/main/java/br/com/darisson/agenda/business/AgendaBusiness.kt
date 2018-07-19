package br.com.darisson.agenda.business

import br.com.darisson.agenda.R
import br.com.darisson.agenda.database.AgendaDatabase
import br.com.darisson.agenda.model.User
import br.com.darisson.agenda.network.AgendaNetwork

object AgendaBusiness {

    fun buscarUsuario(user: User, onSuccess: () -> Unit, onError: (message: Int) -> Unit){

        AgendaNetwork.entrar(user, {

            AgendaDatabase.salvaUsuario(it.data) {
                onSuccess()
            }

        }, {
            onError(R.string.erro_busca_usuario)
        })

    }

}
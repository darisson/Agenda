package br.com.darisson.agenda.database

import br.com.darisson.agenda.model.Contato
import br.com.darisson.agenda.model.User
import io.realm.Realm

object AgendaDatabase {

    fun salvaUsuario(user: User, onSuccess: () -> Unit){

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealm(user)
            realm.commitTransaction()
            onSuccess()
        }
    }

    fun limparBanco(){
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()
        }
    }

    fun salvaContato(contato: Contato, onSuccess: () -> Unit){

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealm(contato)
            realm.commitTransaction()
            onSuccess()
        }
    }
}
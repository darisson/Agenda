package br.com.darisson.agenda.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User: RealmObject()  {

    @PrimaryKey
    var id: Int = 0
    var password: Int? = null
    var name: String? = null
    var email: String? = null
    var phone: String? = null
    var picture: String? = null
    var birth: Int? = null
    var uid: String? = null
    var client: String? = null
    var accessToken: String? = null
}
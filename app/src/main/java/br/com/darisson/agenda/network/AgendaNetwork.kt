package br.com.darisson.agenda.network

import br.com.darisson.agenda.model.Contato
import br.com.darisson.agenda.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AgendaNetwork {

    val agendaAPI by lazy {
        getRetrofit().create(AgendaAPI::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api-agenda-unifor.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    fun entrar(user: User, onSuccess: (user: User) -> Unit, onError: () -> Unit){

        agendaAPI.fazerLogin(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ responseUser ->

                    val user = responseUser.body()
                    user?.let {
                        it.accessToken = responseUser.headers()["accessToken"]
                        it.uid = responseUser.headers()["uid"]
                        it.client = responseUser.headers()["client"]

                        onSuccess(it)
                    }

                },{
                    onError()
                })
    }

    fun criarNovoUsuario(user: User, onSuccess: () -> Unit, onError: () -> Unit){
        agendaAPI.criarConta(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess()

                },{
                    onError()
                })
        }


    fun logout(user: User, onSuccess: () -> Unit, onError: () -> Unit){
        var uid: String = ""
        var accessToken: String = ""
        var client: String = ""

        user.uid?.let {
            uid = it
        }
        user.accessToken?.let {
            accessToken = it
        }
        user.client?.let {
            client = it
        }

        agendaAPI.fazerLogout(uid, client, accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess()

                },{
                    onError()
                })
        }

    fun criarContato(contato: Contato, onSuccess: () -> Unit, onError: () -> Unit){
        var uid: String = ""
        var accessToken: String = ""
        var client: String = ""

        contato.uid?.let {
            uid = it
        }
        contato.accessToken?.let {
            accessToken = it
        }
        contato.client?.let {
            client = it
        }

        agendaAPI.criarContato(uid, client, accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess()

                },{
                    onError()
                })
            }
}
package br.com.darisson.agenda.network

import br.com.darisson.agenda.model.Data
import br.com.darisson.agenda.model.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AgendaAPI {

    @POST("/auth/sign_in")
    fun fazerLogin(@Body user: User): Observable<Data>


//    @GET("users/{username}/repos")
//    fun buscarRepositorios(@Path("username") username: String): Observable<List<>>
}
package br.com.darisson.agenda.network

import br.com.darisson.agenda.model.Contato
import br.com.darisson.agenda.model.Data
import br.com.darisson.agenda.model.User
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface AgendaAPI {

    @POST("/auth")
    fun criarConta(@Body user: User): Observable<Data>

    @POST("/auth/sign_in")
    fun fazerLogin(@Body user: User): Observable<Response<User>>

    @DELETE("/auth/sign_out")
    fun fazerLogout(@Header("uid") uid:String, @Header("client") client: String, @Header("accessToken") accessToken: String): Observable<Data>

    @POST("/contacts")
    fun criarContato(@Header("uid") uid:String, @Header("client") client: String, @Header("accessToken") accessToken: String): Observable<Response<Contato>>


}
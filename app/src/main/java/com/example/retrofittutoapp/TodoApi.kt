package com.example.retrofittutoapp

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TodoApi {


    @GET()
    suspend fun getTodos(): Response<List<Todo>>


    //fun getTodo(@Query(value = "Key") Key: String): Response<List<Todo>> //to get complex response from the server
    //@POST(value = "/createTodo")
    //fun createTodo(@Body todo: Todo): Response< CreateTodoResponse>


}
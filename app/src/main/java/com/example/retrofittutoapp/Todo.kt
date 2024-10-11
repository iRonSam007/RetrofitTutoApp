package com.example.retrofittutoapp

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)


//https://jsonplaceholder.typicode.com/users: Alternativly we can use this.
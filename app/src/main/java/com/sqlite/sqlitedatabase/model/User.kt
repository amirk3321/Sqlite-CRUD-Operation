package com.sqlite.sqlitedatabase.model

data class User(val name : String,val email : String,val password : String) {
    constructor() : this("","","")
}
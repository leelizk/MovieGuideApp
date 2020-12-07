package com.example.movieguideapp.data.remote

class MovieConstants {

    companion object{
        const val V3_BASE_URL = "https://api.themoviedb.org/3/";
        const val V4_BASE_URL = "https://api.themoviedb.org/4/"
        //1 通过 api_key 获取 session_id
        const val GUEST_SESSION_NEW = "/authentication/guest_session/new"
        const val SESSION_NEW = "/authentication/session/new"
        const val SESSION_NEW_V4 = "/authentication/session/convert/4"
        const val TOKEN_NEW = "/authentication/token/new"

        const val API_KEY = "c70e7139a4a0d7295d75c4d1394cbbe1"
        const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNzBlNzEzOWE0YTBkNzI5NWQ3NWM0ZDEzOTRjYmJlMSIsInN1YiI6IjVmY2M5MGJjZTMyOTQzMDAzZDNmYjUxZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.oP6rZeuKJezPtNn-q1KrRp9ZABxYfcRYVuM-dzeTh5Y"

        //官方电影分级？
        const val OFFICIAL_LIST = "/certification/movie/list"

        //https://github.com/holgerbrandl/themoviedbapi/

        //https://github.com/Omertron/api-themoviedb/
    }
}
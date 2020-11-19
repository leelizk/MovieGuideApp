package com.example.movieguideapp.base.net

/* ktlint-disable no-wildcard-imports */
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * This interface will be consumed by the Retrofit library to make API calls.
 */
interface RetrofitService {
	@GET
	fun get(@Url url: String, @HeaderMap headers: Map<String, String>): Call<String>

	@GET
	fun get(
		@Url url: String,
		@QueryMap parameters: Map<String, String>,
		@HeaderMap headers: Map<String, String>
	): Call<String>

	@POST
	@FormUrlEncoded
	fun post(
		@Url url: String,
		@FieldMap parameters: Map<String, String>,
		@HeaderMap headers: Map<String, String>
	): Call<String>

	@POST
	fun post(@Url url: String, @Body body: RequestBody, @HeaderMap headers: Map<String, String>): Call<String>

	@PATCH
	@FormUrlEncoded
	fun patch(
		@Url url: String,
		@FieldMap parameters: Map<String, String>,
		@HeaderMap headers: Map<String, String>
	): Call<String>

	@PATCH
	fun patch(@Url url: String, @Body body: RequestBody, @HeaderMap headers: Map<String, String>): Call<String>

	@PUT
	@FormUrlEncoded
	fun put(
		@Url url: String,
		@FieldMap parameters: Map<String, String>,
		@HeaderMap headers: Map<String, String>
	): Call<String>

	@DELETE
	fun delete(@Url url: String, @HeaderMap headers: Map<String, String>): Call<String>
}

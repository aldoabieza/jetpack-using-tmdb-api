package com.something.subfirstjetpack.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShow(

        @field:SerializedName("first_air_date")
        val firstAirDate: String,

        @field:SerializedName("id")
        val id: Int,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("overview")
        val overview: String,

        @field:SerializedName("poster_path")
        val posterPath: String,

        @field:SerializedName("vote_average")
        val voteAverage: Double

)

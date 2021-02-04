package com.something.subfirstjetpack.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Movies(

        @field:SerializedName("id")
        var id: Int,

        @field:SerializedName("overview")
        var overview: String,

        @field:SerializedName("poster_path")
        var posterPath: String,

        @field:SerializedName("release_date")
        var firstAirDate: String,

        @field:SerializedName("title")
        var name: String,

        @field:SerializedName("vote_average")
        var voteAverage: Double,

)

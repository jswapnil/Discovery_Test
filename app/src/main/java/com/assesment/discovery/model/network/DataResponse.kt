package com.assesment.discovery.model.network


import androidx.annotation.NonNull

class DataResponse<ResultType> {

    val data: ResultType?

    val status: Status

    constructor(@NonNull data: ResultType) {
        this.status = Status.SUCCESS
        this.data = data
    }

    constructor(@NonNull status: Status) {
        this.status = status
        this.data = null
    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

}
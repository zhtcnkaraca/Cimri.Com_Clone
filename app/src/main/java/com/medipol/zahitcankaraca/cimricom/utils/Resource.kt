package com.medipol.zahitcankaraca.cimricom.utils

sealed class Resource<T>(
    val data: T?,
    val throwable: Throwable?,
    val status: ResourceStatus
) {
    class Loading<T> : Resource<T>(null, null, ResourceStatus.LOADING)
    class Success<T>(data: T?) : Resource<T>(data, null, ResourceStatus.SUCCESS)
    class Error<T>(exception: Exception) : Resource<T>(null, exception, ResourceStatus.ERROR)
}

enum class ResourceStatus {
    LOADING,
    SUCCESS,
    ERROR
}

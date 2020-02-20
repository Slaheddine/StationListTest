package me.test.jcdecaux.domain.usercases

interface UseCaseCallBack<T> {
    fun onSuccess(t : T)
    fun onFailure(error : Throwable)
}
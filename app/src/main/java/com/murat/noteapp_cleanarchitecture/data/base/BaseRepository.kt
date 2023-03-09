package com.murat.noteapp_cleanarchitecture.data.base

import com.murat.noteapp_cleanarchitecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.delay

import java.io.IOException

abstract class BaseRepository {
    protected fun <T> doRequest(response : suspend ()-> T )= flow{
        emit(Resource.Loading())
        delay(2000)
        try {
            val data = response()
            emit(Resource.Success(data))
        }catch (ioException: IOException){
            emit(Resource.Error(ioException.localizedMessage?:"Unknown exception"))
        }
    }.flowOn(Dispatchers.IO)
}
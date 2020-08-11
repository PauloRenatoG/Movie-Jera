package br.com.jera.moviejera.data.util

abstract class Mapper<I, O> {
    abstract fun transform(t: I): O
    fun transformList(items: List<I>?): List<O>? {
        return items?.map(::transform)
    }
}
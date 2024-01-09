package openway.com.adapter

interface EntityAdapter<T, B> {
    fun toBusiness(entity: T): B
}
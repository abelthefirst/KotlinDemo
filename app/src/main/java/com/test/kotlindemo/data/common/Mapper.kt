package com.test.kotlindemo.data.common

interface Mapper<T, R> {

    fun map(from: T): R

    fun map(from: List<T>): List<R> = from.map { map(it) }

}
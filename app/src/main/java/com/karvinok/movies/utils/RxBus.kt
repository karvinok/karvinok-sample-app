package com.karvinok.movies.utils

/*
 * Copyright (c) 2011-2018, Zingaya, Inc. All rights reserved.
 */

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus {

    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventClass: Class<T>): Observable<T> = publisher.ofType(eventClass)

}
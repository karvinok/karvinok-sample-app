package com.karvinok.sample.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface DisposableProcessor {
    val disposable : CompositeDisposable

    fun addDispose(job: () -> Disposable) = disposable.add(job.invoke())

    fun addDisposes(vararg jobs: Disposable) = disposable.addAll(*jobs)

    fun addAllDisposes(list: List<Disposable>) = list.forEach { disposable.add(it) }
}
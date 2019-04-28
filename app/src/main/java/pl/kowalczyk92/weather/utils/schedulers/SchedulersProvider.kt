package pl.kowalczyk92.weather.utils.schedulers

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}
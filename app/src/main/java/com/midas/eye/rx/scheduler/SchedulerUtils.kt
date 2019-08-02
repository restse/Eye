package com.midas.eye.rx.scheduler

/**
 * Created by midas on 2019/05/28.
 * desc:
 */

object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}

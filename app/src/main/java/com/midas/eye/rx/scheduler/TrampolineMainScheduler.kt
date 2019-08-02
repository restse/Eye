package com.midas.eye.rx.scheduler

import com.midas.eye.rx.scheduler.BaseScheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by midas on 2019/05/28.
 * desc:
 */


class TrampolineMainScheduler<T> private constructor() :
    BaseScheduler<T>(Schedulers.trampoline(), AndroidSchedulers.mainThread())

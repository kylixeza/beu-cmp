package beukmm.util

import co.touchlab.kermit.Logger
import kotlinx.datetime.Clock

class TimerBenchmark {

    private var _startTime = 0L
    private var _endTime = 0L

    fun start() {
        _startTime = Clock.System.now().toEpochMilliseconds()
        Logger.i { "TimerBenchmark start: $_startTime" }
    }

    fun stop() {
        _endTime = Clock.System.now().toEpochMilliseconds()
        Logger.i { "TimerBenchmark stop: $_endTime" }
    }

    fun elapsedTime(): Int {
        return ((_endTime - _startTime).toInt() / 1000)
    }

    fun convertToMinutes(timeInSecond: Int): String {
        val minutes = timeInSecond / 60
        val seconds = timeInSecond % 60
        return "$minutes menit $seconds detik"

    }

    fun clear() {
        _startTime = 0L
        _endTime = 0L
    }
}
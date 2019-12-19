package com.baegoon.protocol.extension

import com.google.protobuf.Timestamp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun Timestamp.toLocalDate(zoneId: ZoneId = ZoneId.systemDefault()): LocalDate {
    return Instant
        .ofEpochSecond(seconds, nanos.toLong())
        .atZone(zoneId)
        .toLocalDate()
}

fun LocalDate.toTimestampProto(zoneId: ZoneId, hour: Int = 0, minute: Int = 0, second: Int = 0): Timestamp {
    return this.atTime(hour, minute, second)
        .atZone(zoneId)
        .toInstant()
        .let {
            Timestamp.newBuilder()
                .setSeconds(it.epochSecond)
                .setNanos(it.nano)
                .build()
        }
}

package kr.co.kickoffer.authexample.common.db

import java.nio.ByteBuffer
import java.util.UUID

fun randomUUIDBytes(): ByteArray {
    val uuid = UUID.randomUUID()
    return ByteBuffer
        .allocate(16)
        .putLong(uuid.mostSignificantBits)
        .putLong(uuid.leastSignificantBits)
        .array()
}

fun ByteArray.toUUID(): UUID {
    val buf = ByteBuffer.wrap(this)
    return UUID(buf.long, buf.long)
}
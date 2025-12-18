package com.example.et_clearapp.network

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateAdapter : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    override fun serialize(src: LocalDate?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDate {
        val value = json?.asString ?: throw JsonParseException("LocalDate null")
        return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE)
    }
}

class LocalDateTimeAdapter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    override fun serialize(src: LocalDateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDateTime {
        val value = json?.asString ?: throw JsonParseException("LocalDateTime null")
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }
}

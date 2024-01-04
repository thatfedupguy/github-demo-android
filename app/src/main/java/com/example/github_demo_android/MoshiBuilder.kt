package com.example.github_demo_android

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.math.BigDecimal
import javax.annotation.Nullable
import okio.Buffer
import org.json.JSONObject

object MoshiBuilder {
    fun getInstance(): Moshi {
        return Moshi.Builder()
            .add(JSONObjectAdapter)
            .add(StringAdapter)
            .add(BooleanAdapter)
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    private object BooleanAdapter {
        @FromJson
        fun fromJson(@Nullable value: Boolean?): Boolean {
            return value ?: false
        }

        @ToJson
        fun toJson(@Nullable value: Boolean?) = value ?: false
    }

    private object StringAdapter {
        @FromJson
        fun fromJson(@Nullable value: String?): String? {
            return value
        }

        @ToJson
        fun toJson(@Nullable value: String?) = value?.trim()
    }

    private object JSONObjectAdapter {

        @FromJson
        fun fromJson(reader: JsonReader): JSONObject? {
            val data = reader.readJsonValue()
            return if (data == JsonReader.Token.BEGIN_OBJECT) {
                JSONObject(data as Map<*, *>)
            } else {
                null
            }
        }

        @ToJson
        fun toJson(writer: JsonWriter, value: JSONObject?) {
            value?.let { writer.value(Buffer().writeUtf8(value.toString())) }
        }
    }
}
@file:JsQualifier("chrome.storage")
package chrome.storage

import kotlin.js.Json

external val local: Storage

external interface Storage {
    fun get(key: String?, callback: (Json) -> Unit)
    fun remove(key: String, callback: () -> Unit)
    fun set(items: Json, callback: () -> Unit)
}

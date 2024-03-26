@file:JsQualifier("chrome.runtime")
package chrome.runtime

external val onMessage: OnMessage

external interface OnMessage {
    fun addListener(listener: (message: dynamic, sender: dynamic, setResult: (dynamic) -> Unit) -> Boolean)
}

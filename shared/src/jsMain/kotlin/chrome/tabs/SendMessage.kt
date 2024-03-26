@file:JsQualifier("chrome.tabs")
package chrome.tabs

external fun sendMessage(
    tabId: Int,
    message: dynamic,
    options: dynamic = definedExternally,
    callback: (dynamic) -> Unit = definedExternally,
)

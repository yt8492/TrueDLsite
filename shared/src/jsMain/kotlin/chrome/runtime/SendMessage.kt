@file:JsQualifier("chrome.runtime")
package chrome.runtime

external fun sendMessage(
    extensionId: String? = definedExternally,
    message: dynamic,
    options: dynamic = definedExternally,
    callback: (dynamic) -> Unit = definedExternally,
)

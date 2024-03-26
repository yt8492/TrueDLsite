@file:JsQualifier("chrome.tabs")
package chrome.tabs

external fun query(
    queryInfo: dynamic,
    callback: (Array<Tab>) -> Unit
)

external interface Tab {
    val id: Int
}

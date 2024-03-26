import kotlinx.browser.document
import kotlin.js.json

fun main() {
    val button = document.getElementById("replace") ?: return
    button.addEventListener("click", {
        console.log("clicked")
        val info = json("active" to true, "currentWindow" to true)
        chrome.tabs.query(info) { tabs ->
            val currentTab = tabs[0]
            chrome.tabs.sendMessage(
                tabId = currentTab.id,
                message = "replace",
            ) {
                console.log("executed: $it")
            }
        }
    })
}

import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement
import kotlin.js.json

fun main() {
    chrome.storage.local.get("replaceList") { items ->
        val replaceList = items["replaceList"]
        console.log(items)
        if (replaceList == null) {
            console.log("loadDefaultSetting")
            loadDefaultSetting()
        }
    }
    val button = document.getElementById("replace") as HTMLButtonElement
    button.addEventListener("click", {
        val info = json("active" to true, "currentWindow" to true)
        chrome.tabs.query(info) { tabs ->
            val currentTab = tabs[0]
            chrome.storage.local.get("replaceList") { items ->
                console.log(items)
                val replaceList = items["replaceList"]
                if (replaceList != null) {
                    chrome.tabs.sendMessage(
                        tabId = currentTab.id,
                        message = replaceList,
                    ) {
                        console.log("executed: $it")
                    }
                }
            }
        }
    })
    val openSetting = document.getElementById("option") as HTMLButtonElement
    openSetting.addEventListener("click", {
        chrome.runtime.openOptionsPage()
    })
}

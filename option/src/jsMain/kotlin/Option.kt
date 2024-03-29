import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.asList

fun main() {
    chrome.storage.local.get("replaceList") { items ->
        val replaceList = items["replaceList"]
        if (replaceList == null) {
            loadDefaultSetting()
        }
    }
    val input = document.getElementById("setting_json") as HTMLInputElement
    input.onchange = {
        val file = input.files?.asList()?.firstOrNull()
        if (file != null) {
            saveSetting(file)
        }
    }
    val reset = document.getElementById("reset") as HTMLButtonElement
    reset.onclick = {
        loadDefaultSetting()
    }
}

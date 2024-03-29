import kotlin.js.json

fun loadDefaultSetting() {
    console.log("called getPackageDirectoryEntry")
    chrome.runtime.getPackageDirectoryEntry { root ->
        console.log("getPackageDirectoryEntry")
        root.getFile("default.json", json()) { entry ->
            entry.file { file ->
                saveSetting(file)
            }
        }
    }
}

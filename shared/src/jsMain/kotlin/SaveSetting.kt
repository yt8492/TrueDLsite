import org.w3c.files.File
import org.w3c.files.FileReader
import kotlin.js.Json
import kotlin.js.json

fun saveSetting(file: File) {
    val reader = FileReader()
    reader.onloadend = {
        val text = reader.result as String
        val replaceMap = JSON.parse<Json>(text)
        console.log(replaceMap)
        chrome.storage.local.set(
            json(
                "replaceList" to replaceMap
            )
        ) {}
    }
    reader.readAsText(file)
}

@file:JsQualifier("chrome.runtime")
package chrome.runtime

import org.w3c.files.File
import kotlin.js.Json

external fun getPackageDirectoryEntry(callback: (DirectoryEntry) -> Unit)

external interface DirectoryEntry {
    fun getFile(path: String, options: Json, callback: (FileEntry) -> Unit)
}

external interface FileEntry {
    fun file(callback: (File) -> Unit)
}

import js.objects.Object
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.MutationObserver
import org.w3c.dom.MutationObserverInit
import org.w3c.dom.Node

lateinit var replaceList: List<Pair<Regex, String>>

fun main() {
    val body = document.body ?: return
    val config = MutationObserverInit(childList = true, subtree = true)
    window.onload = {
        chrome.runtime.onMessage.addListener { message, _, _ ->
            console.log(message)
            val replaceMap = buildMap {
                Object.keys(message).forEach {
                    put(it, message[it])
                }
            }
            console.log("replaceMap: $replaceMap")
            replaceList = replaceMap.map {
                it.key.toRegex() to it.value
            }
            replaceText(body)
            val observer = MutationObserver { mutationRecords, observer ->
                mutationRecords.forEach { mutation ->
                    console.log(mutation)
                    if (mutation.type == "childList") {
                        observer.disconnect()
                        replaceText(mutation.target)
                        observer.observe(body, config)
                    }
                }
            }
            observer.observe(body, config)
            true
        }
    }
}

fun replaceText(target: Node) {
    var child = target.firstChild
    while (child != null) {
        child = replaceChild(child.nextSibling, child)
    }
}

fun replaceChild(next: Node?, node: Node?): Node? {
    if (node == null) {
        return null
    }
    when (node.nodeType) {
        Node.TEXT_NODE -> {
            val replaced = document.createTextNode(
                replaceList.fold(node.textContent ?: "") { acc, pair ->
                    acc.replace(pair.first, pair.second)
                }
            )
            node.parentNode?.replaceChild(replaced, node)
            return next
        }
        Node.ELEMENT_NODE -> {
            var child = node.firstChild
            while (child != null) {
                child = replaceChild(child.nextSibling, child)
            }
            return next
        }
        else -> {
            return next
        }
    }
}

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.MutationObserver
import org.w3c.dom.MutationObserverInit
import org.w3c.dom.Node

val replaceList = listOf(
    """ざぁ〜こ♡""".toRegex() to "メスガキ",
    """合意なし""".toRegex() to "レイプ",
    """ひよこ""".toRegex() to "ロリ",
    """ひよこババア""".toRegex() to "ロリババア",
    """閉じ込め""".toRegex() to "監禁",
    """超ひどい""".toRegex() to "鬼畜",
    """逆レ(?!イプ)""".toRegex() to "逆レイプ",
    """命令/無理矢理""".toRegex() to "強制/無理矢理",
    """近親もの""".toRegex() to "近親相姦",
    """責め苦""".toRegex() to "拷問",
    """トランス/暗示""".toRegex() to "催眠",
    """動物なかよし""".toRegex() to "獣姦",
    """精神支配""".toRegex() to "洗脳",
    """秘密さわさわ""".toRegex() to "痴漢",
    """しつけ""".toRegex() to "調教",
    """下僕""".toRegex() to "奴隷",
    """屈辱""".toRegex() to "凌辱",
    """回し""".toRegex() to "輪姦",
    """虫えっち""".toRegex() to "虫姦",
    """モブおじさん""".toRegex() to "モブ姦",
    """異種えっち""".toRegex() to "異種姦",
    """機会責め""".toRegex() to "機械姦",
    """すやすやえっち""".toRegex() to "睡眠姦",
    """トランス/暗示ボイス""".toRegex() to "催眠音声",
)

fun main() {
    val body = document.body ?: return
    val config = MutationObserverInit(childList = true, subtree = true)
    window.onload = {
        chrome.runtime.onMessage.addListener { message, _, _ ->
            console.log("onMessage: $message")
            if (message == "replace") {
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
            }
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

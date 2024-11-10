import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class JsoupWrapper {
  def get(url: String): Document =
    Jsoup.connect(url).get()
}

import Scraper.TG_BOT_LIST_URL
import org.jsoup.Jsoup

import scala.jdk.CollectionConverters.*

class Scraper(jsoup: JsoupWrapper) {

  def getTelegramBots: Seq[String] =
    val doc = Jsoup.connect(TG_BOT_LIST_URL).get()
    doc
      .select("a[href^=\"https://github.com\"]")
      .asScala
      .toSeq
      .map(_.absUrl("href"))

  def scrapeGithub(url: String): GithubScrapeResult =
    println(f"getting $url")
    val doc = jsoup.get(url)

    println(f"title= ${doc.title}")

    val result = GithubScrapeResult(
      title = url.split("/").reverse.headOption,
      url,
      language = doc
        .select("a[href*=\"/search?l=\"]")
        .asScala
        .headOption
        .flatMap(_.select("span.text-bold").asScala.headOption.map(_.text())),
      usedBy = doc
        .select("a[href$=\"/network/dependents\"].Link--primary span.Counter")
        .asScala
        .toSeq
        .headOption
        .map(_.attr("title").replace(",", "").toInt),
      stars = doc
        .select("span#repo-stars-counter-star")
        .asScala
        .toSeq
        .headOption
        .map(_.attr("title").replace(",", "").toInt),
      contributors = doc
        .select("a[href$=\"/contributors\"].Link--primary span.Counter")
        .asScala
        .toSeq
        .headOption
        .map(_.attr("title").replace(",", "").replace("+", "").toInt)
    )
    println(result)
    result
}

object Scraper:
  val TG_BOT_LIST_URL = "https://core.telegram.org/bots/samples"

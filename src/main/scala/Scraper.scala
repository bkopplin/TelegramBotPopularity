import org.jsoup.Jsoup

import scala.jdk.CollectionConverters.*

object Scraper {
  val TG_BOT_LIST_URL = "https://core.telegram.org/bots/samples"

  def getTelegramBots: Seq[String] =
    val doc = Jsoup.connect(TG_BOT_LIST_URL).get()
    doc.select("a[href^=\"https://github.com\"]").asScala.toSeq.map(_.absUrl("href"))

  def scrapeGithub(url: String): GithubScrapeResult =
    println(f"getting $url")
    val doc = Jsoup.connect(url).get()
    val stars = doc.select("span#repo-stars-counter-star").asScala.toSeq.headOption.map(_.attr("title").replace(",", "").toInt)
    val usedBy = doc.select("a[href$=\"/network/dependents\"].Link--primary span.Counter").asScala.toSeq.headOption.map(_.attr("title").replace(",", "").toInt)
    val contributors = doc.select("a[href$=\"/contributors\"].Link--primary span.Counter").asScala.toSeq.headOption.map(_.attr("title").replace(",", "").replace("+", "").toInt)
    val language = doc.select("a[href$=\"/contributors\"].Link--primary span.Counter").asScala.toSeq.headOption.map(_.attr("title").replace(",", "").replace("+", "").toInt)

    val result = GithubScrapeResult(None, url, None, usedBy = usedBy, stars = stars, contributors = contributors)
    println(result)
    result
}

import GithubScrapeResult.CSV_SEPERATOR
import Util.{COMMA, EMPTY_STRING, NEWLINE}

final case class GithubScrapeResult(
    title: Option[String],
    url: String,
    language: Option[String],
    usedBy: Option[Int],
    stars: Option[Int],
    contributors: Option[Int]
) {
  override def toString: String =
    f"title=[$title] url=[($url)] language=[${language.getOrElse("")}] stars=[$stars] usedBy=[$usedBy] contributors=[$contributors]"

  def toCsv: String =
    Seq(
      title.getOrElse(EMPTY_STRING),
      url,
      language.getOrElse(EMPTY_STRING),
      stars.getOrElse(EMPTY_STRING),
      usedBy.getOrElse(EMPTY_STRING),
      contributors.getOrElse(EMPTY_STRING)
    ).mkString(CSV_SEPERATOR.toString).appended(NEWLINE)
}

object GithubScrapeResult:
  val CSV_SEPERATOR: Char = COMMA
  private val CSV_HEADERS: Seq[String] =
    Seq("title", "url", "language", "stars", "usedBy", "contributors")

  def writeCsv(path: os.Path, data: Seq[GithubScrapeResult]): Unit =
    os.write.over(
      path,
      CSV_HEADERS
        .mkString(CSV_SEPERATOR.toString)
        .appended(NEWLINE)
    )
    data.foreach(d => os.write.append(path, d.toCsv))

final case class GithubScrapeResult(title: Option[String], url: String, language: Option[String], usedBy: Option[Int], stars: Option[Int], contributors: Option[Int]) {
  override def toString: String =
    f"title=[$title] url=[($url)] language=[${language.getOrElse("")}] stars=[$stars] usedBy=[$usedBy] contributors=[$contributors]"

  def toCsv: String =
    f"$title, $url, ${language.getOrElse("")}, ${stars.getOrElse("")}, ${usedBy.getOrElse("")}, ${contributors.getOrElse("")},"
}

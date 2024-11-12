object Main:
  @main def main(): Unit =
    val scraper = Scraper(JsoupWrapper())
    val data = scraper.getTelegramBots.map(scraper.scrapeGithub)
    GithubScrapeResult.writeCsv(os.pwd / "data" / "tg_bot_apis.csv", data)
    println("Done")

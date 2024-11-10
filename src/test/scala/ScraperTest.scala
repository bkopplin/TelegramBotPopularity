import org.jsoup.Jsoup
import org.scalamock.scalatest.MockFactory
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers.shouldBe

import java.io.File
import scala.language.postfixOps

class ScraperTest extends AnyFunSpec, MockFactory {
  describe("getMostPopularTelegramBot") {
    it("should get a list Telegram bots") {
      val scraper = Scraper(new JsoupWrapper)
      val bots = scraper.getTelegramBots
      assert(bots.lengthIs > 80)
    }
  }

  describe("scrapeGithub") {
    it("should correctly extract information from a github page") {
      val url = "https://github.com/pengrad/java-telegram-bot-api"

      val expectedResult = GithubScrapeResult(
        title = Some("java-telegram-bot-api"),
        url = url,
        language = Some("Java"),
        usedBy = Some(3533),
        stars = Some(1805),
        contributors = Some(20)
      )

      val jsoupMock = mock[JsoupWrapper]
      jsoupMock.get
        .expects(url)
        .returning(
          Jsoup.parse(
            new File(
              "src/test/resources/github-pengrad-java-telegram-bot-api.html"
            )
          )
        )
        .once()

      val scraper = Scraper(jsoupMock)

      val result =
        scraper.scrapeGithub(
          url
        )

      result shouldBe expectedResult
    }
  }
}

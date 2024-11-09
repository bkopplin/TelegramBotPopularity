import Scraper.{getTelegramBots, scrapeGithub}
import org.scalatest.funspec.AnyFunSpec

class ScraperTest extends AnyFunSpec {
  describe("getMostPopularTelegramBot") {
    it("should get a list Telegram bots") {
      val bots = getTelegramBots
      println(bots)
    }
  }

  describe("scrapeGithub") {
    it("should scrape a github page") {
      val result = scrapeGithub("https://github.com/grammyjs/grammY")
      println(result)
    }

    it("should scrape a github page that doesn't have usedBy") {
      val result = scrapeGithub("https://github.com/klappvisor/haskell-telegram-api")
      println(result)
    }
  }
}

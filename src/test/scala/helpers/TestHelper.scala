package helpers

import com.workday.grid.{TwitterAsset, GitHubAsset, GitHubTwitterCell}
import org.json4s.JsonAST.JValue
import org.json4s._
import org.json4s.native.JsonMethods._

import scala.io.Source

trait TestHelper {

  val GitHubAssets = List(GitHubAsset("mdread", "buhtig", "painless Github client"))

  // scalastyle:off
  val TwitterAssets = List(
    TwitterAsset("oss_scala",
      801422082353598465L, "twitter4s - A Scala wrapper for Twitter4J https://t.co/HcXyUqPdsd"),
    TwitterAsset("Leet__Gamer", 801237572366147585L,
      "Any of the devs that follow me work with Twitter4j? I'm running into a problem"),
    TwitterAsset("anis_talha", 800986765859418114L, "Hello from twitter4j. Now getting Tweetss"))
  // scalastyle:on

  private val HarryBelafonteTweetId = 1
  val HarryBelafonteTwitterAsset =
    TwitterAsset("HarryBelafonte", HarryBelafonteTweetId, "Come, mister tally man, tally me banana.")

  private val RyanStilesTweetId = 2
  val RyanStilesTwitterAsset =
    TwitterAsset("RyanStiles", RyanStilesTweetId, "Never interrupt me when I'm eating a banana.")

  val CarlSaganTweetId = 3
  val CarlSaganTwitterAsset =
    TwitterAsset("CarlSagan", CarlSaganTweetId,
      "If you wish to make an apple pie from scratch, you must first invent the universe.")

  val MoeHowardATweetId = 4
  val MoeHowardATwitterAsset =
    TwitterAsset("MoeHoward", MoeHowardATweetId,"I'll squeeze the cider out of your adam's apple.")

  val BananaGitHubAsset = GitHubAsset("MotherNature", "Banana", "Bent yellow thingy")
  val AppleGitHubAsset = GitHubAsset("MotherNature", "Apple", "Round-ish red thingy")

  val BananaCell = GitHubTwitterCell(BananaGitHubAsset, List(HarryBelafonteTwitterAsset, RyanStilesTwitterAsset))

  val AppleCell = GitHubTwitterCell(AppleGitHubAsset, List(CarlSaganTwitterAsset, MoeHowardATwitterAsset))

  def resourceAsString(resource: String): String = Source.fromURL(getClass.getResource(resource)).mkString

  def resourceAsJson(resource: String): JValue = parse(resourceAsString(resource))
}

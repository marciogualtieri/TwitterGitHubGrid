package helpers

import com.workday.grid.{TwitterAsset, GitHubAsset, GitHubTwitterCell}
import org.json4s.JsonAST.JValue
import org.json4s._
import org.json4s.native.JsonMethods._

import scala.io.Source

trait TestHelper {

  val GitHubAssets = List(GitHubAsset("mdread", "buhtig", "painless Github client"))

  val TwitterAssets = List(
    TwitterAsset("oss_scala",
      801422082353598465L, "twitter4s - A Scala wrapper for Twitter4J https://t.co/HcXyUqPdsd"),
    TwitterAsset("Leet__Gamer", 801237572366147585L,
      "Any of the devs that follow me work with Twitter4j? I'm running into a problem"),
    TwitterAsset("anis_talha", 800986765859418114L, "Hello from twitter4j. Now getting Tweetss"))

  val BananaGitHubAsset = GitHubAsset("MotherNature", "Banana", "Bent yellow thingy")
  val AppleGitHubAsset = GitHubAsset("MotherNature", "Apple", "Round-ish red thingy")

  val HarryBelafonteTwitterAsset = TwitterAsset("HarryBelafonte", 1, "Come, mister tally man, tally me banana.")
  val RyanStilesTwitterAsset = TwitterAsset("RyanStiles", 2, "Never interrupt me when I'm eating a banana.")
  val CarlSaganTwitterAsset =
    TwitterAsset("CarlSagan", 3, "If you wish to make an apple pie from scratch, you must first invent the universe.")
  val MoeHowardATwitterAsset = TwitterAsset("MoeHoward", 4,"I'll squeeze the cider out of your adam's apple.")

  val BananaCell = GitHubTwitterCell(BananaGitHubAsset, List(HarryBelafonteTwitterAsset, RyanStilesTwitterAsset))

  val AppleCell = GitHubTwitterCell(AppleGitHubAsset, List(CarlSaganTwitterAsset, MoeHowardATwitterAsset))

  def resourceAsString(resource: String): String = Source.fromURL(getClass.getResource(resource)).mkString

  def resourceAsJson(resource: String): JValue = parse(resourceAsString(resource))
}

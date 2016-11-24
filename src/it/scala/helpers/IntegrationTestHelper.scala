package helpers

import org.json4s._

trait IntegrationTestHelper {
  def loginFromFirstItem(result: JValue): JValue = (result \ "items") (0) \\ "login"
  def nameFromFirstItem(result: JValue): JValue = (result \ "items") (0) \ "name"
  def idFromFirstItem(result: JValue): JValue = (result \ "items") (0) \ "id"

  def screenNameFromFirstTweet(result: JValue): JValue = result(0) \ "user" \ "screenName"
  def textFromFirstTweet(result: JValue): JValue = result(0) \ "text"
  def idFromFirstTweet(result: JValue): JValue = result(0) \ "id"
  def tweets(result: JValue): List[JValue] =  (result \ "text").values.asInstanceOf[List[JValue]]
}

package com.workday.grid

import helpers.IntegrationTestHelper
import org.json4s.JsonAST.{JInt, JString}
import org.scalatest.{Matchers, FlatSpec}

class JsonApiClientsTest extends FlatSpec with Matchers with AppConf with IntegrationTestHelper {

  val buhtigQuery = "buhtig user:mdread"

  "GitHub API Client" should "return JSON for query." in {
    val gitHubClient = GitHubApiClient(GitHubToken)
    val result = gitHubClient.search(buhtigQuery)
    val itemId = 20213484L

    idFromFirstItem(result) shouldBe JInt(itemId)
    nameFromFirstItem(result) shouldBe JString("buhtig")
    loginFromFirstItem(result) shouldBe JString("mdread")
  }

  "GitHub API Client" should "throw custom exception on failure." in {
    val gitHubClient = GitHubApiClient("INVALID_TOKEN")
    val thrown = intercept[gitHubClient.SearchException] {
      gitHubClient.search(buhtigQuery)
    }
    thrown.getMessage shouldBe "Check your authentication details or enable debug mode for more details."
  }

  "Twitter API Client" should "return JSON for query." in {
    val twitterClient = TwitterApiClient(TwitterConsumerCredentials, TwitterTokenCredentials, TwitterMaxTweets)
    val result = twitterClient.search("from:washingtonpost")

    screenNameFromFirstTweet(result) shouldBe JString("washingtonpost")
    textFromFirstTweet(result) shouldBe an[JString]
    idFromFirstTweet(result) shouldBe an[JInt]
    tweets(result) should have size TwitterMaxTweets
  }
}
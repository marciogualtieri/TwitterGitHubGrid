package com.workday.grid

import helpers.IntegrationTestHelper
import org.json4s.JsonAST.{JInt, JString}
import org.scalatest.{Matchers, FlatSpec}

class JsonApiClientsTest extends FlatSpec with Matchers with AppConf with IntegrationTestHelper {

  "GitHub API Client" should "return JSON for query." in {
    val gitHubClient = GitHubApiClient(GitHubToken)
    val result = gitHubClient.search("buhtig user:mdread")
    gitHubClient.close()

    idFromFirstItem(result) shouldBe JInt(20213484L)
    nameFromFirstItem(result) shouldBe JString("buhtig")
    loginFromFirstItem(result) shouldBe JString("mdread")
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
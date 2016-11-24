package com.workday.grid

import helpers.TestHelper
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, FlatSpec}

class ResourcesTest extends FlatSpec with Matchers with MockFactory with TestHelper {
  val fakeClient = stub[JsonApiClient]

  "GitHub resource query" should "return repositories." in {
    val query = "buhtig user:mdread"
    setUpFakeClient(query, "/github_repositories.json")
    GitHubResource(fakeClient).assets(query) shouldBe GitHubAssets
  }

  "Twitter resource query" should "return tweets." in {
    val query = "twitter4j"
    setUpFakeClient(query, "/twitter_statuses.json")
    TwitterResource(fakeClient).assets(query) shouldBe TwitterAssets
  }

  private def setUpFakeClient(query: String, responseFile: String) =
    (fakeClient.search _).when(query).returns(resourceAsJson(responseFile))
}
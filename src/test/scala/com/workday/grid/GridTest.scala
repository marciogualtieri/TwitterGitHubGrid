package com.workday.grid

import helpers.TestHelper
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, FlatSpec}

class GridTest extends FlatSpec with Matchers with MockFactory with GitHubTwitterGridBuilder with TestHelper {

  val fakeFruitHub = stub[Resource]
  val fakeFruitEater = stub[Resource]

  "(GitHub x Twitter) Grid builder" should "return JSON String grid for query." in {
    val query = "fruit"
    setUpFakeResources(query)
    build(query, fakeFruitHub, fakeFruitEater) shouldBe resourceAsString("/output.json")
  }

  private def setUpFakeResources(query: String) = {
    (fakeFruitHub.assets _).when(query).returns(List(BananaGitHubAsset, AppleGitHubAsset))
    (fakeFruitEater.assets _).when("Banana").returns(List(HarryBelafonteTwitterAsset, RyanStilesTwitterAsset))
    (fakeFruitEater.assets _).when("Apple").returns(List(CarlSaganTwitterAsset, MoeHowardATwitterAsset))
  }
}
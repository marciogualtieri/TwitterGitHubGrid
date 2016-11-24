package com.workday.grid

import org.json4s.{Extraction, FieldSerializer, DefaultFormats}
import org.json4s.native.JsonMethods._

case class GitHubTwitterCell(repository: Asset, tweets: List[Asset])

trait GitHubTwitterGridBuilder {

  def build(query: String, primaryResource: Resource, secondaryResource: Resource): String = {
    val summaries = for {
      primaryAsset <- primaryResource.assets(query)
    } yield GitHubTwitterCell(primaryAsset, secondaryResource.assets(primaryAsset.name))
    toJsonString(summaries)
  }

  private def toJsonString(summaries: List[GitHubTwitterCell]): String = {
    implicit val formats = DefaultFormats + FieldSerializer[GitHubTwitterCell]() + FieldSerializer[GitHubAsset]() +
      FieldSerializer[TwitterAsset]()
    pretty(render(Extraction.decompose(summaries)))
  }
}


package com.workday.grid

import org.json4s.{Extraction, FieldSerializer, DefaultFormats}
import org.json4s.native.JsonMethods._

case class GitHubTwitterCell(repository: Asset, tweets: List[Asset])

trait GitHubTwitterGridBuilder {

  def build(query: String, gitHub: Resource, twitter: Resource): String = {
    val cells = for {
      project <- gitHub.assets(query)
    } yield GitHubTwitterCell(project, twitter.assets(project.name))
    toJsonString(cells)
  }

  private def toJsonString(summaries: List[GitHubTwitterCell]): String = {
    implicit val formats = DefaultFormats + FieldSerializer[GitHubTwitterCell]() + FieldSerializer[GitHubAsset]() +
      FieldSerializer[TwitterAsset]()
    pretty(render(Extraction.decompose(summaries)))
  }
}


package com.workday.grid

import org.json4s._

abstract class Resource {
  def assets(query: String): List[Asset]
}

case class GitHubResource(client: JsonApiClient) extends Resource {
  def assets(query: String): List[Asset] = {
    val json = client.search(query)
    for {
      JObject(items) <- json
      JField("name", JString(name)) <- items
      JField("description", JString(description)) <- items
      JField("owner", JObject(owner)) <- items
      JField("login", JString(login)) <- owner
    } yield GitHubAsset(login, name, description)
  }
}

case class TwitterResource(client: JsonApiClient) extends Resource {
  def assets(query: String): List[Asset] = {
    val json = client.search(query)
    for {
      JArray(statuses) <- json
      JObject(status) <- statuses
      JField("id", JInt(id)) <- status
      JField("text", JString(text)) <- status
      JField("user", JObject(user)) <- status
      JField("screenName", JString(screenName)) <- user
    } yield TwitterAsset(screenName, id, text)
  }
}

package com.workday.grid

import net.caoticode.buhtig.Buhtig
import net.caoticode.buhtig.Converters._
import org.json4s.JsonAST.JValue
import twitter4j.{User, Status, Query, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder
import collection.JavaConverters._
import org.json4s.{DefaultFormats, FieldSerializer, Extraction}

import scala.collection.mutable

abstract class JsonApiClient {
  def search(query: String): JValue
  def close(): Unit
}

case class GitHubApiClient(token: String) extends JsonApiClient {
  private val factory = new Buhtig(token)
  private val client = factory.syncClient

  def search(query: String): JValue = {
    val result = (client.search.repositories ? ("q" -> query)).getOpt[JValue]
    if(result.isDefined) result.get
    else throw new SearchException("Check your authentication details or enable debug mode for more details.")
  }

  def close(): Unit = { client.close()
                        factory.close() }

  private case class SearchException(message: String) extends Exception {
    override def getMessage = message
  }
}

case class Credentials(key: String, secret: String)

case class TwitterApiClient(consumer: Credentials, token: Credentials, maxTweets: Int) extends JsonApiClient {
  private val factory = new TwitterFactory(factoryBuilder.build())
  private val client = factory.getInstance()
  implicit val formats = DefaultFormats + FieldSerializer[Status]() + FieldSerializer[User]()

  def search(query: String): JValue = {
    def toJson(statuses: mutable.Buffer[Status]): JValue = {
      implicit val formats = DefaultFormats + FieldSerializer[Status]() + FieldSerializer[User]()
      Extraction.decompose(statuses)
    }

    def toTwitter(query: String): Query = {
      val tq = new Query(query)
      tq.setCount(maxTweets)
      tq
    }

    val result = client.search(toTwitter(query))
    val statuses = result.getTweets.asScala
    toJson(statuses)
  }

  def close(): Unit = {}

  private def factoryBuilder: ConfigurationBuilder = {
    val builder = new ConfigurationBuilder()
    builder.setDebugEnabled(true).setOAuthConsumerKey(consumer.key).setOAuthConsumerSecret(consumer.secret)
      .setOAuthAccessToken(token.key).setOAuthAccessTokenSecret(token.secret)
    builder
  }
}



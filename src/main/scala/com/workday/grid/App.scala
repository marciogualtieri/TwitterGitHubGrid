package com.workday.grid

import java.io.{PrintWriter, File}
import com.typesafe.config.ConfigFactory
import scopt.OptionParser

trait AppConf {
  private val conf = ConfigFactory.load()
  val GitHubToken = conf.getString("github.token")
  private val TwitterConsumerKey = conf.getString("twitter.consumer.key")
  private val TwitterConsumerSecret = conf.getString("twitter.consumer.secret")
  val TwitterConsumerCredentials = Credentials(TwitterConsumerKey, TwitterConsumerSecret)
  private val TwitterTokenKey = conf.getString("twitter.token.key")
  private val TwitterTokenSecret = conf.getString("twitter.token.secret")
  val TwitterTokenCredentials = Credentials(TwitterTokenKey, TwitterTokenSecret)
  val TwitterMaxTweets = conf.getInt("twitter.max.tweets")
}

object App extends GitHubTwitterGridBuilder with AppConf {

  def main(args: Array[String]) {
    val cliArguments = parseCliArguments(args)
    cliArguments match {
      case Some(config) =>
        try {
          val output = execute(config.keyword.get)
          if (config.output.isDefined) outputToFile(output, config.output.get)
          else outputToConsole(output)
        } catch {
          case e: Exception => println(s"Execution failed with exception: $e")
        }
      case None =>
    }
  }

  private def execute(keyword: String): String = {
    val gitHubClient = GitHubApiClient(GitHubToken)
    val twitterClient = TwitterApiClient(TwitterConsumerCredentials, TwitterTokenCredentials, TwitterMaxTweets)
    try {
      build(keyword, GitHubResource(gitHubClient), TwitterResource(twitterClient))
    } finally {
      gitHubClient.close()
    }
  }

  private def outputToFile(output: String, file: File) = {
    val writer = new PrintWriter(file)
    try {
      writer.write(output + "\n")
    } finally {
      writer.close()
    }
  }

  private def outputToConsole(output: String) = println(output)

  private case class CliConfig(keyword: Option[String] = None, output: Option[File] = None)

  private def parseCliArguments(args: Array[String]): Option[CliConfig] = {
    val parser = new OptionParser[CliConfig]("java -jar <jar name>") {

      arg[String]("keyword") action { (x, c) =>
        c.copy(keyword = Some(x)) } text "Search keyword to generate the output for."

      opt[File]('o', "output-file") valueName "<file>" action { (x, c) =>
        c.copy(output = Some(x)) } text "Name of the output file. If not provided, will print output to console."

      help("help") text "prints this help text"
    }
    parser.parse(args, CliConfig())
  }
}


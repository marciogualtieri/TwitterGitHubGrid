package com.workday.grid

abstract class Asset {
  def toString: String
  def owner: String
  def name: String
  def value: String
}
case class GitHubAsset(owner: String, name: String, description: String) extends Asset {
  override def value = description
}
case class TwitterAsset(user: String, id: BigInt, text: String) extends Asset {
  override def owner = user
  override def name = id.toString
  override def value = text
}
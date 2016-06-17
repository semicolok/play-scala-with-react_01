package models

import org.joda.time.DateTime
import scalikejdbc.{WrappedResultSet, autoConstruct}
import skinny.orm.{Alias, SkinnyCRUDMapper}

case class Board(
  id: Long,
  title: String,
  content: String,
  writer: String,
  createdAt: DateTime,
  updatedAt: DateTime
)

object Board extends SkinnyCRUDMapper[Board] {
  override def defaultAlias: Alias[Board] = createAlias("board")
  private[this] lazy val b = defaultAlias

  override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Board]): Board = autoConstruct(rs, n)


  def save(title: String, content: String, writer: String) = {
    createWithNamedValues(
      column.title -> title,
      column.content -> content,
      column.writer -> writer
    )
  }
}

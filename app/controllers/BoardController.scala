package controllers

import javax.inject.Singleton

import scalikejdbc._
import models.{Board, BoardForm}
import pagination.{CustomPagination, Page}
import play.api.Logger
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import skinny.Pagination

@Singleton
class BoardController extends Controller{

  private[this] val JsonContentType = "application/json; charset=utf-8"

  private[this] lazy val boardForm = Form(
    mapping(
      "title" -> nonEmptyText(maxLength = 20),
      "content" -> nonEmptyText,
      "writer" -> nonEmptyText
    )(BoardForm.apply)(BoardForm.unapply)
  )

  implicit val boardWrites = new Writes[Board] {
    def writes(board: Board) = Json.obj(
      "id" -> board.id,
      "title" -> board.title,
      "content" -> board.content,
      "writer" -> board.writer,
      "createdAt" -> board.createdAt,
      "updatedAt" -> board.updatedAt
    )
  }

  implicit def pageWrites[T] (implicit nested: Writes[T]) = new Writes[Page[T]] {
    def writes(page: Page[T]) = Json.obj(
      "results" -> Json.toJson(page.results),
      "pageNumber" -> page.pageNumber,
      "pageSize" -> page.pageSize,
      "totalResults" -> page.totalResults,
      "numberOfResults" -> page.numberOfResults
    )
  }
//  implicit val pageReads = Json.reads[Page]
//  implicit val pageWrites = Json.writes[Page[Board]]

  def index = Action {
    Ok(views.html.index.render())
  }

  def getBoards(page: Option[Int], size: Option[Int]) = Action {
//    val boards = Board.findAll()
    val pagination = Pagination.page(page.getOrElse(1)).per(size.getOrElse(20))
    val boards = Board.findAllWithPagination(pagination, Seq(sqls"id desc"))
    val totalCount = Board.countAllModels()

    Logger.debug(s"boards : $boards")
    Ok(Json.toJson(boards)).as(JsonContentType)
  }

  def getPagedBoards(page: Option[Int], size: Option[Int]) = Action {
    val pageNumber: Int = page.getOrElse(1)
    val pageSize: Int = size.getOrElse(20)
    //    val boards = Board.findAll()
    val pagination = Pagination.page(pageNumber).per(pageSize)
    val boards = Board.findAllWithPagination(pagination, Seq(sqls"id desc"))
    val totalCount = Board.countAllModels()

    Logger.debug(s"boards : $boards")
    Ok(Json.toJson(CustomPagination.createPage[Board](boards, pageNumber, pageSize, totalCount))).as(JsonContentType)
  }

  def addBoard = Action { implicit req =>
    val board = boardForm.bindFromRequest.get
    Board.save(board.title, board.content, board.writer)
    Ok
  }

  def delete(boardId: Long) = Action {
    Board.deleteById(boardId)
    Ok
  }
}

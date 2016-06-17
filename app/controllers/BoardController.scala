package controllers

import javax.inject.Singleton

import scalikejdbc._
import models.{Board, BoardForm}
import play.api.Logger
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import play.api.mvc.{Action, Controller}

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

  def index = Action {
    Ok(views.html.index.render())
  }

  def getBoards = Action {
//    val boards = Board.findAll()
    val boards = Board.findAll(Seq(sqls"id desc"))
    Logger.debug(s"boards : $boards")
    Ok(Json.toJson(boards)).as(JsonContentType)
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

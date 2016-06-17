import $ from "jquery";
import React, {Component} from "react";
import BoardList from "./boardList.jsx";
import BoardForm from "./boardForm.jsx";

export default class Board extends Component {
  constructor(props) {
    super(props);
    this.state = {boards: []};
  }

  loadBoardFromServer() {
    $.ajax({
      url: '/boards',
      method: "GET",
      cache: false,
      success: function (data) {
        this.setState({boards: data})
      }.bind(this),
      error: function (xhr, status, err) {
        this.setState({boards: []})
      }.bind(this)
    });
  }

  componentDidMount() {
    this.loadBoardFromServer();
  }

  saveBoard(newBoard) {
    $.ajax({
      url: '/boards',
      method: "POST",
      data: newBoard,
      dataType: 'json',
      cache: false,
      success: function (data) {
        this.loadBoardFromServer();
      }.bind(this),
      error: function (xhr, status, err) {
        this.loadBoardFromServer();
      }.bind(this)
    });
  }

  render() {
    return (
      <div className="container">
        <div className="header clearfix">
          <h3 className="text-muted">play-scala + react</h3>
        </div>
        <div className="jumbotron">
          <BoardForm saveCallBack={this.saveBoard.bind(this)}/>
        </div>
        <div className="row marketing">
          <BoardList boardList={this.state.boards}/> <br />
        </div>
        <footer className="footer">
          <p>&copy; FOOTER</p>
        </footer>
      </div>
    );
  }
}
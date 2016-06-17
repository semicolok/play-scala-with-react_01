import {Button} from "react-bootstrap";
import React, {Component} from "react";

export default class BoardList extends Component {
  constructor(props) {
    super(props);
  }

  deleteBoard(boardId) {
    this.props.deleteCallBack(boardId);
  }

  render() {
    const {boardList} = this.props;
    const boardListDOM = boardList.map((board) => (
      <tr key={board.id}>
        <td>{board.title}</td>
        <td>{board.writer}</td>
        <td>{board.createdAt}</td>
        <td><Button bsStyle="danger" onClick={this.deleteBoard.bind(this, board.id)} >Del</Button></td>
      </tr>
    ));
    return (
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Title</th>
            <th>Writer</th>
            <th>Created time</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {boardListDOM}
        </tbody>
      </table>
    );
  }
}
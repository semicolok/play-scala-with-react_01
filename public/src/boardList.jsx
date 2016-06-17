import React, {Component} from "react";

export default class BoardList extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    const {boardList} = this.props;
    const boardListDOM = boardList.map((board) => (
      // <li key={board.id}>
      //   {board.title} | {board.content} | {board.writer}
      // </li>
      <tr key={board.id}>
        <td>{board.title}</td>
        <td>{board.writer}</td>
        <td>{board.createdAt}</td>
      </tr>
    ));
    return (
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Title</th>
            <th>Writer</th>
            <th>created time</th>
          </tr>
        </thead>
        <tbody>
          {boardListDOM}
        </tbody>
      </table>
    );
  }
}
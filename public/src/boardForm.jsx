import {Button} from "react-bootstrap";
import React, {Component} from "react";

export default class BoardForm extends Component {
  constructor(props) {
    super(props);

    this.state = {
      title: '',
      content: '',
      writer: ''
    };
  }

  handleTitleChange(event) {
    this.setState({title: event.target.value});
  }

  handleContentChange(event) {
    this.setState({content: event.target.value});
  }

  handleWriterChange(event) {
    this.setState({writer: event.target.value});
  }

  saveBoard() {
    this.props.saveCallBack({title: this.state.title, content: this.state.content, writer: this.state.writer});
    this.setState({title: '', content: '', writer: ''});
  }

  render() {
    var title = this.state.title;
    var content = this.state.content;
    var writer = this.state.writer;

    return (
      <div>
        <div className="form-group">
          <label>Title</label>
          <input className="form-control" value={title} onChange={this.handleTitleChange.bind(this)}></input>
        </div>
        <div className="form-group">
          <label>Content</label>
          <textArea className="form-control" value={content} onChange={this.handleContentChange.bind(this)}></textArea>
        </div>
        <div className="form-group">
          <label>Writer</label>
          <input className="form-control" value={writer} onChange={this.handleWriterChange.bind(this)}></input>
        </div>
        <Button bsStyle="primary" type="button" onClick={this.saveBoard.bind(this)}>SAVE</Button>
      </div>
    );
  }
}
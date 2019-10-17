import React, { Component } from "react";
import Header from "./Components/Header";

class App extends Component {
  render() {
    return (
      <div>
        <Header title="Gerenciamento de Funcionários" />
        <br />
        <div className="container-fluid">
          <div
            className="rounded p-3 pr-4 m-1"
            style={{ border: "1px solid #ddd", background: "#f0f0f0" }}
          >
            {this.props.children}
          </div>
        </div>
      </div>
    );
  }
}
export default App;

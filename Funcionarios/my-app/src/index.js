import React from "react";
import ReactDOM from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import App from "./App";
import { Router, Route, IndexRoute, hashHistory } from "react-router";
import Cadastrar from "./Container/Cadastrar";
import Listar from "./Container/Listar";
import Editar from "./Container/Editar";

ReactDOM.render(
  <Router history={hashHistory}>
    <Route path="/" component={App}>
      <IndexRoute component={Listar} />
      <Route path="/Cadastrar" component={Cadastrar} />
      <Route path="/Editar/:id" component={Editar} />
    </Route>
  </Router>,
  document.getElementById("root")
);

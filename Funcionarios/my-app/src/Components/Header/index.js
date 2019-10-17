import React from "react";
import "./style.css";

const Header = ({ title }) => (
  <header>
    <h1 id="tituloPrincipal" className="font-weigth-bold">
      {title ? title : "Title de Escolha"}
    </h1>
  </header>
);

export default Header;

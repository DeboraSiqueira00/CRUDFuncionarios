import React, { Component } from "react";
import { Table, Button } from "reactstrap";
import { hashHistory } from "react-router";
import { Modal, Alert } from "react-bootstrap";

export default class Listar extends Component {
  Url = "http://localhost:8080/Funcionario";

  state = {
    funcionarios: [],
    message: {
      text: "",
      alert: ""
    },
    editModel: {
      id: "",
      nome: "",
      sobrenome: "",
      email: "",
      pis: ""
    }
  };
  componentDidMount() {
    fetch(this.Url)
      .then(response => response.json())
      .then(funcionarios => this.setState({ funcionarios }))
      .catch(e => console.log(e));
  }

  showCancelModal = id => {
    this.setState(
      {
        handleModalClose: () => {
          this.setState({ showModal: false });
        },
        modalTitle: "Confirmação",
        modalBody: (
          <Alert variant="danger">Tem certeza que deseja excluir?</Alert>
        ),
        modalCloseText: "Não",
        modalConfirmText: "Sim",
        modalOnlyClose: false,
        handleModalConfirm: () => {
          this.setState({ showModal: false }, () => {
            fetch(`${this.Url}/${id}`, { method: "DELETE" })
              .then(response => response.json())
              .then(rows => {
                const funcionarios = this.state.funcionarios.filter(
                  funcionario => funcionario.idFuncionario !== id
                );
                this.setState({
                  funcionarios,
                  message: {
                    text: "O funcionário foi excluido com sucesso! :)",
                    alert: "info"
                  }
                });
              })
              .catch(e => console.log(e));
          });
        }
      },
      () => this.setState({ showModal: true })
    );
  };

  showSuccessModal = () => {
    this.setState(
      {
        handleModalClose: () => {
          this.setState({ showModal: false }, () => {
            hashHistory.push("/");
          });
        },
        modalTitle: "Sucesso",
        modalBody: "Funcionario cadastrado com sucesso",
        modalCloseText: "OK",
        modalConfirmText: "OK",
        modalOnlyClose: true,
        handleModalConfirm: () => {
          this.setState({ showModal: false });
        }
      },
      () => this.setState({ showModal: true })
    );
  };

  render() {
    return (
      <div>
        <Button
          style={{ float: "right", background: "#BA55D3" }}
          onClick={() => hashHistory.push("/Cadastrar")}
        >
          Cadastrar Funcionário
        </Button>
        <Table
          className="table-bordered text-center"
          style={{ padding: "1px" }}
        >
          <thead style={{ background: "#9e065f", color: "#ffff" }}>
            <tr>
              <th>Nome Completo</th>
              <th>Número pis</th>
              <th>Email</th>
              <th>Opções</th>
            </tr>
          </thead>
          <tbody>
            {this.state.funcionarios.map(funcionario => (
              <tr key={funcionario.idFuncionario}>
                <td>
                  {funcionario.nome} {funcionario.sobrenome}
                </td>
                <td>{funcionario.pis}</td>
                <td>{funcionario.email}</td>
                <td>
                  <Button
                    color="info"
                    size="sm"
                    onClick={() =>
                      hashHistory.push("/Editar/" + funcionario.idFuncionario)
                    }
                  >
                    Editar
                  </Button>
                  <Button
                    color="danger"
                    size="sm"
                    onClick={e =>
                      this.showCancelModal(funcionario.idFuncionario)
                    }
                  >
                    Excluir
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>

        <Modal show={this.state.showModal} onHide={this.state.handleModalClose}>
          <Modal.Header closeButton>
            <Modal.Title>{this.state.modalTitle}</Modal.Title>
          </Modal.Header>
          <Modal.Body>{this.state.modalBody}</Modal.Body>
          <Modal.Footer>
            {this.state.modalOnlyClose !== true ? (
              <Button variant="primary" onClick={this.state.handleModalConfirm}>
                {this.state.modalConfirmText}
              </Button>
            ) : null}
            <Button variant="secondary" onClick={this.state.handleModalClose}>
              {this.state.modalCloseText}
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
  }
}

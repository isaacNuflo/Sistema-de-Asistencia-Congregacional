import React, { Component } from 'react';

import PostData from './../../servicios/PostSAC';

import { Row, Col, Container } from 'reactstrap';

class Registrar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            nombre: '',
            apellidos: '',
            edad: '',
            nac: '',
            tel: '',
            direc: ''
        }
        this.handleInputNombre = this.handleInputNombre.bind(this);
        this.handleInputApellido = this.handleInputApellido.bind(this);
        this.handleInputEdad = this.handleInputEdad.bind(this);
        this.handleInputNac = this.handleInputNac.bind(this);
        this.handleInpuTel = this.handleInpuTel.bind(this);
        this.handleInputDir = this.handleInputDir.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleKeyPress = this.handleKeyPress.bind(this);
    }
    handleInputApellido = (e) => { this.setState({ apellidos: e.target.value }); }
    handleInputDir = (e) => { this.setState({ direc: e.target.value }); }
    handleInputEdad = (e) => { this.setState({ edad: e.target.value }); }
    handleInpuTel = (e) => { this.setState({ tel: e.target.value }); }
    handleInputNac = (e) => { this.setState({ nac: e.target.value }); }
    handleInputNombre = (e) => { this.setState({ nombre: e.target.value }); }

    handleKeyPress = (event) => {
        if (event.key === 'enter') {
            this.handleSubmit();
        }
    }

    handleSubmit = () => {

        const data = {
            numeromiembro: " ",
            nombres: this.state.nombre,
            apellidos: this.state.apellidos,
            fechanacimiento: this.state.nac,
            telefono: this.state.tel,
            direccion: this.state.direc,
            bautizado: false,
            cargo: 'miembro',
            rol: false
        }
        //console.log(data);

        PostData(data, "R")
            .then((result) => {
                if (result) {
                    alert(`Usuario ${this.state.nombre} registrado con exito`);
                    console.log(result);
                    this.setState({
                        nombre: '',
                        apellidos: '',
                        edad: '',
                        nac: '',
                        tel: '',
                        direc: ''
                    });
                }
            })
            .catch((err) => {
                console.log("Error en el servidor:".err);
            })

    }

    render() {
        return (
            <Container>
                <Row className="mt-3 justify-content-center" style={{ backgroundColor: "#ffffff" }}>
                    <Col md={9} className="mt-4 mb-3">
                    <h2>Registro de asistencia</h2>
                        <form>
                            <div className="form-group row">
                                <div className="col-12 col-md-6 mb-3">
                                    <label htmlFor="nombre">Nombre</label>
                                    <input type="text" className="form-control" onKeyPress={this.handleKeyPress} placeholder="Nombre" value={this.state.nombre} onChange={this.handleInputNombre} name="nombre" id="nombre" />
                                </div>
                                <div className="col-12 col-md-6 mb-3">
                                    <label htmlFor="ape">Apellidos</label>
                                    <input type="text" className="form-control" onKeyPress={this.handleKeyPress} value={this.state.apellidos} onChange={this.handleInputApellido} placeholder="Apellidos" name="ape" id="ape" />
                                </div>
                            </div>
                            <div className="form-group row">
                                <div className="col-12 col-md-6 mb-3">
                                    <label htmlFor="edad">Edad</label>
                                    <input type="text" className="form-control" onKeyPress={this.handleKeyPress} value={this.state.edad} onChange={this.handleInputEdad} placeholder="edad" name="edad" id="edad" />
                                </div>
                                <div className="col-12 col-md-6 mb-3">
                                    <label htmlFor="fecha">Fecha de Nac</label>
                                    <input type="text" className="form-control" onKeyPress={this.handleKeyPress} placeholder="yyyy-mm-dd" value={this.state.nac} onChange={this.handleInputNac} name="fecha" id="fecha" />
                                </div>
                            </div>
                            <div className="form-group row">
                                <div className="col-12 col-md-6 mb-3">
                                    <label htmlFor="telefono">Telefono</label>
                                    <input type="text" className="form-control" onKeyPress={this.handleKeyPress} placeholder="Telefono" value={this.state.tel} onChange={this.handleInpuTel} name="telefono" id="telefono" />
                                </div>
                                <div className="col-12 col-md-6 mb-3">
                                    <label htmlFor="direccion">Direccion</label>
                                    <input type="text" className="form-control" onKeyPress={this.handleKeyPress} placeholder="av. Tupac Shakur N°2351" value={this.state.direc} onChange={this.handleInputDir} name="direccion" id="direccion" />
                                </div>
                            </div>
                            <Row className="justify-content-end">
                                <Col md={4} xs={12}>
                                    <input type="button" className="btn btn-success btn-block" value="Guardar" onClick={this.handleSubmit} />
                                </Col>
                            </Row>

                        </form>
                    </Col>

                </Row>

            </Container>
        );
    }
}

export default Registrar;
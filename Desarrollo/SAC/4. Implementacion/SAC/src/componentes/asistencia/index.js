import React, { Component } from 'react';

import Checkbox from './Checkbox';
import PostData from './../../servicios/PostSAC';

import { Container, Row, Col, Table } from 'reactstrap';
import items from './data';


class registrar extends Component {
    constructor() {
        super();
        this.enviar = this.enviar.bind(this);
        this.toggleCheckbox = this.toggleCheckbox.bind(this);
        this.handleInputHora = this.handleInputHora.bind(this);
        this.handleInputLugar = this.handleInputLugar.bind(this);
        this.textInput = React.createRef();
        this.state = {
            datos: new Set(),
            datosEnviar: new Set(),
            lugar: '',
            hora: ''
        };
    }
    /*
       componentWillMount() {
           //this.datos = new Set();
       } */
    handleInputHora = (e) => {
        this.setState({
            hora: e.target.value
        });
    }
    handleInputLugar = (e) => {
        this.setState({
            lugar: e.target.value
        });
    }

    toggleCheckbox = label => {
        if (this.state.datos.has(label)) {
            this.state.datos.delete(label);
        } else {
            this.state.datos.add(label);
        }
    }

    objEnviar() {
        let est;
        for (let item in items) {
            if (this.state.datos.has(items[item].id)) {
                est = true;
            } else {
                est = false;
            }
            const datos = {
                idmiembro: items[item].id,
                estado: est
            }
            this.state.datosEnviar.add(datos);
        }

    }

    enviar() {
        this.objEnviar();
        let arr = Array.from(this.state.datosEnviar);
        let date = new Date();
       let fechaA = `${date.getFullYear()}-${date.getMonth()+1}-${date.getDay()}`;
        const asistecia = {
            idcelula: 2,
            total: arr.length,
            lugarreunion: this.state.lugar,
            horainicio: this.state.hora,
            fechareunion: fechaA
        }
        const data = {
            asistencia:asistecia,
            asistenciadetalle: arr
        }
        //console.log(this.textInput.current.value);

        console.log(JSON.stringify(data,null,'\t'));
        PostData(data,'A')
            .then(result => {
                if(result){
                    alert('asistencia registrada');
                    this.setState((prevState,props)=>({
                        datos: prevState.clear,
                        datosEnviar: prevState.clear
                    }));
                }
            })
            .catch(err => {
                console.log(`el servidor dice:   ${err}`);
            });
           
    }

    render() {
        return (
            <Container className="mt-5">
                <Row className="justify-content-end " style={{backgroundColor:'#ffffff'}}>
                    <Col sm={12} className="mt-3 mb-3">
                        <h2 className='mb-2'>Registro de asistencia de clase</h2>
                        <Row>
                            <Col sm={4}>
                                <label htmlFor='lugar'>Lugar de reunion: </label>
                                <input type='text' name='lugar' ref={this.textInput} onChange={this.handleInputLugar} value={this.state.lugar} className='form-control' placeholder='lugar' />
                            </Col>
                            <Col sm={4}>
                                <label htmlFor='hora'>Hora de incio:</label>
                                <input type='text' name='hora' onChange={this.handleInputHora} value={this.state.hora} className='form-control' placeholder='09:00:00' />
                            </Col>
                        </Row>
                        <Table responsive hover size="sm" className='mt-3'>
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nombres</th>
                                    <th>Asistencia</th>
                                </tr>
                            </thead>
                            <tbody>
                                {items && items.map((item, key) =>
                                    <tr key={key}>
                                        <th scope="row">{key + 1}</th>
                                        <td>{item.nombre} {item.apellidos}</td>
                                        <td style={{ textAlign: 'center', verticalAlign: 'middle' }}>
                                            {
                                                <Checkbox
                                                    label={item.id}
                                                    handleCheckboxChange={this.toggleCheckbox}
                                                    check={false}
                                                />
                                            }
                                        </td>
                                    </tr>
                                )
                                }
                            </tbody>
                        </Table>
                    </Col>
                    <Col sm={9} md={3} className="mb-3">
                        <button className='btn btn-primary btn-block' onClick={this.enviar}>Registrar</button>
                    </Col>
                </Row>


            </Container >

        )

    }
}
export default registrar;

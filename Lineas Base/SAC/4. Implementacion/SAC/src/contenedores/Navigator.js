import React, { Component } from 'react';
import SideNav, {NavItem, NavIcon, NavText } from '@trendmicro/react-sidenav';
import { FaUser, FaSearchPlus, FaReadme, FaRegistered } from "react-icons/fa";
import { IoIosLogOut } from "react-icons/io";
import { Link } from 'react-router-dom';
import {MdAssignmentTurnedIn} from 'react-icons/md';

import '@trendmicro/react-sidenav/dist/react-sidenav.css';

class Navigator extends Component {
    render() {
        return (
            <SideNav
                style={{position:'fixed'}}
            >
                <SideNav.Toggle />
                <SideNav.Nav defaultSelected="home">
                    <NavItem eventKey="home">
                        <NavIcon>
                            <Link to="/perfil"><FaUser style={{ fontSize: '1.75em' }} /></Link>
                        </NavIcon>
                        <NavText>
                            <Link to="/perfil">Perfil</Link>
                        </NavText>
                    </NavItem>
                    <NavItem eventKey="home">
                        <NavIcon>
                            <Link to="/buscar"><FaSearchPlus style={{ fontSize: '1.75em' }} /></Link>
                        </NavIcon>
                        <NavText>
                            <Link to="/buscar">Buscar</Link>
                        </NavText>
                    </NavItem>
                    <NavItem eventKey="home">
                        <NavIcon>
                            <Link to="/"><FaRegistered style={{ fontSize: '1.75em' }} /></Link>
                        </NavIcon>
                        <NavText>
                            <Link to="/">Registrar</Link>
                        </NavText>
                    </NavItem>
                    <NavItem eventKey="home">
                        <NavIcon>
                            <Link to="/asistencia"><MdAssignmentTurnedIn style={{ fontSize: '1.75em' }} /></Link>
                        </NavIcon>
                        <NavText>
                            <Link to="/asistencia"> Asistencia</Link>
                        </NavText>
                    </NavItem>
                    <NavItem eventKey="home">
                        <NavIcon>
                            
                            <Link to="/nosotros"><FaReadme style={{ fontSize: '1.75em' }} /></Link>
                        </NavIcon>
                        <NavText>
                             <Link to="/nosotros"> Nosotros</Link>
                        </NavText>
                    </NavItem>
                    <NavItem eventKey="home">
                        <NavIcon>
                            <Link to="/#" onClick={this.props.logout}><IoIosLogOut style={{ fontSize: '1.75em' }} /></Link>
                        </NavIcon>
                        <NavText>
                            <Link to="/#" onClick={this.props.logout}>Cerrar Sesion</Link>
                        </NavText>
                    </NavItem>
                </SideNav.Nav>
            </SideNav>

        );
    }
}

export default Navigator;

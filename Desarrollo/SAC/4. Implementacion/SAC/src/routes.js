import React from 'react';
import {Route, Switch} from 'react-router-dom';

import App from './componentes/App';
import Asistencia from './componentes/asistencia';
import Nosotros from './componentes/nosotros';
import Registrar from './componentes/registrar';
import Perfil from './componentes/perfil';
import Buscar from './componentes/buscar';
import Login from './componentes/Login/Login';
/**
 * Rutas que se maneja en la aplicacion
 */
const AppRoutes=()=>{ 
			return(
				<App>
					<Switch>
						<Route path="/perfil"       component={Perfil} />
						<Route path="/nosotros"     component={Nosotros} />
						<Route path="/asistencia"   component={Asistencia} />
						<Route path="/"    component={Registrar} />
						<Route path='/buscar'             component={Buscar} />
						<Route path='/login'        component={Login} />
					</Switch>
				</App>
				)
}
export default AppRoutes;
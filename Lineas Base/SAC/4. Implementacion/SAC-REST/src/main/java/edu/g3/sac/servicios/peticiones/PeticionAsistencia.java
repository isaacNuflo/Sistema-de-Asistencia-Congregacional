package edu.g3.sac.servicios.peticiones;

import java.util.List;

import edu.g3.sac.dao.beans.Asistencia;
import edu.g3.sac.dao.beans.AsistenciaDetalle;

public class PeticionAsistencia {

    private Asistencia asistencia;
    private List<AsistenciaDetalle> asistenciadetalle;
    
    public PeticionAsistencia(){}

    public Asistencia getAsistencia() {
        return this.asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public List<AsistenciaDetalle> getAsistenciadetalle() {
        return this.asistenciadetalle;
    }

    public void setAsistenciadetalle(List<AsistenciaDetalle> asistenciadetalle) {
        this.asistenciadetalle = asistenciadetalle;
    }
	
}

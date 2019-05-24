package vista.pase_lista;

import javafx.scene.control.CheckBox;
import objetosNegocio.Voluntario;

public class VoluntarioT extends Voluntario {
    public CheckBox checkbox;





    public VoluntarioT(String nombre, String telefono, String direccion) {
        super(nombre, telefono, direccion);
    }

    public VoluntarioT(String nombre, String telefono, String direccion, int idVoluntario) {
        super(nombre, telefono, direccion, idVoluntario);
    }

    public VoluntarioT(String nombre, String telefono, String direccion, int idVoluntario, int horasAcumuladas) {
        super(nombre, telefono, direccion, idVoluntario, horasAcumuladas);
        checkbox = new CheckBox();
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }
}

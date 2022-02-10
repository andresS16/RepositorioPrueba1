
package modelo;

public class Aula {
    private int numero_aula;
    private int edificio;
    private int capacidad;
    private boolean disponibe=false;

    public Aula(int numero_aula, int edificio, int capacidad) {
        this.numero_aula = numero_aula;
        this.edificio = edificio;
        this.capacidad = capacidad;
    }

    public int getNumero_aula() {
        return numero_aula;
    }

    public void setNumero_aula(int numero_aula) {
        this.numero_aula = numero_aula;
    }

    public int getEdificio() {
        return edificio;
    }

    public void setEdificio(int edificio) {
        this.edificio = edificio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponibe() {
        return disponibe;
    }

    public void setDisponibe(boolean disponibe) {
        this.disponibe = disponibe;
    }

    @Override
    public String toString() {
        return "Aula{" + "numero_aula=" + numero_aula + ", edificio=" + edificio + ", capacidad=" + capacidad + ", disponibe=" + disponibe + '}';
    }
    
     
    
}


package modelo;

public class Aula {
    private long id;
    private int numAula;
    private int edificio;
    private int capacidad;
    private boolean disponibe=false;

    public Aula() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Aula(int numero_aula, int edificio, int capacidad) {
        this.numAula = numero_aula;
        this.edificio = edificio;
        this.capacidad = capacidad;
    }

    public int getNumAula() {
        return numAula;
    }

    public void setNumAula(int numAula) {
        this.numAula = numAula;
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
        return "Aula{" + "numero_aula=" + numAula  + ", edificio=" + edificio + ", capacidad=" + capacidad + ", disponibe=" + disponibe + '}';
    }
    
     
    
}

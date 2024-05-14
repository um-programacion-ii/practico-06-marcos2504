package UM;

public class Turno {
    private String id;
    private Paciente paciente;
    private Medico medico;
    private boolean finalizado;
    private boolean conObraSocial;

    // Constructor
    public Turno(Paciente paciente, Medico medico,boolean conObraSocial) {
        this.paciente = paciente;
        this.medico = medico;
        this.conObraSocial= conObraSocial;
        this.finalizado= false;

    }

    // Getters y setters para paciente y médico
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}


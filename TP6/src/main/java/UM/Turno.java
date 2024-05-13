package UM;

public class Turno {
    private Paciente paciente;
    private Medico medico;

    // Constructor
    public Turno(Paciente paciente, Medico medico) {
        this.paciente = paciente;
        this.medico = medico;
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
}


package GestionEntreprise;

public class Agent {
    protected String nomAgent;
    protected int ageAgent;

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }


    public Agent(String nom, int age){
        this.nomAgent = nom;
        this.ageAgent = age;
    }
}

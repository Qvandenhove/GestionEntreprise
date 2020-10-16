package gestionEntreprise;

public class Agent {
    protected String pseudoAgent;

    public String getPseudoAgent() {
        return pseudoAgent;
    }

    public void setNomAgent(String pseudo) {
        this.pseudoAgent = pseudo;
    }


    public Agent(String nom){
        this.pseudoAgent = nom;
    }

    public static Agent login(String pseudo, String pwd, String inputedPwd, int AgentTeam){
        Agent logedAgent = null;
        if (pwd.equals(inputedPwd)){
            switch(AgentTeam){
                case 1 ->{
                    logedAgent = new AgentMaintenance(pseudo);
                }
                case 2 ->{
                    logedAgent = new AgentDSI(pseudo);
                }
                case 3 ->{
                    logedAgent = new AgentDirection(pseudo);
                }
                case 4 ->{
                    logedAgent = new Manager(pseudo);
                }
                default -> {
                    System.out.println("Votre service n'est pas encore référencée merci de contacter le service RH pour plus d'information.");
                }
            }
        }
        return logedAgent;
    }
}

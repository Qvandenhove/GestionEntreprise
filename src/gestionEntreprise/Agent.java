package gestionEntreprise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static gestionEntreprise.gestionEntreprise.database;

public abstract class Agent {
    protected String pseudoAgent;
    public static Scanner scan = new Scanner(System.in);

    public String getPseudoAgent() {
        return pseudoAgent;
    }

    public void setNomAgent(String pseudo) {
        this.pseudoAgent = pseudo;
    }

    protected abstract void menu();

    public Agent(String nom) {
        this.pseudoAgent = nom;
    }

    public static Agent login() {
        Agent loggedAgent = null;
        String userPseudo;
        String userPasswd = "";
        String inBasePwd = null;
        int agentTeam = 0;
        boolean exit = false;
        ResultSet credentials;
        do {
            System.out.print("Quel est votre pseudo : ");
            userPseudo = scan.next();
            credentials = database.getAgent(userPseudo);
            try {
                if (credentials.next()) {

                    System.out.print("Quel est votre mot de passe : ");
                    userPasswd = scan.next();
                    inBasePwd = credentials.getString(1);
                    agentTeam = credentials.getInt(2);
                    if (userPasswd.equals(inBasePwd)) {
                        exit = true;
                        switch (agentTeam) {
                            case 1 -> {
                                loggedAgent = new AgentMaintenance(userPseudo);
                            }
                            case 2 -> {
                                loggedAgent = new AgentDSI(userPseudo);
                            }
                            case 3 -> {
                                loggedAgent = new AgentDirection(userPseudo);
                            }
                            case 4 -> {
                                loggedAgent = new Manager(userPseudo);
                            }
                            default -> {
                                System.out.println("Votre service n'est pas encore référencée merci de contacter le service RH pour plus d'information.");
                            }
                        }
                        if (loggedAgent != null) {
                            System.out.println("Bonjour " + loggedAgent.getPseudoAgent());
                        }
                    }else{
                        System.out.println("Mauvais mot de passe. Voulez vous réessayer ?(O / N)");
                        exit = scan.next().equals("N");
                    }
                } else {
                    System.out.println("Cet identifiants n'existe pas");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } while (!exit);
        return loggedAgent;
    }
}

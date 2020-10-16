package gestionEntreprise;

import static gestionEntreprise.gestionEntreprise.database;
import java.sql.*;

public class AgentDSI extends Agent{
    public AgentDSI(String name){
        super(name);
    }

    public void menu(){
//        int user
    }

    private void addUser(){
        System.out.println("Quel est le nom du nouvel agent ?");
        String agentPseudo = scan.next();
        System.out.println("Quel est le mot de passe du nouvel agent");
        String agentPassword = scan.next();
        try{
            ResultSet teamList = database.getConnec().createStatement().executeQuery("SELECT * FROM equipe");
            System.out.println("* ----------------- *");
            System.out.println("* Liste des equipes *");
            while(teamList.next()){
                System.out.println(teamList.getInt(1) + "  " + teamList.getString(2));
            }
            System.out.println("* ----------------- *");
            System.out.println("Dans quel équipe sera cet agent");
            int agentTeam = scan.nextInt();
            PreparedStatement addUser = database.getConnec().prepareStatement("INSERT INTO agent VALUE(?, ?, ?)");
            addUser.setString(1, agentPseudo);
            addUser.setString(2, agentPassword);
            addUser.setInt(3, agentTeam);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

package gestionEntreprise;

import static gestionEntreprise.gestionEntreprise.database;
import java.sql.*;

public class AgentDSI extends Agent{
    public AgentDSI(String name){
        super(name);
    }

    public void menu(){
        int userAction;
        do{
            // Afficher le menu
            System.out.println("* -----------------------   *");
            System.out.println("* 1 Ajouter utilisateur     *");
            System.out.println("* 2 Modifier utilisateur    *");
            System.out.println("* 3 Supprimer utilisateur   *");
            System.out.println("* 4 Modifier mot de passe   *");
            System.out.println("* 5 Consulter sa messagerie *");
            System.out.println("* 6 Gérer mon compte        *");
            System.out.println("* 0 Se déconnecter          *");
            System.out.println("* -----------------------   *");
            System.out.println("Choisissez une action");
            userAction = scan.nextInt();
            // Exécuter la tâche
            switch(userAction){
                case 1 -> {
                    // Ajouter un utilisateur
                    addUser();
                }
                case 2 -> {
                    // Modifier un utilisateur

                }
                case 3 -> {
                    // Supprimer un utilisateur
                }
                case 4 -> {
                    // Modifier un mot de passe
                }
                case 5 -> {
                    // Consulter sa messagerie
                }
                case 6 -> {
                    // Gérer mon compte
                }
                default -> {
                    System.out.println("Cet acion n'est pas disponible.");
                }
            }
        }while(userAction != 0);

    }

    private void addUser() {
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
            addUser.executeUpdate();
            database.getConnec().commit();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

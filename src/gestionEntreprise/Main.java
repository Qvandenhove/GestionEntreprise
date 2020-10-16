package gestionEntreprise;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static Database database = new Database("jdbc:mysql://localhost:3306/gestionentreprise", "root", "");

    public static void main(String[] args){

        int userAction;
        String userPseudo;
        String userPasswd;
        Agent loggedAgent = null;
        String inBasePwd = null;
        int agentTeam = 0;
        ResultSet credentials;
        do{
            System.out.print("Quel est votre pseudo : ");
            userPseudo = scan.next();
            System.out.print("Quel est votre mot de passe : ");
            userPasswd = scan.next();
            credentials = database.getAgent(userPseudo);
            if (credentials != null){
                try{
                    while(credentials.next()){
                        inBasePwd = credentials.getString(1);
                        agentTeam = credentials.getInt(2);
                    }
                    loggedAgent = Agent.login(userPseudo, inBasePwd, userPasswd,  agentTeam);
                }catch(SQLException e){
                    e.printStackTrace();
                }
                if(loggedAgent != null){
                    System.out.println("Bonjour " + loggedAgent.getPseudoAgent());
                }
            }



        }while(false);
    }
}

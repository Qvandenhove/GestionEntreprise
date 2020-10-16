package gestionEntreprise;

import java.sql.*;
import java.util.Scanner;

public class gestionEntreprise {
    public static Scanner scan = new Scanner(System.in);
    public static Database database = new Database("jdbc:mysql://localhost:3306/gestionentreprise", "root", "");

    public static void main(String[] args){
//        Agent loggedAgent = null;
        Agent loggedAgent = new AgentDSI("La_Saliere");
        int userAction = -1;
        do{
            if(loggedAgent == null){
                System.out.println("* ----------------- *");
                System.out.println("* 1---Connexion---- *");
                System.out.println("* 0----Quitter----- *");
                System.out.println("* ----------------- *");
                System.out.println("Choisissez une option");
                userAction = scan.nextInt();
                switch(userAction){
                    case 1 ->{
                        // Connexion de l'agent
                        loggedAgent = Agent.login();
                    }

                    case 0 ->{
                        // Avant de quitter
                    }
                }
            }else{
                loggedAgent.menu();
            }

        }while(userAction != 0);
    }
}

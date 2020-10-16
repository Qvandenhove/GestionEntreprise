package gestionEntreprise;

import java.sql.*;

public class Database {
    private Connection connec;

    public Connection getConnec() {
        return connec;
    }

    public void setConnec(Connection connec) {
        this.connec = connec;
    }

    public Database(String link, String user, String passwd) {
        try{
            System.out.println("Bonjour");
            Class.forName("com.mysql.jdbc.Driver");
            setConnec(DriverManager.getConnection(link, user, passwd));
            connec.setAutoCommit(false);
        }catch(Exception e){
             e.printStackTrace();
            System.out.println("Une erreur est survenue, merci de reesayer plus tard.");
        }
    }

    public ResultSet getAgent(String pseudo){
        ResultSet agent = null;
        try{
            PreparedStatement getAgent = connec.prepareStatement("SELECT pwd, equipe FROM agent WHERE pseudo = ?");
            getAgent.setString(1, pseudo);
            agent = getAgent.executeQuery();


        }catch(SQLException e){
            e.printStackTrace();
        }
        return agent;
    }
}
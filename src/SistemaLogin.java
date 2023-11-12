import javax.swing.*;
import java.sql.*;

public class SistemaLogin {
    static Connection connection  = conexao();
    Statement executar;
    public static Connection conexao(){
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/farmacia?serverTimezone=America/Sao_Paulo";
        String user = "adm";
        String password = "1234";
        try {
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        return conn;
    }
    public static boolean login(String user, String pass){
        if (credenciaisValidas(user, pass)) {
            return true;
        } else {
            return false;
        }
    }
    public static Boolean credenciaisValidas(String user, String pass){
        // integração com banco de dados aqui!
        // quebra-galho
        try{
            String pesquisa ="SELECT COUNT(*) FROM autenticar WHERE usuario = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(pesquisa);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    int rowCount = resultSet.getInt(1);
                    return rowCount > 0;
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
    public static boolean loginCli(String user, String pass){
        if (credenciaisclienete(user, pass)) {
            return true;
        } else {
            return false;
        }
    }
    public static Boolean credenciaisclienete(String user, String pass){
        try{
            String pesquisa ="SELECT COUNT(*) FROM cliente WHERE nome = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(pesquisa);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    int rowCount = resultSet.getInt(1);
                    return rowCount > 0;
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
}

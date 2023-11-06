import javax.swing.*;
import java.sql.*;

//Takushi aqui
public class Banco {
    Connection connection  = conexao();
    Statement executar;
    {
        try {
            executar = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection conexao() {
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
    public void criartabela(){
        try {
            executar.execute("CREATE TABLE medicamentos(id INT NOT NULL AUTO_INCREMENT ,nome VARCHAR(25), quantidade INT, valor FLOAT, PRIMARY KEY(id))");

        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void inserir(String nome, int qnt, double preco) throws SQLException {
        String dados = ("INSERT INTO medicamentos (nome, quantidade, valor) VALUES (?, ?, ?)");
        PreparedStatement preparedStatement = connection.prepareStatement(dados);
        preparedStatement.setString(1, nome);
        preparedStatement.setInt(2, qnt);
        preparedStatement.setDouble(3, preco);

        preparedStatement.executeUpdate();
    }
    public static void deletar(int num) throws SQLException{
        Connection connection  = conexao();
        String delete = ("DELETE FROM medicamentos WHERE id = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, num);

        preparedStatement.executeUpdate();
    }
    public void update(){

    }
}



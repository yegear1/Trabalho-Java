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
    public void criartabela(){
        try {
            if(!tabelaExiste("medicamentos")) {
                executar.execute("CREATE TABLE medicamentos(id INT NOT NULL AUTO_INCREMENT ,nome VARCHAR(25), quantidade INT, valor FLOAT, PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
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
    public static void deletarcli(int num) throws SQLException{
        Connection connection  = conexao();
        String deletecli = ("DELETE FROM cliente WHERE id = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(deletecli);
        preparedStatement.setInt(1, num);

        preparedStatement.executeUpdate();
    }
    private boolean tabelaExiste(String nomeTabela) throws SQLException {
        // Verificar se a tabela já existe no banco de dados
        try (Connection connection = conexao();
             ResultSet resultSet = connection.getMetaData().getTables(null, null, nomeTabela, null)) {
            return resultSet.next();
        }
    }
    public void autenticar(){
        try {
            if(!tabelaExiste("autenticar")) {
                executar.execute("CREATE TABLE autenticar(id INT NOT NULL AUTO_INCREMENT, usuario VARCHAR(25), password VARCHAR(25), PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void inseriradm(String user, String pass){
        try{
            String adm = ("INSERT INTO autenticar (usuario, password) VALUES (?, ?)");
            PreparedStatement preparedStatement = connection.prepareStatement(adm);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void tablecliete(){
        try{
            if(!tabelaExiste("cliente")){
                executar.execute("CREATE TABLE cliente(id INT NOT NULL AUTO_INCREMENT, nome VARCHAR(25), password VARCHAR(25), PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void inserircliente(String user, String pass){
        try{
            String cliente = ("INSERT INTO cliente (nome, password) VALUES(?,?)");
            PreparedStatement preparedStatement = connection.prepareStatement(cliente);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
        }
    }
}



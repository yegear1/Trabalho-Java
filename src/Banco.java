import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

//Takushi aqui
public class Banco {
    Scanner scanner1 = new Scanner(System.in);
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
                executar.execute("CREATE TABLE medicamentos(id INT NOT NULL AUTO_INCREMENT ,nome VARCHAR(25), quantidade INT, tipo VARCHAR(25), valor FLOAT, PRIMARY KEY(id))");
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
                executar.execute("CREATE TABLE cliente(id INT NOT NULL AUTO_INCREMENT, nome VARCHAR(25), sobrenome VARCHAR(25), usuario VARCHAR(25), password VARCHAR(25), PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public static boolean verificarusuario(String user){
        boolean existe = false;
        Connection connection  = conexao();
        try{
            String verificar = "SELECT * FROM cliente WHERE usuario = ?";
            try(PreparedStatement verificarexis = connection.prepareStatement(verificar)){
                verificarexis.setString(1, user);
                try(ResultSet verificarresultado = verificarexis.executeQuery()){
                    existe = verificarresultado.next();
                }
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return existe;
    }
    public void inserircliente(String nomecli, String sobrenomecli, String user, String pass){
        try{
            if(verificarusuario(user)) {
                System.out.println("O nome de usuario não pode ser utilizado, tente outro.");
            }else{
                String cliente = ("INSERT INTO cliente (nome, sobrenome, usuario, password) VALUES(?,?,?,?)");
                PreparedStatement preparedStatement = connection.prepareStatement(cliente);
                preparedStatement.setString(1, nomecli);
                preparedStatement.setString(2, sobrenomecli);
                preparedStatement.setString(3, user);
                preparedStatement.setString(4, pass);

                preparedStatement.executeUpdate();
            }

        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void updatemedicamentos() throws SQLException {
        Statement declaracao = connection.createStatement();
        String consulta = "SELECT * FROM medicamentos";
        ResultSet resultado = declaracao.executeQuery(consulta);
        while(resultado.next()){
            int id = resultado.getInt("id");
            String nome = resultado.getString("nome");
            int quantidade = resultado.getInt("quantidade");
            float valor = resultado.getFloat("valor");

            System.out.println("ID: " + id + ", Nome: " + nome + ", Quantidade: " + quantidade + ", Valor: " +valor);
        }
        System.out.println("Digite o nome do medicamento que deseja realizar a alteração: ");
        String op = scanner1.next();
        System.out.println("Medicamento selecionado: " + op);
        System.out.println("Digite a coluna que deseja alterar: ");
        System.out.println(" - Quantidade");
        System.out.println(" - Valor");
        String op1 = scanner1.next();
        if (op1.equalsIgnoreCase("quantidade")) {
            System.out.println("Medicamento selecionado: " + op);
            System.out.println("Digite a nova quantidade: ");
            int qntNovo = scanner1.nextInt();
            String updateQuery1 = "UPDATE medicamentos SET quantidade = ? WHERE nome = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery1);
            preparedStatement.setInt(1, qntNovo);
            preparedStatement.setString(2, op);
            preparedStatement.executeUpdate();
        }else if(op1.equalsIgnoreCase("valor")){
            System.out.println("Medicamento selecionado: " + op);
            System.out.println("Digite o novo valor");
            float valorNovo = scanner1.nextFloat();
            String updateQuery2 = "UPDATE medicamentos SET valor = ? WHERE nome = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery2);
            preparedStatement.setFloat(1, valorNovo);
            preparedStatement.setString(2, op);
            preparedStatement.executeUpdate();
        }
    }
    public void updatecli() throws SQLException {
        Statement declaracao1 = connection.createStatement();
        String consulta1 = "SELECT * FROM cliente";
        ResultSet resultado1 = declaracao1.executeQuery(consulta1);
        while(resultado1.next()){
            int idCli = resultado1.getInt("id");
            String nomeCli = resultado1.getString("nome");
            String passCli = resultado1.getString("password");

            System.out.println("ID: " + idCli + ", Nome: " + nomeCli + ", Password: " + passCli);
        }
        System.out.println("Digite o id do cliente que deseja realizar as alterações: ");
        int opcli = scanner1.nextInt();
        System.out.println("Cliente selecionado");
        System.out.println("Digite a coluna que deseja alterar: ");
        System.out.println(" - Nome");
        System.out.println(" - Password");
        String opCli1 = scanner1.next();
        if(opCli1.equalsIgnoreCase("nome")){
            System.out.println("Cliente selecionado: " + opcli);
            System.out.println("Digite o novo nome");
            String nomNovo = scanner1.next();
            String updateQuaryCli = "UPDATE cliente SET nome = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuaryCli);
            preparedStatement.setString(1, nomNovo);
            preparedStatement.setInt(2, opcli);
            preparedStatement.executeUpdate();
        }else if(opCli1.equalsIgnoreCase("password")){
            System.out.println("Cliente selecionado: " + opcli);
            System.out.println("Digite a nova senha: ");
            String passNovo = scanner1.next();
            String updateQuaryCli2 = "UPDATE cliente SET password = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuaryCli2);
            preparedStatement.setString(1, passNovo);
            preparedStatement.setInt(2, opcli);
            preparedStatement.executeUpdate();
        }
    }
    public void consultamedicamentos()throws SQLException{
        Statement declaracao = connection.createStatement();
        String consulta = "SELECT * FROM medicamentos";
        ResultSet resultado = declaracao.executeQuery(consulta);
        while(resultado.next()){
            int id = resultado.getInt("id");
            String nome = resultado.getString("nome");
            int quantidade = resultado.getInt("quantidade");
            float valor = resultado.getFloat("valor");

            System.out.println("ID: " + id + ", Nome: " + nome + ", Quantidade: " + quantidade + ", Valor: " +valor);
        }
    }
    public boolean conMediPorNome(String medi) throws SQLException {
        String MediConsul = "SELECT nome FROM medicamentos WHERE nome = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(MediConsul)) {
            preparedStatement.setString(1, medi);

            try (ResultSet resultadoMedi = preparedStatement.executeQuery()) {
                // Se houver um resultado, o medicamento foi encontrado
                return resultadoMedi.next();
            }
        }
    }
    public void inserirmedicamento(String nomMedi, int quantiMedi,String tipoMedi, float valorMedi ){
        try{
            String medi = ("INSERT INTO medicamentos (nome, quantidade, tipo , valor) VALUES(?,?,?,?)");
            PreparedStatement preparedStatement = connection.prepareStatement(medi);
            preparedStatement.setString(1, nomMedi);
            preparedStatement.setInt(2, quantiMedi);
            preparedStatement.setString(3, tipoMedi);
            preparedStatement.setFloat(4, valorMedi);

            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public void tabelastatus(){
        try {
            if(!tabelaExiste("status")) {
                executar.execute("CREATE TABLE status(id INT NOT NULL AUTO_INCREMENT ,nomecliente VARCHAR(25), nomemedicamento VARCHAR(25), quantidade INT, tipo VARCHAR(25), valor FLOAT, PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void somarentidadeseliente() {
        try {
            String consultaSQLCliente = "SELECT COUNT(id) AS soma_total FROM cliente";

            try (PreparedStatement statement = connection.prepareStatement(consultaSQLCliente);
                 ResultSet consultaSoma = statement.executeQuery()) {

                if (consultaSoma.next()) {
                    int soma = consultaSoma.getInt("soma_total");
                    System.out.println("Soma total de cliente: " + soma);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void somarentidadesefuncionarios() {
        try {
            String consultaSQLCliente = "SELECT COUNT(id) AS soma_total FROM autenticar";

            try (PreparedStatement statement = connection.prepareStatement(consultaSQLCliente);
                 ResultSet consultaSomafu = statement.executeQuery()) {

                if (consultaSomafu.next()) {
                    int somafu = consultaSomafu.getInt("soma_total");
                    System.out.println("Soma total de Funcionarios: " + somafu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void somarentidadesmedicamentos() {
        try {
            String consultaSQLCliente = "SELECT COUNT(id) AS soma_total FROM medicamentos";

            try (PreparedStatement statement = connection.prepareStatement(consultaSQLCliente);
                 ResultSet consultaSomamedi = statement.executeQuery()) {

                if (consultaSomamedi.next()) {
                    int somamedi = consultaSomamedi.getInt("soma_total");
                    System.out.println("Soma total de Medicamentos registrados: " + somamedi);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void tabelaagendamentos(){
        try {
            if(!tabelaExiste("agendamentos")) {
                executar.execute("CREATE TABLE agendamentos(id INT NOT NULL AUTO_INCREMENT ,nomecliente VARCHAR(25), nomemedicamento VARCHAR(25), quantidade INT,  PRIMARY KEY(id))");
            }else{
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void deletarpedido(String nomecli) throws SQLException {
        CompraBanco compra1 = new CompraBanco();
        compra1.status(nomecli);
        System.out.println("Digite o id do Pedido a ser cancelado: ");
        int idpedido = scanner1.nextInt();
        String deletecli = ("DELETE FROM status WHERE id = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(deletecli);
        preparedStatement.setInt(1, idpedido);

        preparedStatement.executeUpdate();
        System.out.println("Pedido cancelado com sucesso");

    }
    public void deletaragendamento(String nomeCliente1) throws SQLException {
        CompraBanco compra1 = new CompraBanco();
        compra1.statusagendamento(nomeCliente1);
        System.out.println("Digite o id do agendamento a ser cancelado: ");
        int idagendamento = scanner1.nextInt();
        String deletecli = ("DELETE FROM agendamentos WHERE id = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(deletecli);
        preparedStatement.setInt(1, idagendamento);

        preparedStatement.executeUpdate();
        System.out.println("Agendamento cancelado com sucesso");
    }

    //Adicionar metodo de deletar carrinho de cliente
}



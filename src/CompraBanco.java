import java.sql.*;
import java.util.Scanner;

public class CompraBanco {
    Banco banco1 = new Banco();
    Scanner scanner1 = new Scanner(System.in);
    Connection connection = Banco.conexao();
    Statement executar;
    public void compramedi(String nomecliente, String nommedi) throws SQLException {
        String consultaMedi = "SELECT id, nome, quantidade, valor FROM medicamentos WHERE nome = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(consultaMedi)) {
            preparedStatement.setString(1, nommedi);

            try (ResultSet resultado = preparedStatement.executeQuery()) {
                while (resultado.next()) {
                    // Itera sobre os resultados (pode haver mais de uma linha)
                    int id = resultado.getInt("id");
                    String nome = resultado.getString("nome");
                    int quantidade = resultado.getInt("quantidade");
                    float valor = resultado.getFloat("valor");


                    System.out.println("-STATUS-");
                    System.out.println("ID: " + id + ", Nome: " + nome + ", Quantidade: " + quantidade + ", Valor: " + valor);
                }
                System.out.println("Quantos dejesa comprar?");
                int Novacompra = scanner1.nextInt();
                String select1 = "SELECT nome, quantidade, tipo, valor FROM medicamentos WHERE nome = ?";
                try (PreparedStatement preparedStatement1 = connection.prepareStatement(select1)) {
                    preparedStatement1.setString(1, nommedi);

                    try (ResultSet resultado1 = preparedStatement1.executeQuery()) {
                        while (resultado1.next()) {
                            String Medi = resultado1.getString("nome");
                            int quant = resultado1.getInt("quantidade");
                            String tipomedi = resultado1.getString("tipo");
                            int novaquant = quant - Novacompra;
                            float valorf = resultado1.getFloat("valor");
                            float Preco = valorf * Novacompra;

                            if(tipomedi.equalsIgnoreCase("TarjaPreta")){
                                System.out.println("Este medicamento é classificado como tarja preta, necessita de receita médica");
                                System.out.println("Você possui receita ? ");
                                String CompraRe = scanner1.next();
                                if(CompraRe.equalsIgnoreCase("Sim") && quant >0){
                                    String updateselect1 = "UPDATE medicamentos SET quantidade = ? WHERE nome = ?";
                                    try (PreparedStatement preparedStatementselect = connection.prepareStatement(updateselect1)) {
                                        preparedStatementselect.setInt(1, novaquant);
                                        preparedStatementselect.setString(2, nommedi);

                                        preparedStatementselect.executeUpdate();
                                    }
                                    String status = ("INSERT INTO status (nomecliente, nomemedicamento, quantidade, tipo, valor) VALUES(?,?,?,?,?)");
                                    PreparedStatement preparedStatementstatus = connection.prepareStatement(status);
                                    preparedStatementstatus.setString(1,nomecliente);
                                    preparedStatementstatus.setString(2, Medi);
                                    preparedStatementstatus.setInt(3, Novacompra);
                                    preparedStatementstatus.setString(4, tipomedi);
                                    preparedStatementstatus.setFloat(5, Preco);
                                    preparedStatementstatus.executeUpdate();
                                    System.out.println("Valor a paga: " +Preco);
                                }else{
                                    System.out.println("Medicamento não pode ser comprado");
                                }
                            }else if(tipomedi.equalsIgnoreCase("TarjaVermelha")){
                                System.out.println("Este medicamento é classificado como antibiotico, portanto você precisará de duas vias de receitas");
                                System.out.println("Você possui as receitas ?");
                                String CompraRe = scanner1.next();
                                if(CompraRe.equalsIgnoreCase("Sim") && quant > 0){
                                    String updateselect1 = "UPDATE medicamentos SET quantidade = ? WHERE nome = ?";
                                    try (PreparedStatement preparedStatementselect = connection.prepareStatement(updateselect1)) {
                                        preparedStatementselect.setInt(1, novaquant);
                                        preparedStatementselect.setString(2, nommedi);

                                        preparedStatementselect.executeUpdate();
                                    }
                                    String status = ("INSERT INTO status (nomecliente, nomemedicamento, quantidade, tipo, valor) VALUES(?,?,?,?,?)");
                                    PreparedStatement preparedStatementstatus = connection.prepareStatement(status);
                                    preparedStatementstatus.setString(1,nomecliente);
                                    preparedStatementstatus.setString(2, Medi);
                                    preparedStatementstatus.setInt(3, Novacompra);
                                    preparedStatementstatus.setString(4, tipomedi);
                                    preparedStatementstatus.setFloat(5, Preco);
                                    preparedStatementstatus.executeUpdate();
                                    System.out.println("Valor a paga: " +Preco);
                                }else{
                                    System.out.println("Medicamento não pode ser comprado");
                                }
                            }else if(tipomedi.equalsIgnoreCase("SemTarja")){
                                String updateselect1 = "UPDATE medicamentos SET quantidade = ? WHERE nome = ?";
                                try (PreparedStatement preparedStatementselect = connection.prepareStatement(updateselect1)) {
                                    preparedStatementselect.setInt(1, novaquant);
                                    preparedStatementselect.setString(2, nommedi);

                                    preparedStatementselect.executeUpdate();
                                }
                                String status = ("INSERT INTO status (nomecliente, nomemedicamento, quantidade, tipo, valor) VALUES(?,?,?,?,?)");
                                PreparedStatement preparedStatementstatus = connection.prepareStatement(status);
                                preparedStatementstatus.setString(1,nomecliente);
                                preparedStatementstatus.setString(2, Medi);
                                preparedStatementstatus.setInt(3, Novacompra);
                                preparedStatementstatus.setString(4, tipomedi);
                                preparedStatementstatus.setFloat(5, Preco);
                                preparedStatementstatus.executeUpdate();
                                System.out.println("Valor a paga: " +Preco);
                            }
                        }
                    }
                }
            }
        }
    }
    public void status(String nomecliente) throws SQLException {
        String consultastatus = "SELECT * FROM status WHERE nomecliente = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(consultastatus)) {
            preparedStatement.setString(1, nomecliente);
                try (ResultSet consulta = preparedStatement.executeQuery()) {
                    if (consulta.next()) {
                        do {
                            int id = consulta.getInt("id");
                            String nomecli = consulta.getString("nomecliente");
                            String nomemedicamento = consulta.getString("nomemedicamento");
                            int quantimedi = consulta.getInt("quantidade");
                            float precomedi = consulta.getFloat("valor");
                            System.out.println("Status do Pedido. ID: " + id + ", Cliente: " + nomecli + ", Medicamento Comprado: " + nomemedicamento + ", Quantidade comprada: " + quantimedi + ", Valor a ser pago: " + precomedi);
                        } while (consulta.next());
                    } else {
                        System.out.println("Seu Carrinho está vazio");
                    }
                }
        }
    }
}
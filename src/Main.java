import javax.naming.AuthenticationNotSupportedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

//Em uma farmácia, um cliente pode comprar N medicamentos. A cada compra de um medicamento o estoque tem que ser atualizado.
// Caso o medicamento solicitado não esteja no estoque,
// o cliente pode optar por realizar um pedido de compra. O cliente pode solicitar o status do pedido de compra que ele fez.
// Um medicamento tarja preta só pode ser comprado se tiver receita. Um antibiótico só pode ser comprado se tiver receita “dupla”.

public class Main {
    public static void main(String[] args) throws SQLException {
        Banco banco = new Banco();
        CompraBanco compra = new CompraBanco();
        banco.autenticar();
        banco.criartabela();
        banco.tabelaagendamentos();
        banco.tablecliete();
        banco.tabelastatus();
        banco.tabelaagendamentos();
        //mostra o total de clientes
        banco.somarentidadeseliente();
        //mostra o total de funcionarios
        banco.somarentidadesefuncionarios();
        //mostra o total de medicamentos
        banco.somarentidadesmedicamentos();

        Farmacia F1 = new Farmacia("Drogaria Gobila", 1);

        Funcionario Fun1 = new Funcionario("Luis Felipe", 1, "Caixista", 2700);
        Funcionario Fun2 = new Funcionario("Igor Batoré", 2, "Zelador", 1100);
        Funcionario Fun3 = new Funcionario("Wallas", 3, "Gerente", 3454);

        Farmaceutico Fun4 = new Farmaceutico("Antonioabate", 4, "Farmaceutico manipulador", 3250);
        Farmaceutico Fun5 = new Farmaceutico("Adalberto", 5, "Farmaceutico", 3130);

        TarjaPreta M1 = new TarjaPreta("Venvanse", 10, 446.29F, "true", false);
        TarjaPreta M2 = new TarjaPreta("Ritalina", 10, 77.39F, "true", false);

        Medicamento M3 = new Medicamento("Dipirona", 10, 3.99F, "false", false);
        Medicamento M4 = new Medicamento("Paracetamol", 10, 10.98F, "false", false);

        TarjaVermelha M5 = new TarjaVermelha("Amoxicilina", 10, 129.91F, "false", false, true, true);
        TarjaVermelha M6 = new TarjaVermelha("Azitromicina", 10, 30.07F, "false", false, true, true);

        ArrayList<Medicamento> estoqueUni = new ArrayList<>(); //lista dos medicamentos
        estoqueUni.add(M1);
        estoqueUni.add(M2);
        estoqueUni.add(M3);
        estoqueUni.add(M4);
        estoqueUni.add(M5);
        estoqueUni.add(M6);

        ArrayList<String> carrinho = new ArrayList<>(); //lista da compra

        // Login
        System.out.println("Olá, por favor, digite suas credenciais ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Usuario: ");
        String user = scanner.next();
        System.out.println("Senha: ");
        String pass = scanner.next();

            if (SistemaLogin.login(user, pass)) {

                while (true) {
                    Farmacia.menuAdmin();
                    String opcao = scanner.nextLine();
                    boolean encontrado = false;

                    if (opcao.equals("1")) {
                        banco.consultamedicamentos();
                        //adicionar medicamentos direto no banco
                        System.out.println("Digite o nome do novo medicamento: ");
                        String nomNovo = scanner.next();
                        System.out.println("Digite a quantidade: ");
                        int quantiNovo = scanner.nextInt();
                        System.out.println("Digite o tipo do medicamento: ");
                        String tipomedi = scanner.next();
                        System.out.println("Digie o valor");
                        int valorNovo = scanner.nextInt();
                        banco.inserirmedicamento(nomNovo, quantiNovo, tipomedi, valorNovo);

                    } else if (opcao.equalsIgnoreCase("2")) {
                        System.out.println("Escolha a tabela que deseja realzar as alterações");
                        System.out.println(" - Medicamentos");
                        System.out.println(" - Cliente");
                        String escol = scanner.next();
                        if(escol.equalsIgnoreCase("Medicamentos")){
                            banco.updatemedicamentos();
                        }else if(escol.equalsIgnoreCase("Cliente")){
                            banco.updatecli();
                        }

                    } else if (opcao.equalsIgnoreCase("3")) {
                        System.out.println("#-#-#-#-#- DELETAR MEDICAMENTO -#-#-#-#-#");
                        System.out.println("Digite o id do medicamento: ");
                        int num = scanner.nextInt();
                        try {
                            Banco.deletar(num);
                        } catch (SQLException e) {
                            System.out.println(e);
                        }

                    } else if (opcao.equals("4")) {
                        System.out.println("Digite o nome do cliente: ");
                        String nomecli = scanner.next();
                        System.out.println("Digite o sobrenome do cliente");
                        String sobrenome = scanner.next();
                        System.out.println("Digite o usuario do cliente a ser cadastrado");
                        String userario = scanner.next();
                        System.out.println("Digite a senha do cliente a ser cadastrado");
                        String password = scanner.next();
                        banco.inserircliente(nomecli, sobrenome, userario, password);

                    } else if(opcao.equals("5")){
                        System.out.println("#-#-#-#-#- DELETAR Cliente -#-#-#-#-#");
                        System.out.println("Digite o id do medicamento: ");
                        int num = scanner.nextInt();
                        try {
                            Banco.deletarcli(num);
                        } catch (SQLException e) {
                            System.out.println(e);
                        }
                    }else if (opcao.equals("6")) {
                        break;
                    } else {
                        Medicamento.entradaInv();
                    }
                }
            }else if(SistemaLogin.loginCli(user,pass)) {

                System.out.printf("Bem vindos a %s !\n", F1.getNomeF());
                System.out.println("Esse é o nosso sistema de atendimento online! ");

                while (true) { // estrutura pra repetir o codigo
                    System.out.println("Medicamentos disponíveis em estoque:");
                    banco.consultamedicamentos();
                    Farmacia.menu();

                    String opcao = scanner.nextLine();

                    if (opcao.equals("1")) {
                        banco.consultamedicamentos();
                        System.out.print("Por favor, insira o nome do medicamento que deseja comprar: ");
                        String CompraNo = scanner.nextLine();
                        compra.compramedi(user, CompraNo);

                    } else if (opcao.equals("2")) {
                        System.out.println("#-#-#-#-#- CARRINHO DE COMPRAS -#-#-#-#-#");
                        compra.status(user);
                            System.out.println("Deseja continuar ou finalizar a compra ?");
                            System.out.println("1 - Continuar");
                            System.out.println("2 - Finalizar");
                            String Fin = scanner.nextLine();
                            if (Fin.equals("1")) {
                            } else if (Fin.equals("2")) {
                                System.out.println("Volte sempre!");
                                break;
                            } else {
                                Medicamento.entradaInv();
                            }
                        }else if(opcao.equals("3")){
                        compra.agendarmedicamento(user);
                    }else if(opcao.equals("4")){
                        compra.statusagendamento(user);
                        System.out.println("Deseja continuar ou finalizar a compra ?");
                        System.out.println("1 - Continuar");
                        System.out.println("2 - Finalizar");
                        String Fin2 = scanner.nextLine();
                        if (Fin2.equals("1")) {
                        } else if (Fin2.equals("2")) {
                            System.out.println("Volte sempre!");
                            break;
                        } else {
                            Medicamento.entradaInv();
                        }
                    }else if(opcao.equals("5")){
                        banco.deletarpedido(user);
                        System.out.println("Deseja continuar ou finalizar a compra ?");
                        System.out.println("1 - Continuar");
                        System.out.println("2 - Finalizar");
                        String Fin3 = scanner.nextLine();
                        if (Fin3.equals("1")) {
                        } else if (Fin3.equals("2")) {
                            System.out.println("Volte sempre!");
                            break;
                        } else {
                            Medicamento.entradaInv();
                        }
                    }else if(opcao.equals("6")){
                        banco.deletaragendamento(user);
                        System.out.println("Deseja continuar ou finalizar a compra ?");
                        System.out.println("1 - Continuar");
                        System.out.println("2 - Finalizar");
                        String Fin3 = scanner.nextLine();
                        if (Fin3.equals("1")) {
                        } else if (Fin3.equals("2")) {
                            System.out.println("Volte sempre!");
                            break;
                        } else {
                            Medicamento.entradaInv();
                        }
                    }else if (opcao.equals("7")) {
                        break;
                    }

                }
            }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

//Em uma farmácia, um cliente pode comprar N medicamentos. A cada compra de um medicamento o estoque tem que ser atualizado.
// Caso o medicamento solicitado não esteja no estoque,
// o cliente pode optar por realizar um pedido de compra. O cliente pode solicitar o status do pedido de compra que ele fez.
// Um medicamento tarja preta só pode ser comprado se tiver receita. Um antibiótico só pode ser comprado se tiver receita “dupla”.

public class Main {
    public static void main(String[] args) {

        Farmacia F1 = new Farmacia("Drogaria War", 1);

        TarjaPreta M1 = new TarjaPreta("Venvanse", 10, 446.29F, "true", false);
        TarjaPreta M2 = new TarjaPreta("Ritalina", 10, 77.39F, "true", false);

        Medicamento M3 = new Medicamento("Dipirona", 10, 3.99F, "false", false);
        Medicamento M4 = new Medicamento("Paracetamol", 10, 10.98F, "false", false);

        TarjaVermelha M5 = new TarjaVermelha("Amoxicilina", 10, 129.91F, "false", false, true, true);
        TarjaVermelha M6 = new TarjaVermelha("Azitromicina", 10, 30.07F, "false", false, true, true);

        ArrayList<Medicamento> estoqueUni = new ArrayList<>();
        estoqueUni.add(M1);
        estoqueUni.add(M2);
        estoqueUni.add(M3);
        estoqueUni.add(M4);
        estoqueUni.add(M5);
        estoqueUni.add(M6);

        ArrayList<String> carrinho = new ArrayList<>();

        System.out.println("Bem vindos a " + F1.getNomeF() + "!");
        System.out.println("Esse é o nosso sistema de atendimento online! ");

        while(true) {
            System.out.println("Esses os medicamentos atuais em estoque:");
            for (Medicamento medicamento : estoqueUni) {
                System.out.println("  Nome: " + medicamento.getNome() + " | Quantidade: " + medicamento.getQnt());
            }
            Farmacia.menu();
            Scanner scanner = new Scanner(System.in);
            int opcao = scanner.nextInt();
            scanner.nextLine();
            boolean encontrado = false;

            if (opcao == 1) {
                System.out.print("Por favor, insira o nome do medicamento que deseja comprar: ");
                String CompraNo = scanner.nextLine();
                Medicamento medicamentoSelecionado = null;

                for (Medicamento medicamento : estoqueUni) {
                    if (CompraNo.equalsIgnoreCase(medicamento.getNome())) {
                        medicamentoSelecionado = medicamento;
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) {
                    System.out.println("Medicamento encontrado: " + medicamentoSelecionado.getNome() + ", Temos " + medicamentoSelecionado.getQnt() + " unidades");
                    if (medicamentoSelecionado instanceof TarjaPreta) {
                        System.out.println("Este medicamento é um tarja preta, necessita de receita médica");
                        System.out.println("Você possui receita ? ");
                        String CompraRe = scanner.nextLine();
                        scanner.nextLine();
                        if (CompraRe.equalsIgnoreCase("sim")) {
                            System.out.print("Quantos deseja comprar: ");
                            int CompraQnt = scanner.nextInt();
                            if(CompraQnt > 0 && CompraQnt <= medicamentoSelecionado.getQnt()){
                                medicamentoSelecionado.atualizarQnt(CompraQnt);
                                carrinho.add("Pedido de " + CompraQnt + " unidades de " + medicamentoSelecionado.getNome() + " por " + CompraQnt* medicamentoSelecionado.getPreco() + "R$");
                                System.out.println("O valor deu: " + medicamentoSelecionado.getPreco() * CompraQnt + " R$");
                                Farmacia.wait(3000);
                                Farmacia.limpatela();
                            } else if (CompraQnt < 0) {
                                System.out.println("De que forma você pretende comprar "+medicamentoSelecionado.getNome() + "negativos");
                                Farmacia.wait(3000);
                                Farmacia.limpatela();
                            } else{
                                System.out.print("Não possuimos mais " + medicamentoSelecionado.getNome() + " em estoque");
                                Farmacia.wait(3000);
                                Farmacia.limpatela();
                            }
                        } else if (CompraRe.equalsIgnoreCase("não")) {
                            System.out.println("Você não pode comprar esse medicamento sem receita");
                            Farmacia.wait(3000);
                            Farmacia.limpatela();
                        } else {
                            System.out.println("Entrada invalida, Por favor, digite 'sim' ou 'não'");
                            Farmacia.wait(3000);
                            Farmacia.limpatela();
                        }
                    } else if (medicamentoSelecionado instanceof TarjaVermelha) {
                        System.out.println("Este medicamento é um antibiotico, portanto você precisará de duas vias de receitas");
                        System.out.println("Você possui as receitas ?");
                        String CompraRe = scanner.nextLine();
                        scanner.nextLine();
                        if (CompraRe.equalsIgnoreCase("sim")) {
                            System.out.print("Quantos deseja comprar: ");
                            int CompraQnt = scanner.nextInt();
                            if(CompraQnt > 0 && CompraQnt <= medicamentoSelecionado.getQnt()) {
                                medicamentoSelecionado.atualizarQnt(CompraQnt);
                                carrinho.add("Pedido de " + CompraQnt + " unidades de " + medicamentoSelecionado.getNome() + " por " + CompraQnt* medicamentoSelecionado.getPreco() + "R$");
                                System.out.println("O valor deu: " + medicamentoSelecionado.getPreco() * CompraQnt + " R$");
                                Farmacia.wait(3000);
                                Farmacia.limpatela();
                            } else if (CompraQnt < 0) {
                                System.out.println("De que forma você pretende comprar "+medicamentoSelecionado.getNome() + " negativos");
                                Farmacia.wait(3000);
                                Farmacia.limpatela();
                            } else{
                                System.out.print("Não possuimos mais " + medicamentoSelecionado.getNome() + " em estoque");
                                Farmacia.wait(3000);
                                Farmacia.limpatela();
                            }
                        } else if (CompraRe.equalsIgnoreCase("não")) {
                            System.out.println("Você não pode comprar esse medicamento sem receita");
                            Farmacia.wait(3000);
                            Farmacia.limpatela();
                        } else {
                            System.out.println("Entrada invalida, Por favor, digite 'sim' ou 'não'");
                            Farmacia.wait(3000);
                            Farmacia.limpatela();

                        }
                    } else {
                        System.out.print("Quantos deseja comprar: ");
                        int CompraQnt = scanner.nextInt();
                        if(CompraQnt > 0 && CompraQnt <= medicamentoSelecionado.getQnt()) {
                            medicamentoSelecionado.atualizarQnt(CompraQnt);
                            carrinho.add("Pedido de " + CompraQnt + " unidades de " + medicamentoSelecionado.getNome() + " por " + CompraQnt* medicamentoSelecionado.getPreco() + "R$");
                            System.out.println("O valor deu: " + medicamentoSelecionado.getPreco() * CompraQnt + " R$");
                            Farmacia.wait(3000);
                            Farmacia.limpatela();
                        } else if (CompraQnt < 0) {
                            System.out.println("De que forma você pretende comprar "+medicamentoSelecionado.getNome() + " negativos");
                            Farmacia.wait(3000);
                            Farmacia.limpatela();
                        } else {
                            System.out.print("Não possuimos mais " + medicamentoSelecionado.getNome() + " em estoque");
                            Farmacia.wait(3000);
                            Farmacia.limpatela();
                        }
                    }
                } else {
                    System.out.println("Medicamento não encontrado no estoque, verifique se a escrita está de acordo com a tabela");
                    Farmacia.wait(3000);
                    Farmacia.limpatela();
                }

            } else if (opcao==2){
                if (carrinho.isEmpty()){
                    System.out.println("Seu carrinho está vazio");
                    Farmacia.wait(3000);
                    Farmacia.limpatela();
                } else {
                    System.out.println("Pedidos registrados:");
                    for (String lista : carrinho) {
                        System.out.println(lista);
                    }
                    System.out.println("Deseja continuar ou finalizar a compra ?");
                    System.out.println("1 - Continuar");
                    System.out.println("2 - Finalizar");
                    int Fin = scanner.nextInt();
                    scanner.nextLine();
                    if (Fin == 1) {
                    } else if (Fin == 2) {
                        System.out.println("Volte sempre!");
                        break;
                    }
                }
            } else if(opcao==3){
                System.out.println("Volte sempre!");
                break;
            } else {
                System.out.println("Opção invalida, escolha uma dentre as mostradas");
                Farmacia.wait(3000);
                Farmacia.limpatela();
            }
        }
    }
}
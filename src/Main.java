import java.util.ArrayList;
import java.util.Scanner;

//Em uma farmácia, um cliente pode comprar N medicamentos. A cada compra de um medicamento o estoque tem que ser atualizado.
// Caso o medicamento solicitado não esteja no estoque,
// o cliente pode optar por realizar um pedido de compra. O cliente pode solicitar o status do pedido de compra que ele fez.
// Um medicamento tarja preta só pode ser comprado se tiver receita. Um antibiótico só pode ser comprado se tiver receita “dupla”.

public class Main {
    public static void main(String[] args) {

        Farmacia F1 = new Farmacia("Drogaria Gobila", 1);

        Funcionario Fun1 = new Funcionario("Luis Felipe",1,"Caixista", 2700);
        Funcionario Fun2 = new Funcionario("Igor Batoré",2,"Zelador", 1100);
        Funcionario Fun3 = new Funcionario("Wallas",3,"Gerente", 3454);

        Farmaceutico Fun4 = new Farmaceutico("Antonioabate",4,"Farmaceutico manipulador", 3250);
        Farmaceutico Fun5 = new Farmaceutico("Adalberto",5,"Farmaceutico", 3130);

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

        ArrayList<String> carrinho  = new ArrayList<>(); //lista da compra

        System.out.println("Bem vindos a " + F1.getNomeF() + "!");
        System.out.println("Esse é o nosso sistema de atendimento online! ");

        while(true) { // estrutura pra repetir o codigo
            System.out.println("Esses os medicamentos atuais em estoque:");
            for (Medicamento medicamento : estoqueUni) {
                System.out.println("  Nome: " + medicamento.getNome() + " | Quantidade: " + medicamento.getQnt());
            }
            Farmacia.menu();
            Scanner scanner = new Scanner(System.in);
            String opcao = scanner.nextLine();
            boolean encontrado = false;

            if (opcao.equals("1")) {
                System.out.print("Por favor, insira o nome do medicamento que deseja comprar: ");
                String CompraNo = scanner.nextLine();
                Medicamento medicamentoSelecionado = null;

                for (Medicamento medicamento : estoqueUni) { // Gera os medicamentos no terminal
                    if (CompraNo.equalsIgnoreCase(medicamento.getNome())) {
                        medicamentoSelecionado = medicamento;
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) {
                    System.out.println("Medicamento encontrado: " + medicamentoSelecionado.getNome() + ", Temos " + medicamentoSelecionado.getQnt() + " unidades");
                    if (medicamentoSelecionado instanceof TarjaPreta) { // Verifica se o medicamentoSelecionado é da classe Tarja Preta
                        System.out.println("Este medicamento é um tarja preta, necessita de receita médica");
                        System.out.println("Você possui receita ? ");
                        String CompraRe = scanner.nextLine();
                        if (CompraRe.equalsIgnoreCase("sim")) {
                            System.out.print("Quantos deseja comprar: ");
                            int CompraQnt = scanner.nextInt();
                            if(CompraQnt > 0 && CompraQnt <= medicamentoSelecionado.getQnt()){
                                medicamentoSelecionado.atualizarQnt(CompraQnt);
                                carrinho.add("Pedido de " + CompraQnt + " unidades de " + medicamentoSelecionado.getNome() + " por " + CompraQnt* medicamentoSelecionado.getPreco() + "R$");
                                Medicamento.compra(medicamentoSelecionado.getPreco(), CompraQnt);
                            } else if (CompraQnt < 0) {
                                Medicamento.compraMenor0(medicamentoSelecionado.getNome());
                            } else{
                                Medicamento.semEstoque(medicamentoSelecionado.getNome());
                            }
                        } else if (CompraRe.equalsIgnoreCase("não")) {
                            System.out.println("Você não pode comprar esse medicamento sem receita");
                            Farmacia.wait(3000);
                            Farmacia.limpatela();
                        } else {
                            Medicamento.entradaInv();
                        }


                    } else if (medicamentoSelecionado instanceof TarjaVermelha) { // Verifica se o medicamentoSelecionado é da classe Tarja Vermelha
                        System.out.println("Este medicamento é um antibiotico, portanto você precisará de duas vias de receitas");
                        System.out.println("Você possui as receitas ?");
                        String CompraRe = scanner.nextLine();

                        if (CompraRe.equalsIgnoreCase("sim")) {
                            System.out.print("Quantos deseja comprar: ");
                            int CompraQnt = scanner.nextInt();
                            if(CompraQnt > 0 && CompraQnt <= medicamentoSelecionado.getQnt()) {
                                medicamentoSelecionado.atualizarQnt(CompraQnt);
                                carrinho.add("Pedido de " + CompraQnt + " unidades de " + medicamentoSelecionado.getNome() + " por " + CompraQnt* medicamentoSelecionado.getPreco() + "R$");
                                Medicamento.compra(medicamentoSelecionado.getPreco(), CompraQnt);
                            } else if (CompraQnt < 0) {
                                Medicamento.compraMenor0(medicamentoSelecionado.getNome());
                            } else{
                                Medicamento.semEstoque(medicamentoSelecionado.getNome());
                            }
                        } else if (CompraRe.equalsIgnoreCase("não")) {
                            System.out.println("Você não pode comprar esse medicamento sem receita");
                            Farmacia.wait(3000);
                            Farmacia.limpatela();
                        } else {
                            Medicamento.entradaInv();
                        }


                    } else { // No caso de não ser nenhum das classes nos if, ele cai aqui
                        System.out.print("Quantos deseja comprar: ");
                        int CompraQnt = scanner.nextInt();
                        if(CompraQnt > 0 && CompraQnt <= medicamentoSelecionado.getQnt()) {
                            medicamentoSelecionado.atualizarQnt(CompraQnt);
                            carrinho.add("Pedido de " + CompraQnt + " unidades de " + medicamentoSelecionado.getNome() + " por " + CompraQnt* medicamentoSelecionado.getPreco() + "R$");
                            Medicamento.compra(medicamentoSelecionado.getPreco(), CompraQnt);
                        } else if (CompraQnt < 0) {
                            Medicamento.compraMenor0(medicamentoSelecionado.getNome());
                        } else {
                            Medicamento.semEstoque(medicamentoSelecionado.getNome());
                        }
                    }
                } else {
                    System.out.println("Medicamento não encontrado no estoque, verifique se a escrita está de acordo com a tabela");
                    Farmacia.wait(3000);
                    Farmacia.limpatela();
                }


            } else if (opcao.equals("2")){
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
                    if(Fin == 1) {
                    } else if (Fin == 2) {
                        System.out.println("Volte sempre!");
                        break;
                    } else{
                        System.out.println("Escolha uma opção válida");
                        Farmacia.wait(3000);
                    }
                }
            } else if(opcao.equals("3")){
                System.out.println("Volte sempre!");
                break;
            } else {
                Medicamento.entradaInv();
            }
        }
    }
}
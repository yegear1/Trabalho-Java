import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Farmacia F1 = new Farmacia("Drogaria War",1);

        Medicamento M1 = new Medicamento("Venvanse",446.29F,10,true,false);
        Medicamento M2 = new Medicamento("Ritalina",77.39F,10,true,false);

        Medicamento M3 = new Medicamento("Dipirona",3.99F,10,false,false);
        Medicamento M4 = new Medicamento("Paracetamol",10.98F,10,false,false);

        Medicamento M5 = new Medicamento("Amoxicilina",129.91F,10,false,true);
        Medicamento M6 = new Medicamento("Azitromicina",30.07F,10,false,true);

        ArrayList<Medicamento> estoqueUni = new ArrayList<>();
        estoqueUni.add(M1);
        estoqueUni.add(M2);
        estoqueUni.add(M3);
        estoqueUni.add(M4);
        estoqueUni.add(M5);
        estoqueUni.add(M6);

        System.out.println("Bem vindos a " + F1.getNome() + "!");
        System.out.println("Esses São os nossos medicamentos atuais em nosso estoque:");
        for (Medicamento Medicamento : estoqueUni) {
            System.out.println("  Nome: " + Medicamento.getNome() + " | Quantidade: " + Medicamento.getQnt());
        }
        }

    }

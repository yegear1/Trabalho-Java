public class Farmacia {
    private String nome;
    private int cnpj;
    private Medicamento[] estoque;


    public Farmacia(String nome,int cnpj){
        this.nome = nome;
        this.cnpj = cnpj;
        this.estoque = new Medicamento[100];

    }

    public String getNome(){
        return this.nome;
    }

    int getCnpj(){
        return this.cnpj;
    }
}

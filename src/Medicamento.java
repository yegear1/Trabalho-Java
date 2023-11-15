public class Medicamento{
    private String nome;
    private int qnt;
    private float preco;
    private String tipo;
    private boolean gen;

    public Medicamento(String nome,int qnt,float preco,String desc,boolean gen){
        this.nome = nome;
        this.qnt = qnt;
        this.preco = preco;
        this.tipo = desc;
        this.gen = gen;
    }

    public static void compra(float preco, int Qnt){
        System.out.printf("O valor deu: %.2f R$", preco * Qnt);
        Farmacia.wait(3000);
        Farmacia.limpatela();
    }

    public static void compraMenor0(String nome){
        System.out.println("De que forma você pretende comprar o medicamento " + nome + " negativo");
        Farmacia.wait(3000);
        Farmacia.limpatela();
    }

    public static void semEstoque(String nome){
        System.out.print("Não possuimos mais " + nome + " em estoque");
        Farmacia.wait(3000);
        Farmacia.limpatela();
    }

    public static void entradaInv(){
        System.out.println("Entrada invalida, Por favor, escolha uma opção válida");
        Farmacia.wait(3000);
        Farmacia.limpatela();
    }

    public static void receita(){
        System.out.println("Você não pode comprar esse medicamento sem receita");
        Farmacia.wait(3000);
        Farmacia.limpatela();
    }

    public int adicionaresto(int upd){
        qnt = qnt + upd;
        return qnt;

    }

    public void atualizarQnt(int qnt1){
        qnt -= qnt1;
    }
    public  String getNome(){
        return nome;
    }
    void setNome(String nome){
        this.nome = nome;
    }

    public int getQnt(){
        return qnt;
    }
    void setQnt(int qnt){
        this.qnt = qnt;
    }

    float getPreco(){
        return this.preco;
    }
    void setPreco(float preco){
        this.preco = preco;
    }

    public  String getDesc(){
        return tipo;
    }
    void setDesc (String desc){
        this.tipo = desc;
    }

    boolean isGen(){
        return this.gen;
    }
};
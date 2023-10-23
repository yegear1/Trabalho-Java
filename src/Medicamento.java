public class Medicamento{
    protected String nome;
    private int qnt;
    private float preco;
    private boolean tarj;
    private boolean anti;
    private boolean receit;

    public Medicamento(String nome,float preco,int qnt,boolean tarja,boolean antb){
        this.nome = nome;
        this.qnt = qnt;
        this.preco = preco;
        this.tarj = tarja;
        this.anti = antb;
    }

    public String getNome(){
        return this.nome;
    }
    void setNome(String nome){
        this.nome = nome;
    }

    int getQnt(){
        return this.qnt;
    }
    void setQnt(int quantid){
        this.qnt = quantid;
    }

    float getPreco(){
        return this.preco;
    }
    void setPreco(float preco){
        this.preco = preco;
    }

    boolean isTarja(){
        return this.tarj;
    }
    boolean isAntb(){
        return this.anti;
    }
    boolean isReceit(){
        return this.receit;
    }
};
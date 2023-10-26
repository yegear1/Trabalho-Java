public class Medicamento{
    private String nome;
    private int qnt;
    private float preco;
    private String desc;
    private boolean gen;

    public Medicamento(String nome,int qnt,float preco,String desc,boolean gen){
        this.nome = nome;
        this.qnt = qnt;
        this.preco = preco;
        this.desc = desc;
        this.gen = gen;
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
        return desc;
    }
    void setDesc (String desc){
        this.desc = desc;
    }

    boolean isGen(){
        return this.gen;
    }
};
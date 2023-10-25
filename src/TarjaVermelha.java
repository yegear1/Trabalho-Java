public class TarjaVermelha extends Medicamento{
    private boolean receita;
    private boolean receitaDupla;

    public TarjaVermelha(String nome,int qnt, float preco, String desc,boolean gen,boolean receita,boolean ReceitaDupla){
        super(nome, qnt, preco, desc, gen);
        this.receita = receita;
        this.receitaDupla = receitaDupla;
    }
    public void setReceita(boolean receita){
        this.receita = receita;
    }
    public boolean isReceita(){
        return receita;
    }
    public boolean isReceitaDupla() {
        return receitaDupla;
    }
    public void setReceitaDupla(boolean receitaDupla) {
        this.receitaDupla = receitaDupla;
    }
}
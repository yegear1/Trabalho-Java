public class TarjaPreta extends Medicamento {
    private boolean receita;

    public TarjaPreta(String nome, int qnt,float preco, String desc, boolean gen) {
        super(nome, qnt, preco, desc, gen);
        boolean receita;
    }

    public boolean isReceita() {
        return receita;
    }

    public void setReceita(boolean receita) {
        this.receita = receita;
    }
}
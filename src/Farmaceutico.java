public class Farmaceutico extends Funcionario{
    private String formacao;
    public Farmaceutico(String nome, int cpf, String cargo, float salario) {
        super(nome, cpf, cargo, salario);
        this.formacao = formacao;
    }
    public String getFormacao(){
        return formacao;
    }
    void setFormacao(String nome){
        this.formacao = formacao;
    }
}
public class Funcionario {
    private String nome;
    private int cpf;
    private String cargo;
    private float salario;

    public Funcionario(String nome, int cpf, String cargo, float salario){
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
    }

    public  String getNome(){
        return nome;
    }
    void setNome(String nome){
        this.nome = nome;
    }

    public int getCpf(){
        return this.cpf;
    }

    void setCpf(){
        this.cpf = cpf;
    }


}

public class Farmacia {

    private String nomeF;
    private int cnpj;

    public Farmacia(String nomeF,int cnpj){
        this.nomeF = nomeF;
        this.cnpj = cnpj;
    }




    public void statusFar(){
        System.out.println("Esses são");
    }

    public String getNomeF(){
        return this.nomeF;
    }

    int getCnpj(){
        return this.cnpj;
    }


}

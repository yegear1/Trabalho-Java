public class SistemaLogin {
    public static boolean login(String user, String pass){
        if (credenciaisValidas(user, pass)) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean credenciaisValidas(String user, String pass){
        // integração com banco de dados aqui!
        // quebra-galho
        if (user.equals("admin") && pass.equals("admin")){
            return true;

        } else if (user.equals("client") && pass.equals("cl")){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAdmin(String user){
        if (user.equals("admin")){
            return true;
        }
        return false;
    }
}

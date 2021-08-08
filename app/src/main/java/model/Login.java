package model;

public class Login {
    public String pseudo;
    public String mdp;

    public Login() { }

    public Login(String pseudo, String mdp) {
        this.pseudo = pseudo;
        this.mdp = mdp;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Login{" +
                "pseudo='" + pseudo + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }
}

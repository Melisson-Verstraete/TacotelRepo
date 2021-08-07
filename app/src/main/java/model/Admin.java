package model;

public class Admin {

    private int idAdmin;
    private String login;
    private String mdpAdmin;

    public Admin(int idAdmin, String login, String mdpAdmin) {
        this.idAdmin = idAdmin;
        this.login = login;
        this.mdpAdmin = mdpAdmin;
    }

    public Admin(String login, String mdpAdmin) {
        this.login = login;
        this.mdpAdmin = mdpAdmin;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdpAdmin() {
        return mdpAdmin;
    }

    public void setMdpAdmin(String mdpAdmin) {
        this.mdpAdmin = mdpAdmin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "idAdmin=" + idAdmin +
                ", login='" + login + '\'' +
                ", mdpAdmin='" + mdpAdmin + '\'' +
                '}';
    }
}

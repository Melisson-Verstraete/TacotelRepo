package model;

public class Admin {

    private int IdAdmin;
    private String Login;
    private String MdpAdmin;

    public Admin(int idAdmin, String login, String mdpAdmin) {
        IdAdmin = idAdmin;
        Login = login;
        MdpAdmin = mdpAdmin;
    }

    public Admin(String login, String mdpAdmin) {
        Login = login;
        MdpAdmin = mdpAdmin;
    }

    public int getIdAdmin() {
        return IdAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        IdAdmin = idAdmin;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getMdpAdmin() {
        return MdpAdmin;
    }

    public void setMdpAdmin(String mdpAdmin) {
        MdpAdmin = mdpAdmin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "IdAdmin=" + IdAdmin +
                ", Login='" + Login + '\'' +
                ", MdpAdmin='" + MdpAdmin + '\'' +
                '}';
    }
}

package model;

import java.util.Date;

public class Client {

    private int IdClient;
    private String Civilite;
    private String Nom;
    private String Prenom;
    private Date DateNais;
    private String Email;
    private String Tel;
    private String MdpClient;

    public Client(int idClient, String civilite, String nom, String prenom, Date dateNais, String email, String tel, String mdpClient) {
        IdClient = idClient;
        Civilite = civilite;
        Nom = nom;
        Prenom = prenom;
        DateNais = dateNais;
        Email = email;
        Tel = tel;
        MdpClient = mdpClient;
    }

    public Client(String civilite, String nom, String prenom, Date dateNais, String email, String tel, String mdpClient) {
        Civilite = civilite;
        Nom = nom;
        Prenom = prenom;
        DateNais = dateNais;
        Email = email;
        Tel = tel;
        MdpClient = mdpClient;
    }

    public Client(String civilite, String nom, String prenom, String email, String tel, String mdpClient) {
        Civilite = civilite;
        Nom = nom;
        Prenom = prenom;
        Email = email;
        Tel = tel;
        MdpClient = mdpClient;
    }

    public int getIdClient() {
        return IdClient;
    }

    public void setIdClient(int idClient) {
        IdClient = idClient;
    }

    public String getCivilite() {
        return Civilite;
    }

    public void setCivilite(String civilite) {
        Civilite = civilite;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public Date getDateNais() {
        return DateNais;
    }

    public void setDateNais(Date dateNais) {
        DateNais = dateNais;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getMdpClient() {
        return MdpClient;
    }

    public void setMdpClient(String mdpClient) {
        MdpClient = mdpClient;
    }

    @Override
    public String toString() {
        return "Client{" +
                "IdClient=" + IdClient +
                ", Civilite='" + Civilite + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
//                ", DateNais=" + DateNais +
                ", Email='" + Email + '\'' +
                ", Tel='" + Tel + '\'' +
                ", MdpClient='" + MdpClient + '\'' +
                '}';
    }
}

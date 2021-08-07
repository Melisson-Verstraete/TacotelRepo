package model;

import java.util.Date;

public class Client {

    private int idClient;
    private String civilite;
    private String nom;
    private String prenom;
    private Date dateNais;
    private String email;
    private String tel;
    private String mdpClient;

    public Client(int idClient, String civilite, String nom, String prenom, Date dateNais, String email, String tel, String mdpClient) {
        this.idClient = idClient;
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNais = dateNais;
        this.email = email;
        this.tel = tel;
        this.mdpClient = mdpClient;
    }

    public Client(String civilite, String nom, String prenom, Date dateNais, String email, String tel, String mdpClient) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNais = dateNais;
        this.email = email;
        this.tel = tel;
        this.mdpClient = mdpClient;
    }

    public Client(String civilite, String nom, String prenom, String email, String tel, String mdpClient) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.mdpClient = mdpClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNais() {
        return dateNais;
    }

    public void setDateNais(Date dateNais) {
        this.dateNais = dateNais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMdpClient() {
        return mdpClient;
    }

    public void setMdpClient(String mdpClient) {
        this.mdpClient = mdpClient;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", civilite='" + civilite + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNais=" + dateNais +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", mdpClient='" + mdpClient + '\'' +
                '}';
    }
}

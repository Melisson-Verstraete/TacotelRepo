package model;

import java.util.Date;
import java.util.List;

public class Panier {

    private int idPanier;
    private Date dateCreation;
    private List<Article> articles;

    public Panier(int idPanier, Date dateCreation) {
        this.idPanier = idPanier;
        this.dateCreation = dateCreation;
    }

    public Panier(int idPanier, Date dateCreation, List<Article> articles) {
        this.idPanier = idPanier;
        this.dateCreation = dateCreation;
        this.articles = articles;
    }

    public Panier(int idUser) {
        idPanier = idUser;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "idPanier=" + idPanier +
                ", dateCreation=" + dateCreation +
                ", articles=" + articles +
                '}';
    }
}

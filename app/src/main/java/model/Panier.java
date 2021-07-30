package model;

import java.util.Date;
import java.util.List;

public class Panier {

    private int IdPanier;
    private Date DateCreation;
    private List<Article> Articles;

    public Panier(int idPanier, Date dateCreation, List<Article> articles) {
        IdPanier = idPanier;
        DateCreation = dateCreation;
        Articles = articles;
    }

    public Panier() {
    }

    public int getIdPanier() {
        return IdPanier;
    }

    public void setIdPanier(int idPanier) {
        IdPanier = idPanier;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        DateCreation = dateCreation;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "IdPanier=" + IdPanier +
                ", DateCreation=" + DateCreation +
                ", Articles=" + Articles +
                '}';
    }
}

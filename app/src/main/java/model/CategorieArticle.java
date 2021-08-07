package model;

public class CategorieArticle {

    private int idArticle;
    private int idCategorie;

    public CategorieArticle(int idArticle, int idCategorie) {
        this.idArticle = idArticle;
        this.idCategorie = idCategorie;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "CategorieArticle{" +
                "idArticle=" + idArticle +
                ", idCategorie=" + idCategorie +
                '}';
    }
}

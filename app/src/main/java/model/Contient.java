package model;

public class Contient {

    private Panier Panier;
    private Article Article;
    private int QteArticleChoisi;

    public Contient(int qteArticleChoisi) {
        QteArticleChoisi = qteArticleChoisi;
    }

    public Contient() {
    }

    public int getQteArticleChoisi() {
        return QteArticleChoisi;
    }

    public void setQteArticleChoisi(int qteArticleChoisi) {
        QteArticleChoisi = qteArticleChoisi;
    }

    @Override
    public String toString() {
        return "Contient{" +
                "Panier=" + Panier +
                ", Article=" + Article +
                ", QteArticleChoisi=" + QteArticleChoisi +
                '}';
    }
}

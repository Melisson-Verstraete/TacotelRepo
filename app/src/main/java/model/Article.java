package model;

public class Article {
    private int idArticle;
    private String libelle;
    private String description;
    private double prix;
    private int qteEnStock;
    private double tailleEcran;
    private String marque;
    private String couleur;
    private double tailleMemoire;
    private String imageURL;

    public Article(int idArticle, String libelle, String description, double prix, int qteEnStock, double tailleEcran, String marque, String couleur, double tailleMemoire, String imageURL) {
        this.idArticle = idArticle;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.qteEnStock = qteEnStock;
        this.tailleEcran = tailleEcran;
        this.marque = marque;
        this.couleur = couleur;
        this.tailleMemoire = tailleMemoire;
        this.imageURL = imageURL;
    }

    public Article(String libelle, String description, double prix, int qteEnStock, double tailleEcran, String marque, String couleur, double tailleMemoire, String imageURL) {
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.qteEnStock = qteEnStock;
        this.tailleEcran = tailleEcran;
        this.marque = marque;
        this.couleur = couleur;
        this.tailleMemoire = tailleMemoire;
        this.imageURL = imageURL;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQteEnStock() {
        return qteEnStock;
    }

    public void setQteEnStock(int qteEnStock) {
        this.qteEnStock = qteEnStock;
    }

    public double getTailleEcran() {
        return tailleEcran;
    }

    public void setTailleEcran(double tailleEcran) {
        this.tailleEcran = tailleEcran;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public double getTailleMemoire() {
        return tailleMemoire;
    }

    public void setTailleMemoire(double tailleMemoire) {
        this.tailleMemoire = tailleMemoire;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", qteEnStock=" + qteEnStock +
                ", tailleEcran=" + tailleEcran +
                ", marque='" + marque + '\'' +
                ", couleur='" + couleur + '\'' +
                ", tailleMemoire=" + tailleMemoire +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}

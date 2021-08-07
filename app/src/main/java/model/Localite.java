package model;

public class Localite {

    private int idLocalite;
    private String rue;
    private int codePostal;
    private String numRue;
    private String ville;
    private String pays;

    public Localite(int idLocalite, String rue, int codePostal, String numRue, String ville, String pays) {
        this.idLocalite = idLocalite;
        this.rue = rue;
        this.codePostal = codePostal;
        this.numRue = numRue;
        this.ville = ville;
        this.pays = pays;
    }

    public Localite(String rue, int codePostal, String numRue, String ville, String pays) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.numRue = numRue;
        this.ville = ville;
        this.pays = pays;
    }

    public int getIdLocalite() {
        return idLocalite;
    }

    public void setIdLocalite(int idLocalite) {
        this.idLocalite = idLocalite;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getNumRue() {
        return numRue;
    }

    public void setNumRue(String numRue) {
        this.numRue = numRue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Localite{" +
                "idLocalite=" + idLocalite +
                ", rue='" + rue + '\'' +
                ", codePostal=" + codePostal +
                ", numRue='" + numRue + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}

package model;

public class Photo {

    private int idPhoto;
    private String nomPhoto;

    public Photo(int idPhoto, String nomPhoto) {
        this.idPhoto = idPhoto;
        this.nomPhoto = nomPhoto;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getNomPhoto() {
        return nomPhoto;
    }

    public void setNomPhoto(String nomPhoto) {
        this.nomPhoto = nomPhoto;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "idPhoto=" + idPhoto +
                ", nomPhoto='" + nomPhoto + '\'' +
                '}';
    }
}

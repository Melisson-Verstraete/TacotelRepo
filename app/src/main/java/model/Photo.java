package model;

public class Photo {

    private int IdPhoto;
    private String NomPhoto;

    public Photo(int idPhoto, String nomPhoto) {
        IdPhoto = idPhoto;
        NomPhoto = nomPhoto;
    }

    public Photo(String nomPhoto) {
        NomPhoto = nomPhoto;
    }

    public int getIdPhoto() {
        return IdPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        IdPhoto = idPhoto;
    }

    public String getNomPhoto() {
        return NomPhoto;
    }

    public void setNomPhoto(String nomPhoto) {
        NomPhoto = nomPhoto;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "IdPhoto=" + IdPhoto +
                ", NomPhoto='" + NomPhoto + '\'' +
                '}';
    }
}

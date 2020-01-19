public class Notice {
    String langue;

    public Notice() {
        this.langue = "français";
    }

    public Notice( String langue ) {
        this.langue = langue;
    }

    public String toString() {
        return "\t ce jeu est en " + this.langue;
    }

}

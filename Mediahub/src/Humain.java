
public class Humain {

    String sexe;
    int    age;
    String nom;

    public Humain( String sexe, int age, String nom ) {
        this.sexe = sexe;
        this.age = age;
        this.nom = nom;
    }

    public static Humain instanceHumain() {
        String sexe = "masculin";
        int age = 36;
        String nom = "Nicolas Papon";

        Humain instance = new Humain( sexe, age, nom );
        return instance;

    }

    public void afficherIdentite() {
        System.out.println( "Sexe " + sexe + " age " + age + " nom " + nom );
    }

}

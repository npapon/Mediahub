
public class Couche extends Patisserie {

    protected Patisserie patisserie;
    protected String     nomCouche;

    public Couche( Patisserie patisserie ) {
        this.patisserie = patisserie;
    }

    public String preparer() {
        String action = patisserie.preparer();

        action += nomCouche;
        return action;
    }

}

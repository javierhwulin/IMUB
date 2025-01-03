package ub.edu.model;

public enum ValorType {
    VALORAR_PER_PUNTS("ValorPunts"),
    VALORAR_PER_ESTRELLES("ValorEstrelles"),
    VALORAR_PER_LIKES("ValorLikes"),
    VALORAR_PER_PROMIG("ValoracioStrategyPromig"),
    VALORAR_PER_ABSOLUT("ValoracioStrategyAbsolut");
    private final String text;

    ValorType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

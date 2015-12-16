package graphExample;

public class Algorithms{
    /* constant declared here must be the number equal to its index in a table algorithms */
    public static final int grundy=0;
    public static final int trivial=1;
    public static final int lf=2;
    public static final int edge=3;
    public static final int mis=4;
    public static final int mds=5;
    public static final String[] algorithms={
	"Grundy coloring", "Trivial coloring", "LF coloring", 
	"Edge coloring", "Maximal Independent Set", "Minimal Dominatng Set"
    };
    public static final String[] shortNames={
	"Grundy", "Trivial", "LF", "Edge", "MIS", "MDS"
    };
}

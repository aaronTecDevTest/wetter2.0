package archiv;

public class StringPattern {

    public static void main(String[] args) {

        String ts1 = "testing.Relaunch_2014.Wetter20_ESEMOS_Sprint_01.US4122.TC2695_Mallorca_Suche_mit_Teilstring";
        String ts2 = "testing.Relaunch_2014.Wetter20_INTERN_Sprint_01.US4122.TC2695_Mallorca_Suche_mit_Teilstring";
        //String patter = "((ESEMOS)\\w*.*|(INTERN)\\w*.*)";
        String patter = "testing.Relaunch_2014.Wetter20_";

        String result1 = ts1.replaceAll(patter, "");
        String result2 = ts2.replaceAll(patter, "");
        //String result1 = ts1.replaceFirst(patter, "");
        //String result2 = ts1.replaceFirst(patter, "");

        System.out.println(result1);
        System.out.println(result2);
        //System.out.println();
        //System.out.println();

    }
}

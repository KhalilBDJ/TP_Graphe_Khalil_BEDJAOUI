import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Listes listes = new Listes("orienté", 7, 0, new ArrayList<>());
        listes.Create_Graphe(listes.getN());
        /*listes.Create_Liason(2,3);
        listes.Create_Liason(3,5);
        listes.Create_Liason(0,1);
        listes.Create_Liason(0,2);
        listes.Remove_Liaison(2,3);
        listes.estAdjacent(2,3);
        listes.estAdjacent(0,1);
        listes.estAdjacent(1,0);*/
        listes.Create_Liason(1,2);
        listes.Create_Liason(1,3);
        listes.Create_Liason(3,4);
        listes.Create_Liason(5,1);
        listes.Create_Liason(5,6);
        listes.Create_Liason(4,7);

        System.out.println("");
        listes.afficheGraphe();
        listes.txtCreator("graphe.txt");

    }
}

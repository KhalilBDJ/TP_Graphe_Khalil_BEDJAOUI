import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Listes listes = new Listes("orienté", 7, 0, new ArrayList<>());
        /*listes.Create_Graphe(listes.getN());
        listes.Create_Liason(2,3);
        listes.Create_Liason(3,5);
        listes.Create_Liason(0,1);
        listes.Create_Liason(0,2);
        listes.Remove_Liaison(2,3);
        listes.estAdjacent(2,3);
        listes.estAdjacent(0,1);
        listes.estAdjacent(1,0);
        listes.Create_Liason(1,2);
        listes.Create_Liason(1,3);
        listes.Create_Liason(3,4);
        listes.Create_Liason(5,1);
        listes.Create_Liason(5,6);
        listes.Create_Liason(4,7);

        System.out.println("");
        listes.afficheGraphe();
        listes.txtCreator("graphe.txt");*/
        //listes.Erdos(8, 0.7);
        //listes.afficheGraphe();
        //listes.Inverse();



        // TP 2

        Matrice matrice = new Matrice("orienté", 0, 0, new ArrayList<>());
        //matrice.createGraphFromText("mots.txt");

        matrice.createGraphFromTextBis("test.txt");
        matrice.adjacents("X");
        //System.out.println(matrice.matrice);
        //matrice.createLiaison("2","3");
        //matrice.createLiaison("1", "4");
        //matrice.isAdjacent("2","3");
        //matrice.isAdjacent("2","4");
        //matrice.deleteLiaison(2,3);
        //matrice.addSommet("5");
        //matrice.parcoursLargeur("VLAN");
        //matrice.findParcours("VLAN","PLAT");
        //matrice.afficheGraph();
        //matrice.txtCreator("graphe.txt");
    }
}

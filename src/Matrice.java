import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Matrice {

    private String type;
    private int n;
    private int m;
    private List<List<Integer>> matrice;
    private List<String> sommets;

    private int userChoice; //Se souvenir du choix de l'utilisateur (utile ?)

    public Matrice(String type, int n, int m, List<List<Integer>>matrice){
        this.type = type;
        this.n = n;
        this.m = m;
        sommets=new ArrayList<>();
        this.matrice = matrice;
    }

    public List<List<Integer>> create_Graphe(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Si vous voulez générer des sommets automatiquement, tapez 0, si vous voulez les entrer vous mêmes, tapez 1.");
        String response = myScanner.nextLine();
        if (response.equals("1")){
            userChoice= Integer.parseInt(response);
            for (int j=1; j<=n; j++){
                Scanner myObj = new Scanner(System.in);
                System.out.println("Entrez l'identifiant du sommet");
                String userName = myObj.nextLine();
                List<Integer> sommet = new ArrayList<>();
                for (int i = 1; i<=n; i++){
                    sommet.add(0);
                }
                sommets.add(userName);
                matrice.add(sommet);
            }
            for (String sommet:sommets) {
                for (String deuxiemeSommet: sommets) {
                    int difference=0;
                    if (sommet.length() == deuxiemeSommet.length() && sommet != deuxiemeSommet){
                        for (int i =0; i<sommet.length();i++){
                            if (sommet.charAt(i) != deuxiemeSommet.charAt(i)){
                                difference++;
                            }
                        }
                    }
                    if (difference == 1 && !isAdjacent(sommet,deuxiemeSommet)){
                        createLiaison(sommet, deuxiemeSommet);
                    }
                }
            }
            System.out.println(matrice);
            return matrice;
        }
        else {
            for (int j = 1; j<=n; j++){
                List<Integer> sommet = new ArrayList<>();
                for (int i = 1; i<=n; i++){
                    sommet.add(0);
                }
                sommets.add(String.valueOf(j));
                matrice.add(sommet);
            }
            System.out.println(matrice);
            return matrice;
        }
    }

    public List<List<Integer>> createLiaison(String a, String b){
        if (type.equals("orienté")){
            matrice.get(sommets.indexOf(a)).set(sommets.indexOf(b), 1);
            m++;
        }
        else {
            matrice.get(sommets.indexOf(a)).set(sommets.indexOf(b), 1);
            matrice.get(sommets.indexOf(b)).set(sommets.indexOf(a), 1);
            m++;
        }
        return matrice;
    }

    public List<List<Integer>> deleteLiaison(String a, String b){
        if (type.equals("orienté")){
            matrice.get(sommets.indexOf(a)).set(sommets.indexOf(b), 0);
            m--;
        }
        else {
            matrice.get(sommets.indexOf(a)).set(sommets.indexOf(b), 0);
            matrice.get(sommets.indexOf(b)).set(sommets.indexOf(a), 0);
            m--;
        }
        System.out.println(matrice);
        return matrice;
    }

    public boolean isAdjacent(String a, String b){
        if (matrice.get(sommets.indexOf(a)).get(sommets.indexOf(b)) == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public List<List<Integer>> addSommet(String a){
        if (sommets.contains(a)){
            System.out.println("le somme " + a + " existe déjà");
        }
        else {
            List<Integer> newSommet = new ArrayList<>();
            sommets.add(String.valueOf(a));
            n++;
            for (List<Integer> sommet: matrice) {
                sommet.add(0);
            }
            for (String i: sommets) {
                newSommet.add(0);
            }
            matrice.add(newSommet);
        }
        System.out.println(sommets);
        System.out.println(matrice);
        return matrice;
    }
    public void afficheGraph(){
        System.out.println("Le graphe est " + type);
        System.out.println("Le graphe possède " + n + " sommets");
        System.out.println("Le graphe possède "+ m + " arrêtes");
        System.out.println(matrice);
    }


    public void txtCreator(String fileName) throws IOException{
        File grph = new File(fileName);
        PrintWriter writer = new PrintWriter(grph);
        List<String> trashCan = new ArrayList<>();
        if (type == "orienté"){
            writer.println("1 " + n + " " + m);
        }
        if (type == "non orienté"){
            writer.println("0 " + n + " " + m);
        }
        for (String s:sommets) {
            for (String sPrime : sommets){
                if (isAdjacent(s, sPrime) && !trashCan.contains(sPrime)){
                    writer.println(s + " " + sPrime);
                    trashCan.add(s);
                }
            }
        }
        writer.close();
    }

    public List<List<Integer>> createGraphFromText(String fileName) throws IOException{
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String strline = scanner.nextLine();
            if (Pattern.matches("[0-9]", strline)){
                n = Integer.parseInt(strline);
            }
            else {
                List<Integer> sommet = new ArrayList<>();
                for (int i = 1; i<=n; i++){
                    sommet.add(0);
                }
                sommets.add(strline);
                matrice.add(sommet);
            }
        }
        for (String sommet:sommets) {
            for (String deuxiemeSommet: sommets) {
                int difference=0;
                if (sommet.length() == deuxiemeSommet.length() && sommet != deuxiemeSommet){
                    for (int i =0; i<sommet.length();i++){
                        if (sommet.charAt(i) != deuxiemeSommet.charAt(i)){
                            difference++;
                        }
                    }
                }
                if (difference == 1 && !isAdjacent(sommet,deuxiemeSommet)){
                    createLiaison(sommet, deuxiemeSommet);
                }
            }
        }

        return matrice;
    }

    public HashMap<String,Integer> parcoursLargeur(String origine) {
        HashMap<String, String> sommetsAndColor = new HashMap<>();
        HashMap<String, Integer> sommetsAndNumber = new HashMap<>();
        int i = 0;
        int s = 1;
        String sommet = origine;
        for (String som:sommets) {
            sommetsAndColor.put(som,"blanc");
        }
        List<String> sommetsGris = new ArrayList<>();
        sommetsGris.add(sommet);
        sommetsAndNumber.put(sommet,0);
        while(!sommetsGris.isEmpty()) {
            sommet=sommetsGris.get(0);
            sommetsAndColor.put(sommet,"gris");
            i++;
            s = 0;
            for (String sommetAdjacent: sommets) {
                if (isAdjacent(sommet, sommetAdjacent) && sommetsAndColor.get(sommetAdjacent).equals("blanc")){
                    sommetsAndColor.put(sommetAdjacent,"gris");
                    sommetsGris.add(sommetAdjacent);
                    sommetsAndNumber.put(sommetAdjacent,i);
                }
                else {
                    s++;
                }
            }
            if (s == sommets.size()){
                i--;
            }
            sommetsAndColor.put(sommet, "noir");
            sommetsGris.remove(sommet);
        }
        return sommetsAndNumber;
    }

    public List<String> findParcours(String origine, String end){
        HashMap<String, Integer> sommetsAndNumber = parcoursLargeur(origine);
        List<String> parcours = new ArrayList<>();
        parcours.add(end);
        int positionEnd = sommetsAndNumber.get(end);
        while (positionEnd>0){
            for (String sommet:sommets) {
                if (isAdjacent(sommet,end) && sommetsAndNumber.get(sommet) == positionEnd-1){
                    parcours.add(0, sommet);
                    positionEnd--;
                    end = sommet;
                }
            }
        }
        System.out.println(parcours);
        return parcours;
    }
}

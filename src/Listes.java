import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Listes {

    private String type; //Type de grapge (orienté ou non)
    private int n ; //Nombre de sommet
    private int m; //Nombre d'arrêtes
    List<List<Integer>> connexions; //le premier élément à gauche correspond au départ de la liasions, les autres correspondent aux arrivés

    private List<Integer> sommets;

    public Listes(String type, int n, int m, List<List<Integer>> connexions) {
        this.type = type;
        this.n = n;
        this.m = m;
        this.connexions = connexions;
        sommets=new ArrayList<>();

    }

    public List<List<Integer>> Create_Graphe(int n){
        for (int i = 1; i<=n; i++){
            List<Integer> sommet = new ArrayList<>();
            sommet.add(i);
            sommets.add(i);
            connexions.add(sommet);
        }
        return connexions;
    }

    public List<List<Integer>> Create_Liason(int a, int b){
        //Permet de changer les liaison d'un graphe existant ou alors d'en créer
        for (List<Integer> line:connexions) {
            if (line.get(0) == a) {
                if (sommets.contains(b)){
                    line.add(b);
                    m = m+1;
                }
                else {
                    System.out.println("le sommet " +b+ " n'existe pas");
                }
            }
            if (type == "non orienté" && line.get(0) == b && sommets.contains(a)){
                line.add(a);
            }
        }
        if (!sommets.contains(a)){
            System.out.println("le sommet "+a+" n'existe pas");
        }
        if (sommets.contains(a) && sommets.contains(b))
        {
            System.out.println("nouvelle liaison ajoutée : ("+ a + ", "+ b +") \n" + connexions );
            System.out.println("Le graphe possède maintenant "+ m + " arrêtes.");
        }
        return connexions;
    }

    public List<List<Integer>> Remove_Liaison(int a, int b){
        //Permet de supprimer une liaison, si celle si n'existe pas, alors on ne supprime rien et on renvoie un message d'erreur
        for (List<Integer> line:connexions){
            if (line.get(0) == a){
                if (line.contains(b)){
                    line.remove(line.indexOf(b));
                    m--;
                }
                else{
                    System.out.println("le sommet "+a+" ne possède pas de liaison avec le sommet "+b);
                }
            }

            if (type == "non orienté" && line.get(0) == b && line.contains(a)){
                line.remove(line.indexOf(a));
            }
            if (type=="non orienté" && line.get(0) == b && !line.contains(a)){
                System.out.println("les sommets " +a+ " et " + b + " ne sont pas liés");
            }

        }
        if (!sommets.contains(a)){
            System.out.println("le sommet "+a+" n'existe pas");
        }
        if (!sommets.contains(b)){
            System.out.println("le sommet "+b+" n'existe pas");
        }
        m = m - 1;
        System.out.println("liaison supprimée : ("+ a + ", "+ b +") \n" + connexions );
        return connexions;
    }

    public void addSommet(int s){
        if (sommets.contains(s)){
            System.out.println("le sommet existe déjà, veuillez en entrer un autre");
            return;
        }
        sommets.add(s);
        List<Integer> sommet = new ArrayList<>();
        sommet.add(s);
        n++;
        connexions.add(sommet);
        System.out.println("nouveau sommet ajouté : " + s + ". Le graphe possède maintenant " + n + " sommets");
        System.out.println(connexions);

    }

    public boolean estAdjacent(int a, int b){
        if (!sommets.contains(a)){
            System.out.println("le sommet : " + a + " n'existe pas");
            return false;
        }
        if (!sommets.contains(b)){
            System.out.println("le sommet : " + b + " n'existe pas");
            return false;
        }

        if (!sommets.contains(a)&&!sommets.contains(b)){
            System.out.println("les sommets entrés n'existent pas");
            return false;
        }
        if (type == "non orienté"){
            for (List<Integer> sommet:connexions) {
                if (sommet.contains(a) && sommet.contains(b)){
                    System.out.println("le sommet "+ a + " et le sommet "+ b + " sont adjacents");
                    return true;
                }
                else{
                    System.out.println("le sommet "+ a + " et le sommet "+ b + " ne sont pas adjacents");
                    return false;
                }
            }
        }
        if (type == "orienté"){
            for (List<Integer> sommet:connexions){
                if (sommet.get(0) == a || sommet.get(0)==b){
                    List<Integer> voisins = new ArrayList<>(sommet);
                    int init = voisins.get(0);
                    voisins.remove(0);
                    if (init == a && voisins.contains(b)){
                        System.out.println("les sommets sont voisins");
                        return true;
                    }
                    if (init == b && voisins.contains(b)){
                        System.out.println("les sommets sont voisins");
                        return true;
                    }
                    else{
                        System.out.println("les sommets : " + a + " et " + b +" ne possède pas de liaisons");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public void afficheGraphe(){
        System.out.println("Le graphe est " + type);
        System.out.println("Il possède : " + n + " sommets et "+ m+ " arrêtes");
        List<Integer> trashCan = new ArrayList<>();
        for (List<Integer>sommet: connexions) {
            List<Integer> linked = new ArrayList<>(sommet);
            linked.remove(0);
            if (linked.isEmpty()){
                if (!trashCan.contains(sommet.get(0))){
                    System.out.println("Le sommet " + sommet.get(0)+ " est adjacent avec aucun sommet");
                }
            }
            else{
                if(!trashCan.contains(sommet.get(0))) {
                    System.out.println("Les sommets " + sommet + " sont adjacents " );
                    trashCan.addAll(sommet);
                }
            }
        }
    }

    public void txtCreator(String fileName) throws IOException {
        File grph = new File(fileName);
        PrintWriter writer = new PrintWriter(grph);
        List<Integer> trashCan = new ArrayList<>();

        if (type == "orienté"){
            writer.println("1 " + n + " " + m);
        }
        if (type == "non orienté"){
            writer.println("0 " + n + " " + m);
        }
        for (List<Integer> sommets:connexions) {
            List<Integer> sommetsSansOrigine = new ArrayList<>(sommets);
            sommetsSansOrigine.remove(0);
            for (int sommet : sommetsSansOrigine ) {
                if (type == "orienté"){
                    writer.println(sommets.get(0) + " " + sommet);
                    System.out.println(sommets.get(0) + " " + sommet);
                }
                if (type == "non orienté" && !trashCan.contains(sommet)){
                    trashCan.add(sommets.get(0));
                    writer.println(sommets.get(0) + " " + sommet);
                    System.out.println(sommets.get(0) + " " + sommet);
                }
            }

        }
        writer.close();
    }

    public void Erdos(int s, double p){
        Create_Graphe(s);
        n = s;
        if (p == 0){
            return;
        }
        int sum = (s*(s-1))/2;
        int l = (int) Math.floor(sum*p);
        System.out.println(l);
        for (int i = 0; i<l;i++){
            int a = sommets.get((int)(Math.random()*sommets.size()));
            int b = sommets.get((int)(Math.random()*sommets.size()));
            if (a == b ){
                i--;
            } else if ((connexions.get(a-1).contains(a) && connexions.get(a-1).contains(b)) || (connexions.get(b-1).contains(a) && connexions.get(b-1).contains(b))) {
                i--;
            }
            else{
                Create_Liason(a,b);
            }
        }

    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}

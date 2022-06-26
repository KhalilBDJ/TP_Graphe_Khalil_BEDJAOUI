import lombok.ToString;

@ToString
public class Liaison {
    public int a;
    public int b;

    public Liaison(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Liaison){
            {
                if (this.a == ((Liaison) obj).a && this.b == ((Liaison) obj).b){
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }
}

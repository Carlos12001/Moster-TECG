package test;

public class MyOject {

    private String atrib1;
    private int atrib2;
    private boolean atrib3;
    private final String atrib4 ="Mira nacho";

    public MyOject(){
        this.atrib1 = "data";
        this.atrib2 = 9;
        this.atrib3 = true;
    }

    public int getAtrib2() {
        return atrib2;
    }

    public String getAtrib1() {
        return atrib1;
    }

    public boolean isAtrib3() {
        return atrib3;
    }

    public String getAtrib4() {
        return atrib4;
    }

    @Override
    public String toString() {
        return "MyOject{" +
                "atrib1='" + atrib1 + '\'' +
                ", atrib2=" + atrib2 +
                ", atrib3=" + atrib3 +
                ", atrib4='" + atrib4 + '\'' +
                '}';
    }
}

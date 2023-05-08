public class Test{
    public static void main(String[] args) throws IncorrectFormatException, RotorBoundsException, RotorIncompatibilityException{
        String[] a=new String[1];
        a[0]="jdas:1:1230";
        Enigma enigma=new Enigma(a,"ggg:5:1032");
        System.out.println(enigma.encode("aaa 0123 aaa"));
    }
}
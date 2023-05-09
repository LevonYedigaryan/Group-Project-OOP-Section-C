public class Test{
    public static void main(String[] args) throws IncorrectFormatException, RotorBoundsException, RotorIncompatibilityException{
        String[] a=new String[1];
        a[0]="aaa:3:2:1:MGydgeFSZJv hRTxftNYm4L7k1oH6pIjW0qC5nu3AQlrUB.Va92w8sXiPzcDOKbE";
        Enigma enigma=new Enigma(a,"ggg:2:1:3:XECMoAJ65dsL3v0u.jQOq4V91PHNGgUWSKT alYhn7ktRbyFeZrc2wIm8fDBpzix");
        System.out.println(enigma.encode("5bjEwmK8ff3n1YrdX9bFfc5YLn aGdymCxtDeuo9jHiDBLCQ4pg"));
    }
}
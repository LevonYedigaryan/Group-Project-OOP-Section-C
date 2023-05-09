public class Test{
    public static void main(String[] args) throws IncorrectFormatException, RotorBoundsException, RotorIncompatibilityException, RequestException{
        String[] a=new String[1];
        a[0]="aaa:3:2:1:MGydgeFSZJv hRTxftNYm4L7k1oH6pIjW0qC5nu3AQlrUB.Va92w8sXiPzcDOKbE";
        EnigmaPro enigma=new EnigmaPro(a,"ggg:2:1:3:XECMoAJ65dsL3v0u.jQOq4V91PHNGgUWSKT alYhn7ktRbyFeZrc2wIm8fDBpzix");
        enigma.changeCharacters('A', 'B');
        enigma.changeCharacters('f', '.');
        enigma.changeCharacters('a', 'O');
        enigma.changeCharacters('O', '6');
        enigma.changeCharacters('s', 'T');
        enigma.changeCharacters('l', '4');
        System.out.println(enigma.encode("5DNGLHKTLJWcjMaZk.0KCBaZTDd9GEZDalBkdQS"));
    }
}
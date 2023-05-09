public class Test{
    public static void main(String[] args) throws IncorrectFormatException, RotorBoundsException, RotorIncompatibilityException, RequestException{
        String[] a=new String[4];
        a[0]="aaa:3:2:1:MGydgeFSZJv hRTxftNYm4L7k1oH6pIjW0qC5nu3AQlrUB.Va92w8sXiPzcDOKbE";
        a[1]="dzmeruk:5:1:2:vHt96G7WiX aIdQo3J0A8nskbEryFLjg5wq.PDV2BcSNCMef1mUK4zOluZhYTpRx";
        a[2]="kanachi:2:1:1:NgxXMcnEuIKZYUbvH.d9Lm6P8OJq720rAi1tkoWlfj45e3GCV apwDSQzFhsyBTR";
        a[3]="xndzor:1:fUkre10 t.lvXP6uIaBGM2Tw5QsV4Hphxq9gAmcO87zLdbnZ3oSjiYyJCFNRDEKW";
        EnigmaProMax enigma=new EnigmaProMax(a,"ggg:2:1:3:XECMoAJ65dsL3v0u.jQOq4V91PHNGgUWSKT alYhn7ktRbyFeZrc2wIm8fDBpzix",2);
        enigma.changeCharacters('A', 'B');
        enigma.changeCharacters('f', '.');
        enigma.changeCharacters('a', 'O');
        enigma.changeCharacters('O', '6');
        enigma.changeCharacters('s', 'T');
        enigma.changeCharacters('l', '4');
        System.out.println(enigma.decode("1YgCrR4PAxZJb0oDSTMLEpHTEQ1zqVnR42HvZkjyEhfHI", "03:01:10:02:32:13:13:10:13:02:31:12:10:20:00:02:13:32:02:01:00:01:22:11:10:12:23:12:13:23:10:33:00:20:23:30:01:11:12:21:11:31:30:30:32"));
    }
}
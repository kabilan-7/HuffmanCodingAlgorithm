package algorithm;

public class Main {
    public static void main(String[] args) throws Exception {
        String string = "abcda";
        HuffmanCoder hf=new HuffmanCoder(string);
        String ec=hf.encode(string);
        System.out.println(ec);
        String dc=hf.decode(ec);
        System.out.println(dc);
    }
}

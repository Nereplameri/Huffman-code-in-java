package huffman_kodu;

public class Harf_Frekans {
    String harf;
    int tane = 1;
    String banary = "";
    
    public Harf_Frekans(String harf) {
        this.harf = harf;
    }

    
    public void banaryAdd(String i){
        banary = banary + i;
    }
    
    public void setHarf(String harf) {
        this.harf = harf;
    }

    public void addTane() {
        this.tane++;
    }
    
    
    
}

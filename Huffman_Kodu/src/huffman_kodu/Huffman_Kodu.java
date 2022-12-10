package huffman_kodu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Huffman_Kodu {
    
    public static void  listeOku(ArrayList<Harf_Frekans> arr){
        for (int i = 0; i < arr.size() ; i++){
            System.out.println("Harf : " + arr.get(i).harf + " Adeti : " + arr.get(i).tane +
                    " Banary : " + arr.get(i).banary);
        }
    }
    
    public static void listeSırala(ArrayList<Harf_Frekans> harfListesi) {
        for (int i = 0; i < harfListesi.size() ; i++){ //geride
            for(int j = i+1; j < harfListesi.size() ; j++){ //ileride
                if (harfListesi.get(i).tane > harfListesi.get(j).tane){
                    
                    Harf_Frekans box = new Harf_Frekans("Ğ");
                    //box = i
                    
                    box.harf = harfListesi.get(i).harf;
                    box.tane = harfListesi.get(i).tane;
                    
                    harfListesi.get(i).harf = harfListesi.get(j).harf;
                    harfListesi.get(i).tane = harfListesi.get(j).tane;
                    
                    harfListesi.get(j).harf = box.harf;
                    harfListesi.get(j).tane = box.tane;
                }
            }
        }
    }
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Harf_Frekans> harfListesi = new ArrayList<Harf_Frekans>();
        
        System.out.println("Harf dizinini giriniz");
        String frekans = scanner.nextLine();
        //g_harf : gelen harf // harfListesi : Harfler class'ını tutan liste
        //
        for (int i = 0; i < frekans.length() ; i++){
            
            String g_harf = Character.toString(frekans.charAt(i));
            boolean listeKontrol = false;
            
            for (int j = 0; j < harfListesi.size() ; j++){
                if (harfListesi.get(j).harf.equals(g_harf)){ //harf class'ında girilmiş harf var mı arama yapıyor.
                    harfListesi.get(j).addTane();
                    listeKontrol = true;
                    break;
                }
            }
            
            if (listeKontrol == false){ //Hiçbir harf bulunamamışsa
                harfListesi.add(new Harf_Frekans(g_harf));
            }
            
        }
        
        System.out.println("\nHarf-Sayı 'ya dökülmüş hali");
        listeOku(harfListesi);
        
        
        //harfListesi 'ni küçükten büyüğe sırala
        
        listeSırala(harfListesi);
        System.out.println("\nSıralanmış hali:");
        listeOku(harfListesi);
        
        //İleride kullanılacak liste yedeği
        ArrayList<Harf_Frekans> yedek_liste = new ArrayList<Harf_Frekans>();
        for (int i = 0; i < harfListesi.size() ; i++){
            
            String harf = harfListesi.get(i).harf;
            Harf_Frekans target = new Harf_Frekans(harf);
            target.tane = harfListesi.get(i).tane;
            
            yedek_liste.add(target);
        }

        //İleride kullanılacak liste yedeği
        
        //Birleştirme işlemi
        for (int i = 0; i < harfListesi.size()-1 ; i += 2){
            //Ardaşık 2 indeksi birleşim
            //Önce birleşmiş bir class oluştur
            
            String g_harf = harfListesi.get(i).harf + harfListesi.get(i+1).harf;
            Harf_Frekans kimera = new Harf_Frekans(g_harf);
            
            kimera.tane = harfListesi.get(i).tane + harfListesi.get(i+1).tane;
            
            harfListesi.add(kimera);
            listeSırala(harfListesi);
        }
        System.out.println("\nBirleşmiş hali:");
        listeOku(harfListesi);
        
        //Tersten giderek binary değerler oluşturma
        
        Stack stack = new Stack();
        stack.push(harfListesi.get(harfListesi.size()-1).harf);
        
        
        
        while (!stack.empty()){
            String element = (String) stack.pop();
            String ekElement = null;
            //Elementin başlangıcına benzer başlangıç arayacağız
            
            for (int i = harfListesi.size()-1 ; 0<= i; i--){ //Listeyi sondan başa doğru arayacak
                String harf = harfListesi.get(i).harf;
                if (element.startsWith(harf) && ! element.equals(harf)){
                    ekElement = harf;
                    
                    
                    //ekElement halloldu. element'i de yenilemek lazım
                    String box = "";

                    for (int j = ekElement.length() ; j < element.length() ; j++){
                        box += Character.toString(element.charAt(j)) ;
                    }
                    
                    element = box;
                    
                    break;
                }
            }
            //Ek element'in tane'si daha küçük olmalı! /////////////////////////////
            int iekElement = 0;
            int iElement = 0;
            for (int d = 0; d < harfListesi.size() ; d++){
                if (harfListesi.get(d).harf.equals(ekElement)){
                    iekElement = d;
                }
                else if (harfListesi.get(d).harf.equals(element)){
                    iElement = d;
                }
            }
            
            if (harfListesi.get(iekElement).tane > harfListesi.get(iElement).tane){
                //ek element ile element yer değiştirecek
                String box = element;
                element = ekElement;
                ekElement = box;
                
            }
            //////////////////////////////////////
            
            //Koda buradan devam edeceksin. Üstte Ek element ve element'i bulmaya çalıştın.
            //Burada, Ek element'in ana yapılarına (harf'i tek basamaklı nesnelerine) binary'sine 0 eklet xxxxxxx
            //element'in ana yapılarının binary'sine 1 eklet
            //Eğer ek element tek elemanlı değilse stack'a at xxxxxxxxxxxx
            //Eğer element tek elemanlı değilse stack'a at
            
            //Ek element'in ana yapıları
            for (int i = 0; i < ekElement.length() ; i++){
                String hedefHarf = Character.toString(ekElement.charAt(i)); //hedefHarf ile arama yap
                
                for (int j = 0; j < yedek_liste.size() ; j++){
                    if (yedek_liste.get(j).harf.equals(hedefHarf)){
                        yedek_liste.get(j).banaryAdd("0");
                        break;
                    }
                }
            }
            
            //Ek elemeenti stack'a atama koşulu
            if (ekElement.length() != 1){
                stack.push(ekElement);
            }
            
            //Aynı işlemi element için de yapıyorum (copy-paste)
            
            for (int i = 0; i < element.length() ; i++){
                String hedefHarf = Character.toString(element.charAt(i)); //hedefHarf ile arama yap
                
                for (int j = 0; j < yedek_liste.size() ; j++){
                    if (yedek_liste.get(j).harf.equals(hedefHarf)){
                        yedek_liste.get(j).banaryAdd("1");
                        break;
                    }
                }
            }
            
            //elementi stack'a atama koşulu
            if (element.length() != 1){
                stack.push(element);
            }
            
            
            
            
            
        }
        
        System.out.println("\nBanary'si eklenmiş hali:");
        listeOku(yedek_liste);
        //FREKANS STRİNG'İNİ OKUTMAK
        
        System.out.println("\nÇıktı:");
        for (int i = 0; i < frekans.length() ; i++){
            String harf = Character.toString(frekans.charAt(i));
            
            for (int j = 0; j < yedek_liste.size(); j++){
                if (yedek_liste.get(j).harf.equals(harf)){
                    System.out.print(yedek_liste.get(j).banary + " ");
                }
            }
            
        }
        
        
        //Sadece A B C D E 'ye değil, tüm harflere duyarlıdır.
        System.out.println("");
    }
    
}

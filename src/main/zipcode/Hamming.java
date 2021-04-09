package zipcode;

public class Hamming {
    String one;
    String two;
    public Hamming(String s, String s1) {
        if(s.length() != s1.length()) {
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }
        else{
            this.one = s;
            this.two = s1;
        }
    }

    public int getHammingDistance(){
        int count = 0;
        for(int i = 0; i < one.length(); i++){
            if(one.charAt(i) != two.charAt(i)){
                count++;
            }
        }
        return count;
    }
}

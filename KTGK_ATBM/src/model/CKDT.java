package model;

import java.math.BigInteger;
import java.security.SecureRandom;

public class CKDT {
	private BigInteger n,d,e;
    public BigInteger getN(){
     return n;
    }
    public void setN(BigInteger n){
        this.n = n;
    }
    public BigInteger getD(){
     return d;
    }
    public void setD(BigInteger d){
        this.d = d;
    }
    public BigInteger getE(){
     return e;
    }
    public void setE(BigInteger e){
        this.e = e;
    }
    public CKDT(BigInteger newn, BigInteger newe){
        n = newn;
        e = newe;
    }
    public CKDT() {
	}
	public void KeyRSA(int bits){
        SecureRandom r =  new SecureRandom();
        BigInteger p = new BigInteger(bits / 2, 100, r);
        BigInteger q = new BigInteger(bits / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));//m = (p-1)(q-1);
        boolean found = false;
        do{
            e = new BigInteger(bits /2 , 50, r);
            if(m.gcd(e).equals(BigInteger.ONE)&&e.compareTo(m) < 0){//neu e<m => e.compareTo(m) = -1
                found = true;
                
            }
        }while(!found);
        d = e.modInverse(m);//tìm d sao cho d*e=1(mod m)

    }
    //mã hóa
    public synchronized String encrypt(String message){
        return (new BigInteger(message.getBytes())).modPow(d, n).toString();
        
    }
    public synchronized BigInteger encrypt (BigInteger message){
        return message.modPow(d , n);
    }
    //giải mã
    public synchronized String decrypt (String message){
       return new String((new BigInteger(message)).modPow(e , n).toByteArray());
    }
    public synchronized BigInteger decrypt (BigInteger message){
        return message.modPow(e , n);
    }
    public static void main(String[] args) throws Exception{
        
    }
}

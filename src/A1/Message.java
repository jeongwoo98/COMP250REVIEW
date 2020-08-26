package A1;

import java.util.HashMap;
import java.util.Map;

public class Message {

    public String message;
    public int lengthOfMessage;

    public Message (String m){
        message = m;
        lengthOfMessage = m.length();
        this.makeValid();
    }

    public Message (String m, boolean b){
        message = m;
        lengthOfMessage = m.length();
    }

    /**
     * makeValid modifies message to remove any character that is not a letter and turn Upper Case into Lower Case
     */
    public void makeValid(){
        char[] charMessage = message.toCharArray();
        message = "";
        for(char c : charMessage){
            //If char is capital case.
            if(c>64 && c<91){
                c+=32;
                message+=c;
            }else if(c>96 && c<123){
                message+=c;
            }else{
                continue;
            }
        }
        lengthOfMessage = message.length();
    }

    /**
     * prints the string message
     */
    public void print(){
        System.out.println(message);
    }

    /**
     * tests if two Messages are equal
     */
    public boolean equals(Message m){
        if (message.equals(m.message) && lengthOfMessage == m.lengthOfMessage){
            return true;
        }
        return false;
    }

    /**
     * caesarCipher implements the Caesar cipher : it shifts all letter by the number 'key' given as a parameter.
     * @param key
     */
    public void caesarCipher(int key){
        // INSERT YOUR CODE HERE
        char[] C = message.toCharArray();
        message = "";
        for(char ch: C){
            if(key>=0){
                if(ch+key<123){
                    message+= (char)(ch+key);
                }else{
                    message += (char)(96+((ch+key)%122));
                }
            }else{
                if(ch+key>96){
                    message += (char) (ch+key);
                }else{
                    int difference = 97 - (ch+key);
                    message += (char) (123-difference);
                }
            }
        }
        lengthOfMessage = message.length();
    }

    public void caesarDecipher(int key){
        this.caesarCipher(- key);
    }

    /**
     * caesarAnalysis breaks the Caesar cipher
     * you will implement the following algorithm :
     * - compute how often each letter appear in the message
     * - compute a shift (key) such that the letter that happens the most was originally an 'e'
     * - decipher the message using the key you have just computed
     */
    public void caesarAnalysis(){
        // INSERT YOUR CODE HERE
        Map<Character,Integer> charMap = new HashMap<>();
        char[] C = message.toCharArray();
        for(char ch: C){
            if(!charMap.containsKey(ch)){
                charMap.put(ch,1);
            }else{
                charMap.put(ch,charMap.get(ch)+1);
            }
        }

        int maxFrequency = 0;
        char mostFrequentCharacter = ' ';
        for(Map.Entry<Character,Integer> entry: charMap.entrySet()){
            int frequency = entry.getValue();
            char ch = entry.getKey();
            if(frequency>maxFrequency){
                maxFrequency = frequency;
                mostFrequentCharacter = ch;
            }
        }

        int key = Math.abs(mostFrequentCharacter - 'e');
        caesarDecipher(key);
    }

    /**
     * vigenereCipher implements the Vigenere Cipher : it shifts all letter from message by the corresponding shift in the 'key'
     * @param key
     */
    public void vigenereCipher (int[] key){
        // INSERT YOUR CODE HERE
        char[] C = message.toCharArray();
        message = "";
        int i=0;
        for(char ch: C){
            int subkey = key[i%key.length];
            if(subkey>=0){
                if(ch+subkey<123){
                    message+= (char)(ch+subkey);
                }else{
                    message += (char)(96+((ch+subkey)%122));
                }
            }else{
                if(ch+subkey>96){
                    message += (char) (ch+subkey);
                }else{
                    int difference = 97 - (ch+subkey);
                    message += (char) (123-difference);
                }
            }
            i++;
        }
        lengthOfMessage = message.length();

    }

    /**
     * vigenereDecipher deciphers the message given the 'key' according to the Vigenere Cipher
     * @param key
     */
    public void vigenereDecipher (int[] key){
        // INSERT YOUR CODE HERE
        for(int i =0; i<key.length;i++){
            key[i] *= -1;
        }
        vigenereCipher(key);
    }

    /**
     * transpositionCipher performs the transition cipher on the message by reorganizing the letters and eventually adding characters
     * @param key
     */
    public void transpositionCipher (int key){
        // INSERT YOUR CODE HERE
        int nbRows;
        if(lengthOfMessage%key==0){
            nbRows = lengthOfMessage/key;
        }else{
            nbRows = lengthOfMessage/key + 1;
        }

        int counter = lengthOfMessage;
        char[][] A = new char[nbRows][key];

        //Initialize the 2D-array.
        for(int i=0; i<nbRows;i++){
            for(int j=0; j<key; j++){
                if(counter>0){
                    A[i][j] = message.charAt(i*key + j);
                }else{
                    A[i][j] = '*';
                }
                counter--;
            }
        }

        //Read it column by column.
        message="";
        for(int i = 0; i<key; i++){
            for(int j =0; j<nbRows;j++){
                message += A[j][i];
            }
        }
        lengthOfMessage = message.length();
    }

    /**
     * transpositionDecipher deciphers the message given the 'key'  according to the transition cipher.
     * @param key
     */
    public void transpositionDecipher (int key){
        // INSERT YOUR CODE HERE

        //Size of Array:
        int nbCols = key;
        int nbRows;
        if(lengthOfMessage%key==0){
            nbRows = lengthOfMessage/key;
        }else{
            nbRows = lengthOfMessage/key + 1;
        }

        char[][] A = new char[nbRows][nbCols];

        for(int i = 0; i<nbCols; i++){
            for(int j =0; j<nbRows;j++){
                A[j][i] = message.charAt(i*nbRows + j);
            }
        }

        message = "";
        //Now read it row by row:
        for(int i=0; i<nbRows;i++){
            for(int j=0; j<nbCols;j++){
                if(A[i][j]!='*'){
                    message += A[i][j];
                }
            }
        }

        lengthOfMessage = message.length();
    }

}

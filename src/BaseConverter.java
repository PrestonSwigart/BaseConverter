import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Base Converter Project for AP CS
 * @author PSwigart
 * @version 11.30.2022
 */
public class BaseConverter {
    private final String DIGITS = "0123456789ABCDEF";
    /**
     * Convert a String num in fromBase to base-10 int.
     * @param num the original number
     * @param fromBase the original from base
     * @return a base-10 int of num base fromBase
     */
    public int strToInt(String num, String fromBase)    {
        int value = 0, decimalBase, exp = 0;
        for(int i = num.length()-1; i >= 0; i--) {
            value += DIGITS.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), exp);
            exp++;
        }

        return value;
    }

    /**
     * Converts base 10 num to the new base
     * @param num
     * @param toBase
     * @return
     */
    public String intToStr(int num, int toBase) {
        String toNum = new String();
        int index = -1;
        while(num > 0) {
            index = num % toBase;
            toNum = DIGITS.charAt(index) + toNum;
            num /= toBase;
        }
        return (toNum.equals("0")) ? "0" : toNum;
    }

    /**
     * Takes input, converts, prints, and writes
     */
    public void inputConvertPrintWrite()    {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(new File("datafiles2/values30.dat"));
            out = new PrintWriter(new File("datafiles2/converted.dat"));
            String[] line;
            while (in.hasNext()) {
                line = in.nextLine().split("\t");
                if (Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 16)
                    System.out.println("Invalid input base " + line[1]);
                else if (Integer.parseInt(line[2]) < 2 || Integer.parseInt(line[2]) > 16)
                    System.out.println("Invalid output base " + line[2]);
                else {
                    out.println(line[0] + "\t" + line[1] + "\t" +
                            intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2])) + "\t" + line[2]);
                    //System.out.println(line[0] + " base "); // String num
                    //System.out.print(line[1] + " = "); // String fromBase
                    System.out.println(line[0] + " base " + line[1] + " = " +
                            intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2])) + " base " + line[2] + " ");
                    //System.out.print(line[2]); // String toBase
                }
            }
            /*String[] printLine = new String[0];
            while(in.hasNext()) {
                in.nextLine().split("\t");
                out.println(printLine[0] + "\t" + printLine[1] + "\t" +
                        intToStr(strToInt(printLine[0], printLine[1]),
                                Integer.parseInt(printLine[2])) + "\t" + printLine[2]);
                System.out.println(printLine[0] + " base "); // String num
                System.out.print(printLine[1] + " = "); // String fromBase
                System.out.print(printLine[2] + " base "); // String toBase
        }*/

            if(out != null)
                out.close();
            if(in != null)
                in.close();
        }
        catch(Exception e){
            System.out.println("Something went wrong, see here: " + e.toString());
        }
    }

    /**
     * Main method for BaseConverter.java
     * @param args
     */
    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();
        bc.inputConvertPrintWrite();
    }
}
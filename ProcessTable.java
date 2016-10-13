/**
 * Created by CHIU on 10/12/2016.
 */
import java.io.*;
import java.util.*;
import java.lang.*;

public class ProcessTable {
    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length == 0) {
            System.out.println("ERROR: NO FILE INPUT. PLEASE TRY AGAIN.");
            System.exit(1); // ZERO FILE INPUT; ERROR CODE: 1
        }
        File input = new File(args[0]); //reads input from terminal
        ArrayList<ProcessInfo> P_Container = new ArrayList<>();
        int num_Proc = file_Load(input ,P_Container); // Process data into Arraylist of objects
        int num_Cycles;
        //System.out.println("HELLO" + P_Container.get(1).get_ID());
        boolean flag = false;
        do{
            for(int i = 0; i < num_Proc ; i++){

            }
        }while(!flag);
    }

    public static int file_Load(File file, ArrayList pc) {
        String temp;
        int count =0;
        String cvsParse = "\\,|\\;|\\:";
        Queue input = new LinkedList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((temp = br.readLine()) != null) {
                String[] test = temp.split(cvsParse);
                for (String s : test) {
                    input.add(Integer.valueOf(s));
                    //System.out.println(Integer.valueOf(s));
                }
                ProcessInfo Proc_Info = new ProcessInfo(input);
                process_File(Proc_Info);
                count++;
                pc.add(Proc_Info);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //process_File(pc);
        return count;
    }

    public static void process_File(ProcessInfo pc){
        System.out.println("TESTING: " + pc.get_ID()+ pc.get_COUNTER());
        /*int count = 0;
        while(count!=3) {
            System.out.println("TESTING:" + pc.get(count++));
        }*/
    }

    public static int process_TNum_Cycles(ArrayList<ProcessInfo> pc) {
        int large = pc.get(0).getNum_Cycles();
        for (int i = 0; i < pc.size(); i++){

        }

    }

    public static void process_ID(ArrayList pc) {

    }
}

import java.util.*;

/**
 * Created by CHIU on 10/12/2016.
 */
public class ProcessInfo {
    private int pid;
    private int pc;
    private int num_Cycles;
    private int current;
    private boolean status;
    private ArrayList<Integer> indx;  // Blocked cycles
    private HashMap<Integer,Integer> blocked;

    ProcessInfo(Queue<Integer> info) {
        current =0;
        status = false;
        pid = info.poll();
        //System.out.println("CHECK:" + pid);
        pc = info.poll();
        //System.out.println("CHECK:" + pc);
        num_Cycles = info.poll();
        //System.out.println("CHECK:" + num_Cycles);
        indx = new ArrayList<>();
        blocked = new HashMap<>();
        while(!info.isEmpty()) {
            //System.out.println("check(indx)" + info.peek());
            int cur=0;
            indx.add(info.poll());
            if (!info.isEmpty()) {
                //System.out.println(current);
                //System.out.println(info.peek());
                blocked.put(indx.get(current), info.poll());
                current++;
            }
        }

        /*if(!blocked.isEmpty()){
            System.out.println("TESTING HASH: " + blocked.get(indx.get(0)) );
            System.out.println(indx.get(0));
            System.out.println("TESTING HASH: " + blocked.get(indx.get(1)) );
            System.out.println(indx.get(1));
        }*/
    }

    public int get_ID(){
        return pid;
    }

    public int get_COUNTER(){
        return pc;
    }

    public int getNum_Cycles(){
        return num_Cycles;
    }

    public HashMap get_BLOCK(){
        return blocked;
    }

    public boolean is_Map_Empty() {
        return blocked.isEmpty();
    }

    public boolean is_Blocked(int cur){ //status = true  == blocked
        if(blocked.isEmpty()){
            return false;
        }
        //System.out.println(pc);
        //int temp = blocked.get(pc)-100;
        if(!(blocked.isEmpty()) && blocked.get(pc)!=null) {
            //System.out.println("pc" + pc);
            if(blocked.get(pc)>0){
                //blocked.put(pc,temp);
                return true;
            }
        }
            return false;
    }

    public int check_Status(int cur) {
        if(!status) {
            //System.out.println("pc" + pc);
            if (is_Blocked(pc)) {
                int temp = blocked.get(pc) - 100;
                System.out.println(pid + " BLOCKED " + blocked.get(pc) + " - > " + temp);
                blocked.put(pc, temp);
                if (temp == 0) {
                    System.out.println(pid + " READY");
                }
            }
            if (num_Cycles == 0) {
                System.out.println(pid + " DONE");
                status = true;//done
                return 1;
            }
            return 0;
        }
        return 0;
    }

    /*public boolean check_Status(int cur) {
        if(!status) {
            //System.out.println("pc" + pc);
            if (is_Blocked(pc)) {
                int temp = blocked.get(pc) - 100;
                System.out.println(pid + " BLOCKED " + blocked.get(pc) + " - > " + temp);
                blocked.put(pc, temp);
                if (temp == 0) {
                    System.out.println(pid + " READY");
                }
            }
            if (num_Cycles == 0) {
                System.out.println(pid + " DONE");
                status = true;//done
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }*/

    public void run(int cur){
        if(!status) {
            if (!(is_Blocked(pc))) {
                System.out.println(pid + " RUNNING");
                if(!((num_Cycles-100)<0)) {
                    System.out.println("pc " + pc + " -> " + (pc + 100));
                    num_Cycles -=100;
                }
                else{
                    System.out.println("pc " + pc + " -> " + (pc + num_Cycles));
                    num_Cycles = 0;
                }
                System.out.println(pid + " READY");
                pc = pc + 100;
                //System.out.println(pc);
            } else {
                System.out.println(pid + " RUNNING");
                System.out.println("pc " + pc + " -> " + pc);
                System.out.println(pid + " BLOCKED");
            }
        }
    }
}

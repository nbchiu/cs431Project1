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
    private ArrayList<Integer> indx;
    private HashMap<Integer,Integer> blocked;

    ProcessInfo(Queue<Integer> info) {
        current =0;
        status = false;
        pid = info.poll();
        System.out.println("CHECK:" + pid);
        pc = info.poll();
        System.out.println("CHECK:" + pc);
        num_Cycles = info.poll();
        System.out.println("CHECK:" + num_Cycles);
        while(!info.isEmpty()) {
            System.out.println("check" + info.peek());
            int cur = 0;
            indx.add(info.poll());
            System.out.println("CHECK" + indx.get(0));
            blocked = new HashMap();
            if (info.peek()!=null) {
                blocked.put(indx.get(cur), info.poll());
                cur++;
            }
        }

        if(!blocked.isEmpty()){
            System.out.println("TESTING HASH: " + blocked.get(indx.get(0)) );
            System.out.println("TESTING HASH: " + blocked.get(indx.get(1)) );
        }
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
        int temp = blocked.get(cur)-100;

        if(!(blocked.isEmpty()) &&  blocked.get(cur)>0){
            blocked.put(cur,temp);
            return true;
        }
        else
            return false;
    }

    public boolean check_Status(int cur) {
        if(!status) {
            if (is_Blocked(cur)) {
                int temp = blocked.get(cur) - 100;
                System.out.println(pid + " BLOCKED" + blocked.get(cur) + " - >" + temp);
                blocked.put(cur, temp);
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
        return false;
    }

    public void run(int cur){
        if(!status) {
            if (!(is_Blocked(cur))) {
                System.out.println(pid + "RUNNING");
                System.out.println("pc " + cur + " -> " + (cur + 100));
                System.out.println(pid + "READY");
                num_Cycles -= 100;
            } else {
                System.out.println(pid + " RUNNING");
                System.out.println("pc" + cur + " -> " + cur);
                System.out.println(pid + " BLOCKED");
            }
        }
    }
}

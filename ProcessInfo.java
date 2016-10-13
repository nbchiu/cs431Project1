import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by CHIU on 10/12/2016.
 */
public class ProcessInfo {
    private int pid;
    private int pc;
    private int num_Cycles;

    private HashMap blocked;

    ProcessInfo(Queue<Integer> info) {
        pid = info.poll();
        System.out.println("CHECK:" + pid);
        pc = info.poll();
        System.out.println("CHECK:" + pc);
        num_Cycles = info.poll();
        System.out.println("CHECK:" + num_Cycles);
        blocked = new HashMap();
        while(!info.isEmpty()) {
            blocked.put(info.poll(),info.poll());
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
}

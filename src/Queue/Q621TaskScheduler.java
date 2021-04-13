package Queue;

import java.util.*;

public class Q621TaskScheduler {
    private static class TaskType{
        char type;
        int num;
        int timer;

        public TaskType(char type){
            this.type = type;
            num = 1;
            timer = 0;
        }

        public char InsertTask(int timer){
            this.timer = timer;
            this.num --;
            return this.type;
        }

        public boolean canInsert(){
            return timer == 0;
        }

        public void nextTime(){
            timer = timer == 0 ? 0 : --timer;
        }

        public boolean isEmpty(){
            return this.num == 0 ;
        }
    }

    public static int TaskScheduler(char[] tasks, int n){
        /*----统计个数----*/
        Map<Character , TaskType> map = new HashMap<>();
        for(int i = 0 ; i < tasks.length ; i++){
            char ch = tasks[i];
            if( !map.containsKey(ch) ) {
                TaskType taskType = new TaskType(ch);
                map.put(ch , taskType);
            } else {
                map.get(ch).num++;
            }
        }

        /*----排序----*/
        ArrayList<TaskType> taskTypes = new ArrayList<>();
        for(char ch : map.keySet()){
            taskTypes.add(map.get(ch));
        }
        taskTypes.sort( (TaskType t1 , TaskType t2) -> { return t2.num - t1.num;} );

        /*---schedule----*/
        int ans = 0;
        ArrayList<Character> output = new ArrayList<>();
        while (taskTypes.size() != 0){
            ans++;
            boolean isInsert = false;
            for(int i = 0 ; i < taskTypes.size() ; i++){
                TaskType taskType = taskTypes.get(i);
                if( taskType.canInsert() ){
                    isInsert = true;
                    output.add( taskType.InsertTask(n + 1) );
                    break;
                }
            }
            if ( !isInsert ) output.add(' ');

            Iterator<TaskType> iterator = taskTypes.iterator();
            while (iterator.hasNext()){
                TaskType taskType = iterator.next();
                if( taskType.isEmpty() ) iterator.remove();
                else taskType.nextTime();
            }
            taskTypes.sort( (TaskType t1 , TaskType t2) -> { return t2.num - t1.num;} );
        }

        for (char ch : output) System.out.print(ch + " ");
        return ans;
    }

    public static void main(String[] args) {
        TaskScheduler( new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'} , 2);
    }
}

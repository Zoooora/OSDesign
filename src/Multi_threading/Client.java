package Multi_threading;

import java.util.Random;

public class Client{
    int direction = (new Random().nextInt()) % 2 == 0 ? 1 : -1;
    int no;
    int arrive_time;

    public Client(int arrive_time, int no){
        this.arrive_time = arrive_time;
        this.no = no;
    }

}

package Multi_threading;

import javafx.scene.control.Label;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Server {
    public static int passable = 0;// l_to_r -> 1, r_to_l -> -1
    public static int last_direction = 0;
    public static int arrive_interval = 0;
    public static int passing_time = 0;
    public static int nowtime = 0;
    public static int totaltime = 0;
    public static ArrayList<String> info = new ArrayList<>();
    public static ArrayList<Client> left_to_right = new ArrayList<>();
    public static ArrayList<Client> right_to_left = new ArrayList<>();

    public void start_up(int arrive_interval, int passing_time, int totaltime) throws InterruptedException {
        this.arrive_interval = arrive_interval;
        this.passing_time = passing_time;
        this.totaltime = totaltime;
        Client temp = null;

        for(int i = 0, no = 1; i <= totaltime; i += arrive_interval, no++){
            Client p = new Client(i, no);
            if(p.direction == 1) left_to_right.add(p);
            else right_to_left.add(p);
        }

        while(!left_to_right.isEmpty() && !right_to_left.isEmpty()){
            ArrayList<Client> tempArray;
            if(passable == 0){
                passable = left_to_right.get(0).arrive_time < right_to_left.get(0).arrive_time ? left_to_right.get(0).direction : right_to_left.get(0).direction;
                if(passable == 1) tempArray = left_to_right;
                else tempArray = right_to_left;
                last_direction = passable;
            }
            else {
                tempArray = (passable == 1 ? left_to_right : right_to_left);
            }
            temp = tempArray.remove(0);
            nowtime += passing_time;
            info.add((temp.no < 10 ? "第0" + temp.no : "第" + temp.no) + "辆车已过桥, 方向" + (temp.direction == 1 ? "从左到右" : "从右到左")
                    + (temp.arrive_time < 10 ? "\n到达时间0" + temp.arrive_time : "\n到达时间" + temp.arrive_time)
                    + (nowtime < 10 ? "  完成过桥时间0" + nowtime : "  完成过桥时间" + nowtime));
            if(tempArray.isEmpty() || temp.arrive_time + passing_time < tempArray.get(0).arrive_time) passable = 0;
        }

        ArrayList<Client> tempArray = !left_to_right.isEmpty() ? left_to_right : right_to_left;
        while(!tempArray.isEmpty()){
            temp = tempArray.remove(0);
            nowtime += passing_time;
            info.add((temp.no < 10 ? "第0" + temp.no : "第" + temp.no) + "辆车已过桥, 方向" + (temp.direction == 1 ? "从左到右" : "从右到左")
                    + (temp.arrive_time < 10 ? "\n到达时间0" + temp.arrive_time : "\n到达时间" + temp.arrive_time)
                    + (nowtime < 10 ? "  完成过桥时间0" + nowtime : "  完成过桥时间" + nowtime));
        }
    }
}

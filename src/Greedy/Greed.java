package Greedy;

import java.util.ArrayList;
import java.util.List;

public class Greed {
//	1.先将任务按照时间惩罚递减顺序进行排序， 
//	2.然后用贪心的思想，尽量把惩罚重的任务先放入待完成队列中。 
	public class Task{
		public int id;
		public int d;//截止时间
		public int w;//惩罚
	}
	public void init_array(int n, Task[] tasks){
		//生成随机数据
		for(int i=0;i<n;i++){
			tasks[i] = new Task();
//			tasks[i].id = i;
//			tasks[i].d = (int)(Math.random() * (n-1))+1;
//			tasks[i].w = (int)(Math.random() * 30);
		}
		tasks[0].id = 1;tasks[0].d = 4;tasks[0].w = 10;
		tasks[1].id = 2;tasks[1].d = 2;tasks[1].w = 20;
		tasks[2].id = 3;tasks[2].d = 4;tasks[2].w = 30;
		tasks[3].id = 4;tasks[3].d = 3;tasks[3].w = 40;
		tasks[4].id = 5;tasks[4].d = 1;tasks[4].w = 50;
		tasks[5].id = 6;tasks[5].d = 4;tasks[5].w = 60;
		tasks[6].id = 7;tasks[6].d = 6;tasks[6].w = 70;
		
//		tasks[0].id = 1;tasks[0].d = 4;tasks[0].w = 70;
//		tasks[1].id = 2;tasks[1].d = 2;tasks[1].w = 60;
//		tasks[2].id = 3;tasks[2].d = 4;tasks[2].w = 50;
//		tasks[3].id = 4;tasks[3].d = 3;tasks[3].w = 40;
//		tasks[4].id = 5;tasks[4].d = 1;tasks[4].w = 30;
//		tasks[5].id = 6;tasks[5].d = 4;tasks[5].w = 20;
//		tasks[6].id = 7;tasks[6].d = 6;tasks[6].w = 10;
		
	}
	public void sort_des(int n, Task[] tasks){
		for(int i = 0; i < n-1; i++){
			for(int j = i+1; j < n; j++){
				if( tasks[i].w < tasks[j].w ){
					Task temp = new Task();
					temp = tasks[i];
					tasks[i] = tasks[j];
					tasks[j] = temp;
				}
			}
		}
	}
	public void sort_asc(int n, Task[] tasks){
		for(int i = 0; i < n-1; i++){
			for(int j = i+1; j < n; j++){
				if( tasks[i].d > tasks[j].d ){
					Task temp = new Task();
					temp = tasks[i];
					tasks[i] = tasks[j];
					tasks[j] = temp;
				}
			}
		}
	}
	public void run(int n, Task[] tasks, List<Task> a,List<Task> b){
		int j = 0;
		for(int i=0;i<n;i++){
			a.add(tasks[i]);
			if(N(i+1, a) == false){
				b.add(tasks[i]);
				a.remove(j);
			}else{
				j++;
			}
		}
	}
	public boolean N(int t, List<Task> a) {
		int length = a.size();
		int total = 0;
		for(int i = 1; i <= t; i++ ){
			total = 0;
			for(int j=0; j < length; j++){
				if( a.get(j).d <= i){
					total++;
				}
			}
			if( total > i){
				return false;
			}
		}
		return true;
	}
	public void Print(int n, Task[] tasks){
		for(int i=0;i<n;i++){
			System.out.println("id:" + tasks[i].id +" d:"+ tasks[i].d +" w:"+ tasks[i].w );
		}
	}
	public void Print(List<Task> tasks){
		for(int i=0;i<tasks.size(); i++){
			System.out.println("id:" + tasks.get(i).id +" d:"+ tasks.get(i).d +" w:"+ tasks.get(i).w );
		}
	}
	public int result( Task[] tasks, List<Task> a, List<Task> b){
		for(int i = 0; i < a.size(); i++){
			tasks[i] = a.get(i);
		}
		sort_asc(a.size(), tasks);
		int punish = 0;
		for(int i = a.size(); i < a.size()+b.size(); i++){
			tasks[i] = b.get(i-a.size());
			punish += tasks[i].w;
		}
		return punish;
	}
	public static void main(String[] args){
		//初始化数据
		int n = 7;
		Task[] tasks = new Task[n];
		List<Task> a = new ArrayList<Task>();
		List<Task> b = new ArrayList<Task>();
		Greed greed = new Greed();
		greed.init_array(n,tasks);
		
		//按照惩罚降序排列
		greed.sort_des(n,tasks);
		//贪心选择
		greed.run(n,tasks,a,b);
		
		//输出初始全部任务
		greed.Print(n,tasks);
		
		//输出提前任务
		greed.Print(a);
		System.out.println("---");
		//输出迟到任务
		greed.Print(b);
		System.out.println("---");
		//整合结果
		System.out.println("punish:" + greed.result(tasks, a, b));
		//输出最优调度序列
		greed.Print(n,tasks);
	}
}

package Dynamic_Programming;

import java.util.Arrays;

public class Knapsack_problem {
	/*
	 * 动态规划求解0-1背包
	 */
	//存储选择结果
	int x[];
	int f[][];
	public int Knapsack(int num, int[] w, int[] v, int weight){
		//x初始化
		x = new int[num+1];
		for(int i = 0; i <= num; i++){
			x[i] = 0;
		}
		//f初始化0行和0列
		f = new int[num+1][weight+1];
		for(int i = 0; i <= num; i++){
			f[i][0] = 0;
		}
		for(int i = 0; i <= weight; i++){
			f[0][i] = 0;
		}
		//第i行的迭代计算
		for(int i = 1; i <= num; i++){
			for(int j = 1; j <= weight; j++){
				if( j < w[i] ){
					f[i][j] = f[i-1][j];
				}else{
					f[i][j] = max(f[i-1][j] , f[i-1][j-w[i]] + v[i]);
				}
			}
		}
		//计算装入背包的物品
		int j = weight;
		for(int i = num; i > 0; i--){
			if( f[i][j] > f[i-1][j] ){
				x[i] = 1;
				j -= w[i];
			}else{
				x[i] = 0;
			}
		}
		return f[num][weight];
	}
	public int max(int a, int b){
		return a>b?a:b;
	}
	public void Print(){
		for( int i = 1; i <= x.length-1; i++){
			System.out.println( "Knapsack" + i + ":" + x[i]);
		}
	}
	
	/*
     * 贪心法求解部分背包
     */
	//存储单位重量价值和对应的index
	double[] r;
	int[] index;
	//按单位重量价值 r[i] = v[i] / w[i] 降序排列
	public void Greed(int num, int[] w, int[] v, int weight){
		r = new double[num+1];
		index = new int[num+1];
		for(int i = 1; i <= num ; i++){
			r[i] = (double)v[i] / (double)w[i];
			index[i] = i;
		}
		//1~num冒泡排序
		double temp = 0;
		for( int i = 1; i <= num-1; i++){
			for( int j = i+1; j <=num; j++){
				if( r[i] < r[j] ){
					temp = r[i];
					r[j] = r[i];
					r[j] = temp;
					int x = index[i];
					index[i] = index[j];
					index[j] = x;
				}
			}
		}
//		System.out.println( "" + Arrays.toString(r));
		//存储排序后的重量和价值
		int[] w1 = new int[num+1];  
        int[] v1 = new int[num+1];  
        for (int i = 1; i <= num; i++) {  
            w1[i] = w[index[i]];
            v1[i] = v[index[i]];
        } 
        //存储选择结果,x存储存放的重量
        for (int i = 1; i <= num; i++) {  
            x[i] = 0;  
        }
        for(int i = 1; i <= num; i++){
        	if( w1[i] < weight ){
        		x[i] = w1[i];
        		weight -= w1[i];
        	}else{
        		x[i] = weight;
        		break;
        	}
        }
//        System.out.println( "" + Arrays.toString(x));
        //计算总价值
        double sum = 0;
        for(int i = 1; i <= num; i++){
        	if( x[i] >0 ){
        		sum += ((double)x[i] / (double)w1[i]) * v1[i];
        	}
        }
		System.out.println("MaxValue:" + sum);
	}
	
	/*
	 * 部分背包的动态规划求解
	 */
	public int part_knapsack_dp(int num, int[] w, int[] v, int weight){
		/*转化为01背包，按重量划分为2的等比序列，转化为新的价值序列v_new[], w_new[]，将新的序列作为01背包函数参数得出结果*/

	    int k = 0;
	    double pv = 0; //平均价值有可能非整数
	    //背包数
	    int amount = 0;
	    //按2的等比序列进行拆分,计算最新的背包数目
	    for(int i = 1; i <= num; i++)
	    {
	        amount = amount + (int)Math.ceil((Math.sqrt(w[i]+1)));
	    }
	    System.out.println("新的背包总数" + amount);
	    int v_new[] = new int[amount+1];//新的价值序列
	    int w_new[] = new int[amount+1];//新的重量序列

	    int j = 1;
	    v_new[0] = 0;
	    w_new[0] = 0;
	    for(int i = 1; i <= num; i++)
	    {
	        k = 1;
	        pv =  (double)v[i] / (double)w[i];
	        while( k <= w[i])
	        {
	            w_new[j] = k;
	            v_new[j] = (int)(k * pv);
	            w[i] =w[i] - k;
	            k = k * 2;
	            j++;
	        }
	        w_new[j] = w[i];
	        v_new[j] = (int)(w[i] * pv);
	        j++;
	    }
	    return Knapsack2(amount, w_new, v_new, weight);
	}
	//存储选择结果
	int xx[];
	int ff[][];
	public int Knapsack2(int num, int[] w, int[] v, int weight){
		//x初始化
		xx = new int[num+1];
		for(int i = 0; i <= num; i++){
			xx[i] = 0;
		}
		//f初始化0行和0列
		ff = new int[num+1][weight+1];
		for(int i = 0; i <= num; i++){
			ff[i][0] = 0;
		}
		for(int i = 0; i <= weight; i++){
			ff[0][i] = 0;
		}
		//第i行的迭代计算
		for(int i = 1; i <= num; i++){
			for(int j = 1; j <= weight; j++){
				if( j < w[i] ){
					ff[i][j] = ff[i-1][j];
				}else{
					ff[i][j] = max(ff[i-1][j] , ff[i-1][j-w[i]] + v[i]);
				}
			}
		}
		//计算装入背包的物品
		int j = weight;
		for(int i = num; i > 0; i--){
			if( ff[i][j] > ff[i-1][j] ){
				xx[i] = 1;
				j -= w[i];
			}else{
				xx[i] = 0;
			}
		}
		return ff[num][weight];
	}
	
	public static void main(String[] args){
		
//		Scanner in = new Scanner(System.in); 
//		int n = in.nextInt();  
		
		// 前面加上一个0
//		int w[] = {0,10,20,30};
//		int v[] = {0,60,100,120};
//		int weight = 50;
		
		int w[] = {0,2,2,6,5,4};
		int v[] = {0,6,3,5,4,6};
		int weight = 10;
		
		//num从0开始计算，需要减去1
		int num = v.length -1;
		
		Knapsack_problem knapsack = new Knapsack_problem();
		
		//动态规划方法
		int value = knapsack.Knapsack(num, w, v, weight);
		System.out.println("MaxValue:" + value);
		knapsack.Print();
		//贪心方法
		knapsack.Greed(num, w, v, weight);
		//部分背包动态规划
		value = knapsack.part_knapsack_dp(num, w, v, weight);
		System.out.println("MaxValue:" + value);
		
	}
}

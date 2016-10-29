/**
 * Subject:动态规划-装配线调度
 * Author:孙剑峰
 * Date:2016.10.29
 * Language:C/C++
 * HomePage:www.csxiaoyao.com
 */
#include <iostream>
#include <stdio.h>
using namespace std;
//变量说明
int n;                 // 一个装配线上有n个装配站
int e1, e2;            // 进入装配线1,2需要的时间
int x1, x2;            // 离开装配线1,2需要的时间
int t[3][100];         // 转移时间，t[1][j]表示从S[1][j]移动到S[2][j+1]所需时间
int a[3][100];         // 装配时间，a[1][j]表示在装配站S[1][j]所需时间
int f[3][100];  // f[1][j], f[2][j]分别表示在第一/第二条装配线上第j个装配站的最优解
int ln[3][100];// ln[1][j]记录第一条装配线上,最优解时第j个装配站的前一个装配站是第一条线还是第二条线上
int r, l;             // 最优解,r代表最小花费时间，l表示最后出来时是从装配线1还是装配线2

void DP()
{
    f[1][1] = e1 + a[1][1];
    f[2][1] = e2 + a[2][1];

    for(int j = 2; j <= n; j++)
    {
        //line1最优子结构
        if(f[1][j-1] + a[1][j] <= f[2][j-1] + a[1][j] + t[2][j-1])
        {
            f[1][j] = f[1][j-1] + a[1][j];
            ln[1][j] = 1;
        }
        else
        {
            f[1][j-1] = f[2][j-1] + a[1][j] + t[2][j-1];
            ln[1][j] = 2;
        }
        //line2最优子结构
        if(f[2][j-1] + a[2][j] <= f[1][j-1] + a[2][j] + t[1][j-1])
        {
            f[2][j] = f[2][j-1] + a[2][j];
            ln[2][j] = 2;
        }
        else
        {
            f[2][j] = f[1][j-1] + a[2][j] + t[1][j-1];
            ln[2][j] = 1;
        }
    }
    if(f[1][n] + x1 <= f[2][n] + x2)
    {
        r = f[1][n] + x1;
        l = 1;
    }
    else
    {
        r = f[2][n] + x2;
        l = 2;
    }
}

void PrintStation()
{
    //line1/line2
    int i = l;
    cout << "line " << i << ", station " << n << endl;
    for(int j = n; j >= 2; j--)
    {
        if( i == 1)
            i = ln[1][j];
        else
            i = ln[2][j];
        cout << "line " << i << ", station " << j-1 << endl;
    }
}

int main()
{
    freopen("input.txt", "r", stdin);
    cout << "输入装配站的个数: ";
    cin >> n;
    cout << "输入进入装配线1，2所需的时间e1, e2 :";
    cin >> e1 >> e2;
    cout << "输入离开装配线1, 2所需的时间x1, x2: ";
    cin >> x1 >> x2;
    cout << "输入装配线1上各站加工所需时间a[1][j]: ";
    for(int i=1; i<=n; i++)
        cin >> a[1][i];
    cout << "输入装配线2上各站加工所需时间a[2][j]: ";
    for(int i=1; i<=n; i++)
        cin >> a[2][i];
    cout << "输入装配线1上的站到装配线2上的站所需时间t[1][j]: ";
     //注意这里是i<n，不是i<=n
    for(int i=1; i<n; i++)
        cin >> t[1][i];
    cout << "输入装配线2上的站到装配线1上的站所需时间t[2][j]: ";
    for(int i=1; i<n; i++)
        cin >> t[2][i];

    DP();
    cout << "最快需要时间: " << f << endl;
    cout << "路线是: " << endl;
    PrintStation();
    cout << endl;
}

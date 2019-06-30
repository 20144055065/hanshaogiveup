/*hihocoder #1174 : Õÿ∆À≈≈–Ú°§“ª*/
#include <iostream>
#include<string.h>
#include<queue>
#include<algorithm>
using namespace std;
#define maxn 100010
int t,n,m,head[maxn],f,in[maxn];
queue<int>q;
struct node
{
  int e,next;
}edge[maxn];
void add(int s,int e)
{
    edge[f].e=e;
    edge[f].next=head[s];
    head[s]=f++;
}
int main()
{
    int i,a,b,ans;
    cin>>t;
    while(t--)
    {  ans=0;f=1;
      cin>>n>>m;
      memset(in,0,sizeof(in));
      memset(head,-1,sizeof(head));
      for(i=0;i<m;i++)
      {
          cin>>a>>b;
          add(a,b);
          in[b]++;
      }
      for(i=1;i<=n;i++)
        if(!in[i])
        {
          q.push(i);
          ans++;
        }
      while(!q.empty())
        {
          int k=q.front();
            q.pop();
          for(i=head[k];i!=-1;i=edge[i].next)
          {
             int v=edge[i].e;
             in[v]--;
             if(!in[v])
             {
               q.push(v);
               ans++;
             }
          }
        }
      if(ans==n)cout<<"Correct\n";
      else cout<<"Wrong\n";
    }
    return 0;
}

import java.security.SecureRandom;
import java.util.*;
//import java.util.Random;
public class Carry_Recursive 
{
	static Scanner sc=new Scanner(System.in);
	static int []score= {0, 0, 0};
	static int s=0, i=1;
	static String bin1="", bin2="";
	static void recursivefunc(int n, int carry, int f)
	{
		int ch;
	    if(n!=0)
	    {
	    	ch=n%carry+'0';
	    	if(n%carry>=10)
	    	{
	    		ch='A'+(n%10);
	    	}
	    	bin1=(char)ch+bin1;
	    	if(f==1)
	    	{
	    		System.out.println("第"+i+"步:"+n+"/"+carry+"的餘數="+(char)ch+", 紀錄餘數, 商="+n/carry);
	    		i++;
	    	}
	    	recursivefunc(n/carry, carry, f);
	    }
	}
	
	public static String teachingMode(double inputN, int carry, int f)
	{
		int z, tmp;
		double k=inputN;
		if (k<0) 
			k=k*(-1);
		z=(int) k;
		double fract=k-z;
		bin1="";
		bin2="";
		i=1;
		
		if(f==1)
		{
			System.out.println("第"+i+"步:"+inputN+"取絕對值後經過type casting為"+z);
			i++;
		}
		
		recursivefunc(z, carry, f);//遞迴
	    
	    if(inputN<0) 
	    {
	    	bin1="-"+bin1;
	    	if(f==1) 
	    	{
	    		System.out.println("第"+i+"步:"+inputN+"<0, 加上負號");
	    		i++;
	    	}
	    }
	    
	    while (fract>0 && bin2.length()<=3) 
	    {
	      if(f==1) 
	      {
	    		System.out.println("第"+i+"步:"+((Math.round(fract*carry*10000.0))/10000.0)/carry+"乘以"+carry+
	    				" 經過type casting後為"+(int)((Math.round(fract*carry*10000.0))/10000.0)+", 記錄整數部分");
	    		i++;
	      }
	      fract=fract*carry;
	      tmp=(int)fract;
	      fract-=tmp;
	      bin2=bin2+tmp;
	    }
	    
	    if(f==1 && bin2.length()==0)
	    {
	    	System.out.println(carry+"進位= "+bin1);
	    	System.out.println("-----------------------------------");
	    }
	    else if(f==1 && bin2.length()!=0)
	    {
	    	System.out.println(carry+"進位= "+bin1+"."+bin2);
	    	System.out.println("-----------------------------------");
	    }
	    return (bin1+"."+bin2);
	}
	
	public static void testMode()
	{
		SecureRandom r=new SecureRandom();
		SecureRandom c=new SecureRandom();
		double n=r.nextDouble(201)-100;
		n=(Math.round(n*100.0))/100.0;
		int carryindex=c.nextInt(3);
		int carry=(int)Math.pow(2, carryindex+1);
		if(carryindex==1)
			carry=16;
		System.out.print("將 "+n+" 轉換為 "+carry+" 進位為:");
		
		String ans=new String();
		sc.nextLine();
		ans=Carry_Recursive.sc.nextLine();
		if(ans.equals(teachingMode(n, carry, 0)))
		{
			System.out.println("答對!");
			score[carryindex]++;
			s+=10;
		}
		else
		{
			System.out.println("答錯!\n解題教學: ");
			teachingMode(n, carry, 1);
		}
	}
	
	public static void main(String[] args) 
	{
		double inputN;
		int mode;
		while(true)
		{
			System.out.print("請選擇: 1.教學模式 2.測驗模式: ");
			mode=sc.nextInt();
			if(mode==1)
			{
				System.out.print("輸入十進位: ");
				inputN=sc.nextDouble();
				teachingMode(inputN, 2, 1);
				teachingMode(inputN, 8, 1);
				teachingMode(inputN, 16, 1);
				
			}
			else if(mode==2)
			{
				testMode();
				System.out.println("轉換為2進位答對"+score[0]+"題");
				System.out.println("轉換為8進位答對"+score[2]+"題");
				System.out.println("轉換為16進位答對"+score[1]+"題");
				System.out.println("得"+s+"分");
				System.out.println("-----------------------------------");
			}
			else
				continue;
		}
	}
}

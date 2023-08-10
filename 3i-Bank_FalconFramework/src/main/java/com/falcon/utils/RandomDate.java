package com.falcon.utils;

public class RandomDate  {

	public static int mont, dat, mont1,dat1,mont2,dat2,i1,i2;

	public static String rdDate(String randDT,int m)
	{

		String dob = randDT;
		String[] dob1 = dob.split("/");
		String date = dob1[1];
		String month = dob1[0];
		String Year = dob1[2];
		dat = Integer.parseInt(date);
		mont = Integer.parseInt(month);
		//int runC = i;
		dat1 = dat+m;
		mont1 = mont+m;
		if(dat1>28)
		{
			i1 = (m/28);				
			dat2 = dat1-((28)*i1)-((dat-1));
		}
		else
		{
			dat2=dat1;
		}
		if(mont1>12)
		{
			//System.out.println("*****    "+m);
			i2 = (m/12);			
			mont2 = mont1-((12)*i2)-((mont-1));

		}
		else {
			mont2=mont1;
		}

		String d1=dat2+"";
		String m1=mont2+"";
		String Datee = m1+"/"+d1+"/"+Year;
		System.out.println("Run count: "+m+" Date: "+Datee);

		return Datee;
	}

	public static void main(String[] args) {}

}

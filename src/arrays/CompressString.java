package arrays;

/*
  	Simple version of the problem: Compress a string (only has alphabet characters), with
	basic encoding, where you simply count the number of repeated characters. Then also write a
	routine to de-compress it.
	e.g.
	Input: "AAAAA"
	Output: "5A"
	Input: "BAAAB"
	Output: "B3AB"
	Input: "ABAB"
	Output: "ABAB" [We are not concerned about characters repeating in groups]
	Assume that a given character will not repeat more than 127 times.
	Solution: Compression solution to this is very simple. It pretty much needs one loop.
	Decompression is equally simple. Let us know if that is not clear.
	Important twists to the problem:
	* String can have any character from the basic ASCII set (ASCII values 0 to 127). i.e. it can now
	include numbers.
 	Compressed length must not exceed original length. It can be same or less.

 */
public class CompressString
{
	public void compressStr()
	{
		System.out.println(decompress2("a2b3"));
	}
	private String RLE(String str)
	{
		String result="";
		int[]map = new int[128];
		int last=-1;
		
		for(int i=0;i<str.length();i++)
		{
			int c = str.charAt(i);
			if(last==c)
				map[c]=map[c]+1;
			else
				map[c]=1;
			last=c;
		}
		for(int i=0;i<str.length();i++)
		{
			int c = str.charAt(i);
			int n = map[c];
			
			if(n>1)
			{
				result=result+n+Character.toString(str.charAt(i));
				i=i+n-1;
			}
			else
				result=result+Character.toString(str.charAt(i));
		}
		return result;
    }
	private String decompress(String str)
	{
		String result="";
		for(int i=0;i<str.length();i++)
		{
			int num=0;
			int n = str.charAt(i)-'0';
			while(n>=0 && n<=10)
			{
				num=num*10+n;
				i++;
				n = str.charAt(i)-'0';
			}
			char c = str.charAt(i);
			for(int j=0;j<num;j++)
				result+=c;
			if(num==0)
				result+=c;
		}
		return result;
	}

	/*********************************************REVISION***********************************************/
	//BAAAB
	private String RLE2(String str)
	{
		String result="";
		char[]a=str.toCharArray();
		if(a.length==1)
			return str;
		int count=1;
		int i=0;
		
		while(i<a.length)
		{
			while((i+1)<a.length && a[i]==a[i+1]){
				count++;
				i++;
			}
			if(count>1)
				result+=count;
			result+=a[i];
			i++;
			count=1;
		}
		return result;
	}
	private String decompress2(String str)
	{
		char last=' ';
		String result="";
        if(str==null || str.length()==0)
            return result;
        char[]a = str.toCharArray();
        int i =0;
        for(i =0 ; i < a.length;i++)
        {
            int num = 0;
            int c = a[i]-'0';
            while(c>=0 && c<=9 && i<a.length)
            {
                num = num * 10 + c;
                i++;
                if(i<a.length)
                  c = a[i]-'0';
            }
            
            if(num == 0 ){
                result +=a[i];
                last = a[i];
            }
            if(num ==1)
            {
            	i--;
            	continue;
            }
          while(num !=0)
          {
              result +=Character.toString(last);
              num--;
          }
    }
        return result;
	}
}

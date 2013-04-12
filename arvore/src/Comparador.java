/*
 * Created on 09/03/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author robinson
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Comparador {	
	public int comparar(Object a, Object b){
	    Integer a1=null,b1=null;
	    if(a!=null)
	      a1=new Integer(a.toString());	    
	    if(b!=null)
	      b1=new Integer(b.toString());
	    
	    if((a==null)&&(b!=null))
	        return (new Integer(0)).compareTo(b1);
	    else if((b==null)&&(a!=null))
	        return a1.compareTo((new Integer(0)));
	    else
	        return a1.compareTo(b1);	}
}

package com.esic.code;

import com.esic.domain.ESICRecord;
import com.esic.domain.annotations.ESICExcelColumns;
import com.esic.domain.annotations.ESICExcelColumns;

/**
 * run this class to generate methods based on each key in man for {@link ESICRecord}
 * @author meet
 *
 */
public class ESICREcordMapMethodGenerator {
	
	
	
	public static void main(String[] args) {
		
		
		System.out.println(getMethodStrings());
		
	}
	
	public static String getMethodStrings()
	{
		
		
		String ret ="";
		
		for (ESICExcelColumns op : ESICExcelColumns.values()) {
		   //
			ret = ret+   "/** \n  *    see  ESICREcordMapMethodGenerator \n */ \n";
			ret = ret +  "\n public String get"+capitalizeFirstLetter(op.name())+ "(){\n";
			ret = ret+   "return this.get(\""+op.name()+"\"); }\n\n";
				
			
		}
		
		
		
		return ret ;
	}
	
	
	
	

	public static String capitalizeFirstLetter(String original){
        if(original.length() == 0)
            return original;
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }
	

}

package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		if(text.isEmpty()){return 0;}
		else if(text.contains(",") || text.contains("\n")){
			return sum(splitNumbers(text));
		}
		else if(text.startsWith("//")){
			String delimiter = text.substring(2,3);
			return sum(splitNumbers(text));
		}
		else{return 1;} 
			
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    if(numbers.startsWith("//")){
	    	String delimiter = "";
	    	int indexOfNewline = numbers.indexOf("\n");

	    	// if only one delimiter, we know newline comes as the third character.
	    	if(numbers.indexOf("\n") == 3){
		    	// we find the delimiter, and put it in a string.
				delimiter = numbers.substring(2,3);
				// we make a new regex delimiter like [,\n;] if the delimiter is ;
				delimiter = "[,\n" + delimiter + "]";
				// find the index of the first newline
				// trim the first newline and everything in front of it
				numbers = numbers.substring(indexOfNewline + 1);
				// split the string with our delimiter
				return numbers.split(delimiter);
			}	
    	}			
    	return numbers.split(",|\n");
	}
      
    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
        	// check if the number is < 0, then construct the illegalMessage
        	// and throw the illegalArgumentException
        	if(toInt(number) < 0){
        		String illegalMessage = IllegalArgumentMessage(numbers);
        		throw new IllegalArgumentException(illegalMessage);
        	}
        	else
        		if(toInt(number) > 1000){
        			total = total;
        		}
        		else		    
        			total += toInt(number);
		}
		return total;
    }

    private static String IllegalArgumentMessage(String[] numbers){
    	// construct a new illegalMessage with all the illegal numbers
    	String illegalMessage = "Negatives not allowed: ";
    	for(int i = 0; i < numbers.length; i++){
    		if(numbers[i].contains("-")){
    			illegalMessage += numbers[i] + ",";
    		}
    	}
    	// we have to trim the message so that is doesnt include the last comma
    	return illegalMessage = illegalMessage.substring(0, illegalMessage.length() - 1);
    }
}
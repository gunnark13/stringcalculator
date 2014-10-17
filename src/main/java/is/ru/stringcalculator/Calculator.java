package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0; // empty comment for first commit
		}
		else if(text.contains(",") || text.contains("\n")){
			return sum(splitNumbers(text));
		}
		else if(text.startsWith("//")){
			String delimiter = text.substring(2,3);
			return sum(splitNumbers(text));
		}
		else{
			return 1;
		}
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    if(numbers.startsWith("//")){
	    	// we find the delimiter, and put it in a string.
			String delimiter = numbers.substring(2,3);
			// we make a new regex delimiter like [,\n;] if the delimiter is ;
				delimiter = "[,\n" + delimiter + "]";
			// find the index of the first newline
				int index = numbers.indexOf("\n");
			// trim the first newline and everything in front of it
				numbers = numbers.substring(index + 1);
			// split the string with our delimiter
			return numbers.split(delimiter);
    	}
    	else
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
        	else{		    
        		total += toInt(number);
			}
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
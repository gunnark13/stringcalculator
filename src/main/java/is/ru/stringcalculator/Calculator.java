package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		if(text.isEmpty()){return 0;}
		else if(text.contains(",") || text.contains("\n")){
			return sum(splitNumbers(text));
		}
		else if(CheckForDelimiterSlash(text)){
			String delimiter = text.substring(2,3);
			return sum(splitNumbers(text));
		}
		else{return 1;} 
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
		String delimiter = "[" + ",\n" + "]";
		// if the string starts with //
	    if(CheckForDelimiterSlash(numbers)){
	    	// locate the first newline
	    	int indexOfNewline = numbers.indexOf("\n");
	    	// if only 1 delimiter of length 1, we know newline comes as the third character.
	    	if(numbers.indexOf("\n") == 3){
		    	// we find the delimiter, and put it in a string.
				delimiter = numbers.substring(2,3);
				// we change the regex delimiter to [,\n;] if the delimiter is ";"
				delimiter = "[,\n" + delimiter + "]";
				// trim the first newline and everything tha comes before it
				numbers = numbers.substring(indexOfNewline + 1);
				// split the string with our new delimiters
				return numbers.split(delimiter);
			}	
	   		// if the third character is an opening bracket, we will recieve an delimiter
			// of length > 1 or multiple delimiters
			else if(numbers.contains("[")){
				// count the number of delimiters
				int countDelimiters = 0;
				String bracket = "[";
				for(int i = 0; i < numbers.length(); i++){
					if(numbers.substring(i, i + 1).equals("[")){
						countDelimiters++;
					}
				}
				// if we have one delimiter only
				if(countDelimiters < 2){				
					// change the delimiter to whats inside the brackets, and add the
					// Match-One-Or-More operator to the regular expression.
					delimiter = "[" + numbers.substring(3, numbers.indexOf("]")) + "]+";
					// get rid of everything but the numbers and their delimiters.
					numbers = numbers.substring((indexOfNewline + 1));
					return numbers.split(delimiter);
				}
				else{					
					delimiter = "";
					// temp string so that we don't ruin the numbers string
					// temp only contains the delimiters themselves, no numbers.
					String temp = numbers.substring(2, indexOfNewline);
					// we try and loop through the string until we go out of bounds,
					// when we do we know there are no more delimiters, and return them
					// when we catsh the StringIndexOutOfBoundsException.
					try {
						while(temp.charAt(0) == '['){
							delimiter +=	 temp.substring(1, temp.indexOf("]"));
							temp = temp.substring(temp.indexOf("]") + 1);
						}
					} catch(StringIndexOutOfBoundsException a) {
						delimiter = "[" + delimiter + "]+";
						numbers = numbers.substring(indexOfNewline + 1);
						return numbers.split(delimiter);
					}
				}
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
        		if(toInt(number) > 1000){total = total;}
        		else{total += toInt(number);}
		}
		return total;
    }

    private static boolean CheckForDelimiterSlash(String text){
    	return text.startsWith("//");
    }	
    private static String IllegalArgumentMessage(String[] numbers){
    	// construct a new illegalMessage with all the illegal numbers
    	String illegalMessage = "Negatives not allowed: ";
    	for(int i = 0; i < numbers.length; i++){
    		if(numbers[i].contains("-")){illegalMessage += numbers[i] + ",";}
    	}
    	// we have to trim the message so that is doesnt include the last comma
    	return illegalMessage = illegalMessage.substring(0, illegalMessage.length() - 1);
    }
}
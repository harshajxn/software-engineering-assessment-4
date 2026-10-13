package course;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

class Assessment {
   private String name;
   private String description;
   private int points;
   private String submissionType;
   private boolean group;
   private String groupingName;
   private String publishDateTime;
   private String dueDateTime; 
   private String hideDateTime;
   public Assessment(String name, String description, int points, 
                     String submissionType, boolean group, String groupingName, 
                     String publishDateTime, String dueDateTime, String hideDateTime) {
      //TODO: initilise all the attributes
   }
}

public class Course {
   private List<Assessment> assessments;
   
   public boolean createNewAssessment(String name, String description, int points, 
                                      String submissionType, boolean group, String groupingName, 
                                      String publishDateTime, String dueDateTime, String hideDateTime) {
      boolean valid = true;
      
      //validate the dates. JFB
      if (!dateOrderValidator(hideDateTime, dueDateTime, publishDateTime)) {
    	    return false;
    	}
      
      //TODO: Validate all the provided parameters.
      //if the assessment parameters meet the given conditions,
      //   the function should create a new Assessment object, 
      //   add it in the assessments list and then return true.
      //else
      //   the function should only return false.
      
      return valid;
   }
   
   public boolean dateOrderValidator(String hideDate, 
		   String dueDate, 
		   String publishDateTime) {
	   
	   DateTimeFormatter formatter = 
			   DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
			   .withResolverStyle(ResolverStyle.STRICT);
	   try {
		   LocalDateTime hide =
				   LocalDateTime.parse(hideDate, formatter);
		   LocalDateTime due =
				   LocalDateTime.parse(dueDate, formatter);
		   LocalDateTime publish =
				   LocalDateTime.parse(publishDateTime, formatter);
		   
		   return due.isAfter(publish) && hide.isAfter(due);
	   } catch (DateTimeParseException e) {
		   return false;
	   }
	   
   }
}
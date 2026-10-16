package course;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
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
      
      this.name = name;
      this.description = description;
      this.points = points;
      this.submissionType = submissionType;
      this.group = group;
      this.groupingName = groupingName;
      this.publishDateTime = publishDateTime;
      this.dueDateTime = dueDateTime;
      this.hideDateTime = hideDateTime;
   }

   
   public String getName() { return name; }
   public String getDescription() { return description; }
   public int getPoints() { return points; }
   public String getSubmissionType() { return submissionType; }
   public boolean isGroup() { return group; }
   public String getGroupingName() { return groupingName; }
   public String getPublishDateTime() { return publishDateTime; }
   public String getDueDateTime() { return dueDateTime; }
   public String getHideDateTime() { return hideDateTime; }
}

public class Course {
  
   private List<Assessment> assessments = new ArrayList<>();

  
   List<Assessment> getAssessments() {
      return assessments;
   }

   public boolean createNewAssessment(String name, String description, int points, 
                                      String submissionType, boolean group, String groupingName, 
                                      String publishDateTime, String dueDateTime, String hideDateTime) {

      // Validate first. Any failure returns false before anything is stored.

      // Author: Christina - Conditions 2 and 1: name not blank, then unique
      if (!isNameNotBlank(name) || !isNameUnique(name)) return false;
      // Author: Christina - Condition 3: description has at least 3 words
      if (!hasAtLeastThreeWords(description)) return false;
      // Author: Christina - Condition 4: points greater than 0
      if (!arePointsPositive(points)) return false;

      // Validate the inputed dates. JFB
      if (!dateOrderValidator(hideDateTime, dueDateTime, publishDateTime)) return false;

      // TODO: other teammates add their guard clauses here, BEFORE the add() below

      // Only reached when every check passed: create the object and store it
      assessments.add(new Assessment(name, description, points, submissionType, group,
            groupingName, publishDateTime, dueDateTime, hideDateTime));
      return true;
   }

   // Author: Christina - Condition 1: name must be unique in the course
   boolean isNameUnique(String name) {
      for (Assessment a : assessments) {
         if (a.getName().equals(name)) return false;
      }
      return true;
   }

   // Author: Christina - Condition 2: name cannot be null, empty or only spaces
   boolean isNameNotBlank(String name) {
      return name != null && !name.trim().isEmpty();
   }

   // Author: Christina - Condition 3: description must have at least 3 words
   boolean hasAtLeastThreeWords(String description) {
      if (description == null || description.trim().isEmpty()) return false;
      return description.trim().split("\\s+").length >= 3;
   }

   // Author: Christina - Condition 4: points must be greater than 0
   boolean arePointsPositive(int points) {
      return points > 0;
   }

   // Author Jason (JFB): strict date parsing plus due > publish and hide > due
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

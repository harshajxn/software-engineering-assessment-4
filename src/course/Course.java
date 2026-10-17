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

      // Author: Jason Condition 10 and 11. Validate the inputed dates.
      if (!dateOrderValidator(hideDateTime, dueDateTime, publishDateTime)) return false;

      // Author: Harsha - Condition 5: a single assessment cannot exceed 50 points
      if (!isWithinSingleAssessmentLimit(points)) return false;
      // Author: Harsha - Condition 6: total points across all assessments cannot exceed 100
      if (!isWithinTotalPointsLimit(points)) return false;
      // Author: Harsha - Condition 7: submission type must be pdf, zip or doc
      if (!isValidSubmissionType(submissionType)) return false;
      // Author: Harsha - Condition 8: a group assessment must provide a grouping name
      if (!isGroupingValid(group, groupingName)) return false;
      // Author Jason (JFB): Condition 9: The grouping name must be unique for each assessment.
      if (!isGroupingNameUnique(group, groupingName)) return false;

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
   
// Author: Harsha - Condition 5: a single assessment's points must not exceed 50
   boolean isWithinSingleAssessmentLimit(int points) {
      return points <= 50;
   }

   // Author: Harsha - Condition 6: the running total of all assessments' points
   // (including the one being created) must not exceed 100
   boolean isWithinTotalPointsLimit(int newPoints) {
      int total = newPoints;
      for (Assessment a : assessments) {
         total += a.getPoints();
      }
      return total <= 100;
   }

   // Author: Harsha - Condition 7: submission type must be exactly one of
   // "pdf", "zip" or "doc" (inputs are guaranteed lowercase)
   boolean isValidSubmissionType(String submissionType) {
      if (submissionType == null) return false;
      return submissionType.equals("pdf") || submissionType.equals("zip") || submissionType.equals("doc");
   }

   // Author: Harsha - Condition 8: a group assessment must be given a non-blank
   // grouping name; an individual assessment does not need one
   boolean isGroupingValid(boolean group, String groupingName) {
      if (!group) return true;
      return groupingName != null && !groupingName.trim().isEmpty();
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
   // Author Jason (JFB): Condition 9: The grouping name must be unique for each assessment.
   boolean isGroupingNameUnique(boolean group, String groupingName) {
	    if (!group) return true;

	    for (Assessment a : assessments) {
	        if (a.isGroup() && a.getGroupingName().equalsIgnoreCase(groupingName)) {
	            return false;
	        }
	    }
	    return true;
	}
}

Assessment Task
Follow the steps below to get started on this assessment task:

General Description

Based on the requirements collected for the StudentScholar website in Assessment 1, you need to do the following activities for the StudentScholar platform.

IMPORTANT: 

Each student must take responsibility for testing + implementing one segment of the system and developing user stories and acceptance criteria. Each members contribution must be clearly identified within the section that it is completed.
In a group of 3, each member must write up test cases for 4 conditions and implement the code related to those tests.
In a group of 4, each member must write up test cases for 3 conditions and implement the code related to those tests.
Each member must write one user story (each with three acceptance criteria).
Activities in Assessment 4

Activity 1: Git and Unit Test (16 points) + a Video of your program:  In the StudentScholar website, there are classes called Course and Assessment. The Assessment class has a constructor, while the Course class has a function called "createNewAssessment". You are asked to test and implement this function. The Course (and Assessment) class has the following methods and attributes. A copy can be downloaded hereDownload here. 

package course;
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
      
      //TODO: Validate all the provided parameters.
      //if the assessment parameters meet the given conditions,
      //   the function should create a new Assessment object, 
      //   add it in the assessments list and then return true.
      //else
      //   the function should only return false.
      
      return valid;
   }
}
1. createNewAssessment function. This function will validate the new assessment criteria to determine if the assessment details are valid. The following conditions should be considered when validating the assessment parameters. If the assessment details meets the following conditions, the createNewAssessment function should create a new Assessment object (by calling the constructor which must initialise the attributes in the Assessment class), then store the new Assessment object in the assessments List and then return true. If the assessment details do not meet the following conditions, the function should not initialise the values and should instead return false. Note that all string values are lowercase - when executing test cases all string input parameters should be lowercase (hence variations in case are not to be considered as part of your test cases).

Condition 1. The assessment name must be unique to the course - the assessment list cannot have assessments with the same name.

Condition 2. The assessment name cannot be blank.

Condition 3. The assessment description must have at least 3 words.

Condition 4. The assessment points must be greater than 0.

Condition 5. No single assessment can exceed 50 points.

Condition 6. The total sum of points across all assessments cannot exceed 100 points.

Condition 7. Submission type must be one of ("pdf", "zip", "doc").

Condition 8. Assessment are either individual or group. If it is a group assessment, the grouping name must be provided.

Condition 9. The grouping name must be unique for each assessment.

Condition 10. Date times must be formatted in the format DD/MM/YYYY hh:mm eg "23/11/2026 23:59" (24 hour clock) and must be validated to ensure that the combination is correct (eg "29/02/2026 20:00" would be invalid as 2026 is not a leap year). Ensure that STRICT date validation is applied.

Condition 11. The hide date time must be after the due date time, which must be after the publish date time.

In this activity, each group is requested to do the following activities :

Activity 1.1 Develop and write down a reasonable number of test cases. The function should have at least 12 test cases - one for each condition, and one case where all input is valid (use this template Download). Each test case should have one test data (except for condition 10 & 11 which have two test data, and the case where all input is valid which will need at least 3 tests data to properly test different combinations). Your test cases should be testing for boundary values. In addition, your test code should include precondition tests to verify the conditions before the test is run. You need to implement the unit tests for this function using JUnit (7 points). Each member should complete an equal number of test cases and implement the functionality and junit test code related to their own test cases.

Activity 1.2 Implement this function in Java using any Java IDEs (7 points).

Activity 1.3 Move the implemented function and the unit tests to a private GitHub repository (2 point). In the video, you need to show that you have submitted the code to GitHub. There is no need to show the process. 

Activity 1.4 Record your screen while running and testing your program. In the recorded video, you need to explain the implementation of the function (30 seconds), the implementation of unit tests for the function (30 seconds, and run the unit tests (1 minute). The recorded video should also show that your program is in GitHub (30 seconds). The total recording should not be more than 4 minutes and 100 MB. The recorded video should have a 720p resolution. You need to upload the recorded video to Canvas as part of your submission. Alternatively, once you created the video with SharePoint or MS Teams, you can insert the link to the recorded video in a WORD/PDF file and give access to your tutor to watch the recorded video. The WORD/PDF file should be uploaded as part of your submission to Canvas. The recorded video will be used by markers to mark the student. Recorded videos have no mark, but you will lose significant marks if you do not upload a recorded video.

Note 1. Your program should initialise the values in the class (if the assessment details are valid) and then store them in the assessment list.

Note 2. You MUST write enough meaningful comments to explain your code in JUnit 5 and Java.

Note 3. You MUST develop your code (the implementation of functions and JUnit test code) in any Java IDEs and use JUnit 5 for tests.

Note 4. You just need to develop the classes for Assessment, Course and the test class. There is no need for a main method or driver class.

Note 5. The code written above is just a placeholder. You are allowed to edit it and add new functions (e.g., validator methods for various fields etc.) and new attributes. You are completely FREE on how you want to implement and test these functions. The key point is that you need to write enough test cases to test the conditions. 

Note 6. You only need to write test cases to test the conditions that are explicitly mentioned above. There is no need to test other conditions.

Note 7. Your test cases should check the return value matches the expected result (true or false).

In addition, your test case should check that the attributes in the Assessment class have been initialised appropriately and that the assessment object is stored successfully in the list. (The attributes should be modified to match the parameters, and then the object added to the list if they are all validated successfully. The attributes should not be modified and no object added if any of the validation fails).

Activity 2: User Story and Acceptance Criteria (9 points): Based on the requirements collected for the StudentScholar website, each student is asked to write one user story and three acceptance criteria for each user story. Each user story and its acceptance criteria have 3 points. The user stories and acceptance criteria should be written based on the template and principles taught in class.

Software Tools

For Activities 1, you can develop your code (the implementation of functions and JUnit test code) in any Java IDEs such as Eclipse and IntelliJ and use JUnit for tests. However, projects must be able to be opened in Eclipse for testing/review by markers.
For Activity 2, you can use inks to an external site.MS Word.
Notes

Please note that you can and should get ideas from the examples in lectures/tutorials/lectorials/assignment descriptions while you are working on your assignments. However, please note that you are not allowed to use (copy and paste) the same examples provided in lectures/tutorials/tutorials/assignment descriptions as answers to your assignment, even if they can be applied to the StudentScholar website.  For example, although some architectural decisions (e.g., selecting SQL Server or MySQL to store data) discussed in lectures/tutorials/lectorials can be made for and applied to many systems, I strongly suggest you develop your own solutions for the assignments. Otherwise, you will lose some marks.
You are NOT allowed to simply use (copy and paste) answers generated by AI-based tools such as ChatGPT for your assignments.
You need to work on Assignment 4 as part of a group. You are not allowed to submit Assignment 4 individually. The individual submission in Assignment 4 will NOT be marked (Zero Mark).

Extension requests should be made at least one working day before the deadline. 
Extensions are for individuals, not per group. In other words, if a member of a group gets an extension, the group should submit the assignment by the deadline and that member should submit the updated version of the assignment to me via email. I will check the differences between the original version and the updated version. 

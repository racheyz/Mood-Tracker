# "Year In Pixels"

A mood tracking app that lets the user log their mood for every day of the year. The format will follow the "year in 
pixels" template, which assigns a square/pixel to each day of the year. Different colours will be used to represent 
different moods. Additional features could include: 
- A summary of the user's mood inputs. For example, the number of days in total
they felt "sad" or "happy" or "average"
- Having the option to add a small comment for each day

## Who will use it?
Anyone who have the habit of using tracking/logging tools for mental health or organization purposes.
This program provides a minimalistic and concise way to record of one's mood pattern throughout the year.

## Why I Chose This Project?
An online mood tracker is something I personally would use regularly. I have tried keeping a physical mood logging 
sheet, but it became very inconvenient to have the paper and different coloured pens with me at all times. Having an 
online version of this would be much simpler and more motivating to keep up with because the logging process will be 
less time-consuming. On top of that, it would be fun and useful to implement additional functionality such as a mood 
summary or comments.

## User story:
Phase 1:
- As a user, I would want to be able to create a mood board of any size I choose
- As a user, I would want to be able to choose a starting date
- As a user, I would want to see the date and month associated with each day
- As a user, I would want to have a selection of moods to choose from
- As a user, I would want to see a summary of the number of days I've felt each mood
- As a user, I would want to assign a mood to a specific day
- As a user, I would want to be able to change the mood of a specific day

Phase 2:
- As a user, I want to be able to save my mood tracker at any point (if I so choose)
- As a user, at the start of the application, I want to be able to load my mood tracker from file (if I so choose)


##Citations:
Code in persistence class package and persistence test package is modified from
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git 

Phase 3:
Instructions for Grader:

- You can generate the first required action of adding days to the mood board by clicking the button 
 labeled "Add Day"
- You can generate the second required action of changing the mood of a particular day by clicking on a
  pixel, and selecting the mood of your choice
- You can locate my visual component by clicking on the button labeled "Generate Pie Chart" which will display 
  a pie chart representation of the current mood summary
- You can save the state of my application by clicking the button labeled "Save Tracker"
- You can reload the state of my application by clicking the button labeled "Load Mood Tracker From File" on the
  start page

##Citations:
Code for creating a pie chart is inspired and modified from 
http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/DrawingaPieChart.htm

Phase 4: Task 2:

Wed Apr 12 16:01:28 PDT 2023
Recorded a mood
Wed Apr 12 16:01:29 PDT 2023
Recorded a mood
Wed Apr 12 16:01:30 PDT 2023
Added a day to board
Wed Apr 12 16:01:32 PDT 2023
Recorded a mood
Wed Apr 12 16:01:34 PDT 2023
Recorded a mood
Wed Apr 12 16:01:35 PDT 2023
Added a day to board

"Phase 4: Task 3:
An area that could use some refactoring is my gui package, because the three "Page" classes I have 
share a lot of similar code. For example, they all have methods that set up the frame, clears the frame, initializing 
components, and the run method which contains action listeners. They also all have associations with the Board class.
If time permitted, I would have considered creating a new abstract class that the other three "Page" classes could 
extend. I would have put concrete methods in the abstract class for initializing and resetting the frame, so the other 
classes do not need its own redundant implementation. A Board field could be defined in the abstract class as well. 
I could also make an abstract class for initializing components, and each class could have its own implementation of it 
according to what components it needs for that specific page.

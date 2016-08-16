================================================================================================
Requirements:
================================================================================================
- JDK8
- JUnit 4.9
- Maven
- Eclipse

================================================================================================
Assumption:
================================================================================================
It is assumed that,
- user has knowledge on Maven, Eclipse etc
- user will supply data within the /MarsRoverProj/resources/input.txt file
- user will provide data as mentioned in the original problem description


================================================================================================
Project Structure:
================================================================================================
MarsRoverProj
|-- src: contains the source for implementation
|
|-- test: contains the JUnit test file
|
|-- resources: contains the input file 


================================================================================================
How to run the program?
================================================================================================

1. Clone the Truelocal's Git project into an empty directory using the following command,
 git clone http://github.com/mchisty/TrueLocalRepo.git

2. Open Eclipse, then
- select File > Import > Existing Maven Projects  
- select YOUR_NEW_DIRECTORY\TrueLocalRepo\MarsRoverProj (e.g. in my case, it was C:\MY_WORKSPACE\TrueLocalRepo\MarsRoverProj)
- click Finish

3. Build the project from Eclipse (if 'Project > Build Automatically' is already selected, then project will be built automatically)

4. Right click MarsRoverClientSolution, select Run As > Java Application


******************** Please Note ******************
The input file is hard-coded as "input.txt" and should be under the directory "/MarsRoverProj/resources/". 


Issues that I considered in the current solution:
-----------------------------------------------------
1. NumberFormatException will be thrown if invalid (non-numeric) character was supplied instead of a valid numeric co-ordination
2. File handing exception and I/O exception will be thrown if file path/location is not found in the pre-defined directory
3. "Invalid direction or co-ordination" message will be displayed if any direction other than N, S, E, W is supplied
4. "Invalid direction or co-ordination" message will be displayed if any coordination point exceeds Plateau boundary limit
5. "Invalid signal received" message will be displayed if anything other than L,R,M is supplied as input signal
6. "Out of boundary" message will be displayed if rover movement reaches outside the min and max XY co-ordination of the Plateau



================================================================================================
Scope of improvement:
================================================================================================
The solution could further be improved by the following way:
1. Defining more objects e.g. Plateau, Position/XYCoOrdinate etc; hence making the solution fully more object oriented. 
As an addition, some interface could also be defined. However since this is simple application, only the core business requirements are solved.

2. Handling more exceptions. Several possibilities of deviations are not handled into the current solution e.g. 
	a) what if the input file name/location is different?
	b) what if input is supplied from console instead of being read from a file?
	c) since every rover has two lines of commands, what if one line e.g. the co-ordination data is missing and only signal data is provided? 
	c) what if the plateau if too big, the input signal is huge and there are huge number of repetitions (i.e. the idea of repeated matrix rotation)?   

The above scenarios are not considered for the sake of simplicity (as part of this simple task). 
However these issues can be handled as per further business requirements.
 
  
 
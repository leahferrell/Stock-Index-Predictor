# Stock-Index-Predictor

A simple web service that predicts stock indexes one day ahead of time.

Project Owner: Leah Ferrell

## I. Intro:

Over the past few months I have become increasingly interested in the worlds of investing through stock trading, machine learning, and software engineering. I decided it would be best to merge all of the interests in one and begin working on a project to help me learn all of these interests, and at the end have a cool/useful project to show off.


## II. Influence:

A research paper titled "A Genetic Algorithm Optimized Decision Tree SVM based Stock Market Trend Prediction System" is the basis for this project. It can be found here: http://stocktrendresearch.googlecode.com/svn-history/r7/trunk/Paper/SVM_ANN_UpDownNoTrend.pdf


## III. Overall Design:

A. The core project consists of 4 parts: 
	1. Daily inputs: Daily Open, Daily High, Daily Low, Daily Closing, Trading Volumes
	2. Technical Indicators (features): there are 28 of these (and discussed in the paper) and are obtained through analysis on the inputs.
	3. Prediction System: The prediction system is made up of a Decision Tree, SVM, and Genetic Algorithm and is fed the technical indicators as inputs.
	4. Trading Rule: Based on the predicted trend from the prediction system.

B. There will be a database to store the data obtained from both the daily inputs, the technical indicators, and the prediction system.
C. This will be hosted as a web service and will provide a web API, so other applications can interface with it.
D. Web and phone apps that get data from the web API will be created as examples.


## IV. Technical Details:

IDE: Eclipse
Testing: TBD
Language: 
	Backend: Java (most likely), potentially: Python or Scala
	Frontend: JavaScript, HTML, CSS
Framework: Spring, Jersey (will change if a different language is chosen)
Database: MySQL
Dependency Management: Maven
Hosting: DigitalOcean
Website: leahferrell.info/services/ or services.leahferrell.info
VCS: git -- GitHub
Repository: https://github.com/leah3393/Stock-Index-Predictor


## V. Timeline of Tasks:

1. Design and write the code to compute techinical indicators (features) used in the analysis.
2. Design and implement the machine learning data analysis tool used in the program.
3. Create an easily expandable database (probably MySQL) that will receive a daily feed of 5 inputs from a finance API (probably Bloomberg or Yahoo Finance)
4. Build a REST web service with an API to host the program.
5. Make the system extendable so that it includes many indices.
6. Create a web display for the information and an interface for results.
7. Create phone applications (iOS, Android) to display information.


## VI. Detailed Design - Core:

### A. Daily Inputs
	i. coming soon

### B. Technical Indicators
	i. coming soon

### C. Prediction System
	i. coming soon

### D. Trading Rule
	i. coming soon


## VII. Detailed Design - Database:

Coming soon.


## VIII. Detailed Design - RESTful Web Service:

Coming soon.


## IX. Detailed Design - Web and Phone Apps:

Coming soon.


## X. Future Plans:

Coming soon.


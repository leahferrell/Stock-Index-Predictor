# Stock-Index-Predictor

##### A simple web service that predicts stock indexes one day ahead of time.

##### Project Owner: Leah Ferrell

## Table of Contents
[I. Intro](https://github.com/leah3393/Stock-Index-Predictor#i-intro)

[II. Influence](https://github.com/leah3393/Stock-Index-Predictor#ii-influence)

[III. Overall Design](https://github.com/leah3393/Stock-Index-Predictor#iii-overall-design)

[IV. Technical Details](https://github.com/leah3393/Stock-Index-Predictor#iv-technical-details)

[V. Timeline of Tasks](https://github.com/leah3393/Stock-Index-Predictor#v-timeline-of-tasks)

[VI. Detailed Design - Core](https://github.com/leah3393/Stock-Index-Predictor#vi-detailed-design---core)

[VII. Detailed Design - Database](https://github.com/leah3393/Stock-Index-Predictor#vii-detailed-design---database)

[VIII. Detailed Design - RESTful Web Service](https://github.com/leah3393/Stock-Index-Predictor#viii-detailed-design---restful-web-service)

[IX. Detailed Design - Web and Phone Apps](https://github.com/leah3393/Stock-Index-Predictor#ix-detailed-design---web-and-phone-apps)

[X. Future Plans](https://github.com/leah3393/Stock-Index-Predictor#x-future-plans)

[XI. Installing and Running the Tool Yourself](https://github.com/leah3393/Stock-Index-Predictor#xi-installing-and-running-the-tool-yourself)

[XII. Contributing](https://github.com/leah3393/Stock-Index-Predictor#xii-contributing)

[XIII. License](https://github.com/leah3393/Stock-Index-Predictor#xiii-license)

## I. Intro:

Over the past few months I have become increasingly interested in the worlds of investing, machine learning, and software engineering. I decided it would be best to merge all of these interests through a project to enhance my knowledge, and at the end have a cool/useful project to show off.


## II. Influence:

A research paper titled "A Genetic Algorithm Optimized Decision Tree SVM based Stock Market Trend Prediction System" is the basis for this project. It can be found here: http://stocktrendresearch.googlecode.com/svn-history/r7/trunk/Paper/SVM_ANN_UpDownNoTrend.pdf


## III. Overall Design:

#### A. The [core](https://github.com/leah3393/Stock-Index-Predictor#vi-detailed-design---core) project consists of 4 parts: 
1. [Daily inputs](https://github.com/leah3393/Stock-Index-Predictor#a-daily-inputs): Daily Open, Daily High, Daily Low, Daily Closing, Trading Volumes
2. [Technical Indicators (features)](https://github.com/leah3393/Stock-Index-Predictor#b-technical-indicators): there are 28 of these (and discussed in the paper) and are obtained through analysis on the inputs.
3. [Prediction System](https://github.com/leah3393/Stock-Index-Predictor#c-prediction-system): The prediction system is made up of a Decision Tree, SVM, and Genetic Algorithm and is fed the technical indicators as inputs.
4. [Trading Rule](https://github.com/leah3393/Stock-Index-Predictor#d-trading-rule): Based on the predicted trend from the prediction system.

#### B. There will be a [database](https://github.com/leah3393/Stock-Index-Predictor#vii-detailed-design---database) to store the data obtained from both the daily inputs, the technical indicators, and the prediction system.
#### C. This will be [hosted as a web service](https://github.com/leah3393/Stock-Index-Predictor#viii-detailed-design---restful-web-service) and will provide a web API, so other applications can interface with it.
#### D. [Web and phone apps](https://github.com/leah3393/Stock-Index-Predictor#ix-detailed-design---web-and-phone-apps) that get data from the web API will be created as examples.


## IV. Technical Details:

##### IDE: 
	Eclipse
##### Testing: 
	TBD
##### Language: 
	Backend: Java (most likely), potentially: Python or Scala
	Frontend: JavaScript, HTML, CSS
##### Framework: 
	Spring, Jersey (will change if a different language is chosen)
##### Database: 
	MySQL
##### Dependency Management: 
	Maven
##### Hosting: 
	DigitalOcean
##### Website: 
	leahferrell.info/services/ or services.leahferrell.info
##### VCS: 
	git -- GitHub
##### Repository: 
	https://github.com/leah3393/Stock-Index-Predictor


## V. Timeline of Tasks:

| #	| Task 	| Expected Completion Date | Completed (Y/N) |
|---|-------|--------------------------|-----------------|
|1. | Design and write the code to compute techinical indicators (features) used in the analysis. | 1/12/15 | N |
|2. | Design and implement the machine learning data analysis tool used in the program. | 1/19/15 | N |
|3. | Create an easily expandable database that will receive a daily feed of 5 inputs from a finance API. | 1/26/15 | N |
|4. | Build a REST web service with an API to host the program. | 1/26/15 | N |
|5. | Make the system extendable so that it includes many indices. | 1/26/15 | N |
|6. | Create a web display for the information and an interface for results. | TBD | N |
|7. | Create phone applications (iOS, Android) to display information. | TBD | N |


## VI. Detailed Design - Core:

#### A. Daily Inputs
1. These will be updated daily through a finance API (most likely Bloomberg or Yahoo Finance)
2. The inputs are: Daily Open, Daily High, Daily Low, Daily Closing, Trading Volumes
3. The inputs are queried through their date.
4. Each stock index has its own table of daily input values.

#### B. Technical Indicators
1. Positive Volume Index (PVI)
2. Negative Volume Index (NVI)
3. On-Balance Volume (OBV)
4. Typical Volumes
5. Price-Volume trend
6. Accumulation/Distribution Oscillator
7. Chaikin's Oscillator
8. Chaikin's Volitility
9. Acceleration
10. Highest High
11. Lowest Low
12. Relative Strength Index
13. MACD Nine Period Moving Average
14. MACD Line
15. Momentum
16. Stochastic Oscillator %k
17. Stochastic Oscillator %d
18. Typical Price
19. Median Price
20. Weighted Close
21. William's %R
22. Price Rate of Change
23. William's Accumulation/Distribution
24. Bollinger Upper
25. Bollinger Lower
26. Bollinger Middle
27. 25 Day Moving Average
28. 65 Day Moving Average

###### Helper Indicators: (used to find the technical indicators)
1. Exponential Moving Average
2. Simple Moving Average
3. Accumulation Distribution Line
4. True Range High
5. True Range Low
6. Today's Accumulation Distribution
7. Relative Strength
8. Unnamed Oscillator


#### C. Prediction System
##### Decision Tree

##### Support Vector Machine

##### Genetic Algorithm

#### D. Trading Rule
The trading rule will be one of three rules:

1. Up Trend: Buy

2. Down Trend: Sell

3. No Trend: Stay


## VII. Detailed Design - Database:

### Schema

### Visual Diagram


## VIII. Detailed Design - RESTful Web Service:

### Architecture

#### Tools

#### Diagram

### API Services

##### Create
Coming soon

##### Read
Coming soon

##### Update
Coming soon

##### Delete
Coming soon


## IX. Detailed Design - Web and Phone Apps:

##### General
Coming soon

##### Web
Coming soon

##### Mobile
Coming soon


## X. Future Plans:

Coming soon.


## XI. Installing and Running the Tool Yourself:

Coming soon.


## XII. Contributing:

This is currently a solo project, so I don't have any plans for adding in contributions. Check back later.


## XIII. License:

Some sort of open source license. I need to look into this stuff.


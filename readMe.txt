Following file is a short guide to the project structure and its working.

How to run the project:
Through maven >>  mvn test -Denv={BAU/SBX} or default: mvn test [BAU is default entry]
Through Intellij IDEA >> -ea -Denv=={BAU/SBX}

* com/dummy/project/letsL ruearn/lib:
This package will hold all the class files that will assist in basic framework functionality.
baseClass.java >>
Holds driver start and stop activities.
Reading and initialing env variables.
envConfig.java >>
supporting methods to read envConfig.xml

* src\main\resources\env:
Directory name under is the evn name like BAU and SBX.
Each dir have a config file name as envConfig.xml, this name is constant and remains unchanged.



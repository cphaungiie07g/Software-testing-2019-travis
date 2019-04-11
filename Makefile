all: compile compileTest

Simple: compileTest runCov calCov showCov
Positive: compileTestPositive runCovPositive calCovPositive showCov
Negative: compileTestNegative runCovNegative calCovNegative showCov

compile:
	mkdir -p bin
	javac src/Distant.java src/UnhandledCaseException.java -d bin

compileTest:
	mkdir -p bin
	javac -classpath /usr/share/java/junit4.jar src/Distant.java src/UnhandledCaseException.java \
	testcase/TestDistant.java testcase/TestRunner.java -d bin

compileTestPositive:
	mkdir -p bin
	javac -classpath /usr/share/java/junit4.jar src/Distant.java src/UnhandledCaseException.java \
	testcase/Positive/TestDistantPositive.java testcase/Positive/TestRunnerPositive.java -d bin

compileTestNegative:
	mkdir -p bin
	javac -classpath /usr/share/java/junit4.jar src/Distant.java src/UnhandledCaseException.java \
	testcase/Negative/TestDistantNegative.java testcase/Negative/TestRunnerNegative.java -d bin

test:
	cd bin ; java -classpath .:/usr/share/java/junit4.jar TestRunner

runCov:
	cd bin ; java -javaagent:../lib/jacocoagent.jar -cp /usr/share/java/junit4.jar:. TestRunner

runCovPositive:
	cd bin ; java -javaagent:../lib/jacocoagent.jar -cp /usr/share/java/junit4.jar:. TestRunnerPositive

runCovNegative:
	cd bin ; java -javaagent:../lib/jacocoagent.jar -cp /usr/share/java/junit4.jar:. TestRunnerNegative

calCov:
	cd bin ; java -jar ../lib/jacococli.jar report jacoco.exec --classfiles ../bin \
	--sourcefiles ../src --sourcefiles ../testcase --xml ../Distant.xml

calCovPositive:
	cd bin ; java -jar ../lib/jacococli.jar report jacoco.exec --classfiles ../bin \
	--sourcefiles ../src --sourcefiles ../testcase/Positive --xml ../Distant.xml

calCovNegative:
	cd bin ; java -jar ../lib/jacococli.jar report jacoco.exec --classfiles ../bin \
	--sourcefiles ../src --sourcefiles ../testcase/Negative --xml ../Distant.xml

showCov:
	RED="\033[0;31m" ; NC='\033[0m' ; printf "$${RED}http://" ; curl -s https://api.ipify.org/ ; printf ":8000/$${NC}\n"
	cd bin/reportDir ; python3 -m http.server 8000

kill:
	killall python3

clean:
	rm -r bin/*.class bin/*.exec bin/reportDir bin/Positive bin/Negative || true

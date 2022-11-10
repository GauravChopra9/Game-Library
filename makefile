default: runProgram

runProgram: FrontendEngineerTest.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontendEngineerTest

FrontendEngineerTest.class: FrontendEngineerTest.java GameFrontEnd.class FDGameBackend.class TextUITester.class junit5.jar
	javac -cp .:junit5.jar FrontendEngineerTest.java

GameFrontEnd.class: GameFrontEnd.java IGameFrontEnd.class IGameBackend.class IGame.class
	javac GameFrontEnd.java

IGameFrontEnd.class: IGameFrontEnd.java
	javac IGameFrontEnd.java

IGame.class: IGame.java
	javac IGame.java

FDGameBackend.class: FDGameBackend.java IGameBackend.class FDGame.class
	javac FDGameBackend.java

IGameBackend.class: IGameBackend.java
	javac IGameBackend.java

FDGame.class: FDGame.java
	javac FDGame.java

TextUITester.class: TextUITester.java
	javac TextUITester.java

clean: 
	rm *.class

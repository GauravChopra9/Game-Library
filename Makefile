runTests: compile 
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDeveloperTests
compile: GameLibraryBackend.class BackendDeveloperTests.class GameAE.class GameBD.class 
GameLibraryBackend.class: GameLibraryBackend.java
	javac GameLibraryBackend.java
BackendDeveloperTests.class: BackendDeveloperTests.java
	javac -cp .:junit5.jar BackendDeveloperTests.java
GameAE.class: GameAE.java
	javac GameAE.java
GameBD.class: GameBD.java
	javac GameBD.java
clean: 
	rm *.class


runTests: AlgorithmEngineerTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests
AlgorithmEngineerTests.class: RemovingRedBlackTree.class AEGame.class AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
RemovingRedBlackTree.class:
	javac Node.java
	javac RemovingRedBlackTree.java
AEGame.class:
	javac AEGame.java

clean: 
	rm *.class


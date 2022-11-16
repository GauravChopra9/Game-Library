
runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests
AlgorithmEngineerTests.class: RemovingRedBlackTree.class AEGame.class AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
RemovingRedBlackTree.class:
	javac Node.java
	javac RemovingRedBlackTree.java
AEGame.class:
	javac AEGame.java

 
runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar --cp . --scan-classpath
DataWranglerTests.class: DataWranglerTests.java GameLoader.class DWGame.class vgsales2.xml
	javac -cp .:junit5.jar DataWranglerTests.java
GameLoader.class: GameLoader.java DWGame.class
	javac GameLoader.java
DWGame.class: DWGame.java
	javac DWGame.java

runTests: runDataWranglerTests runAlgorithmEngineerTests

clean:
	rm *.class



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
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=DataWranglerTests
DataWranglerTests.class: DataWranglerTests.java GameLoader.class DWGame.class vgsales2.xml
	javac -cp .:junit5.jar DataWranglerTests.java
GameLoader.class: GameLoader.java DWGame.class
	javac GameLoader.java
DWGame.class: DWGame.java
	javac DWGame.java


runBackendDeveloperTests: compile 
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

runTests: runDataWranglerTests runAlgorithmEngineerTests runBackendDeveloperTests

clean:
	rm *.class

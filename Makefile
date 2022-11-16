default: run

run: GameLibrary.class
	java GameLibrary

GameLibrary.class: GameLibrary.java GameLoader.class vgsales2.xml RemovingRedBlackTree.class GameLibraryBackend.class GameFrontEnd.class AEGame.class
	javac GameLibrary.java

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


runFrontendDeveloperTests: FrontendDeveloperTest.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontendDeveloperTest
FrontendDeveloperTest.class: FrontendDeveloperTest.java GameFrontEnd.class FDGameBackend.class TextUITester.class
	javac -cp .:junit5.jar FrontendDeveloperTest.java
GameFrontEnd.class: GameFrontEnd.java
	javac GameFrontEnd.java
FDGameBackend.class: FDGameBackend.java FDGame.class
	javac FDGameBackend.java
FDGame.class: FDGame.java
	javac FDGame.java
TextUITester.class: TextUITester.java
	javac TextUITester.java

runTests: runDataWranglerTests runAlgorithmEngineerTests runBackendDeveloperTests runFrontendDeveloperTests

clean:
	rm *.class

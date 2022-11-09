runTests: DataWranglerTests.class
	java -jar junit5.jar --cp . --scan-classpath
DataWranglerTests.class: DataWranglerTests.java GameLoader.class DWGame.class vgsales2.xml
	javac -cp .:junit5.jar DataWranglerTests.java
GameLoader.class: GameLoader.java DWGame.class
	javac GameLoader.java
DWGame.class: DWGame.java
	javac DWGame.java

clean:
	rm *.class

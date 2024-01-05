
runFDtest:
	javac -cp .:../junit5.jar FrontendDeveloperTests.java
	java -jar ../junit5.jar --class-path=. --select-class=FrontendDeveloperTests

runBDTests: compile
	java -jar ../junit5.jar --class-path=. --select-class=BackendDeveloperTests

compile: IterableMultiKeySortedCollectionInterface.java KeyListInterface.java SortedCollectionInterface.java ingredients.csv  FrontendInterface.java IngredientInterface.java BackendRbtPlaceholderClass.java BackendDeveloperTests.java BackendImplementation.java Ingredient.java BackendInterface.java  
	javac -cp .:../junit5.jar BackendDeveloperTests.java


clean:
	rm *.class

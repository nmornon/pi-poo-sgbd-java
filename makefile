SRC_DIR=src
SRC_TEST_DIR=srctest
BIN_DIR=classes
BIN_TEST_DIR=classestest
DOC_DIR=doc

JUNIT_JAR=/usr/share/java/junit4.jar
HAMCREST_JAR=/usr/share/java/hamcrest-all.jar

MAIN=fr.insarouen.iti.compilation.sgbd.Main
TEST_FILE=fr.insarouen.iti.compilation.sgbd.AllTests
SOURCES=$(shell find $(SRC_DIR) -name "*.java")
SOURCES_TESTS=$(shell find $(SRC_TEST_DIR) -name "*.java")

all: run

compile: clean
	mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) -classpath $(BIN_DIR) -sourcepath $(SRC_DIR) $(SOURCES)

print: compile
	javap -classpath $(BIN_DIR) $(shell find $(CLASSES_DIR) -name "*.class")

run: compile
	java -classpath $(BIN_DIR) $(MAIN)

test: compile
	mkdir -p $(BIN_TEST_DIR)
	javac -d $(BIN_TEST_DIR) -classpath $(BIN_TEST_DIR):$(BIN_DIR):$(JUNIT_JAR):$(HAMCREST_JAR) -sourcepath $(SRC_TEST_DIR) $(SOURCES_TESTS)
	echo ""
	java -classpath $(BIN_TEST_DIR):$(JUNIT_JAR):$(BIN_DIR):$(HAMCREST_JAR) org.junit.runner.JUnitCore $(TEST_FILE)

doc: compile
	mkdir -p $(DOC_DIR)
	javadoc -d $(DOC_DIR) -sourcepath $(SRC_DIR) -subpackages fr.insarouen.iti.compilation.sgbd

clean:
	rm -rf $(BIN_DIR) $(DOC_DIR) $(BIN_TEST_DIR)

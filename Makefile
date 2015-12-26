javac:
	find . -name "*.java" | xargs javac

run:
	java tests/TestPhysics

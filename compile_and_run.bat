@echo off
echo Compilando o projeto...
if not exist target\classes mkdir target\classes
javac -encoding UTF-8 -d target/classes src/main/java/com/metro/Main.java src/main/java/gui/*.java src/main/java/controller/*.java src/main/java/util/*.java
if %errorlevel% neq 0 (
    echo Erro na compilacao!
    pause
    exit /b 1
)
echo Executando o programa...
java -cp target/classes com.metro.Main
pause 
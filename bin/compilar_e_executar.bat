@echo off
echo Compilando o projeto...
javac -encoding UTF-8 gui/*.java ProjetoIntegrado.java
if errorlevel 1 (
    echo Erro na compilacao!
    pause
    exit /b 1
)
echo Executando o programa...
java ProjetoIntegrado
pause 
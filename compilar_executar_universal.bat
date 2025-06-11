@echo off

REM Apagar todos os arquivos .class do projeto
for /r %%i in (*.class) do del "%%i"

echo Compilando o projeto...

if not exist "bin" mkdir bin

javac -d bin -cp . ProjetoIntegrado.java gui/*.java util/*.java controller/*.java

if errorlevel 1 (
    echo Erro na compilacao!
    pause
    exit /b 1
)

echo Compilacao concluida!
echo Executando o programa...

java -cp bin ProjetoIntegrado

pause 
@echo off
echo Compilando o projeto...

if not exist "bin" mkdir bin

:: Compilar todos os arquivos Java
javac -d bin -cp . ProjetoIntegrado.java gui/*.java util/*.java controller/*.java

if errorlevel 1 (
    echo Erro na compilacao!
    pause
    exit /b 1
)

:: Copiar recursos
echo Copiando recursos...
xcopy /E /I /Y imagens bin\imagens
xcopy /E /I /Y sons bin\sons

echo Compilacao concluida!
echo Para executar, use: java -cp bin ProjetoIntegrado

pause 
@echo off
title To-Do List App
color 0A

echo.
echo ============================================================
echo                TO-DO LIST APP LAUNCHER
echo ============================================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java from https://www.java.com/download/
    echo.
    pause
    exit /b 1
)

REM Check if the class file exists, if not compile it
if not exist "TodoListApp.class" (
    echo Compiling TodoListApp.java...
    javac TodoListApp.java
    if %errorlevel% neq 0 (
        echo ERROR: Compilation failed!
        pause
        exit /b 1
    )
    echo Compilation successful!
    echo.
)

REM Run the application
echo Starting To-Do List App...
echo.
java TodoListApp

REM Keep window open if there was an error
if %errorlevel% neq 0 (
    echo.
    echo Application ended with an error.
    pause
)

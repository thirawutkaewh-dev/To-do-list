@echo off
title To-Do List Manager
color 0B

:menu
cls
echo.
echo ============================================================
echo                TO-DO LIST MANAGER
echo ============================================================
echo.
echo Choose an option:
echo.
echo [1] Run To-Do List App
echo [2] Compile Java Code
echo [3] View Source Code
echo [4] Open Folder
echo [5] Exit
echo.
set /p choice="Enter your choice (1-5): "

if "%choice%"=="1" goto run_app
if "%choice%"=="2" goto compile
if "%choice%"=="3" goto view_code
if "%choice%"=="4" goto open_folder
if "%choice%"=="5" goto exit
goto invalid_choice

:run_app
cls
echo.
echo ============================================================
echo                STARTING TO-DO LIST APP
echo ============================================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java from https://www.java.com/download/
    echo.
    pause
    goto menu
)

REM Check if the class file exists, if not compile it
if not exist "TodoListApp.class" (
    echo Compiling TodoListApp.java...
    javac TodoListApp.java
    if %errorlevel% neq 0 (
        echo ERROR: Compilation failed!
        pause
        goto menu
    )
    echo Compilation successful!
    echo.
)

REM Run the application
echo Starting To-Do List App...
echo.
java TodoListApp
echo.
echo Application ended. Press any key to return to menu...
pause >nul
goto menu

:compile
cls
echo.
echo ============================================================
echo                COMPILING JAVA CODE
echo ============================================================
echo.
echo Compiling TodoListApp.java...
javac TodoListApp.java
if %errorlevel% neq 0 (
    echo.
    echo ERROR: Compilation failed!
    echo Please check your Java code for errors.
) else (
    echo.
    echo SUCCESS: Compilation completed!
    echo TodoListApp.class has been created.
)
echo.
pause
goto menu

:view_code
cls
echo.
echo ============================================================
echo                VIEWING SOURCE CODE
echo ============================================================
echo.
if exist "TodoListApp.java" (
    type TodoListApp.java
) else (
    echo ERROR: TodoListApp.java not found!
)
echo.
pause
goto menu

:open_folder
cls
echo.
echo ============================================================
echo                OPENING FOLDER
echo ============================================================
echo.
echo Opening current folder in Windows Explorer...
explorer .
echo.
pause
goto menu

:invalid_choice
echo.
echo Invalid choice! Please enter 1-5.
timeout /t 2 >nul
goto menu

:exit
cls
echo.
echo ============================================================
echo                GOODBYE!
echo ============================================================
echo.
echo Thanks for using To-Do List Manager!
echo.
timeout /t 2 >nul
exit /b 0

call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo runcrud has error - breaking work
goto fail

:openbrowser
start chrome.exe  http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo chrome has error
goto fail

:fail
echo There were errors

:end
echo .
echo Work is finished
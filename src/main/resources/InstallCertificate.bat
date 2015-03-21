@echo off
goto install_certificates
# for explanation of code below: http://stackoverflow.com/questions/4051883/batch-script-how-to-check-for-admin-rights

:install_certificates
    echo Verifying if batch file is running as Admin

    net session >nul 2>&1
    if %errorLevel% == 0 (
        echo Installing the certificates:
#%~dp0 will fetch the path to the current directory
#alternate method is using >> set list= dir *.cer /b /s
#this will fetch all certificates with the full path from the directory the batch is executed
#advantage is if the name of certi is modified, it will still get installed
#disadvantage many certis(even the ones that are not a part of esic will be installed)
#for now keeping fixed name solution
certutil -f -addstore Root "%~dp0\certi\(n)CodeSolutionsCA2011-1.cer"
certutil -f -addstore Root "%~dp0\certi\cca_india_2011.cer"
certutil -f -addstore Root "%~dp0\certi\esic.in.cer"
	echo Please check console to confirm if files have been installed successfully
    ) else (
        echo Please execute the batch file with admin rights.
    )

    pause >nul
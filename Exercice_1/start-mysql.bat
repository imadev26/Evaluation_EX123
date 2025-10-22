@echo off
REM Script batch pour démarrer MySQL sur Windows
REM Exécutez ce script en tant qu'administrateur

title Démarrage de MySQL

echo ========================================
echo   Demarrage de MySQL pour le projet
echo ========================================
echo.

REM Vérifier les privilèges administrateur
net session >nul 2>&1
if %errorLevel% neq 0 (
    echo [ERREUR] Ce script doit etre execute en tant qu'administrateur !
    echo.
    echo Comment executer en tant qu'administrateur :
    echo 1. Clic droit sur ce fichier
    echo 2. Selectionnez "Executer en tant qu'administrateur"
    echo.
    pause
    exit /b 1
)

echo [OK] Script execute en tant qu'administrateur
echo.

echo Tentative de demarrage de MySQL...
echo.

REM Essayer de démarrer MySQL80 (version la plus courante)
echo Essai 1 : Demarrage du service MySQL80...
net start MySQL80 2>nul
if %errorLevel% equ 0 (
    echo [SUCCES] MySQL80 a demarre avec succes !
    goto :success
)

REM Si MySQL80 n'existe pas, essayer MySQL
echo Essai 2 : Demarrage du service MySQL...
net start MySQL 2>nul
if %errorLevel% equ 0 (
    echo [SUCCES] MySQL a demarre avec succes !
    goto :success
)

REM Si MySQL57 existe
echo Essai 3 : Demarrage du service MySQL57...
net start MySQL57 2>nul
if %errorLevel% equ 0 (
    echo [SUCCES] MySQL57 a demarre avec succes !
    goto :success
)

REM Si aucun service n'a été trouvé
echo.
echo [ERREUR] Aucun service MySQL trouve !
echo.
echo MySQL n'est peut-etre pas installe sur votre systeme.
echo.
echo Pour installer MySQL :
echo 1. Visitez : https://dev.mysql.com/downloads/installer/
echo 2. Telechargez MySQL Installer for Windows
echo 3. Installez MySQL Server
echo.
goto :end

:success
echo.
echo ========================================
echo   MySQL est maintenant demarre !
echo ========================================
echo.
echo Vous pouvez maintenant lancer votre application Java :
echo   mvn exec:java -Dexec.mainClass="ma.projet.test.TestApplication"
echo.
echo Ou utiliser le test simple :
echo   mvn exec:java -Dexec.mainClass="ma.projet.test.TestSimple"
echo.

:end
echo Appuyez sur une touche pour fermer...
pause >nul


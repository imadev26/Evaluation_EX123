# Script PowerShell pour démarrer MySQL sur Windows
# Exécutez ce script en tant qu'administrateur

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Démarrage de MySQL pour le projet" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Vérifier si le script est exécuté en tant qu'administrateur
$isAdmin = ([Security.Principal.WindowsPrincipal] [Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)

if (-not $isAdmin) {
    Write-Host "⚠️  ATTENTION : Ce script doit être exécuté en tant qu'administrateur !" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "Comment exécuter en tant qu'administrateur :" -ForegroundColor Yellow
    Write-Host "1. Clic droit sur PowerShell" -ForegroundColor White
    Write-Host "2. Sélectionnez 'Exécuter en tant qu'administrateur'" -ForegroundColor White
    Write-Host "3. Relancez ce script" -ForegroundColor White
    Write-Host ""
    Pause
    Exit
}

Write-Host "✓ Script exécuté en tant qu'administrateur" -ForegroundColor Green
Write-Host ""

# Rechercher les services MySQL
Write-Host "Recherche des services MySQL installés..." -ForegroundColor Cyan
$mysqlServices = Get-Service -Name "MySQL*" -ErrorAction SilentlyContinue

if ($null -eq $mysqlServices -or $mysqlServices.Count -eq 0) {
    Write-Host ""
    Write-Host "❌ Aucun service MySQL trouvé !" -ForegroundColor Red
    Write-Host ""
    Write-Host "MySQL n'est peut-être pas installé sur votre système." -ForegroundColor Yellow
    Write-Host ""
    Write-Host "Pour installer MySQL :" -ForegroundColor White
    Write-Host "1. Visitez : https://dev.mysql.com/downloads/installer/" -ForegroundColor White
    Write-Host "2. Téléchargez MySQL Installer for Windows" -ForegroundColor White
    Write-Host "3. Installez MySQL Server" -ForegroundColor White
    Write-Host ""
    Pause
    Exit
}

Write-Host ""
Write-Host "Services MySQL trouvés :" -ForegroundColor Green
$mysqlServices | Format-Table -Property Name, Status, DisplayName -AutoSize

Write-Host ""

# Démarrer chaque service MySQL trouvé
foreach ($service in $mysqlServices) {
    Write-Host "Traitement du service : $($service.Name)" -ForegroundColor Cyan
    
    if ($service.Status -eq "Running") {
        Write-Host "  ✓ Le service $($service.Name) est déjà démarré" -ForegroundColor Green
    } else {
        Write-Host "  ⏳ Démarrage du service $($service.Name)..." -ForegroundColor Yellow
        try {
            Start-Service -Name $service.Name -ErrorAction Stop
            Write-Host "  ✓ Le service $($service.Name) a démarré avec succès !" -ForegroundColor Green
        } catch {
            Write-Host "  ❌ Erreur lors du démarrage de $($service.Name) : $_" -ForegroundColor Red
        }
    }
    Write-Host ""
}

# Vérifier l'état final
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "État final des services MySQL :" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Get-Service -Name "MySQL*" | Format-Table -Property Name, Status, DisplayName -AutoSize

Write-Host ""

# Test de connexion au port 3306
Write-Host "Test de connexion au port 3306..." -ForegroundColor Cyan
$tcpConnection = Test-NetConnection -ComputerName localhost -Port 3306 -WarningAction SilentlyContinue

if ($tcpConnection.TcpTestSucceeded) {
    Write-Host "✓ MySQL écoute sur le port 3306 !" -ForegroundColor Green
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "  🎉 MySQL est prêt à être utilisé !" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "Vous pouvez maintenant lancer votre application Java :" -ForegroundColor White
    Write-Host "  mvn exec:java -Dexec.mainClass='ma.projet.test.TestApplication'" -ForegroundColor Cyan
} else {
    Write-Host "⚠️  MySQL ne répond pas sur le port 3306" -ForegroundColor Yellow
    Write-Host "   Le service peut avoir besoin de quelques secondes pour démarrer complètement" -ForegroundColor Yellow
    Write-Host "   Ou vérifiez les logs MySQL pour plus d'informations" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "Appuyez sur une touche pour fermer..." -ForegroundColor Gray
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")


rem
rem Create the keys and Keystores for wM Advanced Integration Workshop
rem
    set TOP=C:\SoftwareAG
    set STOREDIR=%TOP%\IntegrationServer\config
rem
    set KEYTOOL=%TOP%\jvm\jvm\bin\keytool.exe
    set SERVERKEYSTOREFILE=ServerKeyStore.jks
    set SERVERTRUSTSTOREFILE=ServerTrustStore.jks
    set CLIENTKEYSTOREFILE=ClientKeyStore.jks
    set CLIENTTRUSTSTOREFILE=ClientTrustStore.jks
rem ----------------------------------------------------------------------------
    set CKSP=manage
    set CKYP=manage
	set CTSP=manage
rem ----------------------------------------------------------------------------
    set SKSP=manage
    set SKYP=manage
	set STSP=manage
rem ----------------------------------------------------------------------------
    set SDNAME="CN=ServerOrganisation, OU=Server Corporation, O=Server Corp., L=Reston, S=VA, C=US"
    set CDNAME="CN=ClientOrganisation, OU=Client GmbH, O=Client GmbH, L=Hippelsbach, S=Odenwaldkreis, C=CN"
rem ----------------------------------------------------------------------------
    set SKS=%STOREDIR%\%SERVERKEYSTOREFILE%
    if exist %SKS%   del %SKS%
rem    
    set STS=%STOREDIR%\%SERVERTRUSTSTOREFILE%
    if exist %STS%   del %STS%
rem    
    set CKS=%STOREDIR%\%CLIENTKEYSTOREFILE%
    if exist %CKS%   del %CKS%
rem    
    set CTS=%STOREDIR%\%CLIENTTRUSTSTOREFILE%
    if exist %CTS%   del %CTS%
rem
rem ----------------------------------------------------------------------------
rem generate client keystore and export the clients certificate (public key)
rem ----------------------------------------------------------------------------
rem
    %KEYTOOL% -genkey -dname %CDNAME% -keyalg RSA -alias client -keystore %CKS% -storepass %CKSP% -keypass %CKYP%
    if errorlevel 1 pause
    %KEYTOOL% -export -keyalg RSA -keystore %CKS% -storepass %CKSP% -alias client -file Client.cer 
    if errorlevel 1 pause
rem
rem ----------------------------------------------------------------------------
rem generate server keystore and export the servers certificate (public key)
rem ----------------------------------------------------------------------------
rem
    %KEYTOOL% -genkey -dname %SDNAME% -keyalg RSA -alias server -keystore %SKS% -storepass %SKSP% -keypass %SKYP%
    if errorlevel 1 pause
    %KEYTOOL% -export -keystore %SKS% -storepass %SKSP% -alias server -file Server.cer 
    if errorlevel 1 pause
rem
rem ----------------------------------------------------------------------------
rem create the truststores for client and server
rem ----------------------------------------------------------------------------
rem
    %KEYTOOL% -importcert -alias server -noprompt -keystore %CTS% -storepass %CTSP% -file Server.cer
    if errorlevel 1 pause
    %KEYTOOL% -importcert -alias client -noprompt -keystore %CTS% -storepass %CTSP% -file Client.cer
    if errorlevel 1 pause
rem
    %KEYTOOL% -importcert -alias server -noprompt -keystore %STS% -storepass %STSP% -file Server.cer
    if errorlevel 1 pause
    %KEYTOOL% -importcert -alias client -noprompt -keystore %STS% -storepass %STSP% -file Client.cer
    if errorlevel 1 pause
rem
rem ----------------------------------------------------------------------------
rem list the clients and the servers key and trust store
rem ----------------------------------------------------------------------------
rem
    %KEYTOOL% -list -v  -storepass %CKSP% -keystore %CKS%
    if errorlevel 1 pause
    %KEYTOOL% -list -v  -storepass %SKSP% -keystore %SKS%
    if errorlevel 1 pause
    %KEYTOOL% -list -v  -storepass %CTSP% -keystore %CTS%
    if errorlevel 1 pause
    %KEYTOOL% -list -v  -storepass %STSP% -keystore %STS%
    if errorlevel 1 pause
rem
rem ----------------------------------------------------------------------------
rem clean up
rem ----------------------------------------------------------------------------
rem
    if exist *.csr del *.csr
rem if exist *.cer del *.cer
    if exist *.key del *.key

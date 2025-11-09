# Sock Paradox - Build Instructions

## Požiadavky na build

### 1. Java Development Kit (JDK) 11 alebo vyššie

**Použitá verzia:** JDK 17 (D:\java\jdk-17.0.9)

**Nastavenie JAVA_HOME:**

**Windows PowerShell:**
```powershell
$env:JAVA_HOME = "D:\java\jdk-17.0.9"
$env:PATH = "D:\java\jdk-17.0.9\bin;$env:PATH"
```

**Windows CMD:**
```cmd
set JAVA_HOME=D:\java\jdk-17.0.9
set PATH=D:\java\jdk-17.0.9\bin;%PATH%
```

## Build AAB balíka pre Google Play

### 1. Overenie keystore súborov

Uistite sa, že existujú:
- `keystore.jks` - keystore súbor (v root adresári projektu)
- `keystore.properties` - konfigurácia pre signing (v root adresári projektu)

**⚠️ DÔLEŽITÉ: Nikdy necommitujte tieto súbory do GIT!**

### 2. Build release AAB

```powershell
cd d:\ProjektyAndroid\SockParadox
$env:JAVA_HOME = "D:\java\jdk-17.0.9"
$env:PATH = "D:\java\jdk-17.0.9\bin;$env:PATH"
.\gradlew.bat bundleRelease
```

### 3. Výstupný súbor

AAB balík sa vytvorí v:
```
app\build\outputs\bundle\release\app-release.aab
```

**Veľkosť:** ~4.6 MB

## Build APK (pre testovanie)

```powershell
$env:JAVA_HOME = "D:\java\jdk-17.0.9"
$env:PATH = "D:\java\jdk-17.0.9\bin;$env:PATH"
.\gradlew.bat assembleRelease
```

APK sa vytvorí v:
```
app\build\outputs\apk\release\app-release.apk
```

## Keystore informácie

**⚠️ BEZPEČNE USCHOVAJTE TIETO ÚDAJE!**

- **Keystore heslo:** `SockParadox2025!`
- **Key alias:** `sockparadox`
- **Key heslo:** `SockParadox2025!`
- **Platnosť:** 10 000 dní

## Riešenie problémov

### Chyba: "Dependency requires at least JVM runtime version 11"
- Uistite sa, že máte nainštalované JDK 11+ a nastavené JAVA_HOME

### Chyba pri signing
- Skontrolujte, či existuje `keystore.jks` a `keystore.properties`
- Overite správnosť hesiel v `keystore.properties`

### Chyba pri ProGuard
- Skontrolujte `proguard-rules.pro` pre správne keep rules

## Verzia aplikácie

Aktuálna verzia je definovaná v `app/build.gradle`:
- `versionCode`: 1
- `versionName`: "1.0"

Pri každom novom release zvýšte `versionCode` a aktualizujte `versionName`.

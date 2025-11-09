# Sock Paradox - Testing Documentation

## Test Coverage

Projekt obsahuje komplexnú sadu JUnit testov pre overenie funkcionality.

### Unit Tests (54 testov)

#### 1. ExampleUnitTest (6 testov)
Základné unit testy pre overenie základnej funkcionality:
- ✅ Aritmetické operácie
- ✅ Reťazcové operácie
- ✅ Boolean logika
- ✅ Práca s poliami
- ✅ Null checks
- ✅ Package name validation

#### 2. Model Tests

**RoomTest (19 testov)**
Testy pre Room model class:
- ✅ Room properties (roomCode, nameKey, descriptionKey, imageResource)
- ✅ Room collections (objects, npcs, compass, actions)
- ✅ Compass inner class (direction, wayTo)
- ✅ Action inner class (commandKey, conditions, resultKey, effects, repeatable)
- ✅ Condition inner class (hasObject)
- ✅ Effect inner class (unlockRoom, addToInventory, endGame)

**ObjectDefinitionTest (4 testy)**
Testy pre ObjectDefinition model:
- ✅ Object code
- ✅ Object nameKey
- ✅ Object descriptionKey
- ✅ Instance validation

**NpcTest (13 testov)**
Testy pre Npc model:
- ✅ NPC properties (code, nameKey, descriptionKey)
- ✅ NPC collections (dialogues, actions)
- ✅ Dialogue inner class (trigger, textKey)
- ✅ NpcAction inner class (commandKey, conditions, resultKey, effects, repeatable)

#### 3. Engine Tests

**GameEngineLogicTest (12 testov)**
Testy pre herné logické funkcie:
- ✅ Action repeatability
- ✅ Multiple conditions handling
- ✅ Multiple effects handling
- ✅ Empty collections validation
- ✅ Null checks for actions
- ✅ Multiple compass directions
- ✅ Multiple room actions

### Instrumented Tests (Android device required)

#### ExampleInstrumentedTest (5 testov)
Základné Android Context testy:
- ✅ App context validation
- ✅ Package name verification
- ✅ Resources accessibility
- ✅ PackageManager accessibility
- ✅ Raw resource existence (rooms.json)

#### GameEngineInstrumentedTest (19 testov)
Integračné testy pre GameEngine s Android Context:
- ✅ GameEngine initialization
- ✅ Initial room (BEDROOM)
- ✅ Starting inventory (SOCK1)
- ✅ Room movement
- ✅ Room locking/unlocking
- ✅ Available directions
- ✅ Available actions
- ✅ Game state management
- ✅ String resources loading
- ✅ NPC data loading
- ✅ Object data loading
- ✅ Inventory management
- ✅ Room properties validation

## Spustenie testov

### Unit Tests (bez Android zariadenia)

```powershell
cd d:\ProjektyAndroid\SockParadox
$env:JAVA_HOME = "D:\java\jdk-17.0.9"
$env:PATH = "D:\java\jdk-17.0.9\bin;$env:PATH"
.\gradlew.bat test
```

**Výstup:**
- Test výsledky: `app\build\reports\tests\testDebugUnitTest\index.html`
- XML výsledky: `app\build\test-results\testDebugUnitTest\`

### Instrumented Tests (Android zariadenie/emulátor)

```powershell
.\gradlew.bat connectedAndroidTest
```

**Výstup:**
- Test výsledky: `app\build\reports\androidTests\connected\index.html`

## Test Dependencies

Projekt používa:
- **JUnit 4** - základný testing framework
- **Mockito 5.7.0** - mocking framework (pripravené pre budúce použitie)
- **AndroidX Test** - Android instrumented testing

## Test Reports

Po spustení testov otvorte HTML report:

```powershell
# Unit tests
start app\build\reports\tests\testDebugUnitTest\index.html

# Instrumented tests
start app\build\reports\androidTests\connected\index.html
```

## Test Štatistiky

### Debug Build
- **Celkovo testov:** 54
- **Úspešných:** 54
- **Neúspešných:** 0
- **Chýb:** 0
- **Success rate:** 100%

### Test Coverage

| Modul | Triedy | Testy | Coverage |
|-------|--------|-------|----------|
| Model | 3 | 36 | Model classes |
| Engine | 1 | 12 | Logic methods |
| General | 1 | 6 | Basic functionality |
| **Total** | **5** | **54** | - |

## Continuous Integration

Pre CI/CD môžete použiť:

```yaml
# GitHub Actions example
- name: Run Unit Tests
  run: ./gradlew test

- name: Upload Test Reports
  uses: actions/upload-artifact@v3
  with:
    name: test-reports
    path: app/build/reports/tests/
```

## Best Practices

1. ✅ **Všetky testy musia prechádzať pred commit**
2. ✅ **Spúšťajte testy po každej zmene v kóde**
3. ✅ **Pridávajte testy pre nové funkcie**
4. ✅ **Udržiavajte test coverage nad 80%**
5. ✅ **Používajte deskriptívne názvy testov**

## Riešenie problémov

### Testy zlyhávajú s "Context not found"
- Používajte Unit testy pre logic bez Android závislostí
- Používajte Instrumented testy pre Android komponenty

### Gradle build failed
- Overte Java 11+ v JAVA_HOME
- Spustite `.\gradlew.bat clean`

### Mockito errors
- Uistite sa, že máte správnu verziu Mockito v build.gradle

# 🧦 Sock Paradox

**A domestic comedy text adventure for Android**

*Lost Sock. Lost Mind.*

---

## 📖 About

Sock Paradox is a humorous text-based adventure game where you must find your missing lucky sock before the big football match. Navigate through your apartment, interact with cats and a pigeon, solve puzzles, and discover where lost socks really hide!

---

## 🎮 Features

- 🧭 **Compass-based Navigation** - Explore your apartment room by room
- 🎒 **Inventory System** - Collect and use items to progress
- 💬 **NPC Interactions** - Talk to Muro the Cat, Cica the Cat, and an Artist Pigeon
- 🔐 **Puzzle Solving** - Conditional actions and progressive room unlocking
- 🎨 **Custom UI Design** - Beautiful interface with custom dialogs and toasts
- 🌍 **Multilingual** - Full support for Slovak, Czech, and English

---

## 📱 Technical Details

- **Platform:** Android
- **Minimum SDK:** 29 (Android 10)
- **Target SDK:** 36
- **Language:** Java
- **Architecture:** MVVM with JSON-based game data
- **Size:** ~22.5 MB

---

## 🎯 Gameplay

You wake up at 7:55 AM with only 5 minutes to get to your football match. You find one sock, but where's the other one? Search through:

- 🏠 Bedroom
- 🚪 Hallway  
- 🍳 Kitchen
- 🛋️ Living Room
- 🌿 Balcony
- 🚿 Bathroom
- 🧺 Washing Machine
- 🪑 Under the Couch
- 🏆 ...and more!

**Playing Time:** 10-15 minutes  
**Difficulty:** Medium  
**Replayability:** Discover all NPC dialogues!

---

## 📚 Game Walkthroughs

Need help? Check out our complete walkthroughs:

**[→ View All Walkthroughs](./WALKTHROUGHS.md)**

Available in:
- 🇸🇰 [Slovenčina](./WALKTHROUGH_SK.md)
- 🇨🇿 [Čeština](./WALKTHROUGH_CS.md)  
- 🇬🇧 [English](./WALKTHROUGH_EN.md)

---

## 🚀 Installation

1. Download `app-debug.apk` from the releases
2. Enable "Install from unknown sources" in your Android settings
3. Install the APK
4. Launch and enjoy!

---

## 🛠️ Building from Source

### Prerequisites
- Android Studio
- JDK 17 or higher
- Android SDK 36

### Build Steps

```bash
# Clone the repository
git clone https://github.com/yourusername/SockParadox.git
cd SockParadox

# Build with Gradle
./gradlew assembleDebug

# APK will be in: app/build/outputs/apk/debug/
```

---

## 📂 Project Structure

```
SockParadox/
├── app/
│   ├── src/main/
│   │   ├── java/org/javerland/sockparadox/
│   │   │   ├── GameActivity.java       # Main game activity
│   │   │   ├── GameEngine.java         # Game logic engine
│   │   │   ├── Room.java               # Room data model
│   │   │   ├── Npc.java                # NPC data model
│   │   │   └── ObjectDefinition.java   # Item data model
│   │   ├── res/
│   │   │   ├── raw/rooms.json          # Game data definition
│   │   │   ├── layout/                 # UI layouts
│   │   │   ├── drawable/               # Images and graphics
│   │   │   └── values/                 # Strings and themes
│   └── build.gradle
├── WALKTHROUGH_SK.md                   # Slovak walkthrough
├── WALKTHROUGH_CS.md                   # Czech walkthrough
├── WALKTHROUGH_EN.md                   # English walkthrough
├── WALKTHROUGHS.md                     # Walkthrough index
└── README.md                           # This file
```

---

## 🎨 Game Design

### Color Scheme
- **Primary:** Dark Blue (#2D3A4B)
- **Accent:** Red (#CC3333)
- **Background:** Cream (#F4EBD0)
- **Text:** Dark on light backgrounds, white on dark backgrounds

### Game Mechanics
- **JSON-driven content** - Easy to modify and extend
- **Conditional actions** - Based on inventory items
- **Progressive unlocking** - New rooms appear as you solve puzzles
- **Multiple interaction types** - Actions, NPC dialogues, item inspection

---

## 🏆 Credits

**Developer:** JaverLand  
**Game Design:** Original concept  
**Version:** 1.0  
**Year:** 2025

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.

---

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Report bugs
- Suggest new features
- Improve translations
- Add new game content

---

## 📞 Contact

For questions or feedback, please open an issue on GitHub.

---

**Enjoy solving the Sock Paradox! 🧦**

*Where do lost socks really go? Play to find out!*


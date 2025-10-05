# 📱 Android Kotlin Study Project

A collection of small exercises built with **Kotlin** and **Jetpack Compose** to practice modern Android development concepts.  
Each exercise focuses on a specific learning goal from basic UI setup to state handling and building a responsive layout.

---

## 🧩 Exercises Overview

<details>
<summary>🟢 <b>Exercise 00 – Basic Display</b></summary>

**Goal:** Create a simple screen with a text and a button.  
**Key points:**
- Display centered text (`"Welcome!"`) and a **“Press me”** button.  
- On button click, print **"Button pressed"** to **Logcat**.  
- Responsive layout using `Column` + `Arrangement.Center`.  

**Concepts practiced:**  
Jetpack Compose basics, layout alignment, and logging in Android.

</details>

---

<details>
<summary> 🟡 <b>Exercise 01 – Say Hello to the World</b></summary>

**Goal:** Add interactivity by toggling text on button click.  
**Key points:**
- Reuse Exercise 00 layout.  
- Clicking the button toggles between `"Welcome!"` and `"Hello World!"`.  
- Implemented using `rememberSaveable` for UI state persistence.  

**Concepts practiced:**  
Compose state management, recomposition, and state persistence.
</details>

---

<details>
<summary>  🟠 <b>Exercise 02 – Calculator UI</b></summary>

**Goal:** Build a calculator user interface (no logic yet).  
**Key points:**
- Top AppBar titled **"Calculator"**.  
- Two read-only **TextFields** showing `"0"`.  
- Complete keypad with digits, operators, `AC`, `C`, `=`, and `.`.  
- Each button press logs its label to **Logcat**.  
- Responsive layout with `LazyVerticalGrid(GridCells.Adaptive)`.  

**Concepts practiced:**  
Compose scaffolds, app bars, grids, component modularization, responsive UI.

</details>

---

<details>

<summary>🟣 <b>Exercise 03 – It’s Alive! (Calculator App)</b></summary>

**Goal:** Implement full calculator functionality.  
**Key points:**
- Perform real mathematical operations: addition, subtraction, multiplication, and division.  
- Support for negative numbers and decimals.  
- Allow multiple operations in one expression.  
- Handle invalid input and division by zero.  
- Display both expression and result dynamically.  

**Concepts practiced:**  
State management with ViewModel, expression parsing, and input validation.

</details>

---

<details>
<summary>  🧰 Tech Stack </b></summary>
- **Language:** Kotlin  
- **UI Toolkit:** Jetpack Compose (Material 3)  
- **Build System:** Gradle Kotlin DSL  
- **Logging:** Logcat (`Log.d`)  
- **IDE:** Android Studio  

</details>

---
<details>
<summary>  📝 Project Structure</b></summary>

```
MobileModule00/
├── ex00/           # Basic display
├── ex01/           # Hello World toggle
├── ex02/           # Calculator UI
└── calculator_app/ # Working calculator (ex03)
```
</details>

---

<details>
<summary> 👤 Author </b></summary>

**Author:** emanuela.licameli@gmail.com

[<img alt="Email" height="32px" src="https://cdn-icons-png.flaticon.com/512/732/732200.png" />](mailto:emanuela.licameli@gmail.com)
[<img alt="GitHub" height="32px" src="https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png" target="_blank" />](https://github.com/MagicEmy)
[<img alt="LinkedIn" height="32px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/LinkedIn_logo_initials.png/600px-LinkedIn_logo_initials.png" target="_blank" />](https://www.linkedin.com/in/emanuelalicameli/)

</details>

---
**Goal:** Deepen understanding of Android development using Kotlin and Compose through structured, progressive exercises.

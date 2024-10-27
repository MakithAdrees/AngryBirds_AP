# Angry Birds Static GUI Prototype

## Contributors

- **Arhan Jain** - Roll No: 2023118, MakithAdrees
- **Nihal** - Roll No: 2023345, GolDRoger69

---

## Overview
- This repository contains a static GUI  for an **Angry Birds**-like game developed using **LibGDX** in Java for **Advanced Programming Course CS201**.
- This is Deadline 2 of the Project.
- The project includes the design of various game screens, buttons, and UI elements. The buttons are functional only for navigation between different screens, while other game mechanics are not yet implemented.

## Structure
```game/
├── Main.java
├── Birds/
│   ├── Bird.java
│   ├── Blues.java
│   ├── Bomb.java
│   ├── Chuck.java
│   ├── Red.java
│   ├── SpecialAbility.java
│   └── Terrance.java
├── Blocks/
│   ├── Block.java
│   ├── Dimension.java
│   ├── Glass.java
│   ├── Stone.java
│   └── Wood.java
├── Extras/
│   ├── Catapult.java
│   └── TNT.java
├── Pigs/
│   ├── HelmetPig.java
│   ├── KingPig.java
│   ├── MoustachePig.java
│   ├── NormalPigs.java
│   └── Pig.java
└── Screen/
    ├── Level1.java
    ├── Levels.java
    ├── LoadingScreen.java
    ├── LoginScreen.java
    ├── MainScreen.java
    ├── OptionsScreen.java
    └── SignUpScreen.java
```

## Features
- `Screens`: Different game screens such as the Loading Screen, Main Screen, Signup Screen, Login Screen, Options Screen, levsels, level1 Screen.
- `Buttons`: Interactive buttons with navigation functionality and a **+** on Levels Screen so that User Can add New Levels of their Choice.**(This is a part for Bonus)**.
- `Textures`: Custom textures for buttons and UI components, including modified circular buttons and all the textures are stored in assets folder in the Project **AngryBirds**.

## Screens Implemented
- `Loading Screen`: The starting point of the game with the is the Loading Screen with a loading Bar in the Bottom.
- `Main Screen`: Main Screen with Buttons and Navigations to the Login Screen and Signup Screen.
- `Login/SignUp Screen`: These Screens are for taking User Name and Password for Login **OR** Signup in the Game.
- `Play Screen`: This screen allows players to mute the game music, feedback submission and move to the level select screen.
- `Level Screen`: Select different levels from this screena and there is a **+** on Levels Screen so that User Can add New Levels of their Choice **(This is a part for Bonus)**.
- `Level1`: Level 1 Screen with all the Elements and Textures.(**game mechanics are not yet implemented as it was not a part of this Deadline**)

## Game Classes
Several classes and objects such as Catapult, TNT, birds, pigs, blocks classes have been declared as per the originally designed UML. <br>
Admin functionality from the original UML has been removed. <br>

## Play Game
To start the game open the AngryBirds folder in some IDLE, and follow the following path : <br>
`lwjl3 -> src -> main -> java -> com.angrybirds.game.lwjgl3 -> Lwjgl3Launcher` <br>
Proceed to launch the game by running the executable file. <br>
<br>
`Repository Link :` https://github.com/MakithAdrees/AngryBirds_AP.git <br>

## OOPS Conecepts
`Polymorphism :` Each bird class implements the special ability interface as part of bonus, which can be accessed while functionality is added to game <br>
`Inheritance :` Each bird extends Bird class, each pig extends Pig class, each block extends Block class <br>
`Abstraction :` The user class is abstract, which is extended by player and admin; The Bird class declares necessary attributes and methods, similarly abstract Pig <br>
`Generic :` Blocks<T> class takes three types of block textures Stone, wood, glass, which further implement the different shapes of these textures. <br>
`Encapsulation :` Methods and attributes have been declared private to classes and some volatile fields can be changed via getters and setters.<br>
`Association :` Classes such as catapult hold reference to bird objects. <br>
`Composition :` A level class can create and hold objects to many birds, pigs and blocks. <br>
`Static :` The Catapult class has been declared static, since at a single time only a single level is played and each level only requires one catapult. <br>

## UML
**Canva Link :** https://www.canva.com/design/DAGRUEwezGA/1OZeFpgAq9tISUAlCS0Qiw/edit?utm_content=DAGRUEwezGA&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton <br>

## LDTS_2LEIC0501 - GET COOKED

### THEME

In Get Cooked you will experience true hell in the kitchen.
Alone or with a friend you must prepare dishes to satisfy food orders, that will become faster and increase in number each round.

This project was developed by *Gabriel Magalhães* (*up202405890@fe.up.pt*), *Guilherme Cunha* (*up202405961@fe.up.pt*) and *Pedro Pires* (*up202405967@fe.up.pt*) for LDTS 2025⁄26.

### IMPLEMENTED FEATURES

- **Single Player and Two-Player Modes** - The game can be played by one or two players, in the same device (locally),
  with customized difficulty.
- **Connected Menus** - The user has the capability of browsing through the different menus including in game ones, using
  interactive buttons (ex: Main Menu, Cook Menu, Settings, Escape Menu).
  Once the player(s) lose, a Game Over Menu is prompted, showing the total number of orders fulfilled and options to
  exit the game and to return to the Main Menu.
- **Background Music** - The game has background music with adjustable volume level
  and can be turned on or off in the Settings Menu.
- **Collisions detection** - Collisions between chef's and interactables
  (counters, delivery spots, dispensers, cookwares) are verified.
- **Grab** - When not holding anything, chefs will grab food and plates by pressing the space bar and enter keys, for player 1 and player 2, respectively.
- **Drop** - When holding something, chefs will drop food and plates by pressing the space bar and enter keys, for player 1 and player 2, respectively.
- **Pot cooking** - When the Pot cookware receives a chopped potato or carrot,
  it will generate a potato soup or a carrot soup which, after 4 seconds, can be collected by a chef holding a plate, and delivered.
  Grill and Fryer have similar behaviour.
- **Trash** - When a chef interacts with a trash bin, all the things he was holding get dumped.
- **Delivery Spot** - Chefs can only deliver orders in the delivery spots.
- **Dispensers** - Chefs can get raw foods from dispensers by interacting with them.
- **Cutter** - Chefs can chop raw foods  by interacting with cutters pressing space or enter 5 times.
  CounterCutter and KebabCutter share this behaviour, even though a plate is needed to collect the ready kebab.
- **Counters** - Chefs can place grabables on counters and pick them up from there.
- **Order Generation** - Orders are randomly generated in accordance to the existent foods and interactables in the current kitchen map.
- **Order Matching** - Chefs can only fulfill orders that are currently active. Once an active order is fulfilled, it will leave the active orders queue.
- **Orders by level** - A higher level means more orders per level and a higher frequency of orders (orders come faster).
- **Lives** - The playing session ends when there are no more lives left.
- **Sprites** - The game uses sprites for enhanced game visuals.
- **Game Progression** - The game gets progressively harder as the player(s) advance through levels,
  with more orders to fulfill, with greater diversity and less time to do so.
- **Enhanced Resolution** - The game's resolution adapts to the pc's screen resolution.

<p align="center" justify="center">
  <img src="gifs/menu_navigation.gif"/>
</p>
<p align="center">
  <b><i>Gif 1. Menu Navigation  </i></b>
</p>
<br>
<br />

<p align="center" justify="center">
  <img src="gifs/pot_cooking.gif"/>
</p>
<p align="center">
  <b><i>Gif 2. Pot cooking  </i></b>
</p>
<br>
<br />

<p align="center" justify="center">
  <img src="gifs/order_matching.gif"/>
</p>
<p align="center">
  <b><i>Gif 3. Order Matching  </i></b>
</p>
<br>
<br />

### UML DIAGRAMS

These are the UML diagrams of each package of the project.

<p align="center" justify="center">
  <img src="images/game.png"/>
</p>
<p align="center">
  <b><i>Fig 3. Game UML  </i></b>
</p>
<br>

<p align="center" justify="center">
  <img src="images/sound.png"/>
</p>
<p align="center">
  <b><i>Fig 4. Music UML  </i></b>
</p>
<br>

<p align="center" justify="center">
  <img src="images/gui.png"/>
</p>
<p align="center">
  <b><i>Fig 5. GUI UML  </i></b>
</p>
<br>

<p align="center" justify="center">
  <img src="images/game_states.png"/>
</p>
<p align="center">
  <b><i>Fig 6. States UML  </i></b>
</p>
<br>

<p align="center" justify="center">
  <img src="images/controller.png"/>
</p>
<p align="center">
  <b><i>Fig 7. Controller UML  </i></b>
</p>
<br>

<p align="center" justify="center">
  <img src="images/viewer.png"/>
</p>
<p align="center">
  <b><i>Fig 8. Viewer UML  </i></b>
</p>
<br>

<p align="center" justify="center">
  <img src="images/kitchen.png"/>
</p>
<p align="center">
  <b><i>Fig 9. Kitchen UML  </i></b>
</p>
<br>

<p align="center" justify="center">
  <img src="images/elements.png"/>
</p>
<p align="center">
  <b><i>Fig 10. Elements UML  </i></b>
</p>
<br>

<p align="center" justify="center">
  <img src="images/dish.png"/>
</p>
<p align="center">
  <b><i>Fig 11. Dish UML  </i></b>
</p>
<br>

### DESIGN

#### General Code Structure

#### Model/Viewer/Controller (MVC) Architectural Design

- **Problem in Context**<br>
  When developing software that relies on a user interface, it's crucial to choose a proper structure. The code must stay organized and separated so the Single Responsability Principle isn't broken. Due to this, we applied the architectural pattern **Model/Viewer/Controller**, which is very common when using a GUI.
- **The Pattern**<br>
  For our game, this means that we must implement dedicated parts for each task - handling inputs, rendering graphics and managing game logic. It consists on three different sections:

  - **Model** - responsible for storing the game's data, rules and logic.
  - **Viewer** - in charge of rendering the current state of the model.
  - **Controller** - processes the player's inputs and translates it in to actions that update the model.
- **Implementation**<br>
  The implementation of this Architectural Design can be seen by the following diagram:

  <p align="center" justify="center">
    <img src="images/MVC.png"/>
  </p>
  <p align="center">
    <b><i>Fig 12. MVC Architetural Design  </i></b>
  </p>
  <br>
- **Consequences**<br>
  Using the Model/Viewer/Controller design brings some advantages, such as:

  - Clear separation of responsibilities, acknowledging the Single Responsibility Principle.
  - Easier to implement new features due to the the separation of responsabilities. So that when adding or changing new features does not directly affect others.

#### Game Loop

- **Problem in Context** <br>
  A game requires a continuous cycle of execution to process user input, update states, and render visuals. A critical challenge in this cycle is time consistency: the game simulation must progress at a predictable speed, ensuring a consistent gameplay experience regardless of the user's hardware performance or processor speed.
- **The Pattern** <br>
  The identified solution was the Game Loop design pattern. This pattern decouples the progression of game from user input and processor speed. By separating the game simulation from the rendering cycle, it ensures the game runs at a consistent framerate, regardless of the underlying hardware performance.
- **Implementation** <br>
  In our game, the Game Loop is implemented in the start() method of the Game class, also the entry point of the whole game. It operates at a fixed frame rate of 30 FPS, which translates to approximately 33 milliseconds per frame.
  At the beginning of execution, background music is initialized and begins playing continuously throughout gameplay.

  Each iteration of the loop performs the following steps:

  - Record Frame Start Time: Capture the current system time to measure frame execution duration.
  - Update Game State: Call the step() method of the State class (will be explored later, but essentially handles user input actions and updates game logic and renders elements).
  - Calculate Sleep Duration: Compute the remaining time in the frame by subtracting elapsed execution time from the target frame duration.
  - Sleep if Necessary: If processing completed early, sleep for the remaining time to maintain the consistent 30 FPS target.
    The loop continues as long as a valid game state exists. When the state becomes null (indicating the player has exited), the loop terminates, background music stops, and the GUI closes gracefully.

  <p align="center" justify="center">
    <img src="images/game_loop.png"/>
  </p>
  <p align="center">
    <b><i>Fig 13. Game Loop</i></b>
  </p>
  <br>
- **Consequences** <br>
  As said before, implementing this pattern has a multitude of advantages. However, Thread.sleep() makes the loop reliant on busy-waiting, which is not a very good practice since CPU cycles are being wasted.

#### Factory

- **Problem in Context** <br>
  The Factory Pattern is used throughout our project in two concrete Classes:

  - <p> State: The game comprises multiple distinct execution states such as MainMenuState, SettingsMenuState, and KitchenState (the active gameplay state). To manage these effectively, we needed a design that balanced customization with code reuse.<br>
    While each State requires unique Viewers and Controllers tailored to the specific needs of that State, they all share the same underlying architecture: the Model-Viewer-Controller (MVC).<br>
    The system would benefit from defining a family of related State classes that share this common structure while preserving the extensibility needed to implement distinct behaviors for menus versus gameplay.
    </p>
  - <p> Element: The game features multiple visual components such as Delivery Spot, Trash, and various Food items(Potato, Beef, etc.) that needed to be rendered on screen.<br>
    Every Element demands its own concrete rendering logic, yet all Elements fundamentally represent the same, objects positioned in the Kitchen.<br>
    We needed an approach that allows each Element to determine its own Viewer implementation while maintaining the rendering system consistent and generic enough so that it could be used for all Elements, improving maintainability and extensebility.

  </p>
- **The Pattern**<br>
  The identified solution was the Factory design pattern. This pattern provides a way to create objects without specifying their exact classes. Instead, it defines an abstract creator class with a method for the object creation.
- **Implementation** <br>

  <p>State:
  To model this, a generic class, parameterized with the type T, which represents the specific type of the model associated with that state, serving as the base class for all possible game states, encapsulating the core MVC structure, a Model (T), a Viewer and a Controller.
  The base class defines abstract factory methods getViewer() and getController(), delegating the responsibility of specifying the exact Controller and Viewer to instantiate, trough the overrides in the subclasses.
  </p>
  <p> Element:
  Instead of a generic class it uses an abstract class was implemented, serving as the base class for all visual game elements (Chef, Trash, Carrot,etc.). This class encapsulates the common element proprieties. The base class Element also defines an abstract factory method getViewer(), in charge of specifying the exact ElementViewer to instantiate,
  through overrides in its subclasses.
  </p>
- **Consequences** <br>

  - Polymorphism: This approach enables the Game Loop to treat all states polymorphically (calling the same step() method on any active state);
  - Extensibility: Respects the Open/Closed Principle, since its possible to introduce new States and Elements without breaking the existing code;
  - Single Responsibility: Each concrete State and Element is responsible for knowing their according Viewer and Controller(only for State), rather than having the base class containing the instantition logic for all possible types.
  - Although introducing a factory method added some initial complexity, the game architecture significantly benefited from the structural clarity and decoupling provided by this pattern.

#### Game States

- **Problem in Context**<br>
  Since our game has multiple game states (MainMenu, SettingsMenu, EscapeMenu, etc.), we had to find a way to keep the current state of the game and manage them without creating complex and large conditional statements.
- **The Pattern**<br>
  For this problem, we adopted the **State** pattern. This pattern allows each game state to be represented as a class with its own behaviour, while the main game loop simply delegates actions to the current state. All state classes extend an abstract class called State.
- **Implementation**<br>
  In our code, the State Pattern is centered arround the **Game** class, but most of the actual logic is contained in the states package. This package defines the general **State** class, which acts as a type of template, and the specific implementations: **MainMenuState**, **SettingsMenuState**, **CookMenuState**, **ControlsInfoState**, **EscapeMenuState** and **KitchenState**. Each state handles its own inputs, updates and rendering.

  Diagram that represents the State pattern:

  <p align="center" justify="center">
    <img src="images/game_states.png"/>
  </p>
  <p align="center">
    <b><i>Fig 14. State Pattern  </i></b>
  </p>
  <br>

  State diagram showing how state transitions are proceeded during the game:

  <p align="center" justify="center">
    <img src="images/state_diagram.png"/>
  </p>
  <p align="center">
    <b><i>Fig 15. State Diagram  </i></b>
  </p>
  <br>
  
  Diagram showing all ElementViewers
<p align="center" justify="center">
    <img src="images/element_viewer.png"/>
  </p>
  <p align="center">
    <b><i>Fig 16. State Diagram  </i></b>
  </p>
  <br>

- **Consequences**<br>
  This approach provided several benefits:
  - Keeping the code well organized and respecting the Single Responsibility Principle.
  - Adding new states can be done without modifying existing states. This allows the code to be more modular, following a Open/Closed Principle.
  - Switching between states becomes simple and very consistent, reducing risks of errors.

#### GUI

- **Problem in Context**<br>
  In our game we needed to render the element and handle player inputs using Lanterna. However, Lanterna's API is not very convenient to use. If we relied on it, our code would become very messy and repetitive, making it harder to test or replace it later. So we needed a cleaner layer between our game logic and the library.
- **The pattern**<br>
  We opted to use the **Adapter** pattern. This pattern creates an object that will translate one interface into another. In our case, the game interacts only with the simple GUI interface, while LanternaGUI adapter handles all Lanterna specific details on the backend, with its library.
- **Implementation**<br>
  This implementation can be found in the gui package: the general GUI class and the specific LanternaGUI (adapter).

<p align="center" justify="center">
    <img src="images/gui.png"/>
  </p>
  <p align="center">
    <b><i>Fig 16. GUI  </i></b>
  </p>
  <br>


<p align="center" justify="center">
    <img src="images/adapter.png"/>
  </p>
  <p align="center">
    <b><i>Fig 17. Adapter Pattern  </i></b>
  </p>
  <br>

- **Consequences**<br>
  By using the Adapter pattern, we avoid spreading Lanterna code throughout the project, providing several benefits:

  - Lanterna specific code stays inside the adpater, making sure that other classes don't need to deal with them.
  - Each class focuses only on its own logic, providing the Single Responsibility Pattern.
  - Increases the modularity of code. The GUI is isolated, making it easier to understand and maintain.

#### Facade
- **Problem in Context** <br>
  The implementation of the GUI in LanternaGUI was responsible for handling inputs, creating the terminal screen, drawing elements, and managing the overall rendering process. This led to a complex class with multiple responsibilities, making it harder to maintain and understand.
- **The Pattern** <br>
  To address this complexity, we applied the **Facade** pattern. This pattern provides a simplified interface to a complex subsystem, allowing clients to interact with it without needing to understand its exact implementation details.
- **Implementation** <br>
  To implement this pattern, we changed LanternaGUI to act as a Facade and created 3 new classes FontManager, InputManager and ScreenCreator.<br>
  LanternaGUI delegates screen initialization to ScreenCreator, which encapsulates the complexity of configuring and creating the terminal screen. ScreenCreator handles all the calculations and settings required to set up the screen properly.<br>
  The FontManager class manages all font-related operations, such as setting the correct font size to be used by the Lanterna Terminal and loading and drawing the pixel font used by all menus.<br>
  The keyboard inputs are managed by the InputManager class, which encapsulates the logic for handling key presses and releases. LanternaGUI simply calls inputManager.getNextAction() to retrieve the next user action without needing to know how the input is processed.

<p align="center" justify="center">
    <img src="images/facade.png"/>
  </p>
  <p align="center">
    <b><i>Fig 18. GUI  </i></b>
  </p>
  <br>

- **Consequences** <br>
  By implementing the Facade pattern, we avoid having a large and complex LanternaGUI class, providing several benefits:
    - Simplified Interface: LanternaGUI provides a clean and straightforward interface for the rest of the game to interact with, hiding the complexities of screen creation, font management, and input handling.
    - Centralized Control: The Facade pattern centralizes the control of the GUI subsystem, making it easier to manage and modify without affecting other parts of the code.
    - Improved Maintainability: By separating concerns into distinct classes, the code becomes more modular and easier to maintain. Each class has a clear responsibility, adhering to the Single Responsibility Principle.

#### KNOWN CODE SMELLS
- **ElementViewer** - In the Element class, getViewer() returns an ElementViewer, giving a warning of
raw parameterized use. We tried to mitigate this error by parameterizing the return value but a solution was not found.
- **LoaderKitchenBuilder** - The createinteractables() method is long due to only wanting to read the file once
  (only one loop) and the switch case is quite long given the diversity of interactables.
- **Magic Numbers** - Some classes, especially menu Viewers, contain magic numbers, only meaningful 
to correctly position elements on the screen.

### TESTING
<p align="center" justify="center">
    <img src="images/test_coverage.png"/>
  </p>
  <p align="center">
    <b><i>Fig 19. Test Coverage</i></b>
  </p>
  <br>

<p align="center" justify="center">
    <img src="images/jacoco.png"/>
  </p>
  <p align="center">
    <b><i>Fig 20. Jacoco Test Coverage</i></b>
  </p>
  <br>

<p align="center" justify="center">
    <img src="images/pit_test.png"/>
  </p>
  <p align="center">
    <b><i>Fig 21. Pitest Mutation Testing</i></b>
  </p>
  <br>

### SELF-EVALUATION

The members of the group contributed equally to the project, therefore the work distribution is:

- Gabriel Magalhães: 33.3%
- Guilherme Cunha: 33.3%
- Pedro Pires: 33.3%

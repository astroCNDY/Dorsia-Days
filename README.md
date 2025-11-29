# Dorsia-Days
Dorsia Days: A simple text based game based on the movie American Psycho (2001).

Dorsia Days by Aditya Chandrasekar.
User level description:
Dorsia Days is a text-based adventure game inspired by the foundational structure of Zuul. It is
reimagined through the chilling themes of American Psycho, the iconic horror novel by Bret Easton
Ellis, published in 1991. The game plunges players into a sinister and immersive narrative,
beginning in a blood-soaked bathroom—the site of a gruesome murder that sets the tone for the
experience.
The gameplay is based around, exploration, and solving tasks in an apartment filled with horrors.
Players must navigate the eerie environment while uncovering disturbing clues about the killer's
psyche. To enhance player engagement, Dorsia Days features an inventory system that allows
players to pick up, drop, and manage items. However, there's a catch—inventory management is
bound by a weight limit, forcing players to think strategically about the items they choose to carry.
A detailed inventory view provides constant insight into carried items and their weights.
The Goal
The ultimate objective is to escape the deadly house by unlocking the main door, which requires
finding and using two separate keys. These keys are hidden behind a series of interconnected
puzzles and tasks that challenge players to explore every room, interact with numerous objects, and
piece together the fragments of a chilling story. The game demands a keen eye for catching subtle
details leading to the player's eventual freedom from this apartment of horrors.
The tasks and puzzles
The player needs to discover 2 keys, 2 keys to the 2 locks on the main door to escape to freedom.
One of the keys is conveniently located in the closet, and is easy to discover, the issue, the key is
inside a locked safe, with a missing keypad. With no keypad, password and keys, the player begins
to search the apartment for clues to find both keys. Starting with a diary in the dining room, the
player can get an insight into the psyche of Patrick, the killer. He goes on to write in his diary about
his deteriorating memory. The player now discovers 3 important clues, the killer's phone contains
the PIN code to the safe, the phone's password is the killer's birthday and one of the keys is in the
study. The player now either decides to go for the key in the study or to find the phone in hopes of
opening the safe. Finding the first key is straightforward, although the same cannot be said about
the second key. The phone is found out of battery, the player now scours the house to search for a
charger, which is located in the guest bedroom. Finally charging the phone using the charger, the
password is still unknown. Now begins the search for any clues that would lead to finding the
killer's birthday. An ID card in the master bedroom reveals his birthday to be 13.08.04. Entering
130804 as the password, we can see the safe's password, 6969. Although the safe cannot be opened
just yet, the keypad still needs to be found. After a search through the house, it is discovered in the
storage room. Using the keypad on the safe and entering the correct password opens the safe, giving
the player the second key. If not already collected from the study, the first key can be picked up and
in unison, both keys are used to open the main door. This leads to the player's escape and the game's
completion.
Base tasks:
>The game has at least 6 locations.
This was done using the same format as Zuul, to merely add on new rooms, with new descriptions
and exits.
>There are items in some rooms. Every room can hold any number of items. Some items can
be picked up by the player, others can’t.
This was done by creating an item class, creating a new type, Item. The Item class represents
individual objects in the game world, each defined by a name, weight, and a boolean indicating
whether it can be carried by the player. Then the different items where defined in the main Game
class. With take and drop commands added to command words, items are able to be picked up and
dropped. Later a Use command was added to enable a player to interact with certain objects, the
interaction uses a newly constructed interact class.
>The player can carry some items with him. Every item has a weight. The player can
carry items only up to a certain total weight.
Items that can be carried contribute to the player's inventory, subject to a maximum weight limit.
Each Item also came attached with a double for the weight. With a maximum weight of 10 Kgs,
each item is defined with a certain weight. Using am inventory system, all items’ weights are added
up and it is not possible to pick up any more items after the weight of the inventory exceeds the
maximum weight the player is able to carry.
>The player can win. There has to be some situation that is recognised as the end of
the game where the player is informed that he/she has won.
As detailed about the player is required to find 2 keys through multiple tasks and then use the keys
while in the living room to open the main door. This is implemented through the interact class and
the use command. Which uses stacked if statements to ensure the player meets all requirements to
complete the game. When there requirements are met, the game outputs a statement informing the
player that they have won the game.
>Implement a command “back” that takes you back to the last room you’ve been in.
The “back” command should keep track of every move made, allowing the player to
eventually return to its starting room.
To implement the "back" command, a stack was implemented to keep track of the player's
movement history. Each time the player enters a new room, the current room is pushed onto the
stack. A back command was added to the acceptable commands. When the "back" command is
executed, the most recent room is popped from the stack, and the player is returned to that room.
This approach ensures that the player can backtrack through all previous rooms in reverse order,
Allowing them to return to their starting point if desired.
>Add at least four new commands (in addition to those that were present in the code
you got from us).
7 extra commands where added apart from the base functions. "go", "quit", "help", "take", "drop",
"inventory", "use", "back", “talk_to" and “give”. The 4 extra commands added as part of the base
tasks are "take", "drop", “inventory" and “use”. Take, drop and inventory were added as a part of
the implementation of items, and have been explained above. “Use” was implemented through the
interaction class. With every item returning it’s respective function form the game class to the
interact class where it processed the different interactions with each item.
Challenge tasks
>Extend the parser to recognise three-word commands. You could, for example,
have a command
This was done by adding a new string, third word to the parser. Extending the stacked if statement
to accept another word. This was to be used to interact with NPCs, while detailing the command,
NPC and item to be given to the NPC. The third word was then also added as a function to be able
to return the third word of the command to the Game class.
>Add a magic transporter room – every time you enter it you are transported to a
random room in your game.
To implement this functionality I stored all the rooms in an array. Using a random generator to pick
a random room when the player enters the transported room. This random room was then fed to
nextRoom, which transported the player to this new random room.
>Add characters to your game. Characters are people or animals or monsters – anything
that moves, really. Characters are also in rooms (like the player and the items).
Unlike items, characters can move around by themselves.
To implement NPCs in the game I created a new Npc class. This class detailed all the properties of
an NPC, their name, dialogue, items, and the rooms they were permitted in. Then a similar system
to the random room transporter was implemented to transport NPCs into a random permitted room
each time the player enters a new room. The characters where then created in the main game class.
Coupling
In my project, I paid close attention to minimising the dependencies between different classes,
which helps reduce coupling. For example, the Game class does not directly handle items or NPCs,
but instead interacts with them through separate classes like Item, Npc, and Parser. This modular
approach makes it easier to update or modify individual components without impacting others. By
structuring the system this way, the game becomes more flexible and easier to maintain, as changes
in one class, such as Npc, won’t require significant changes in the Game class or elsewhere.
Modularisation also aids abstraction.
Cohesion
I ensured that each class in my project has a clear and focused responsibility, which results in high
cohesion. The Room class is dedicated solely to managing room-related tasks, such as storing
descriptions, exits, items, and NPCs. Every method in this class contributes to this single
responsibility. This is also a depiction of encapsulation. Likewise, the Npc class is tightly focused
on managing NPC-specific tasks, like dialogue, room assignments, and holding items. By
maintaining this level of focus, the project becomes easier to understand and modify.
Responsibility-Driven Design
Throughout the project, I followed responsibility-driven design principles, making sure each class
has well-defined and clear responsibilities. For example, the Parser class is responsible for
interpreting user input and extracting commands, while the Game class delegates the responsibility
of managing interactions, like using items or talking to NPCs, to the Interact class. This separation
of concerns ensures that each class can evolve independently without overloading any single part of
the system with too many responsibilities, keeping the code clean and easy to follow.
Maintainability
To make the game more maintainable, I structured the code in a modular way. This allows me to
easily modify or extend the game without disrupting the overall logic. For instance, adding new
rooms or NPCs is straightforward because the Room and Npc classes are separate from the core
gameplay mechanics. This modular structure, combined with the clear separation of concerns,
makes the game flexible and easy to update, whether I’m adding new features or fixing bugs.
Game walk through
A walk through of the tasks needed to be completed are mentioned as a part of the game
description, below are all the functions needed to be inputted in order to complete and win the
game:
1. Go south
2. Go east
3. Take diary
4. Use diary
5. Go south
6. Go east
7. Take key
8. Go west
9. Go north
10. Go west
11. Go north
12. Take phone
13. Go south
14. Go east
15. Go east
16. Take charger
17. Use charger
18. Go west
19. Go west
20. Take id_card
21. Use id_card
22. Use phone
23. Input 130804
24. Go east
25. Go north
26. Go east
27. Take safe_keypad
28. Go west
29. Go south
30. Go west
31. Go west
32. Use keypad
33. Input 6969
34. Go east
35. Go south
36. Use key

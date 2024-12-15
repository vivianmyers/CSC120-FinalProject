# CSC120-FinalProject

## Deliverables:

- Your final codebase
- Your revised annotated architecture diagram
- Design justification (including a brief discussion of at least one alternative you considered)
- A map of your game's layout (if applicable)
- `cheatsheet.md`
- Completed `rubric.md`

## Design Justification

We considered creating interface where the game tells you what options you have and you type a number (ex. Would you like to 1. Go North 2. Grab item 3. Fight character) instead of a text based, typing interface. We decided against this because we felt it made the game boring/less engaging, and took away independence from the player. While this design may have made the game easier and more straightforward to play, it lets the user to experiment, learning what they can do in the game to win.

## Additional Reflection Questions

- What was your **overall approach** to tackling this project?

After we figured out what classes we needed, we started out by figuring out how to handle user input and connect that input to certain methods (e.g. user types grab banana, program executes grab method). From there, we added increasingly complex interactions, items, places, and methods. With a basic skeleton of the project formed initially, it was easier to add details later.

- What **new thing(s)** did you learn / figure out in completing this project?

We learned about version control and collaborating with other people through git and github. It was a first time to work on coding in a group for all of us. We also learned about how to approach a large/intimidating project one step at a time without getting overwhelmed.

- Is there anything that you wish you had **implemented differently**?

We wish that we had used more abstraction. For example, making more smaller methods instead of large blocks on complicated, hard-to-read code. This could have been done in Game Main (like using a setup() method).

- If you had **unlimited time**, what additional features would you implement?

We would implement more comprehensive text-understanding. Right now, our game cannot understand punctuation (entering 'grab banana.' is not valid), so we may want to implement a way to handle punctuation. We would also expand the size of our map, and add more npcs/items/places, as well as interactions/puzzles.

- What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?

The most helpful feedback was from Milka (a 110 student) who recommended us to add some sort of extra function to allow users to access map, so it could help to make our game easier.

- If you could go back in time and give your past self some **advice** about this project, what hints would you give?

We would recommend thinking through the way you want the project to work before starting to code. For example, how exactly is the game loop going to play out? Or, where is each interaction going to be handled, game main or in a specific class? Planning the structure of the game in advance can help you understand where and what to implement when the time comes. It would also help you anticipate the limits and boundaries before facing them.

- _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.

Vivian - Working in a team helped open my mind to other ideas/ways of doing things that I never would have thought of.
Ruby - Our team's open communication made the project better because of so many different inputs and creative ideas to incorporate in the project.
Sophia- Working in a team allowed me to focus on parts of the project I felt more confident on, while learning from other people about things I didn't understand as well.
Evelyn - When working in a team, I found that I learned a lot more about Java as a language. For example, I was familiar with switch statements before, but now I know how to use one!

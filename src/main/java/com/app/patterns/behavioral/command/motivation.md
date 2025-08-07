Imagine you're developing a basic text editor with button for bold, italic and underline text formatting.

Without the command pattern, the button directly interact with the TextEditor class, and you'd end up
hardcoding behavior into the UI classes, making them tightly coupled.
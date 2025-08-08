Consider an application that needs to support multiple UI themes (e.g., Windows, macOS). Each
theme has its own set of UI components such as buttons, scrollbars, and windows. The challenge
is to create an architecture that allows switching between these themes without changing the 
client code that uses the UI components.

Without the Abstract Factory Pattern, the client code would be tightly coupled with the concrete
implementations of buttons, scrollbars, etc., and switching between themes would require modifying
the client code.
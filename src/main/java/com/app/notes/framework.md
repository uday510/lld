# A Framework for the OOD Interview

Having a clear framework for the OOD interview is more important than
many realize. Without structure, the interview can feel disorganized and
difficult for you and the interviewer to follow.

This chapter introduces a four-step framework to help you navigate
open-ended OOD discussions with confidence. It guides you in
transforming abstract requirements into concrete architecture or code,
while showcasing your ability to make thoughtful trade-offs under
real-world constraints.

Keep in mind that OOD interviews are highly versatile, and this
framework is not foolproof. The structure and expectations can vary
depending on the interviewer's preferences, so you'll need to be
flexible and adapt accordingly.

Before we dive into the framework itself, let's first explore the common
types of OOD interviews you might encounter.

------------------------------------------------------------------------

# Different Types of OOD Interviews

OOD interviews typically emphasize one of three areas, each with a
preferred deliverable format. Early in the interview, gauge the
interviewer's expectations by asking:

> "Are we focusing on high-level class diagram, code structure, or a
> full implementation?"

This question helps you tailor your approach and ensures you deliver
what's needed within the time constraints (usually 45--60 minutes). The
three primary deliverable formats are:

-   **UML Diagrams:** UML diagrams were once the standard and are still
    commonly used to visually represent system designs. A UML class
    diagram helps illustrate the relationships between classes,
    including their attributes, methods, and interactions.
-   **Code Skeleton:** This approach has become increasingly popular in
    modern interviews, as it more closely resembles real-world software
    development. It allows interviewers to explore implementation
    details as needed. In this style, you define the structure of your
    design directly in code using appropriate class and method
    declarations, while leaving method bodies unimplemented.
-   **Working Code:** With the renewed emphasis on OOD interviews,
    interviewers sometimes request fully functional, bug-free
    implementations. They may also ask for test cases. This approach
    offers the highest fidelity to real industry development.

The expected deliverable often depends on the interviewer's preference
and the time constraints. If you're asked to produce working code, don't
be intimidated. Interviewers typically simplify the problem to ensure
it's manageable within the allotted time.

> 💡 **Tip:** In an OOD interview, the journey is just as important as
> the final deliverable. Coding in silence doesn't make a strong
> impression. Instead, share thoughtful insights throughout the process
> to demonstrate your design thinking and communication skills.

------------------------------------------------------------------------

# A Guiding Framework for the OOD Interview

Here are the four steps we recommend:

![OOD Interview Framework](ood_framework.png)

### Step 1: Requirements Gathering (5--10 minutes)

Begin by thoroughly analyzing the problem statement and identifying key
functional and non-functional requirements. Ask targeted questions to
resolve ambiguities, establish realistic constraints, and confirm any
assumptions. This ensures that you and the interviewer share a clear
understanding of the scope and priorities.

### Step 2: Identify Core Objects (3--7 minutes)

With requirements clarified, select a primary use case and walk through
it step-by-step to identify core objects and their interactions. A
practical approach is to map nouns in the requirements to objects (e.g.,
*parking lot*, *vehicle*, *ticket*) and verbs to methods (e.g., *assign
spot*, *calculate fee*). This creates a naive but relevant initial
design, serving as a foundation for refinement.

> **Note:** While use case diagrams can help visualize workflows and
> clarify interactions between objects, they are optional for most OOD
> interviews.

### Step 3: Design Class Diagram and Code (20--25 minutes)

Now that the core objects and their roles are clear, it's time to
develop the class diagram and demonstrate how it translates into code.

-   **Top-down Approach:** First, identify high-level components or
    parent classes, then refine their attributes and methods.\
-   **Bottom-up Approach:** Define concrete classes first (attributes,
    methods) and build relationships from there.

Define how the objects will interact and assign responsibilities in a
way that follows key design principles such as **low coupling** and
**high cohesion**.

Once the design is in place, implement the core classes to demonstrate
how the structure translates into code. In some cases, a complete
implementation isn't necessary. Focus on the essential parts unless the
interviewer requests otherwise.

> **Note:** The primary focus of an OOD interview is design and code
> quality. But you should not ignore time and space complexity and
> efficiency. Strong class and relationship modeling includes selecting
> appropriate data structures for performance.

### Step 4: Deep Dive Topics (10--15 minutes, optional)

After validating your design with key use cases, refine it to handle
edge cases and resolve any inconsistencies. Interviewers may ask
follow-up questions to assess your understanding, challenge your design
decisions, or explore more advanced aspects of your solution.

------------------------------------------------------------------------

# A Step-by-Step Example

To better understand how an OOD interview unfolds, let's walk through a
realistic example from start to finish. This section shows how an
interview might naturally unfold, from a vague problem description to a
structured and thoughtful solution.

## Step 1: Requirements Gathering

Anne, a software engineer, is interviewing for a backend role. The interviewer, Beth, asks her to design a parking lot system, giving her **45 minutes** to present the design.

Anne starts by digesting the problem, asking a few clarification questions to create a shared understanding of the scope. She quickly learns that the parking lot needs to support **different vehicle types**, **reserved spaces**, and **accurate fee calculation**.

**Sample Dialogue**

- **Anne:** What types of vehicles should the parking lot support? Are we considering cars and motorcycles?
- **Beth:** Yes, and also buses. Each bus takes up three spots.
- **Anne:** Should we design different types of parking spaces for the different types of vehicles?
- **Beth:** Yes, you can decide how to design that.

Anne continues to ask thoughtful questions to clearly define the scope and constraints. She avoids common mistakes such as:

- Asking overly obvious or excessively detailed questions.
- Repeating previously answered questions, which could signal inattentiveness.
- Introducing irrelevant or overly complex topics that distract from the main problem.

### Tips for Effective Requirements Gathering

The first few minutes of an OOD interview are critical. Here are a few tips for effective requirements gathering.

#### Focus on the Most Essential Requirements
Start by focusing on the most essential requirements and confirming that both you and the interviewer are aligned on the problem’s scope. Once Anne has a clear understanding of the task, she restates and lists down the core functionality to validate her interpretation:

> **Anne:** The system will support parking and unparking vehicles, track space availability, and calculate fees based on vehicle type and parking duration. It should also support three types of vehicles.

#### Use Examples to Clarify Scope
Rather than relying solely on stating the requirements, Anne uses concrete examples to ground the discussion and expose edge cases. She presents one simple scenario and one more complex one to fully explore the system’s expected behavior.

**Simple Case**
> **Anne:** Let’s consider a basic scenario: a car enters the lot, finds an available space, parks, and leaves after two hours. The system should allocate a space, track the duration, and calculate the fee.

**Complex Case**
> **Anne:** Now, imagine a bus with a reservation entering the lot. Some spaces are too small or reserved for other types. The system needs to find the most suitable available space while optimizing future availability.

By walking through these contrasting examples, Anne clarifies ambiguities and ensures both the interviewer and she are on the same page.

With a solid grasp of the core problem and its constraints, she’s now ready to move on to identifying the building blocks (classes, methods, and attributes) that will form the backbone of her design.

---

## Step 2: Identify Core Objects

To kick off the design, Anne walks through a key use case: **parking a car**. As she steps through the process, she identifies relevant objects by paying attention to **nouns** and **verbs** in the requirements. This leads her to a simple but effective initial design.

> **Anne:** When a car enters, the system will find an available space of the appropriate size, assign it, generate a ticket, and mark the space as occupied.

By focusing on two or three representative use cases, Anne allows the requirements to naturally guide the design. She avoids trying to model everything up front, prioritizing clarity and relevance over completeness.

As she works through the use case, she keeps the design focused and minimal. For example:

- **Beth:** How would you handle edge cases like a full lot?
- **Anne:** Good question. If no spaces are available, the system should return an appropriate message. I’ll refine this logic once I have the complete design.

When more complex topics arise, Anne acknowledges them without getting sidetracked:

> **Anne:** Let’s finish the core use case first. If time permits, I’ll extend the design to support configurable pricing, perhaps using a strategy pattern.

Anne’s goal during this phase is to identify the **core objects** and define their **responsibilities** clearly.

![Core Objects of Parking Lot (placeholder)](core_objects_parking_lot.png)  
*Image: Simplified class diagram with four classes — ParkingLot, ParkingSpot, Vehicle, Ticket — as boxes labeled with class names.*

> **Optional:** A simple use case diagram can help visualize workflows and clarify object interactions. Ask the interviewer if they’d like to see one.

---

## Step 3: Class Design and Code

Once the core objects are identified, Anne begins defining classes, sketching relationships, and implementing a basic structure in code.

### Defining the Classes

She starts with foundational components that form the system’s backbone. In the parking lot example, she focuses on: **ParkingLot**, **ParkingSpot**, **Vehicle**, and **Ticket**.

> **Anne:** The key entities are ParkingLot, ParkingSpot, Vehicle, and Ticket. Each space has attributes like size and availability, and each vehicle has a type. A Ticket will track the entry time and calculate the fee.

She then sketches a UML diagram to show relationships:

![Class diagram of Parking Lot (placeholder)](class_diagram_parking_lot.png)  
*Image: ParkingLot contains many ParkingSpots (composition). Ticket associates with ParkingSpot and Vehicle. ParkingSpot can hold a Vehicle.*

**Relationships & Responsibilities**

- **ParkingLot** contains multiple **ParkingSpots**.
- Each **ParkingSpot** can hold one **Vehicle**.
- A **Ticket** links a **Vehicle** to a **ParkingSpot** and tracks time.

Anne ensures each class is well-defined and adheres to OOP principles like **encapsulation**, **single responsibility**, and **inheritance**:

- **ParkingLot** manages the overall structure, including tracking spaces and handling vehicle flow.
- **ParkingSpot** handles its own availability status and the vehicle parked in it.
- **Vehicle** has a base type with subclasses like **Car**, **Motorcycle**, **Bus** for differing requirements and fee calculations.

She avoids overcomplicating the model and focuses only on objects that carry meaningful behavior.

### Code Implementation (Skeleton)

With the design in place, Anne writes class definitions and adds relevant attributes and method signatures. For example:

- **ParkingLot:** manages a collection of spots and handles assignments.
- **ParkingSpot:** tracks size, availability, and assigned vehicle.
- **Ticket:** stores entry time and calculates the fee.

As she codes, Anne explains her rationale to the interviewer, ensuring her thought process remains transparent. She also validates the design as she progresses:

> **Anne:** This setup covers the main use cases we discussed. I’ll check if it also holds up under edge conditions.

By staying focused and grounding her choices in solid OOD principles, Anne builds a practical and extensible design.

---

## Step 4: Deep Dive Topics

At this point, Anne’s design is nearly complete, whether in the form of a detailed UML diagram or a coherent code skeleton. The final step is refinement: take a step back to examine the design from a high level, address edge cases, and consider improvements.

### Addressing Gaps
- **Full lot handling** and grouped spaces for buses.
- **Validation** for invalid tickets during checkout.
- **Special logic** for larger vehicles or reservations.

### Summarizing the Design
> **Anne:** This design supports key use cases, scales to different vehicle types, and includes logic for core edge cases. If time allows, I’d explore enhancements like dynamic pricing based on time of day.

### Making Thoughtful Trade-offs
Refinements often involve trade-offs in areas like **inheritance vs. composition**, **data modeling**, or **design patterns**. The goal isn’t just to choose the “right” answer, but to clearly explain **why** the decision makes sense. Also know when to say “this is good enough” and move on.

---

## When Things Don’t Go as Planned

Real interviews rarely follow a perfectly linear path. You might face curveballs such as shifting requirements, unexpected deep dives, or even a disengaged interviewer. Stay adaptable, communicate clearly, and remain focused on delivering a thoughtful design.

### 1) Shifting Requirements and Expanding Scope
**What to do:**
- Acknowledge the new requirement and briefly assess its impact.
- Explain how your current design can accommodate the change, or what trade-offs might be required.
- Be flexible but strategic; adapt without overhauling unnecessarily.
- If scope keeps expanding, it’s likely a test of **flexibility** and **scalability**.
- Re-evaluate if the new requirement exposes a blind spot.

### 2) Being Pulled into a Deep Dive Too Early
**What to do:**
- Set expectations early: “I’ll start with a high-level overview, then we can dive deeper where needed.”
- Periodically check in on time and structure.
- If stuck, say: “Here’s the direction I’d take for now. Completing the rest will give context to refine this.”
- Circle back later to show follow-through.
- Avoid premature optimization or over-specificity early.

### 3) Struggling to Communicate Your Thought Process
**What to do:**
- Begin with a high-level summary before class-level details.
- Use visuals (class diagram or code skeleton).
- Emphasize **why** over **what**.
- Choose intuitive names for classes/methods.

### 4) Dealing with a Disengaged Interviewer
**What to do:**
- Politely ask for feedback to re-engage.
- If that fails, let the work speak for itself (clean diagrams or runnable code).
- Executing working code can demonstrate competence effectively.

### 5) When Your Design Decisions Are Challenged
**What to do:**
- Stay calm and explain your reasoning.
- Use concrete examples/analogies.
- Reference trade-offs (time complexity, extensibility, maintainability).
- Offer alternatives and justify your choice.
- Ask for clarification if needed.

### 6) Encountering Unfamiliar Terminology
**What to do:**
- Ask politely for clarification.
- Or align with partial understanding: “My understanding is X; how does it differ here?”

### 7) Struggling with the Right Level of Abstraction
**What to do:**
- Start broad; layer in details as needed.
- Ask for preferred depth: high-level vs. detailed class breakdown.
- Be ready to zoom in/out based on cues.
- Emphasize the problem’s natural complexity (abstraction, data modeling, behavior logic).

### 8) Addressing Concurrency in OOD Interviews
**What to do (at a high level):**
- Describe concurrency strategy (e.g., locking, optimistic locking).
- Show a brief snippet or pseudocode if time allows.
- Keep it simple and focused on preventing race conditions.
- If using Java: be familiar with `Thread`, `Runnable`, `Callable`, `ExecutorService`.

---

## Final Thoughts

The OOD interview is about more than technical skill. It’s about **clear thinking under pressure**, **effective communication**, and **applying OOP principles** to build maintainable, scalable solutions.

By breaking the process into manageable steps and learning how to navigate unexpected challenges, you’ll be well-prepared to handle even the most unpredictable interviews. With practice and the right mindset, you can turn curveballs into opportunities and leave a lasting impression.

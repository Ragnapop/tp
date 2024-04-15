---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# AronaPro Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

AronaPro is based on the AddressBook Level 3(AB3) sample project created by the [SE_EDU initiative](https://se-education.org) under the CS2103T module in NUS School of Computing.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S2-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S2-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete id/E0123456`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S2-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `ScheduleListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S2-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S2-CS2103T-T15-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S2-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete id/E0123456")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete E0123456` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S2-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Group` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Group` object per unique group, instead of each `Person` needing their own `Group` objects. Additionally, a `Remark` can also be encapsulated in the `Schedule` class to ensure more OOP-ness while allowing each `Person` to have multiple `Schedules` with its corresponding `Remark`<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S2-CS2103T-T15-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### `Add` feature

`Add` for a person can be added using the `add` command. The `AddCommand` class is responsible for handling the addition of a person. This command is implemented through `AddCommand` which extends the `Command` class.

A new `Person` can be added by specifying `NUSID`, `name`, `phone`, `email`, `tags` and optional `group`.

<box type="info" seamless>

**Note:** There can be 0 or more optional `group`.

</box>

#### Implementation

Given below is an example usage scenario and how the `AddCommand` mechanism behaves at each step.

Step 1. The user executes `add` command.

Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `AddCommandParser`.

Step 3. `AddCommandParser` will call `parse` which create instances of objects for each of the fields and return an instance of `AddCommand`.

Step 4. The `LogicManager` calls the `execute` method in `AddCommand`.

Step 5. The `execute` method in `AddCommand` executes and calls `Model#addPerson()` to add the person to the address book.

Step 6. Success message is printed onto the results display to notify user.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#addPerson()` and the person will not be added to the address book.

</box>

The following sequence diagram shows how an add operation goes through the `Logic` component:

<puml src="diagrams/AddSequenceDiagram.puml" alt="AddSequenceDiagram" />

The following activity diagram summarizes what happens when a user inputs a schedule command:

<puml src="diagrams/AddDiagram.puml" width="250" />

#### Design considerations:

**How add executes**

* User inputs an `add` command with `NUSID`, `name`, `phone`, `email`, `tags` and optional `group` fields. The inputs are parsed and a `AddCommand` is created.
* The instances of the relevant fields are created and the person is added to the model.

**Alternative considerations**  

* **Alternative 1 (current choice):** Create instances of objects for each of the fields and add the person to the model.
    * Pros: Allow for each field to be validated before adding the person.
    * Cons: Additional checks are required.

### `Edit` feature

#### Implementation

`Edit` on a person can be done using the `edit` command. The `EditCommand` class is responsible for handling the editing of a person's information. This command is implemented through `EditCommand` which extend the `Command` class.

A new `Edit` can be added by specifying a compulsory `NUSID`, while `NAME`, `PHONE_NUMBER`, `EMAIL`, `TAG` and `GROUP` are optional fields but the user needs to enter at least 1 of these optional fields.

<box type="info" seamless>

**Note:** Existing values will be replaced by and updated to the new input values.

</box>

Given below is an example usage scenario and how the `EditCommand` mechanism behaves at each step.

Step 1. The user executes `Edit` command.

Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `EditCommandParser`.

Step 3. `EditCommandParser` will call `parse` which create instances of objects for each of the fields and return an instance of `EditCommand`.

Step 4. The `LogicManager` calls the `execute` method in `EditCommand`.

Step 5. The `execute` method in `EditCommand` executes and calls `Model#getFilteredPersonListWithNusId()` to get a list of person in the address book and filter to find the relevant person with the given `NUSID`.

Step 6. `Model#setPerson()` is called to update the contact information for that person.

Step 7. `Model#updateFilteredPersonList()` is called to update the person list.

Step 8. Success message is printed onto the results display to notify user.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#setPerson()` and the contact information will not be updated for that person.

</box>

The following sequence diagram shows how a schedule operation goes through the `Logic` component:

<puml src="diagrams/EditSequenceDiagram.puml" alt="EditSequenceDiagram" />

The following activity diagram summarizes what happens when a user inputs an Edit command:

<puml src="diagrams/EditDiagram.puml" width="250" />

#### Design considerations:

**How edit executes**

* User inputs a `Edit` command with `NUSID`, and at least 1 of these optional fields: `NAME`, `PHONE_NUMBER`, `EMAIL`, `TAG` and `GROUP`. The inputs are parsed and a `EditCommand` is created.
* A list of persons is retrieved from `model` and the relevant person is found by matching `NUSID`.
* The relevant fields are updated for the person and the person is set back into the model.


**Alternative considerations**

* **Alternative 1 (current choice):** Create instances of objects for each of the fields and edits the person's contact information to the model.
    * Pros: Allow for each field to be validated before editing the person.
    * Cons: Additional checks are required.
    

### `Schedule` feature

#### Implementation

`Schedule` for a person can be added or removed using the `schedule` command. The `ScheduleCommand` class is responsible for handling the scheduling of events for a person. This command is implemented through `ScheduleCommand` which extends the `Command` class.

A new `Schedule` can be added by specifying `NUSID`, `schedule` and `remark`. If the `schedule` and `remark` prefixes are not specified, the schedule will be removed instead.

<box type="info" seamless>

**Note:** `schedule` and `remark` are either both present or absent.

</box>

Given below is an example usage scenario and how the `ScheduleCommand` mechanism behaves at each step.

Step 1. The user executes `Schedule` command.

Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `ScheduleCommandParser`.

Step 3. `ScheduleCommandParser` will call `parse` which create instances of objects for each of the fields and return an instance of `ScheduleCommand`.

Step 4. The `LogicManager` calls the `execute` method in `ScheduleCommand`.

Step 5. The `execute` method in `ScheduleCommand` executes and calls `Model#getFilteredPersonList()` to get a list of person in the address book and filter to find the relevant person with the given `NUSID`. 

Step 6. `Model#setPerson()` is called to update the schedule for that person.

Step 7. Success message is printed onto the results display to notify user.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#setPerson()` and the schedule will not be updated for that person.

</box>

The following sequence diagram shows how a schedule operation goes through the `Logic` component:

<puml src="diagrams/ScheduleSequenceDiagram.puml" alt="ScheduleSequenceDiagram" />

The following activity diagram summarizes what happens when a user inputs a schedule command:

<puml src="diagrams/ScheduleDiagram.puml" width="250" />

#### Design considerations:

**How schedule executes**

* User inputs a `Schedule` command with `NUSID`, `schedule` and  `remark`. The inputs are parsed and a `ScheduleCommand` is created.
* A list of persons is retrieved from `model` and the relevant person is found by matching `NUSID`.
* The relevant fields are updated for the person and the person is set back into the model.

**Why is it implemented this way?**

* The functionality of adding and removing schedule is similar to the `EditCommand`. Both require changes in the `Person` object.
* Hence, the approach is similar to how `edit` command works.

**Alternative considerations**

* **Alternative 1 (current choice):** Set the schedule for the person by indicating `schedule` and `remark`, otherwise remove schedule.
    * Pros: Easy to implement.
    * Cons: Additional checks are required to check if it is an add or remove schedule command.

* **Alternative 2:** Introduce add schedule and remove schedule command as separate commands.
    * Pros: There is usage of Single Responsibility Principle.
    * Cons: We must ensure that the implementation of each individual command are correct.

* **Alternative 3:** Since schedule and edit commands are similar, we could consider adding a generic class which both extend from.
    * Pros: It follows the DRY principle.
    * Cons: We must ensure that the implementation of each individual command are correct.


### `Find` feature

A `Person` has many details one may query for, this command searches for contacts that matches all the given details.
Currently, this command supports finding by `NUSID`, `name`, `phone`, `email`, `group`s, `tag` fields.

<box type="info" seamless>

**Note:** `find` requires at least one field mentioned above to be an input

</box>

Given below is an example usage scenario and how the `find` mechanism behaves at each step.

Step 1. The user executes `find` command.

Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `FindCommandParser`.

Step 3. `FindCommandParser` will call `parse` which create instances of objects for each of the fields and return an instance of `FindCommand`.

Step 4. The `LogicManager` calls the `execute` method in `FindCommand`.

Step 5. The `execute` method in `FindCommand` executes and finds the relevant person(s) with the given fields.

Step 6. `Model#updateFilteredPersonList()` is called to update the list of persons displayed in AronaPro.

Step 7. Success message is printed onto the results display to notify user.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#updateFilteredPersonList()` and the list of persons displayed remains the same.
**Note:** If a command finds no person, it will display an empty list and not an error message.

</box>

The following sequence diagram shows how a find operation goes through the `Logic` component:

<puml src="diagrams/FindSequenceDiagram.puml" alt="FindSequenceDiagram">

The following activity diagram summarizes what happens when a user inputs a `find` command:

<puml src="diagrams/FindActivityDiagram.puml" alt="FindActivityDiagram">

#### Design considerations:

**How find executes**

* User inputs a `find` command with at least one of the fields `NUSID`, `name`, `phone`, `email`, `group` or `tag`. The inputs are parsed and a `FindCommand` is created.
* Each of these fields are made into Java `Predicates` which checks if each field's input matches any person's field (Choice of matching can be changed flexibly in each field's corresponding `Predicate` class) in AronaPro.
* If any field was not required by the user, a special string (not possible for the user to type when using the add or edit commands) is used to default the `Predicate` to True.
* A list of persons is created by chaining these `Predicates` using logical `AND`. A list of relevant person(s) are found.
* The relevant persons are used to update the person list which the model displays.

**Why is it implemented this way?**

* The functionality of find is advertised as finding people that matches ALL the supplemented fields. As such logical AND search is relevant to our case.
* Use of predicates is also easily extendable, requiring future programmers to simply create a new `Predicate` for new fields and chaining it with the existing predicates
* The use of a special string to denote a non-specified field, while rudimentary, avoids the hassle required to juggle Java `Optional`s and less transparent Functional Programming paradigms.

**Alternative considerations**

* **Alternative 1 (current choice):** Set non-required fields to a special string that makes a field match everyone, otherwise filter based on the input.
    * Pros: Easy to implement.
    * Cons: If the user is able to enter this special string in `add` or `edit` commands, it could result in unexpected behaviour.

* **Alternative 2:** Introduce Java `Optional`s to determine which fields are required.
    * Pros: There is usage of Single Responsibility Principle. (Current implementation has `Predicate` implicitly handling added responsibility of checking optionality)
    * Cons: Harder to debug when using Functional Programming paradigms while passing results across classes.

### `Pin` feature

`Pin` for a person can be done using the `pin` command. The `PinCommand` class is responsible for handling the addition of a person. This command is implemented through `PinCommand` which extend the `Command` class.

A `Person` can be pinned by specifying `NUSID`.

#### Implementation

Given below is an example usage scenario and how the `PinCommand` mechanism behaves at each step.

Step 1. The user executes `pin` command.

Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `PinCommandParser`.

Step 3. `PinCommandParser` will call `parse` which create instances of objects for each of the fields and return an instance of `PinCommand`.

Step 4. The `LogicManager` calls the `execute` method in `PinCommand`.

Step 5. The `execute` method in `PinCommand` executes and calls `Model#pinPerson()` to add the person to the address book.

Step 6. Success message is printed onto the results display to notify user.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#pinPerson()` and the person will not be added to the address book.

</box>

The following sequence diagram shows how a pin operation goes through the `Logic` component:

<puml src="diagrams/PinSequenceDiagram.puml" alt="PinSequenceDiagram" />

The following activity diagram summarizes what happens when a user inputs a pin command:

<puml src="diagrams/PinDiagram.puml" width="250" />

#### Design considerations:

**How Pin executes**

* User inputs a `pin` command with `NUSID`.The inputs are parsed and a `PinCommand` is created.
* The instances of the relevant fields are created and the person is added to the model.


### `Group` feature

`Group` for a person can be added using the `group` command. The `GroupCommand` class is responsible for handling the grouping and the tagging of a person. This command is implemented through `GroupCommand` which extends the `Command` class.

A new `Person` can be grouped by specifying `NUSID` and either `group` and/or `tag`.

<box type="info" seamless>

**Note:** There needs to be at least one `group` and/or `tag` keyword.
**Note:** More than one `NUSID` keyword can be used if grouping more than one person at once.

</box>

#### Implementation

Given below is an example usage scenario and how the `GroupCommand` mechanism behaves at each step.

Step 1. The user executes `group` command with `NUSID`, `group` and  `tag`.

Step 2. The `AddressBookParser` will call `parseCommand` on the user's input string and return an instance of `GroupCommandParser`.

Step 3. `GroupCommandParser` will call `parse` which create instances of objects for each of the fields and return an instance of `GroupCommand`.

Step 4. The `LogicManager` calls the `execute` method in `GroupCommand`.

Step 5. The `execute` method in `GroupCommand` executes and creates a list of Persons `personToGroup`. It then calls `Model#setPerson()` on all Persons in the list to modify the group and tag fields of the Persons in the address book.

Step 6. Success message is printed onto the results display to notify user.

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#setPerson()` and the Persons will not be grouped in the address book.

</box>

The following sequence diagram shows how a group operation goes through the `Logic` component:

<puml src="diagrams/AddSequenceDiagram.puml" alt="GroupSequenceDiagram" />


#### Design considerations:

**How group executes**

* User inputs an `group` command with `NUSID` and either `tags` and/or `group` fields. The inputs are parsed and a `GroupCommand` is created.
* The instances of the relevant fields are created and the person(s) is/are grouped in the model.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Tech-savvy university Computer Science teaching assistants and professors
* Manages an array of students, professors and students' contacts
* Appreciates an organised approach to query, and manage contacts with CLI
* Prefers desktop apps over other types
* Prefers typing to mouse interactions
* Someone who is reasonably comfortable using CLI application

**Value proposition**: Manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                                       | I want to …​                                                 | So that I can…​                                                        |
|----------|-------------------------------------------------------------------------------|--------------------------------------------------------------|------------------------------------------------------------------------|
| `* * *`  | new user                                                                      | see usage instructions                                       | refer to instructions when I forget how to use the App                 |
| `* * *`  | Teaching Assistant                                                            | add a new student, prof, TA                                  | know how to contact them                                               |
| `* * *`  | user                                                                          | delete a person                                              | remove entries that I no longer need                                   |
| `* * *`  | Teaching Assistant                                                            | find a student by name or class                              | locate details of persons without having to go through the entire list |
| `* * *`  | Teaching Assistant                                                            | group students into classes                                  | know which class my student is in                                      |
| `* * *`  | Teaching assistant                                                            | find my supervisor(s)                                        | Report any admin issues my students would raise                        |
| `* * *`  | University student with different friend groups                               | tag or categorize my contacts                                | Search by the friend groups I'm concerned with                         |
| `* * *`  | Teaching Assistant who wants to meet up with Profs and students               | schedule events to do so                                     | remind myself to meet up or know my free times.                        |
| `* * *`  | Teaching Assistant with important people to report to or stay in contact with | pin important contacts                                       |                                                                        |
| `* * *`  | Teaching Assistant who needs to announce information to his students          | copy a (group of) student(s)' contact info onto my clipboard | announce a message efficiently to many students                        |
| `* *`    | Forgetful Teaching Assistant                                                  | have a check for addition of duplicate contacts              | can reduce clutter of my list                                          |
| `* *`    | Teaching assistant who might mistype                                          | have a Confirm Delete warning when doing deletions           | provide a safety net in case I accidentally delete important info      |
| `* *`    | Teaching assistant with many students                                         | personalise contacts with photos                             | attribute names to faces and distinguish similar names.                |
| `* *`    | user                                                                          | hide private contact details                                 | minimise chance of someone else seeing them by accident                |
| `*`      | user with many persons in the address book                                    | sort persons by name                                         | locate a person easily                                                 |
| `*`      | detail-oriented Teaching Assistant                                            | add a note with additional information about the contact     | remind myself of things I may need to follow up on                     |
| `*`      | Teaching assistant who tires from work                                        | use an app with a cheerful interface                         | feel encouraged / at peace                                             |
| `*`      | Teaching assistant                                                            | import and export contact details to/from the app            | can easily shift to using this app, or another                         |



### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: View all contacts**

**MSS**

1.  User requests to view all contacts.
2.  AddressBook shows a list of contacts added.

    Use case ends.

**Extensions**

* 1a. User uses the wrong format in his request.

    * 1a1. AddressBook shows an error message.
    * 1a2. User uses the correct format as shown in the error message for his request.

      Use case resumes at step 2.


**Use case: Add a contact**


**MSS**

1.  User requests to add a contact.
2.  User inputs all information required to add a contact into AddressBook.
3.  AddressBook adds the contact with all relevant information into a list.

    Use case ends.

**Extensions**

* 2a. The given information is in an incorrect format.

    * 2a1. AddressBook shows an error message.

      Use case resumes at step 1.
      
* 2b. The given information is insufficient to form a contact.

    * 2b1. AddressBook shows an error message.

      Use case resumes at step 1.

**Use case: Edit a contact's information**

**MSS**

1.  User requests to edit a contact.
2.  User inputs new information about the fields he wishes to edit about a specified contact.
3.  AddressBook edits the contact by changing all the specified fields into the newly inputted information.

    Use case ends.

**Extensions**

* 2a. The given information is in an incorrect format.

    * 2a1. AddressBook shows an error message.

      Use case resumes at step 1.
      
* 2a. The contact does not exist.

    * 2a1. AddressBook shows an error message.

      Use case ends.

**Use case: Delete a contact**

**MSS**

1.  User requests to delete a specific contact.
2.  AddressBook deletes the specified contact.

    Use case ends.

**Extensions**

* 1a. User uses the wrong format in his request.

    * 1a1. AddressBook shows an error message.
    * 1a2. User uses the correct format as shown in the error message for his request.

      Use case resumes at step 2.

* 2a. The contact does not exist.

  * 2a1. AddressBook shows an error message.

    Use case ends.



**Use case: Find some specific contact(s)**

**MSS**

1.  User requests to find some contact(s).
2.  AronaPro outputs the contact(s) with all relevant information about the contact.

    Use case ends.

**Extensions**

* 1a. User uses the wrong format in his request.

    * 1a1. AronaPro shows an error message.
    * 1a2. User uses the correct format as shown in the error message for his request.

      Use case resumes at step 2.

* 2a. The contact does not exist.

    * 2a1. AronaPro shows an empty list, informing that no contacts were found.

      Use case ends.

**Use case: Assign an existing contact to a tutorial group**

**MSS**

1.  User requests to tag a specific contact to a tutorial group.
2.  AddressBook tags the contact to the tutorial group.

    Use case ends.

**Extensions**

* 1a. User uses the wrong format in his request.

    * 1a1. AddressBook shows an error message.
    * 1a2. User uses the correct format as shown in the error message for his request.

      Use case resumes at step 2.

* 2a. The contact does not exist.

    * 2a1. AddressBook shows an error message.

      Use case ends.

* 2a. The tutorial group does not yet exist.

    * 2a1. AddressBook creates a new tag with the name of the tutorial group.

        Use case resumes at step 2.

**Use case: Classify an existing contact (Prof, TA, Student)**

**MSS**

1.  User requests to classify a specific contact.
2.  AddressBook classifies the contact as either a Professor, TA or Student.

**Extensions**

* 1a. User uses the wrong format in his request.

    * 1a1. AddressBook shows an error message.
    * 1a2. User uses the correct format as shown in the error message for his request.

      Use case resumes at step 2.

* 2a. The contact does not exist.

    * 2a1. AddressBook shows an error message.

      Use case ends.

* 2a. The tag does not exist.

    * 2a1. AddressBook shows an error message.
    * 2a2. User has to request the classification again using a correct classifier.

      Use case resumes at step 1.


**Use case: Schedule an event with a contact**

**MSS**

1.  User requests to schedule an event with a specific contact.
2.  User inputs all necessary information into AddressBook in order for the event to be scheduled.
3.  AddressBook creates the event and classifies it under the contact.

**Extensions**

* 1a. User uses the wrong format in his request.

    * 1a1. AddressBook shows an error message.
    * 1a2. User uses the correct format as shown in the error message for his request.

      Use case resumes at step 2.

* 2a. The contact does not exist.

    * 2a1. AddressBook shows an error message.
    * 2a2. User inputs an existing contact as required in the error message for his request.

      Use case resumes at step 2.

* 2a. The information required is not sufficiently inputted.

    * 2a1. AddressBook shows an error message.
    * 2a2. User has to request the classification again using a correct classifier.

      Use case resumes at step 1.

**Use case: Delete a group of people**

**MSS**

1.  User requests to delete all people in a specific group.
2.  User inputs the name of the group into the AddressBook.
3.  AddressBook deletes all people that are classified under the specified group.

**Extensions**

* 1a. User uses the wrong format in his request.

    * 1a1. AddressBook shows an error message.
    * 1a2. User uses the correct format as shown in the error message for his request.

      Use case resumes at step 2.

* 2a. The group does not exist.

    * 2a1. AddressBook shows an error message.

      Use case ends.

**Use case: Pin a contact**

**MSS**

1.  User requests to pin a specific contact.
2.  AddressBook pins the specified contact.

    Use case ends.

**Extensions**

* 1a. User uses the wrong format in his request.

    * 1a1. AddressBook shows an error message.
    * 1a2. User uses the correct format as shown in the error message for his request.

      Use case resumes at step 2.

* 2a. The contact does not exist.

    * 2a1. AddressBook shows an error message.

      Use case ends.


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  A novice user should be able to learn basic operations (add, delete, search entries) within 30 minutes of using the documentation.
5.  The system should respond within two seconds.
6.  The system should be backward compatible with data produced by earlier versions of the system.
7.  The system should smoothly handle user input errors and system issues, providing meaningful error messages without crashing.
8.  Comply with relevant data protection regulations, Personal Data Protection Act (PDPA) in handling personal information.
9.  Adhere to recommended coding standards, such as readability, modularity, and application of design patterns, to make upgrades and maintenance simpler.

### Glossary

* **Teaching Assistant (TA)**: A student-tutor hired by NUS. TAs are responsible for conducting tutorial lessons and assist in the students' learning whenever required.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder.

   1. cd into that folder and execute the command `java -jar AronaPro.jar`. <br>
   Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Example: Deleting a person

1. Deleting a person with a specified `while all persons are being shown.

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete id/E0123456`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete id/E0000000`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete id/x`, `...` (where x is NUSID which does not exist currently in the address book)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files.

   1. Open a command terminal, `cd` into the folder you put the jar file in, and delete the data file `data/addressbook.json`.<br>
        Expected: The app should create a new data file with default data when it is launched.

   1. Open the data file `data/addressbook.json` in a text editor and delete some lines from the middle of the file.<br>
           Expected: The app should show an error message and starts with an empty AronaPro.
2. _{ more test cases …​ }_

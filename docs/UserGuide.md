---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# About AronaPro

--------------------------------------------------------------------------------------------------------------------

Welcome to AronaPro, and we thank you for choosing us as your all-in-one solution! 
Our product is a desktop app designed primarily for **Computing Professors and Teaching Assistants(TAs)** to manage your 
students/professors/TAs' contacts and schedules efficiently, optimised for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI). Our simple-to-grasp software also warmly welcomes
professors and TAs from various faculties without any technological background! 

We are excited to have you on board and to guide you through how to use our software with this guide. Click 
[here](#about-userguide) to learn more about our User Guide.

Together, let us **_Centralise_, _Connect_ and _Coordinate_!**

--------------------------------------------------------------------------------------------------------------------

## Table of content

* [**About AronaPro**](#about-aronapro)
  *  [**Table of Content**](#table-of-content)
  * [**About UserGuide**](#about-userguide)
  * [**Quick Start**](#quick-start)
  * [**Features**](#features)
    * [Understanding our application's interface](#understanding-our-application-s-interface)
    * [Notes about command format](#notes-about-the-command-format)
    * [Help command](#viewing-help-help)
    * [Add command](#adding-a-person-add)
    * [View command](#viewing-all-persons-view)
    * [Edit command](#editing-a-person-s-information-edit)
    * [Find command](#finding-a-person-find)
    * [Delete command](#deleting-a-person-delete)
    * [Group command](#assigning-people-to-a-group-group)
    * [Schedule command](#schedule-a-meeting-with-a-person-schedule)
    * [Pin command](#pinning-a-person-pin)
    * [Clear command](#clearing-all-entries-clear)
    * [Exit command](#exiting-the-program-exit)
    * [Saving the data](#saving-the-data)
    * [Editting the data file](#editing-the-data-file)
  * [**FAQ**](#faq)
  * [**Existing issues**](#known-issues)
  * [**Planned enhancements**](#planned-enhancements)
  * [**Command summary**](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## About UserGuide

Welcome to our User Guide! Here, we have got everything you need to know about using all of our application's features. 
If you are already familiar with our application, dive straight into the [Features](#features) section to discover more. 
However, if you are new to the application, we have got you covered. You can simply do so by following our guide systematically, 
starting with the [Quick Start](#quick-start) section, to quickly get up to speed with AronaPro before you start your own adventure. 
Let us get started!

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `aronapro.jar` [here](https://github.com/AY2324S2-CS2103T-T15-2/tp/releases). 

1. Copy the file to the folder you want to use as the _home folder_ for AronaPro in your desired location. For demonstration purpose, we will save the downloaded jar file into `test` folder and saved it to `Desktop` on either Mac or Windows machines. 

1. Open a command terminal, either by typing `terminal` on Mac or `PowerShell` on Windows machine and perform the following steps to reach the `test` folder created earlier in order to run the application:
   1. First type `ls` to see what are all the folders in your current directory.
   2. Type `cd <file_name>` where the file name refers to the file name you are trying to enter. One tip is that file name can be auto-completed simply by typing the first few letters and hitting `Tab`.
   3. Repeat i. and ii. above until you see the `test` folder and `cd` into it as show in the sequence of diagrams for both Mac and Windows machines below:
   <p align="centerleft">
    <img src="images/UserGuide/Navigation step one.png" alt="Navigation step one" width="900" style="margin-left: 10px; margin-top:30px"/>
   </p>

   <p align="centerleft">
    <img src="images/UserGuide/Navigation step two.png" alt="Navigation step two" width="900" style="margin-left: 10px; margin-top:30px"/>
   </p> 

   <p align="centerleft">
    <img src="images/UserGuide/Navigation step three.png" alt="Navigation step three" width="900" style="margin-left: 10px; margin-top:30px"/>
   </p>  
   
1. Lastly, enter the command: `java -jar aronapro.jar` to run the application.<br>
   A GUI similar to the below should appear in a few seconds.<br>
   <p align="centerleft">
    <img src="images/Ui.png" alt="Ui" width="900" style="margin-left: 10px; margin-top:20px"/>
   </p>
   e.g. Typing `help` and pressing Enter will open the help window.<br>
   Some example commands you can try to quickly get started with our application:

   * `view` : Lists all contacts.

   * `add id/E1234567 n/John Doe p/98765432 e/johnd@example.com t/Student g/CS2103T` : Adds a student with `NAME` John Doe with `NUSID` of E1234567 to AronaPro.

   * `delete id/E1234567` : Deletes the person with `NUSID` of E1234567.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Understanding our application's interface

<p align="centerleft">
    <img src="images/UserGuide/Understanding UI.png" alt="Understanding UI" width="900" style="margin-top:10px"/>
</p>


| Name                                       | Functionality                                                                                               |
|--------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| **Command panel**                          | Allows you to key in your commands here.                                                                    |
| **Response panel**                         | Echoes the contact's information you have keyed and displays error messages if any to allow you to rectify. |
| **Students/TAs/Professors contacts panel** | Displays all the information of your contact list.                                                          |
| **Scheduled meetings information panel**   | Displays all the information of your scheduled meetings.                                                    |

    

### **Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  > In the delete command as such: `delete id/NUSID`, `NUSID` is a parameter which need to be supplied.

* Items in square brackets are optional.<br>
  > `n/NAME [t/TAG]` can be used as `n/John Doe t/Student` or simply as `n/John Doe` without specifying the `TAG` as it is optional.

* Items with `…`​ after them can be used multiple times or zero times.<br>
  > * `[g/GROUP]…​` can be used as ` ` (i.e. 0 times) or `g/CS2101 g/CS2103T`, demonstrating that group can be used 2 times by writing them successively on the same line etc.
  > * The only exception to the above is when `NUSID` is of concern where it needs to be used **at least once** or more. `id/NUSID…​` means that you need to use it once as such: `id/E1234567` 
  > or you can choose to use it more than once: `id/E1234567 id/E0123456`.
* Parameters can be in any order.<br>
  > If the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `view`, `exit` and `clear`) will be ignored.<br>
  > If the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to AronaPro.

Format: `add id/NUSID n/NAME p/PHONE_NUMBER e/EMAIL t/TAG [g/GROUP]…​`

> Note:
> * The `NUSID` refers to the NUSID shown in the displayed person list.
> * The `NUSID` **must be a 7-digit number following an 'E'**.
> * `NAME` can consist of numbers.
> * `NAME` can be duplicated for different persons.
> * `PHONE_NUMBER` has to be 3-10 digits long.
> * `EMAIL` can be duplicated for different persons.
> * `EMAIL` follows the following format and constraints:
  >   * It should have a local-part followed by a `@` and then a domain name.
>     * Local-part should only contain alphanumeric characters and special characters(+ _ . -).
>     * Local-part may not start or end with any special characters.
>     * Domain name is made up  of domain label separated by periods.
  >     * Domain name must end with a domain label at least 2 characters long.
>       * Have each domain label start and end with alphanumeric characters.
>       * Have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
> * For `TAG`, the valid forms have to be either 1 of these:  Professor, TA, Student, None.
> * For `GROUP`, the group must only contain alphanumeric characters or spaces.

**Tip:** A person can have 0 or more groups.

Examples:
* `add id/E1234567 n/John Doe p/98765432 e/johnd@example.com t/Student g/CS2103T`
  > This command would add a person with `NUSID` of E1234567, `NAME` of John Doe, `PHONE_NUMBER` of 98765432, `EMAIL` of johnd@example.com
    `TAG` of Student, `GROUP` of CS2103T into AronaPro.

  <p align="centerleft">
        <img src="images/add/add-new-person.png" alt="add new person" width="700" style="margin-top:20px"/>
  </p>
  
 

* `add id/E7654321 n/Betsy Crowe t/TA e/betsycrowe@example.com p/92345678`
  > This command would add a person with `NUSID` of E7654321, `NAME` of Betsy Crowe, `TAG` of TA,
    `EMAIL` of betsycrowe@example.com, `PHONE_NUMBER` of 92345678 into AronaPro.
  <p align="centerleft">
        <img src="images/add/add-new-ta-without-group.png" alt="add new ta without group" width="700" style="margin-top:20px"/>
  </p>  

### Viewing all persons : `view`

Shows the entire list of persons in AronaPro.

Format: `view`

### Editing a person's information : `edit`

Edits the information of a person of a specified `NUSID` in AronaPro.

Format: `edit id/NUSID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG] [g/GROUP]…​` 

> Note:
> * The `NUSID` refers to the NUSID shown in the displayed person list.
> * The `NUSID` **must be a 7-digit number following an 'E'**.
> * `NAME` can consist of numbers.
> * `NAME` can be duplicated for different persons.
> * `PHONE_NUMBER` has to be 3-10 digits long.
> * `EMAIL` can be duplicated for different persons.
> * `EMAIL` follows the following format and constraints:
>   * It should have a local-part followed by a `@` and then a domain name.
>   * Local-part should only contain alphanumeric characters and special characters(+ _ . -).
>   * Local-part may not start or end with any special characters.
>   * Domain name is made up  of domain label separated by periods.
>     * Domain name must end with a domain label at least 2 characters long.
>     * Have each domain label start and end with alphanumeric characters.
>     * Have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
> * When editing `TAG`, the valid forms have to be either 1 of these:  Professor, TA, Student, None.
> * When editing `GROUP`, the group must only contain alphanumeric characters or spaces.
> * **At least one of the optional fields must be provided.**
> * Existing values will be replaced by and updated to the new input values.
> * Attempt to edit a person with `NUSID` not in AronaPro would result in an error message.



Examples:
*  `edit id/E0123456 p/91234567 e/johndoe@example.com` 
   > This command would edit the phone number and email address of the person with `NUSID` E0123456 to be `91234567` and `johndoe@example.com` respectively.
    <p align="centerleft">
        <img src="images/edit/edit-phone-and-email.png" alt="edit-phone-and-email" width="700" style="margin-top:20px; margin-bottom:20px"/>
    </p> 
   
*  `edit id/E1234567 n/Betsy t/Professor` 
   > This command would edit the name and the tag of the person with `NUSID` E1234567 to be `Betsy` and `Professor` respectively.
    <p align="centerleft">
        <img src="images/edit/edit-name-and-tag.png" alt="edit-name-and-tag" width="700" style="margin-top:20px"/>
    </p>

### Finding a person: `find`

Finds persons whose details match ALL the given keywords.

Format: `find [id/NUSID] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG] [g/GROUP]…​`

> Note:
> * The `NUSID` search matches people that has a prefix that STARTS WITH the query (e.g `E0123` fetches `E0123456`).
> * The `NAME` search is case-insensitive. e.g `hans` will match `Hans`.
> * The order of `NAME` keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
> * Only full words will be matched for `NAME` e.g. `Han` will not match `Hans`.
> * Persons matching ANY word will be selected for `NAME` (e.g. `Hans Bo` will fetch `Hans Gruber`, `Bo Yang`).
> * The `PHONE_NUMBER` search matches people that has a number that STARTS WITH the query (e.g `9123` fetches `91237654`).
> * The `EMAIL` search uses a PARTIAL, case-insensitive match. (e.g. `charles` matches `PrinceCharles@kingston.com`).
> * The `TAG` search uses an EXACT case-sensitive match. 
> * The `GROUP` search fetches people with ALL specified groups (e.g `g/CS2101 g/CS2103T` matches a person who minimally has BOTH these Groups).
> * Persons matching all parameters will be returned (i.e. `AND` search).
  
Examples:
* `find n/roy` 
  > This command would return `Roy G Biv` and `Roy Balakrishnan`. 
  > 
    <p align="centerleft">
        <img src="images/find/find-by-name.png" alt="find-by-name" width="700" style="margin-top:20px; margin-bottom:20px"/>
    </p>
  
* `find n/roy g/CS2101`
  > This command would return `Roy Balakrishnan` who is the only person matching both the 2 conditions.
  > 
    <p align="centerleft">
        <img src="images/find/find-by-name-and-group.png" alt="find-by-name-and-group" width="700" style="margin-top:20px"/>
    </p>

### Deleting a person : `delete`


Deletes person of a specified `NUSID` from the filtered address book OR deletes all persons from a specified `GROUP` from the filtered address book.

Format 1: `delete id/NUSID`

> Note:
> * Deletes the person of a specified `NUSID`.
> * The `NUSID` refers to the NUSID shown in the displayed person list.
> * The `NUSID` **must be a 7-digit number following an 'E'**.

Examples:
* `delete id/E0123456` 
  > This commmand will delete an existing person with `NUSID` of "E0123456".
  >
    <p align="centerleft">
        <img src="images/delete/delete-person.png" alt="delete-person" width="700" style="margin-top:20px"/>
    </p>

Format 2: `delete g/GROUP`

> Note:
> * Deletes the person in a specified `GROUP`.
> * The group refers to the group shown in the displayed person list.
> * The group **must exist in AronaPro beforehand**.

Examples:
* `delete g/CS2103T`
  > This command will delete an existing person with `GROUP` of "CS2103T".
  >
    <p align="centerleft">
        <img src="images/delete/delete-group.png" alt="delete-group" width="700" style="margin-top:20px"/>
    </p>

### Assigning people to a group : `group`

Assigns either a group or a tag to a person of a specified `NUSID` from AronaPro.

Format: `group id/NUSID…​ [g/GROUP]…​ [t/TAG]`

> Note:
> * Groups the person of a specified `NUSID`.
> * The `NUSID` refers to the NUSID shown in the displayed person list.
> * The `NUSID` **must be a 7-digit number following an 'E'**.
> * More than one `NUSID` can be specified for a group command to group more than one person (`group id/E1234567 id/E0123456 g/Friends`).
> * **At least one of the optional fields must be provided.**
> * When assigning `TAG`, the valid forms have to be either 1 of these:  Professor, TA, Student, None.
> * When assigning `GROUP`, the group must only contain alphanumeric characters or spaces.
> * Existing values will be replaced by and updated to the new input values.
> * A person can be assigned to a non-existing group.

Examples:
* `group id/E0123456 g/CS2101`
  > This command will assign or change a group of the person with `NUSID` E0123456 to 'CS2101'.
  >
    <p align="centerleft">
        <img src="images/group/assign-group.png" alt="assign-group" width="700" style="margin-top:20px; margin-bottom:20px"/>
    </p>
* `group id/E0123456 t/TA` 
  > This command will assign or change the tag of the person with `NUSID` E0123456 to 'TA'.
  >
    <p align="centerleft">
        <img src="images/group/assign-tag.png" alt="assign-tag" width="700" style="margin-top:20px"/>
    </p>


### Schedule a meeting with a person: `schedule`

Schedule a meeting with a person in AronaPro.

Format: `schedule id/NUSID [s/SCHEDULE r/REMARK]`

> Note: 
> * Schedule a meeting with a person of the specified `nusId` on the specified `SCHEDULE` with a `REMARK`.
> * The `NUSID` refers to the NUSID shown in the displayed person list.
> * The `NUSID` **must be a 7-digit number following an 'E'**.
> * Both `SCHEDULE` and `REMARK` must be either provided or not provided.
> * **If `SCHEDULE` and `REMARK` are not provided and the `Schedule` command is used as a standalone, the schedule will be removed (`schedule id/E1234567`).**

**Tip:** The `SCHEDULE` must be in one of the formats: `DD/MM/YYYY`, `DD-MM-YYYY`, `DD.MM.YYYY`, `MMM DD, YYYY`, `DD MMM YYYY`. Example of date formats include: `12-12-2024`, `12/12/2024`, `12.12.2024`, `Dec 12, 2024`, `12 Dec 2024`.


Examples:

* `schedule id/E0123456 s/12-12-2024 r/Consultation` 
  > This command will schedule a meeting with the person of `NUSID` E0123456, with `SCHEDULE` on 12th December 2024 and a `REMARK` of Consultation.
  > If there was a previous date, or remark, it will be replaced!
  > 
    <p align="centerleft">
        <img src="images/schedule/schedule-with-remark.png" alt="schedule-with-remark" width="700" style="margin-top:20px; margin-bottom:20px"/>
    </p>
* `schedule id/E0123456` 
  > This command will remove the schedule with the person of `NUSID` E0123456.
  >
    <p align="centerleft">
        <img src="images/schedule/remove-schedule.png" alt="remove-schedule" width="700" style="margin-top:20px"/>
    </p>

### Pinning a person: `pin`

Pins a person to the top of AronaPro.

Format: `pin id/NUSID`

> Note:
> * The `NUSID` refers to the NUSID shown in the displayed person list.
> * The `NUSID` **must be a 7-digit number following an 'E'**.

Examples:

* `pin id/E0123456` 
  > This command will pin a student with `NUSID` of "E0123456".
  >
    <p align="centerleft">
        <img src="images/pin/pin.png" alt="pin" width="700" style="margin-top:20px"/>
    </p>


### Clearing all entries : `clear`

Clears all entries from AronaPro.

Format: `clear`

<p align="centerleft">
   <img src="images/clear.png" alt="clear" width="700" style="margin-top:20px"/>
</p>


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AronaPro data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

All data from the application is saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless

**Caution:**
If your changes to the data file makes its format invalid, AronaPro will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause AronaPro to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **When using the `schedule` command**, the command does not check for the validity of the date entered. Hence, it is important to ensure that the date entered is valid.
3. **When using the `schedule` command**, the command does not check if the valid date entered is in the future. Hence, it is important to ensure that the date entered is in the future.
4. **When using the `edit` command**, the command does not check for the validity of the domain name of the email entered as writing an incorrect domain name after the `@` is accepted (e.g. `@random.com`). Hence, it is important to ensure that a valid domain name is entered.
4. **When using the `group` command with a very long group name**, the group name may be hidden from view. The remedy is to resize the window to view the full group name.
5. **When adding/editing a person's email address with a very long email address**, the email address may be hidden from view. The remedy is to resize the window to view the full email address.
6. **When resizing the window to a smaller size**, the GUI may not fully display the person's information; for example: name, tag, remark. The remedy is to resize the window to a larger size to view all the information.
7. **When adding/editing names with special characters, i.e. `!@#$%^&*()_+`**, the application does not allow such names to be added/edited. The remedy is to avoid using special characters in the names.

--------------------------------------------------------------------------------------------------------------------

## Planned enhancements

Team size: 5

1. **Validity of date entered:** The current behaviour of the `schedule` command does not check for the validity of the date entered. We plan to implement a check to ensure that the date entered is valid.
2. **Future date check:** The current behaviour of the `schedule` command does not check if the valid date entered is in the future. We plan to implement a check to ensure that the date entered is in the future.
3. **Validity of email domain entered:** The current behaviour of the `edit` command does not check for the validity of the domain name of the email entered. We plan to implement a check to ensure that a valid domain name is entered.
3. **Ordering date in chronological order:** There is currently no ordering of date in any ways in our UI. We plan to implement sorting of the dates in chronological order to aid our users in remembering the dates for their schedules.
3. **Group name visibility:** When using the `group` command with a very long group name, the group name may be hidden from view. We plan to implement a feature to allow users to view the full group name. Either by truncating the group name, allowing the user to view the full group name or limiting the length of the group name.
4. **Email address visibility:** When adding/editing a person's email address with a very long email address, the email address may be hidden from view. We plan to implement a feature to allow users to view the full email address. Either by truncating the email address, allowing the user to view the full email address or limiting the length of the email address.
5. **Tag information visibility:** When resizing the window to a smaller size, the GUI may not fully display the person's tag information. We plan to implement a minimum size for the window to ensure that all information is displayed.
6. **Tag case sensitivity:** When editing tags, the valid forms have to be either 1 of these:  Professor, TA, Student, None. We plan to implement a feature to allow users to enter tags in any case (e.g., professor, ta, student, none) and still be recognised as valid tags.
7. **Special characters in names:** When adding/editing names with special characters, i.e. `!@#$%^&*()_+`, the application does not allow such names to be added/edited. We plan to implement a feature to allow users to enter names with special characters.
8. **One schedule per person:** Currently, a person can only have one schedule. We plan to implement a feature to allow a person to have multiple schedules.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action       | Format, Examples                                                                                                                                                               |
|--------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**      | `add id/NUSID n/NAME p/PHONE_NUMBER e/EMAIL t/TAG [g/GROUP]…​` <br> e.g., `add id/E1234567 n/James Ho p/22224444 e/jamesho@example.com t/Student g/CS2101`                     |
| **Clear**    | `clear`                                                                                                                                                                        |
| **Delete**   | `delete id/NUSID`<br> e.g., `delete id/E01234567`<br> OR `delete g/GROUP` <br> e.g., `delete g/CS2103`                                                                         |
| **Edit**     | `edit id/NUSID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG] [g/GROUP]…​`<br> e.g.,`edit id/E1234567 n/James Lee e/jameslee@example.com`                                         |
| **Find**     | `find [id/NUSID] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG] [g/GROUP]…​`<br> e.g., `find n/James g/CS2103T` <br>**Tip:** At least one of the optional fields must be provided |
| **Group**    | `group id/NUSID…​ [g/GROUP]…​ [t/TAG] ` <br> e.g., `group id/E1234567 g/T15` <br>**Tip:** At least one of the optional fields must be provided                                 |
| **Schedule** | `schedule id/NUSID [s/SCHEDULE r/REMARK]` <br> e.g., `schedule id/E1234567 s/12-12-2021 r/Consultation`                                                                        |
| **Pin**      | `pin id/NUSID`                                                                                                                                                                 |
| **View**     | `view`                                                                                                                                                                         |
| **Help**     | `help`                                                                                                                                                                         |



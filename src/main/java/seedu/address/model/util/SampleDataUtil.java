package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final NusId SAMPLE_NUSID = new NusId("E1234567");
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(SAMPLE_NUSID, new Name("Alex Yeoh"),
                new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Tag("Student"),
                getGroupSet("friends")),
            new Person(SAMPLE_NUSID, new Name("Bernice Yu"),
                new Phone("99272758"), new Email("berniceyu@example.com"),
                new Tag("Student"),
                getGroupSet("colleagues", "friends")),
            new Person(SAMPLE_NUSID, new Name("Charlotte Oliveiro"),
                new Phone("93210283"), new Email("charlotte@example.com"),
                new Tag("TA"),
                getGroupSet("neighbours")),
            new Person(SAMPLE_NUSID, new Name("David Li"),
                new Phone("91031282"), new Email("lidavid@example.com"),
                new Tag("Professor"),
                getGroupSet("family")),
            new Person(SAMPLE_NUSID, new Name("Irfan Ibrahim"),
                new Phone("92492021"), new Email("irfan@example.com"),
                new Tag("TA"),
                getGroupSet("classmates")),
            new Person(SAMPLE_NUSID, new Name("Roy Balakrishnan"),
                new Phone("92624417"), new Email("royb@example.com"),
                new Tag("None"),
                getGroupSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a group set containing the list of strings given.
     */
    public static Set<Group> getGroupSet(String... strings) {
        return Arrays.stream(strings)
                .map(Group::new)
                .collect(Collectors.toSet());
    }

}

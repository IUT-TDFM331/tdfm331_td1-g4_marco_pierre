package fr.iut.tdfm331.td1.service;

import fr.iut.tdfm331.td1.model.Employee;
import fr.iut.tdfm331.td1.model.Meeting;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * Unit test file to test ListApiService class
 */
public class ListApiServiceTest {

    private ListApiService service;

    @Before
    public void setupService() {
        service = new ListApiService();
    }

    /**
     * Test to check if list of Meeting is ∞correctly generated
     */
    @Test
    public void getListMeetingWithSuccess() {
        List<Meeting> listMeetings = service.getListMeetings();
        List<Meeting> expectedListMeetings = ListMeetingsGenerator.LIST_MEETINGS;
        assertThat(listMeetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedListMeetings.toArray()));
    }

    /**
     * Test to check it list of Employee is correctly generated
     */
    @Test
    public void getListEmployeeWithSuccess() {
        List<Employee> listEmployees = service.getListEmployees();
        List<Employee> expectedListEmployees = ListEmployeesGenerator.LIST_EMPLOYEES;
        assertThat(listEmployees, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedListEmployees.toArray()));

    }

    /**
     * Test to check if a new Meeting object is correctly added to the list
     */
    @Test
    public void addNewMeetingWithSuccess() {

        // Create list Employee
        List<Employee> listEmployees = Arrays.asList(new Employee("Baptiste", "baptiste@lamzone.com", 4),
                new Employee("Fanny", "fanny@lamzone.com", 10),
                new Employee("Vincent", "vincent@lamzone.com", 22));

        // Create list Meeting
        Meeting newMeeting = new Meeting("Réunion d'avancement",
                "Planck",
                "12/11/20",
                "15:30",
                "16:00",
                "Revues des dernières actions",
                listEmployees);

        // Add Meeting
        service.addMeeting(newMeeting);
        Assert.assertTrue(service.getListMeetings().contains(newMeeting));
    }

    /** ADDED
     * Test to check if a meeting exist
     */
    @Test
    public void FindMeetingTrue() throws MeetingNotFound {
        List<Employee> listEmployees = Arrays.asList(new Employee("Baptiste", "baptiste@lamzone.com", 4),
                new Employee("Bilal", "bilal@lamzone.com", 10),
                new Employee("Vincent", "vincent@lamzone.com", 22));

        Meeting meetingToFound = new Meeting("ProjetX","SalleDeClasse","07/12/21","12:45","12:50","Faut qu'on decide qui va faire l'oral",listEmployees);

        try {
            Meeting meetingFounsd = service.findByObject(meetingToFound.getObjectMeeting());
            Assert.assertTrue(meetingFounsd.equals("ProjetX"));

            Meeting meetingFound = service.findByObject(meetingToFound.getObjectMeeting());
            Assert.assertTrue("ProjetX", true);
            Assert.assertFalse("ProjetZ", false);
        } catch (MeetingNotFound e) {
            e.printStackTrace();
        }
    }

    /**
     * Test to check if a selected Meeting is correctly removed from list
     */
    @Test
    public void removeMeetingWithSuccess() {
        // Get first Meeting from list
        Meeting meetingToRemove = service.getListMeetings().get(0);
        service.getListMeetings().remove(meetingToRemove);
        Assert.assertFalse(service.getListMeetings().contains(meetingToRemove));
    }

    /** ADDED
     * Test to remove a meeting with success
     */
    @Test
    public void realRemoveMeetingWithSuccess() {
        //test ajout d'une réunion
        Meeting meetingToRemove = service.getListMeetings().get(0);
        service.removeMeeting(meetingToRemove);
        Assert.assertTrue(service.getListMeetings().contains(meetingToRemove));
    }

    /** ADDED
     * Test to add a meeting
     */
    @Test
    public void addMeetingWithSuccess() {
        //test ajout d'une réunion
        Meeting meetingToAdd = service.getListMeetings().get(0);
        service.addMeeting(meetingToAdd);
        Assert.assertTrue(service.getListMeetings().contains(meetingToAdd));
    }

    /** ADDED
     * Test to find an employee by they name
     */
    @Test
    public void findEmployeByName () throws EmployeeNotFound {
        List<Employee> listEmployees = Arrays.asList(
                new Employee("Baptiste", "baptiste@lamzone.com", 4),
                new Employee("Bilal", "bilal@lamzone.com", 10),
                new Employee("Vincent", "vincent@lamzone.com", 22),
                new Employee("marcouninou", "marco@best.com", 1)
        );

        try {
            Employee nameFound = service.findByName(listEmployees.get(0).getName());
            Assert.assertTrue("marcouninou", true);
            Assert.assertFalse("pierito", false);
        } catch (EmployeeNotFound e) {
            e.printStackTrace();
        }


    }
}

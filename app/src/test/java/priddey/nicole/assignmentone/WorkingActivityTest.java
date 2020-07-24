package priddey.nicole.assignmentone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkingActivityTest {

    long seconds, expectedMinutes, actualMinutes;
    WorkingActivity testObj;

    @Before
    public void setUp() throws Exception {
        seconds = 320955;

        testObj = new WorkingActivity();
        expectedMinutes = (seconds/1000)/60;
        actualMinutes = testObj.getMinutes(320955);


    }

    @After
    public void tearDown() throws Exception {
        testObj = null;
    }

    @Test
    public void testGetMinutes() {
        assertEquals(expectedMinutes, actualMinutes);
    }
}
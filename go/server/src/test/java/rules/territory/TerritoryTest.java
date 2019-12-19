package rules.territory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TerritoryTest {
    Territory territory;

    @Before
    public void setUp() throws Exception {
        territory = new Territory(null, null);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setTerritory() {
        assertEquals(territory.owner, OwnerTerritory.EMPTY);
        assertNotEquals(territory.owner, OwnerTerritory.BLACK);
        assertNotEquals(territory.owner, OwnerTerritory.NEUTRAL);
        assertNotEquals(territory.owner, OwnerTerritory.WHITE);
    }
}
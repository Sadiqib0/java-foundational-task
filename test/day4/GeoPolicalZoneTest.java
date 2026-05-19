import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GeoPoliticalZoneTest {

    @Test
    void shouldReturnNorthCentralForValidStatesIncludingFCT() {
        Assertions.assertEquals(GeoPoliticalZone.NORTH_CENTRAL, GeoPoliticalZone.findByState("Benue"));
        Assertions.assertEquals(GeoPoliticalZone.NORTH_CENTRAL, GeoPoliticalZone.findByState("FCT"));
        Assertions.assertEquals(GeoPoliticalZone.NORTH_CENTRAL, GeoPoliticalZone.findByState("Kogi"));
        Assertions.assertEquals(GeoPoliticalZone.NORTH_CENTRAL, GeoPoliticalZone.findByState("  fct  "));
    }

    @Test
    void shouldReturnNorthEastForBorno() {
        Assertions.assertEquals(GeoPoliticalZone.NORTH_EAST, GeoPoliticalZone.findByState("Borno"));
    }

    @Test
    void shouldReturnNorthWestForKano() {
        Assertions.assertEquals(GeoPoliticalZone.NORTH_WEST, GeoPoliticalZone.findByState("Kano"));
    }

    @Test
    void shouldReturnSouthEastForImo() {
        Assertions.assertEquals(GeoPoliticalZone.SOUTH_EAST, GeoPoliticalZone.findByState("Imo"));
    }

    @Test
    void shouldReturnSouthSouthForHyphenatedStateAkwaIbom() {
        Assertions.assertEquals(GeoPoliticalZone.SOUTH_SOUTH, GeoPoliticalZone.findByState("Akwa-Ibom"));
        Assertions.assertEquals(GeoPoliticalZone.SOUTH_SOUTH, GeoPoliticalZone.findByState("akwa-ibom"));
    }

    @Test
    void shouldReturnSouthWestForLagos() {
        Assertions.assertEquals(GeoPoliticalZone.SOUTH_WEST, GeoPoliticalZone.findByState("Lagos"));
    }

    @Test
    void shouldThrowExceptionForInvalidState() {
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class,
                () -> GeoPoliticalZone.findByState("UnknownState"));
        Assertions.assertTrue(ex.getMessage().contains("Unknown state"));
    }

    @Test
    void shouldThrowExceptionForEmptyOrNullState() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPoliticalZone.findByState(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPoliticalZone.findByState("   "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> GeoPoliticalZone.findByState(null));
    }
}
import java.util.Scanner;

public enum GeoPoliticalZone {

    NORTH_CENTRAL("North-Central", "Benue", "FCT", "Kogi", "Kwara", "Nasarawa", "Niger", "Plateau"),
    NORTH_EAST("North-East", "Adamawa", "Bauchi", "Borno", "Gombe", "Taraba", "Yobe"),
    NORTH_WEST("North-West", "Kaduna", "Katsina", "Kano", "Kebbi", "Sokoto", "Jigawa", "Zamfara"),
    SOUTH_EAST("South-East", "Abia", "Anambra", "Ebonyi", "Enugu", "Imo"),
    SOUTH_SOUTH("South-South", "Akwa-Ibom", "Bayelsa", "Cross-River", "Delta", "Edo", "Rivers"),
    SOUTH_WEST("South-West", "Ekiti", "Lagos", "Osun", "Ondo", "Ogun", "Oyo");

    private final String displayName;
    private final String[] states;

    GeoPoliticalZone(String displayName, String... stateList) {
        this.displayName = displayName;
        this.states = stateList;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static GeoPoliticalZone findByState(String inputState) {
        if (inputState == null || inputState.trim().isEmpty()) {
            throw new IllegalArgumentException("State cannot be empty");
        }

        String normalized = inputState.trim().toUpperCase();

        for (GeoPoliticalZone zone : values()) {
            if (containsIgnoreCase(zone.states, normalized)) {
                return zone;
            }
        }

        throw new IllegalArgumentException("Unknown state: " + inputState);
    }
    private static boolean containsIgnoreCase(String[] array, String target) {
        for (String state : array) {
            if (state.toUpperCase().equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.println(" Nigeria Geo-Political Zone Finder");
        System.out.print("Enter your State (e.g. Lagos, FCT, Akwa-Ibom): ");
        String stateInput = inputReader.nextLine();

        try {
            GeoPoliticalZone zone = GeoPoliticalZone.findByState(stateInput);
            System.out.println("\n You belong to the " + zone.getDisplayName() +
                    " Geo-political zone.");
        } catch (IllegalArgumentException e) {
            System.out.println("\n " + e.getMessage());
            System.out.println("Tip: Use the exact spelling from the 6 zones (case doesn't matter).");
        }

        inputReader.close();
    }
}
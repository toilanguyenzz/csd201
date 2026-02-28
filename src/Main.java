import controller.ManagementController;

/**
 * Main class for the Software Developer Management System.
 * Entry point that initializes the Controller.
 */
public class Main {
    /**
     * Entry point of the application.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ManagementController controller = new ManagementController();
        controller.run();
    }
}

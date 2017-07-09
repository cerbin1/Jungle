public class Application {
    public static void main(String[] args) {
        final int width = 10;
        final int height = 20;
        char[][] array = new char[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[j][i] = '*';
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(array[j][i]);
            }
            System.out.println();
        }

    }
}

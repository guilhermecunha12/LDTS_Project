package getcooked.gui;

public class AspectRatio {
    private int aspectRatioWidth;
    private int aspectRatioHeight;

    public AspectRatio(int width, int height) {
        findAspectRatio(width, height);
    }

    private void findAspectRatio(int width, int height) {
        int gcdValue = gcd(width, height);
        this.aspectRatioWidth = width / gcdValue;
        this.aspectRatioHeight = height / gcdValue;
    }

    public int getAspectRatioWidth() {
        return aspectRatioWidth;
    }

    public int getAspectRatioHeight() {
        return aspectRatioHeight;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}

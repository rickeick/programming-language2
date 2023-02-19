public class Circulo implements Forma {
    private final double pi = 3.1415;
    private final double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    @Override
    public double perimetro() {
        return 2 * pi * raio;
    }

    @Override
    public double area() {
        return pi * raio * raio;
    }

    public double getRaio() {
        return raio;
    }
}

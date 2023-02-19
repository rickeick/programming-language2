public class Retangulo extends Quadrilatero implements Forma {
    public Retangulo(double base, double altura) {
        super(base, base, altura, altura);
    }

    @Override
    public double perimetro() {
        return 2 * lado1 + 2 * lado3;
    }

    @Override
    public double area() {
        return lado1 * lado3;
    }

    public double getBase() {
        return lado1;
    }

    public double getAltura() {
        return lado3;
    }
}

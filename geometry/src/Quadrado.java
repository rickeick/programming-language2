public class Quadrado extends Quadrilatero implements Forma {
    public Quadrado(double lado) {
        super(lado, lado, lado, lado);
    }

    @Override
    public double perimetro() {
        return 4 * lado1;
    }

    @Override
    public double area() {
        return lado1 * lado1;
    }

    public double getLado() {
        return lado1;
    }
}

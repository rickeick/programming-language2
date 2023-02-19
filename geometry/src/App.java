import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int quant = 0, opcao = 0, i = 0;
        Scanner entrada = new Scanner(System.in);
        ArrayList<Forma> formas = new ArrayList<>();

        while (true) {
            System.out.print("Quantidade de Formas para Criar: ");
            try {
                quant = Integer.parseInt(entrada.next()); break;
            }
            catch (Exception e) {
                System.out.println("Valor Inválido!");
            }
        }

        while (i < quant) {
            System.out.println("[1] Criar Retângulo");
            System.out.println("[2] Criar Quadrado");
            System.out.println("[3] Criar Circulo");
            System.out.print("Escolha uma Opção: ");
            try {
                opcao = Integer.parseInt(entrada.next());
                switch (opcao) {
                    case 1 -> {
                        double base, altura;
                        System.out.print("Base do Retângulo: ");
                        base = Integer.parseInt(entrada.next());
                        System.out.print("Altura do Retângulo: ");
                        altura = Integer.parseInt(entrada.next());
                        formas.add(new Retangulo(base, altura));
                    }
                    case 2 -> {
                        double lado;
                        System.out.print("Lado do Quadrado: ");
                        lado = Integer.parseInt(entrada.next());
                        formas.add(new Quadrado(lado));
                    }
                    case 3 -> {
                        double raio;
                        System.out.print("Raio do Círculo: ");
                        raio = Integer.parseInt(entrada.next());
                        formas.add(new Circulo(raio));
                    }
                }
                System.out.println(); i++;
            }
            catch (Exception e) {
                System.out.println("Valor Inválido!\n");
            }
        }
        entrada.close();

        for (i=0; i<formas.size(); i++) {
            System.out.printf("Objeto Geométrico %d:\n", i);
            if (formas.get(i) instanceof Retangulo) {
                System.out.println("[Retângulo]");
                System.out.print("Base: ");
                System.out.printf("%2.2f\n", ((Retangulo) formas.get(i)).getBase());
                System.out.print("Altura: ");
                System.out.printf("%2.2f\n", ((Retangulo) formas.get(i)).getAltura());
            }
            else if (formas.get(i) instanceof Quadrado) {
                System.out.println("[Quadrado]");
                System.out.print("Lado: ");
                System.out.printf("%2.2f\n", ((Quadrado) formas.get(i)).getLado());
            }
            else if (formas.get(i) instanceof Circulo) {
                System.out.println("[Circulo]");
                System.out.print("Raio: ");
                System.out.printf("%2.2f\n", ((Circulo) formas.get(i)).getRaio());
            }
            System.out.print("Perímetro: ");
            System.out.printf("%2.2f\n", formas.get(i).perimetro());
            System.out.print("Área: ");
            System.out.printf("%2.2f\n", formas.get(i).area());
        }
    }
}

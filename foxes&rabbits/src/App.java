public class App {
    public static void main(String[] args) {
        Simulador simulador = new Simulador(20, 50, 100);
        System.out.println(simulador);

        for (int i=0; i<10; i++) {
            simulador.executar();
            System.out.println(simulador);
        }
    }
}

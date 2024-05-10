public class Main {
    public static void main(String[] args) {
        Contexto contexto = new Contexto(1000); // Saldo inicial

        ExpresionCajero consultarSaldo = new ConsultarSaldo();
        ExpresionCajero depositar = new DepositarDinero(500);
        ExpresionCajero retirar = new RetirarDinero(200);

        System.out.println("Saldo actual: " + consultarSaldo.interpretar(contexto));
        System.out.println("Depositar $500. Nuevo saldo: " + depositar.interpretar(contexto));
        System.out.println("Retirar $200. Nuevo saldo: " + retirar.interpretar(contexto));
    }
}

interface ExpresionCajero {
    double interpretar(Contexto contexto);
}

class Contexto {
    private double saldo;

    public Contexto(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

class ConsultarSaldo implements ExpresionCajero {
    @Override
    public double interpretar(Contexto contexto) {
        return contexto.getSaldo();
    }
}

class DepositarDinero implements ExpresionCajero {
    private double monto;

    public DepositarDinero(double monto) {
        this.monto = monto;
    }

    @Override
    public double interpretar(Contexto contexto) {
        double nuevoSaldo = contexto.getSaldo() + monto;
        contexto.setSaldo(nuevoSaldo);
        return nuevoSaldo;
    }
}

class RetirarDinero implements ExpresionCajero {
    private double monto;

    public RetirarDinero(double monto) {
        this.monto = monto;
    }

    @Override
    public double interpretar(Contexto contexto) {
        if (contexto.getSaldo() >= monto) {
            double nuevoSaldo = contexto.getSaldo() - monto;
            contexto.setSaldo(nuevoSaldo);
            return nuevoSaldo;
        } else {
            return -1; // Saldo insuficiente
        }
    }
}

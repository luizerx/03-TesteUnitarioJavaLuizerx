package contabancaria;

/**
 * Classe Conta Bancária — laboratório de Testes Unitários e TDD.
 *
 * INSTRUÇÕES:
 * 1. Leia os requisitos de cada método (javadoc + regras).
 * 2. Escreva os testes PRIMEIRO no arquivo ContaTest.java.
 * 3. Execute os testes e veja-os FALHAR (Red).
 * 4. Implemente o código mínimo para os testes PASSAREM (Green).
 * 5. Refatore se necessário (Refactor).
 */
public class Conta {

    private String titular;
    private double saldo;
    private boolean ativa;

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public Conta(String titular, double saldoInicial) {
        if (titular == null || titular.isBlank())
            throw new IllegalArgumentException("O titular não pode ser nulo ou vazio.");
        if (saldoInicial < 0)
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo.");

        this.titular = titular;
        this.saldo = saldoInicial;
        this.ativa = true;
    }

    public Conta(String titular) {
        this(titular, 0);
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }
        if (!ativa) {
            throw new IllegalStateException("Conta inativa.");
        }

        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }
        if (!ativa) {
            throw new IllegalStateException("Conta inativa.");
        }
        if (valor > saldo) {
            throw new IllegalStateException("Saldo insuficiente.");
        }

        saldo -= valor;
    }

    public void transferir(Conta destino, double valor) {
        if (!this.ativa || !destino.ativa) {
            throw new IllegalStateException("Conta inativa.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }
        if (valor > this.saldo) {
            throw new IllegalStateException("Saldo insuficiente.");
        }

        this.saldo -= valor;
        destino.saldo += valor;
    }

    public void encerrar() {
        if (!ativa) {
            throw new IllegalStateException("Conta já está inativa.");
        }
        if (saldo != 0) {
            throw new IllegalStateException("Conta com saldo não pode ser encerrada.");
        }

        ativa = false;
    }
}
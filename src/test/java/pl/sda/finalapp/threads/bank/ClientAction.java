package pl.sda.finalapp.threads.bank;

public class ClientAction implements Runnable{

    @Override
    public void run() {
        Integer amount = 10;
        Bank.withdraw(amount);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bank.deposit(amount);
    }
}

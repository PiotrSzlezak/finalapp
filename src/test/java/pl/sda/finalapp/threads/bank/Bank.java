package pl.sda.finalapp.threads.bank;

public class Bank {

    private static Integer balance = 1000;
    private static Integer counter = 0;

    public synchronized static void withdraw (Integer amount){
        balance = balance - amount;
//        balance -= amount;
        System.out.println(Thread.currentThread().getName()+" Current balance after withdraw = "+balance);
    }

    public synchronized static void deposit (Integer amount){
        balance = balance + amount;
//        balance += amount;
        System.out.println(Thread.currentThread().getName()+" Current balance after deposit = "+balance);
        counter++;
    }

    public static Integer getCounter() {
        return counter;
    }

    public static Integer getBalance() {
        return balance;
    }
}
